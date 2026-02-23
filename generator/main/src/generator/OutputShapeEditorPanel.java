package generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class OutputShapeEditorPanel extends JPanel {
    
    // UI Components
    private JComboBox<String> modelBox;
    private JComboBox<String> orderBox;
    private JComboBox<String> shapeBox;
    private JComboBox<String> directionBox;
    private JTextField asymptoteField;
    private JTextField slopeField;
    private JTextField inputMinField;
    private JTextField inputMaxField;
    private JTextField outputMinField;
    private JTextField outputMaxField;
    
    // Chart component — pure Java2D, no external library needed
    private CurvePanel curvePanel;
    
    // Info display
    private JLabel formulaLabel;
    private JLabel gainInputLabel;
    private JLabel gainAsymptoteLabel;
    private JLabel midOutputLabel;
    
    // -------------------------------------------------------------------------
    // Inner class: draws the gain curve using Java2D Graphics2D only
    // -------------------------------------------------------------------------
    private class CurvePanel extends JPanel {
        private double[] xPoints = new double[201];
        private double[] yPoints = new double[201];
        private int pointCount = 0;
        private static final int PAD = 50;

        CurvePanel() {
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 226, 230)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            setPreferredSize(new Dimension(600, 400));
        }

        void setData(double[] x, double[] y, int count) {
            this.xPoints = x;
            this.yPoints = y;
            this.pointCount = count;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int plotW = w - 2 * PAD;
            int plotH = h - 2 * PAD;

            // Plot background
            g2.setColor(new Color(250, 250, 250));
            g2.fillRect(PAD, PAD, plotW, plotH);

            // Grid lines
            g2.setColor(Color.LIGHT_GRAY);
            g2.setStroke(new BasicStroke(1f));
            int gridLines = 5;
            for (int i = 0; i <= gridLines; i++) {
                int x = PAD + i * plotW / gridLines;
                int y = PAD + i * plotH / gridLines;
                g2.drawLine(x, PAD, x, PAD + plotH);
                g2.drawLine(PAD, y, PAD + plotW, y);
            }

            // Axes border
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRect(PAD, PAD, plotW, plotH);

            // Axis tick labels
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            g2.setColor(Color.GRAY);
            for (int i = 0; i <= gridLines; i++) {
                String lbl = String.format("%.1f", i / (double) gridLines);
                int x = PAD + i * plotW / gridLines;
                g2.drawString(lbl, x - 8, PAD + plotH + 16);
            }
            for (int i = 0; i <= gridLines; i++) {
                String lbl = String.format("%.1f", 1.0 - i / (double) gridLines);
                int y = PAD + i * plotH / gridLines;
                g2.drawString(lbl, PAD - 35, y + 4);
            }

            // X axis title
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2.setColor(Color.DARK_GRAY);
            String xTitle = "Normalized Input (0 \u2013 1)";
            g2.drawString(xTitle, PAD + plotW / 2 - g2.getFontMetrics().stringWidth(xTitle) / 2, h - 5);

            // Y axis title (rotated)
            Graphics2D g2r = (Graphics2D) g2.create();
            g2r.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2r.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2r.setColor(Color.DARK_GRAY);
            String yTitle = "Normalized Output (0 \u2013 1)";
            g2r.rotate(-Math.PI / 2, 14, PAD + plotH / 2);
            g2r.drawString(yTitle, 14 - g2r.getFontMetrics().stringWidth(yTitle) / 2, PAD + plotH / 2);
            g2r.dispose();

            // Chart title
            g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
            g2.setColor(new Color(51, 51, 51));
            String title = "Gain Function Visualisation";
            g2.drawString(title, PAD + plotW / 2 - g2.getFontMetrics().stringWidth(title) / 2, PAD - 12);

            // Draw the curve
            if (pointCount >= 2) {
                g2.setColor(new Color(51, 153, 255));
                g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                GeneralPath path = new GeneralPath();
                boolean first = true;
                for (int i = 0; i < pointCount; i++) {
                    double cx = PAD + xPoints[i] * plotW;
                    double cy = PAD + (1.0 - Math.max(0, Math.min(1, yPoints[i]))) * plotH;
                    if (first) { path.moveTo(cx, cy); first = false; }
                    else         path.lineTo(cx, cy);
                }
                g2.draw(path);
            }

            g2.dispose();
        }
    }
    // -------------------------------------------------------------------------

    public OutputShapeEditorPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        createControlPanel();
        createChartPanel();
        createInfoPanel();
        updateGraph();
    }

    /**
     * Parameterized constructor — opens the editor pre-populated with the
     * values already entered on the Lab Configuration page.
     *
     * @param modelIndex     0=Polynomial, 1=Exponential, 2=Sigmoid
     * @param order          1 or 2
     * @param shapeIndex     0=Flatten, 1=Swing, 2=Asymptote
     * @param directionIndex 0=Down, 1=Up
     * @param asymptote      asymptote text (may be empty)
     * @param slope          slope text (may be empty)
     * @param outMin         output minimum text (may be empty)
     * @param outMax         output maximum text (may be empty)
     */
    public OutputShapeEditorPanel(int modelIndex, int order, int shapeIndex, int directionIndex,
                                   String asymptote, String slope, String outMin, String outMax) {
        this();
        if (modelIndex >= 0 && modelIndex < modelBox.getItemCount())
            modelBox.setSelectedIndex(modelIndex);
        int orderIdx = order - 1;
        if (orderIdx >= 0 && orderIdx < orderBox.getItemCount())
            orderBox.setSelectedIndex(orderIdx);
        if (shapeIndex >= 0 && shapeIndex < shapeBox.getItemCount())
            shapeBox.setSelectedIndex(shapeIndex);
        if (directionIndex >= 0 && directionIndex < directionBox.getItemCount())
            directionBox.setSelectedIndex(directionIndex);
        if (asymptote != null && !asymptote.isEmpty())
            asymptoteField.setText(asymptote);
        if (slope != null && !slope.isEmpty())
            slopeField.setText(slope);
        if (outMin != null && !outMin.isEmpty())
            outputMinField.setText(outMin);
        if (outMax != null && !outMax.isEmpty())
            outputMaxField.setText(outMax);
        updateGraph();
    }
    
    private void createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(new Color(248, 249, 250));
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        controlPanel.setPreferredSize(new Dimension(350, 0));
        
        JLabel titleLabel = new JLabel("Output Shape Editor");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        controlPanel.add(titleLabel);
        
        controlPanel.add(createControlGroup("Gain Model", 
            modelBox = new JComboBox<>(new String[]{"0 - Polynomial", "1 - Exponential", "2 - Sigmoid"})));
        
        controlPanel.add(createControlGroup("Order", 
            orderBox = new JComboBox<>(new String[]{"1", "2"})));
        orderBox.setSelectedIndex(1);
        
        controlPanel.add(createControlGroup("Gain Shape", 
            shapeBox = new JComboBox<>(new String[]{"0 - Flatten", "1 - Swing", "2 - Asymptote"})));
        
        controlPanel.add(createControlGroup("Direction", 
            directionBox = new JComboBox<>(new String[]{"0 - Down", "1 - Up"})));
        
        controlPanel.add(createControlGroup("Asymptote (%)", 
            asymptoteField = new JTextField("50")));
        
        controlPanel.add(createControlGroup("Slope", 
            slopeField = new JTextField("2.2")));
        
        controlPanel.add(createControlGroup("Input Min", 
            inputMinField = new JTextField("0")));
        controlPanel.add(createControlGroup("Input Max", 
            inputMaxField = new JTextField("100")));
        
        controlPanel.add(createControlGroup("Output Min", 
            outputMinField = new JTextField("0")));
        controlPanel.add(createControlGroup("Output Max", 
            outputMaxField = new JTextField("100")));
        
        JPanel presetPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        presetPanel.setBackground(new Color(248, 249, 250));
        presetPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        addPresetButton(presetPanel, "Poly Flat Down",  0, 2, 0, 0, 50,   1);
        addPresetButton(presetPanel, "Poly Flat Up",    0, 2, 0, 1, 50,   1);
        addPresetButton(presetPanel, "Poly Swing Down", 0, 2, 1, 0, 50,   1);
        addPresetButton(presetPanel, "Poly Swing Up",   0, 2, 1, 1, 50,   1);
        addPresetButton(presetPanel, "Exp Flat Down",   1, 1, 0, 0,  0, 2.2);
        addPresetButton(presetPanel, "Exp Flat Up",     1, 1, 0, 1,  0, 2.2);
        addPresetButton(presetPanel, "Sigmoid Down",    2, 1, 2, 0, 60,  25);
        addPresetButton(presetPanel, "Sigmoid Up",      2, 1, 2, 1, 25,  10);
        
        controlPanel.add(presetPanel);
        
        ActionListener updateListener = e -> updateGraph();
        modelBox.addActionListener(updateListener);
        orderBox.addActionListener(updateListener);
        shapeBox.addActionListener(updateListener);
        directionBox.addActionListener(updateListener);
        
        javax.swing.event.DocumentListener docListener = new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateGraph(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { updateGraph(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { updateGraph(); }
        };
        asymptoteField.getDocument().addDocumentListener(docListener);
        slopeField.getDocument().addDocumentListener(docListener);
        inputMinField.getDocument().addDocumentListener(docListener);
        inputMaxField.getDocument().addDocumentListener(docListener);
        outputMinField.getDocument().addDocumentListener(docListener);
        outputMaxField.getDocument().addDocumentListener(docListener);
        
        JScrollPane scrollPane = new JScrollPane(controlPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        
        add(scrollPane, BorderLayout.WEST);
    }
    
    private JPanel createControlGroup(String label, JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 0, 5, 0),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 224, 224)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            )
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelComp.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(labelComp);
        panel.add(Box.createVerticalStrut(5));
        
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
        if (component instanceof JTextField) {
            component.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            component.setPreferredSize(new Dimension(200, 30));
        }
        panel.add(component);
        
        return panel;
    }
    
    private void addPresetButton(JPanel panel, String name, int model, int order, int shape, int dir, double asym, double slope) {
        JButton btn = new JButton(name);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(51, 153, 255));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 153, 255), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            modelBox.setSelectedIndex(model);
            orderBox.setSelectedIndex(order - 1);
            shapeBox.setSelectedIndex(shape);
            directionBox.setSelectedIndex(dir);
            asymptoteField.setText(String.valueOf(asym));
            slopeField.setText(String.valueOf(slope));
            updateGraph();
        });
        panel.add(btn);
    }
    
    /** Creates the Java2D curve panel — no JFreeChart dependency */
    private void createChartPanel() {
        curvePanel = new CurvePanel();
        add(curvePanel, BorderLayout.CENTER);
    }
    
    private void createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(248, 249, 250));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel infoTitle = new JLabel("Current Configuration");
        infoTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        infoPanel.add(infoTitle);
        infoPanel.add(Box.createVerticalStrut(10));
        
        formulaLabel = new JLabel("Formula: ");
        formulaLabel.setFont(new Font("Courier New", Font.PLAIN, 12));
        formulaLabel.setForeground(Color.DARK_GRAY);
        infoPanel.add(formulaLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        
        JPanel valuesPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        valuesPanel.setBackground(new Color(248, 249, 250));
        valuesPanel.add(createInfoBox("Gain Input (norm)",       gainInputLabel     = new JLabel("0.50")));
        valuesPanel.add(createInfoBox("Gain Asymptote (norm)",   gainAsymptoteLabel = new JLabel("0.50")));
        valuesPanel.add(createInfoBox("Output at 50% Input",     midOutputLabel     = new JLabel("50.0")));
        infoPanel.add(valuesPanel);
        
        add(infoPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createInfoBox(String label, JLabel valueLabel) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 153, 255), 0, true),
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(51, 153, 255)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            )
        ));
        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        labelComp.setForeground(Color.GRAY);
        box.add(labelComp);
        box.add(Box.createVerticalStrut(5));
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        valueLabel.setForeground(Color.DARK_GRAY);
        box.add(valueLabel);
        return box;
    }
    
    private void updateGraph() {
        try {
            int model     = modelBox.getSelectedIndex();
            int order     = Integer.parseInt(orderBox.getSelectedItem().toString());
            int shape     = shapeBox.getSelectedIndex();
            int direction = directionBox.getSelectedIndex();
            
            double asymptote = asymptoteField.getText().isEmpty() ? 50
                             : Double.parseDouble(asymptoteField.getText());
            double slope     = slopeField.getText().isEmpty() ? 1
                             : Double.parseDouble(slopeField.getText());
            double inMin  = Double.parseDouble(inputMinField.getText());
            double inMax  = Double.parseDouble(inputMaxField.getText());
            double outMin = Double.parseDouble(outputMinField.getText());
            double outMax = Double.parseDouble(outputMaxField.getText());
            
            // Build 201-point curve
            double[] xPts = new double[201];
            double[] yPts = new double[201];
            for (int i = 0; i <= 200; i++) {
                double normIn    = i / 200.0;
                double actualIn  = inMin + (inMax - inMin) * normIn;
                double gainOut   = calculateGainFunction(actualIn, inMax, inMin,
                                       asymptote, order, slope, model, direction, shape);
                xPts[i] = normIn;
                yPts[i] = gainOut;
            }
            curvePanel.setData(xPts, yPts, 201);
            
            // Info labels
            double gainAsym = calculateGainAsymptote(asymptote, inMax, inMin);
            gainInputLabel.setText("0.50");
            gainAsymptoteLabel.setText(String.format("%.2f", gainAsym));
            double midOut = calculateGainFunction((inMin + inMax) / 2, inMax, inMin,
                                asymptote, order, slope, model, direction, shape);
            midOutputLabel.setText(String.format("%.2f", outMin + (outMax - outMin) * midOut));
            
            updateFormula(model, order, shape, direction);
            
        } catch (NumberFormatException e) {
            // Ignore invalid input during typing
        }
    }
    
    private void updateFormula(int model, int order, int shape, int direction) {
        String formula;
        switch (model) {
            case 0:  formula = "g = g2*x\u00B2 + g1*x + g0"; break;
            case 1:  formula = "g = direction - (2*direction - 1) * (expNumerator/expDenominator)"; break;
            default: formula = "g = 1 - (direction - (2*direction - 1)/(1 + exp(-slope*(x-asymptote))))"; break;
        }
        formulaLabel.setText("Formula: " + formula);
    }
    
    private double calculateGainAsymptote(double asymptote, double max, double min) {
        double range = max - min;
        if (asymptote > max) return 1;
        else if (asymptote < min) return 0;
        else return (asymptote - min) / range;
    }
    
    /**
     * Main gain function calculation — translated from Generator.java gainFunction method
     */
    private double calculateGainFunction(double inVal, double max, double min, 
                                        double asymptoteParam, int order, double slope,
                                        int model, int direction, int shape) {
        if (inVal > max) inVal = max;
        else if (inVal < min) inVal = min;
        
        double range = max - min;
        double gainInput = (inVal - min) / range;
        
        double gainAsymptote;
        if (asymptoteParam > max)       gainAsymptote = 1;
        else if (asymptoteParam < min)  gainAsymptote = 0;
        else                            gainAsymptote = (asymptoteParam - min) / range;
        
        // POLYNOMIAL
        if (model == 0) {
            double g1, g2, g0;
            if (order == 2) {
                if (shape == 0) {
                    if (direction == 0) { g2 = 1;    g1 = -2;   g0 = 1; }
                    else                { g2 = -1;   g1 =  2;   g0 = 0; }
                } else if (shape == 1) {
                    if (direction == 0) { g2 = -0.5; g1 = -0.5; g0 = 1; }
                    else                { g2 =  0.5; g1 =  0.5; g0 = 0; }
                } else {
                    g2 = 2 * (0.5 - direction) / Math.pow(0.5 + Math.sqrt(Math.pow(0.5 - gainAsymptote, 2)), 2);
                    g1 = -2 * g2 * gainAsymptote;
                    g0 = g2 * Math.pow(gainAsymptote, 2) + direction;
                }
            } else {
                g2 = 0;
                if (direction == 0) { g1 = -1; g0 = 1; }
                else                { g1 =  1; g0 = 0; }
            }
            return g2 * Math.pow(gainInput, 2) + g1 * gainInput + g0;
        }
        // EXPONENTIAL
        else if (model == 1) {
            double slopeSign, gainDirection;
            if (order == 1) {
                gainAsymptote = 0;
                slopeSign = (shape == 0) ? -1 : 1;
                gainDirection = (direction == 0) ? 1 : 0;
            } else {
                slopeSign = -1;
                if (shape == 0) {
                    gainAsymptote = 0;
                    gainDirection = (direction == 0) ? 1 : 0;
                } else if (shape == 1) {
                    gainAsymptote = 1;
                    gainDirection = (direction == 0) ? 0 : 1;
                } else {
                    gainDirection = direction;
                }
            }
            double expNumerator   = Math.exp(slope * slopeSign * Math.pow((gainInput - gainAsymptote), order)) - 1;
            double expDenominator = Math.exp(slope * slopeSign) - 1;
            return gainDirection - (2 * gainDirection - 1) * (expNumerator / expDenominator);
        }
        // SIGMOID
        else {
            double sigDenominator = 1 + Math.exp(-1 * slope * (gainInput - gainAsymptote));
            return 1 - (direction - (2 * direction - 1) / sigDenominator);
        }
    }
}

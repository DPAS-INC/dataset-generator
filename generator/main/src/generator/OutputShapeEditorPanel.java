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
        private static final int PAD = 55;

        CurvePanel() {
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(new Color(222, 226, 230)));
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
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int plotW = w - 2 * PAD;
            int plotH = h - 2 * PAD;

            if (plotW <= 0 || plotH <= 0) { g2.dispose(); return; }

            // Plot background
            g2.setColor(new Color(250, 250, 250));
            g2.fillRect(PAD, PAD, plotW, plotH);

            // Grid lines
            g2.setColor(new Color(220, 220, 220));
            g2.setStroke(new BasicStroke(1f));
            int gridLines = 5;
            for (int i = 0; i <= gridLines; i++) {
                int x = PAD + i * plotW / gridLines;
                int y = PAD + i * plotH / gridLines;
                g2.drawLine(x, PAD, x, PAD + plotH);
                g2.drawLine(PAD, y, PAD + plotW, y);
            }

            // Axes border
            g2.setColor(new Color(100, 100, 100));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRect(PAD, PAD, plotW, plotH);

            // Axis tick labels
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            g2.setColor(new Color(100, 100, 100));
            FontMetrics fm = g2.getFontMetrics();
            for (int i = 0; i <= gridLines; i++) {
                String lbl = String.format("%.1f", i / (double) gridLines);
                int x = PAD + i * plotW / gridLines;
                g2.drawString(lbl, x - fm.stringWidth(lbl) / 2, PAD + plotH + 16);
            }
            for (int i = 0; i <= gridLines; i++) {
                String lbl = String.format("%.1f", 1.0 - i / (double) gridLines);
                int y = PAD + i * plotH / gridLines;
                g2.drawString(lbl, PAD - fm.stringWidth(lbl) - 6, y + 4);
            }

            // X axis title
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2.setColor(new Color(80, 80, 80));
            String xTitle = "Normalized Input (0 \u2013 1)";
            g2.drawString(xTitle, PAD + plotW / 2 - g2.getFontMetrics().stringWidth(xTitle) / 2, h - 6);

            // Y axis title (rotated)
            Graphics2D g2r = (Graphics2D) g2.create();
            g2r.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2r.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2r.setColor(new Color(80, 80, 80));
            String yTitle = "Normalized Output (0 \u2013 1)";
            int yTitleX = 14;
            int yTitleY = PAD + plotH / 2;
            g2r.rotate(-Math.PI / 2, yTitleX, yTitleY);
            g2r.drawString(yTitle, yTitleX - g2r.getFontMetrics().stringWidth(yTitle) / 2, yTitleY);
            g2r.dispose();

            // Chart title
            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            g2.setColor(new Color(51, 51, 51));
            String title = "Gain Function Visualisation";
            g2.drawString(title, PAD + plotW / 2 - g2.getFontMetrics().stringWidth(title) / 2, PAD - 14);

            // Draw the curve
            if (pointCount >= 2) {
                g2.setColor(new Color(51, 153, 255));
                g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
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
        setLayout(new BorderLayout(8, 8));
        setBackground(new Color(245, 246, 248));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        createControlPanel();
        createChartPanel();
        createInfoPanel();
        updateGraph();
    }

    /**
     * Parameterized constructor — pre-populated with values from Lab Configuration.
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

    // -------------------------------------------------------------------------
    // Control panel — compact 2-column grid at NORTH, presets below it
    // -------------------------------------------------------------------------
    private void createControlPanel() {
        // ── Top strip: all controls in a 4-column GridBagLayout (label|field|label|field) ──
        JPanel topStrip = new JPanel(new GridBagLayout());
        topStrip.setBackground(new Color(248, 249, 250));
        topStrip.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        GridBagConstraints lc = new GridBagConstraints();
        lc.anchor  = GridBagConstraints.LINE_END;
        lc.insets  = new Insets(3, 6, 3, 4);

        GridBagConstraints fc = new GridBagConstraints();
        fc.anchor  = GridBagConstraints.LINE_START;
        fc.fill    = GridBagConstraints.HORIZONTAL;
        fc.weightx = 1.0;
        fc.insets  = new Insets(3, 0, 3, 12);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);

        // Title spanning all 4 columns
        JLabel titleLbl = new JLabel("Output Shape Editor");
        titleLbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLbl.setForeground(new Color(40, 40, 40));
        GridBagConstraints tc = new GridBagConstraints();
        tc.gridx = 0; tc.gridy = 0; tc.gridwidth = 4;
        tc.anchor = GridBagConstraints.CENTER;
        tc.insets = new Insets(0, 0, 8, 0);
        topStrip.add(titleLbl, tc);

        // Row 1: Gain Model | Direction
        addRow(topStrip, labelFont, lc, fc,
            "Gain Model",  modelBox     = new JComboBox<>(new String[]{"0 - Polynomial", "1 - Exponential", "2 - Sigmoid"}),
            "Direction",   directionBox = new JComboBox<>(new String[]{"0 - Down", "1 - Up"}),
            1);

        // Row 2: Gain Shape | Order
        addRow(topStrip, labelFont, lc, fc,
            "Gain Shape",  shapeBox = new JComboBox<>(new String[]{"0 - Flatten", "1 - Swing", "2 - Asymptote"}),
            "Order",       orderBox = new JComboBox<>(new String[]{"1", "2"}),
            2);
        orderBox.setSelectedIndex(1);

        // Row 3: Asymptote | Slope
        addRow(topStrip, labelFont, lc, fc,
            "Asymptote (%)", asymptoteField = new JTextField("50"),
            "Slope",         slopeField     = new JTextField("2.2"),
            3);

        // Row 4: Input Min | Input Max
        addRow(topStrip, labelFont, lc, fc,
            "Input Min",  inputMinField = new JTextField("0"),
            "Input Max",  inputMaxField = new JTextField("100"),
            4);

        // Row 5: Output Min | Output Max
        addRow(topStrip, labelFont, lc, fc,
            "Output Min", outputMinField = new JTextField("0"),
            "Output Max", outputMaxField = new JTextField("100"),
            5);

        // ── Presets: 8 buttons in one horizontal row ──
        JPanel presetStrip = new JPanel(new GridLayout(1, 8, 6, 0));
        presetStrip.setBackground(new Color(248, 249, 250));
        presetStrip.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230)),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));

        addPresetButton(presetStrip, "Poly Flat \u25BC",  0, 2, 0, 0, 50,   1);
        addPresetButton(presetStrip, "Poly Flat \u25B2",  0, 2, 0, 1, 50,   1);
        addPresetButton(presetStrip, "Poly Swing \u25BC", 0, 2, 1, 0, 50,   1);
        addPresetButton(presetStrip, "Poly Swing \u25B2", 0, 2, 1, 1, 50,   1);
        addPresetButton(presetStrip, "Exp Flat \u25BC",   1, 1, 0, 0,  0, 2.2);
        addPresetButton(presetStrip, "Exp Flat \u25B2",   1, 1, 0, 1,  0, 2.2);
        addPresetButton(presetStrip, "Sigmoid \u25BC",    2, 1, 2, 0, 60,  25);
        addPresetButton(presetStrip, "Sigmoid \u25B2",    2, 1, 2, 1, 25,  10);

        // ── North panel = controls + presets ──
        JPanel northPanel = new JPanel(new BorderLayout(0, 6));
        northPanel.setBackground(new Color(245, 246, 248));
        northPanel.add(topStrip,    BorderLayout.CENTER);

        add(northPanel, BorderLayout.NORTH);

        // ── Listeners ──
        ActionListener al = e -> updateGraph();
        modelBox.addActionListener(al);
        orderBox.addActionListener(al);
        shapeBox.addActionListener(al);
        directionBox.addActionListener(al);

        javax.swing.event.DocumentListener dl = new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateGraph(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { updateGraph(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { updateGraph(); }
        };
        asymptoteField.getDocument().addDocumentListener(dl);
        slopeField.getDocument().addDocumentListener(dl);
        inputMinField.getDocument().addDocumentListener(dl);
        inputMaxField.getDocument().addDocumentListener(dl);
        outputMinField.getDocument().addDocumentListener(dl);
        outputMaxField.getDocument().addDocumentListener(dl);
    }

    /** Adds two label+component pairs on the same GridBagLayout row. */
    private void addRow(JPanel p, Font lf,
                        GridBagConstraints lc, GridBagConstraints fc,
                        String label1, JComponent comp1,
                        String label2, JComponent comp2,
                        int row) {
        JLabel l1 = new JLabel(label1 + ":");
        l1.setFont(lf);
        lc.gridx = 0; lc.gridy = row; lc.gridwidth = 1;
        p.add(l1, lc);

        comp1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        if (comp1 instanceof JTextField) ((JTextField) comp1).setColumns(7);
        fc.gridx = 1; fc.gridy = row; fc.gridwidth = 1;
        p.add(comp1, fc);

        JLabel l2 = new JLabel(label2 + ":");
        l2.setFont(lf);
        lc.gridx = 2; lc.gridy = row;
        p.add(l2, lc);

        comp2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        if (comp2 instanceof JTextField) ((JTextField) comp2).setColumns(7);
        fc.gridx = 3; fc.gridy = row;
        p.add(comp2, fc);
    }

    private void addPresetButton(JPanel panel, String name,
                                  int model, int order, int shape, int dir,
                                  double asym, double slope) {
        JButton btn = new JButton(name);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(51, 153, 255));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 153, 255), 1),
            BorderFactory.createEmptyBorder(4, 4, 4, 4)
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
        JPanel infoPanel = new JPanel(new BorderLayout(10, 0));
        infoPanel.setBackground(new Color(248, 249, 250));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(222, 226, 230)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        formulaLabel = new JLabel("Formula: ");
        formulaLabel.setFont(new Font("Courier New", Font.PLAIN, 12));
        formulaLabel.setForeground(new Color(60, 60, 60));
        infoPanel.add(formulaLabel, BorderLayout.CENTER);

        JPanel valuesPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        valuesPanel.setBackground(new Color(248, 249, 250));
        valuesPanel.add(createInfoBox("Gain Input (norm)",     gainInputLabel     = new JLabel("0.50")));
        valuesPanel.add(createInfoBox("Gain Asymptote (norm)", gainAsymptoteLabel = new JLabel("0.50")));
        valuesPanel.add(createInfoBox("Output at 50% Input",   midOutputLabel     = new JLabel("50.0")));
        infoPanel.add(valuesPanel, BorderLayout.EAST);

        add(infoPanel, BorderLayout.SOUTH);
    }

    private JPanel createInfoBox(String label, JLabel valueLabel) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(51, 153, 255)),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lbl.setForeground(Color.GRAY);
        box.add(lbl);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
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

            double[] xPts = new double[201];
            double[] yPts = new double[201];
            for (int i = 0; i <= 200; i++) {
                double normIn   = i / 200.0;
                double actualIn = inMin + (inMax - inMin) * normIn;
                double gainOut  = calculateGainFunction(actualIn, inMax, inMin,
                                      asymptote, order, slope, model, direction, shape);
                xPts[i] = normIn;
                yPts[i] = gainOut;
            }
            curvePanel.setData(xPts, yPts, 201);

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
            case 0:  formula = "g = g2\u00B7x\u00B2 + g1\u00B7x + g0"; break;
            case 1:  formula = "g = dir - (2\u00B7dir - 1) \u00D7 (expNum / expDen)"; break;
            default: formula = "g = 1 - (dir - (2\u00B7dir - 1) / (1 + exp(-slope\u00B7(x - asym))))"; break;
        }
        formulaLabel.setText("Formula:  " + formula);
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

        double range     = max - min;
        double gainInput = (inVal - min) / range;

        double gainAsymptote;
        if (asymptoteParam > max)      gainAsymptote = 1;
        else if (asymptoteParam < min) gainAsymptote = 0;
        else                           gainAsymptote = (asymptoteParam - min) / range;

        // POLYNOMIAL
        if (model == 0) {
            double g1, g2, g0;
            if (order == 2) {
                if (shape == 0) {
                    if (direction == 0) { g2 =  1;   g1 = -2;   g0 = 1; }
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
                slopeSign     = (shape == 0) ? -1 : 1;
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
            double expNum = Math.exp(slope * slopeSign * Math.pow((gainInput - gainAsymptote), order)) - 1;
            double expDen = Math.exp(slope * slopeSign) - 1;
            return gainDirection - (2 * gainDirection - 1) * (expNum / expDen);
        }
        // SIGMOID
        else {
            double sigDen = 1 + Math.exp(-1 * slope * (gainInput - gainAsymptote));
            return 1 - (direction - (2 * direction - 1) / sigDen);
        }
    }

    // -------------------------------------------------------------------------
    // Getters — allow the caller to read back the (possibly modified) values
    // -------------------------------------------------------------------------

    /** Returns the selected Gain Model index (0=Polynomial, 1=Exponential, 2=Sigmoid). */
    public int getModelIndex() {
        return modelBox.getSelectedIndex();
    }

    /** Returns the selected Order value (1 or 2). */
    public int getOrder() {
        return Integer.parseInt(orderBox.getSelectedItem().toString());
    }

    /** Returns the selected Gain Shape index (0=Flatten, 1=Swing, 2=Asymptote). */
    public int getShapeIndex() {
        return shapeBox.getSelectedIndex();
    }

    /** Returns the selected Direction index (0=Down, 1=Up). */
    public int getDirectionIndex() {
        return directionBox.getSelectedIndex();
    }

    /** Returns the current Asymptote text (may be empty). */
    public String getAsymptoteText() {
        return asymptoteField.getText().trim();
    }

    /** Returns the current Slope text (may be empty). */
    public String getSlopeText() {
        return slopeField.getText().trim();
    }
}
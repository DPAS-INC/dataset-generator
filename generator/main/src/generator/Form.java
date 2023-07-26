package generator;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.awt.Color;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


// Form class that creates the User Interface (UI) for the application
public class Form extends javax.swing.JFrame {

   // Following code fields had been auto-generated
   private javax.swing.JTextField dateField;
   private javax.swing.JLabel dateLabel;
   private javax.swing.JTextField deadtimeReelField;
   private javax.swing.JLabel deadtimeReelLabel;
   private javax.swing.JComboBox<String> descBox;
   private javax.swing.JButton descButton;
   private javax.swing.JTextField descNameField;
   private javax.swing.JLabel descNameLabel;
   private javax.swing.JComboBox<String> directionBox;
   private javax.swing.JButton directionInfoButton;
   private javax.swing.JButton downloadButton;
   private javax.swing.JTextField downloadField;
   private javax.swing.JButton downloadFolderButton;
   private javax.swing.JLabel downloadLabel;
   private javax.swing.JTextField drawField;
   private javax.swing.JLabel drawLabel;
   private javax.swing.JLabel formatLabel;
   private javax.swing.JLabel gainDirectionLabel;
   private javax.swing.JLabel gainModelLabel;
   private javax.swing.JLabel gainShapeLabel;
   private javax.swing.JButton genButton;
   private javax.swing.JButton genDataButton;
   private javax.swing.JLabel genLabel;
   private javax.swing.JPanel generate;
   private javax.swing.JButton homeButton;
   private javax.swing.JPanel homePage;
   private javax.swing.JLabel infoLabel;
   private javax.swing.JButton inputAddButton;
   private javax.swing.JButton inputButton;
   private javax.swing.JButton inputClearButton;
   private javax.swing.JPanel inputConfig;
   private javax.swing.JLabel inputConfigLabel;
   private javax.swing.JButton inputDeleteButton;
   private javax.swing.JButton processDescAddButton;
   private javax.swing.JButton processDescClearButton;
   private javax.swing.JPanel processDescConfig;
   private javax.swing.JButton processDescDeleteButton;
   private javax.swing.JLabel processDescLabel;
   private javax.swing.JLabel processDescMessageLabel;
   private javax.swing.JScrollPane processDescScroll;
   private javax.swing.JButton processDescSubmitButton;
   private javax.swing.JTable processDescTable;
   private javax.swing.JLabel processDescriptionLabel;
   private javax.swing.JTextField inputMaxField;
   private javax.swing.JLabel inputMaxLabel;
   private javax.swing.JLabel inputMessageLabel;
   private javax.swing.JTextField inputMinField;
   private javax.swing.JLabel inputMinLabel;
   private javax.swing.JTextField inputMvLagField;
   private javax.swing.JLabel inputMvLagLabel;
   private javax.swing.JTextField inputNameField;
   private javax.swing.JLabel inputNameLabel;
   private javax.swing.JTextField inputNoiseField;
   private javax.swing.JLabel inputNoiseLabel;
   private javax.swing.JScrollPane inputScroll;
   private javax.swing.JButton inputSubmitButton;
   private javax.swing.JTable inputTable;
   private javax.swing.JLabel inputTitleLabel;
   private javax.swing.JLabel generateTitleLabel;
   private javax.swing.JButton labAddButton;
   private javax.swing.JTextField labAsymptoteField;
   private javax.swing.JLabel labAsymptoteLabel;
   private javax.swing.JButton labButton;
   private javax.swing.JButton labClearButton;
   private javax.swing.JPanel labConfig;
   private javax.swing.JButton labDeleteButton;
   private javax.swing.JTextField labField;
   private javax.swing.JLabel labLabel;
   private javax.swing.JLabel labMessageLabel;
   private javax.swing.JLabel labOrderLabel;
   private javax.swing.JScrollPane labScroll;
   private javax.swing.JTextField labSlopeField;
   private javax.swing.JLabel labSlopeLabel;
   private javax.swing.JButton labSubmitButton;
   private javax.swing.JTable labTable;
   private javax.swing.JLabel labTitleLabel;
   private javax.swing.JTextField labWeightField;
   private javax.swing.JLabel labWeightLabel;
   private javax.swing.JLabel labWeightLabel2;
   private javax.swing.JLabel labWeightLabel3;
   private javax.swing.JTextField lag1Field;
   private javax.swing.JLabel lag1Label;
   private javax.swing.JTextField lag2Field;
   private javax.swing.JLabel lag2Label;
   private javax.swing.JScrollPane listScrollPane;
   private javax.swing.JTextArea listTextArea;
   private javax.swing.JProgressBar loadBar;
   private javax.swing.JButton loadButton;
   private javax.swing.JTextField loadField;
   private javax.swing.JButton loadFolderButton;
   private javax.swing.JLabel loadLabel;
   private javax.swing.JLabel loadMessageLabel;
   private javax.swing.JLabel loadStatusLabel;
   private javax.swing.JPanel main;
   private javax.swing.JComboBox<String> modelBox;
   private javax.swing.JButton modelInfoButton;
   private javax.swing.JComboBox<String> orderBox;
   private javax.swing.JButton outputAddButton;
   private javax.swing.JComboBox<String> outputBox;
   private javax.swing.JButton outputButton;
   private javax.swing.JButton outputClearButton;
   private javax.swing.JPanel outputConfig;
   private javax.swing.JLabel outputConfigLabel;
   private javax.swing.JButton outputDeleteButton;
   private javax.swing.JTextField outputDescField;
   private javax.swing.JLabel outputDescLabel;
   private javax.swing.JTextField outputMaxField;
   private javax.swing.JLabel outputMaxLabel;
   private javax.swing.JLabel outputMessageLabel;
   private javax.swing.JTextField outputMinField;
   private javax.swing.JLabel outputMinLabel;
   private javax.swing.JTextField outputNameField;
   private javax.swing.JLabel outputNameLabel;
   private javax.swing.JTextField outputNoiseField;
   private javax.swing.JLabel outputNoiseLabel;
   private javax.swing.JScrollPane outputScroll;
   private javax.swing.JButton outputSubmitButton;
   private javax.swing.JTable outputTable;
   private javax.swing.JLabel outputTitleLabel;
   private javax.swing.JButton processButton;
   private javax.swing.JPanel processConfig;
   private javax.swing.JLabel processConfigLabel;
   private javax.swing.JTextField processField;
   private javax.swing.JLabel processLabel;
   private javax.swing.JLabel processMessageLabel;
   private javax.swing.JLabel processTitleLabel;
   private javax.swing.JLabel progressLabel;
   private javax.swing.JTextField pulpField;
   private javax.swing.JLabel pulpLabel;
   private javax.swing.JTextField qcsField;
   private javax.swing.JLabel qcsLabel;
   private javax.swing.JTextField settleField;
   private javax.swing.JLabel settleLabel;
   private javax.swing.JComboBox<String> shapeBox;
   private javax.swing.JButton shapeInfoButton;
   private javax.swing.JPanel sideBar;
   private javax.swing.JTextField sineAmpField;
   private javax.swing.JLabel sineAmpLabel;
   private javax.swing.JTextField sinePeriodField;
   private javax.swing.JLabel sinePeriodLabel;
   private javax.swing.JButton stateAddButton;
   private javax.swing.JButton stateButton;
   private javax.swing.JButton stateClearButton;
   private javax.swing.JPanel stateConfig;
   private javax.swing.JLabel stateConfigLabel;
   private javax.swing.JButton stateDeleteButton;
   private javax.swing.JComboBox<String> stateDescBox;
   private javax.swing.JLabel stateDescriptionLabel;
   private javax.swing.JTextField stateMaxField;
   private javax.swing.JLabel stateMaxLabel;
   private javax.swing.JLabel stateMessageLabel;
   private javax.swing.JTextField stateMinField;
   private javax.swing.JLabel stateMinLabel;
   private javax.swing.JTextField stateNameField;
   private javax.swing.JLabel stateNameLabel;
   private javax.swing.JTextField stateNoiseField;
   private javax.swing.JLabel stateNoiseLabel;
   private javax.swing.JScrollPane stateScroll;
   private javax.swing.JButton stateSubmitButton;
   private javax.swing.JTable stateTable;
   private javax.swing.JLabel stateTitleLabel;
   private javax.swing.JButton submitProcessButton;
   private javax.swing.JLabel timeLabel;
   private javax.swing.JTextField trimField;
   private javax.swing.JLabel trimLabel;
   private javax.swing.JTextField ucField;
   private javax.swing.JLabel ucLabel;
   private javax.swing.JButton valAddButton;
   private javax.swing.JButton valButton;
   private javax.swing.JButton valClearButton;
   private javax.swing.JPanel valConfig;
   private javax.swing.JButton valDeleteButton;
   private javax.swing.JLabel valMessageLabel;
   private javax.swing.JScrollPane valScroll;
   private javax.swing.JButton valSubmitButton;
   private javax.swing.JTable valTable;
   private javax.swing.JLabel valTitleLabel;
   private javax.swing.JComboBox<String> varBox;
   private javax.swing.JLabel welcomeLabel;

   // Following fields were manually added

   // processVariables: the values entered on the Process Configuration page
   private HashMap<String, Double> processVariables;
   // startDate: stores the Start Date value on the Process Configuration page
   private String startDate;
   // processDescModel: Table model for the Process Area Description table
   private DefaultTableModel processDescModel;
   // inModel: Table model for the Input Configuration table
   private DefaultTableModel inModel;
   // valModel: Table model for the Input Validation table
   private DefaultTableModel valModel;
   // stateModel: Table model for the State Configuration table
   private DefaultTableModel stateModel;
   // outModel: Table model for the Output Configuration table
   private DefaultTableModel outModel;
   // labModel: Table model for the Lab Configuration table
   private DefaultTableModel labModel;
   // desc: Data table for the Process Area Descriptions
   private Table<Integer, String, String> desc;
   // input: Data table for the Input Configurations
   private Table<Integer, Integer, String> input;
   // state: Data table for the State Configurations
   private Table<Integer, Integer, String> state;
   // output: Data table for the Output Configurations
   private Table<Integer, Integer, String> output;
   // labOutputs: Data map for the Lab configurations (a table for each output name)
   private LinkedHashMap<String, Table<Integer, Integer, String>> labOutputs;
   // inTableBox: Dropdown box for the input descriptions
   private JComboBox<String> inTableBox;
   // inOrderBox: Dropdown box for the input order
   private JComboBox<String> inOrderBox;
   // stateTableBox: Dropdown box for the state descriptions
   private JComboBox<String> stateTableBox;
   // outTableBox: Dropdown box for the output variables in Lab Configurations
   private JComboBox<String> outTableBox;
   // varTableBox: Dropdown box for the input/state variables in Lab Configurations
   private JComboBox<String> varTableBox;
   // orderTableBox: Dropdown box for the order in Lab Configurations
   private JComboBox<String> orderTableBox;
   // modelTableBox: Dropdown box for the model in Lab Configurations
   private JComboBox<String> modelTableBox;
   // dirTableBox: Dropdown box for the direction in Lab Configurations
   private JComboBox<String> dirTableBox;
   // shapeTableBox: Dropdown box for the shape in Lab Configurations
   private JComboBox<String> shapeTableBox;
   // valCount: The number of rows in Input Validations
   private int valCount;
   // orderCount: The number of rows in Input Configurations to keep track of the order value
   private int orderCount;
   // processSet: Have the dependencies for Process Configuration been changed?
   private boolean processSet;
   // processDescSet: Have the dependencies for Process Area Descriptions been changed?
   private boolean processDescSet;
   // inputSet: Have the dependencies for Input Configuration been changed?
   private boolean inputSet;
   // stateSet: Have the dependencies for State Configuration been changed?
   private boolean stateSet;
   // outputSet: Have the dependencies for Output Configuration been changed?
   private boolean outputSet;
   // process: The process period
   private Double process;
   // valPrinted: Has the Input Validation page been printed yet?
   private boolean valPrinted;
   // inPrinted: Has the Input Configuration page been printed yet?
   private boolean inPrinted;
   // outPrinted: Has the Output Configuration page been printed yet?
   private boolean outPrinted;
   // statePrinted: Has the State Configuration page been printed yet?
   private boolean statePrinted;
   // generating: Is the dataset being generated?
   private boolean generating;
   // loaded: Has the data been loaded?
   private boolean loaded;

   public Form() {
      // Method below initialises most of the components
      initComponents();
      // Remaining variables are initialised below
      processVariables = new HashMap<>();
      desc = TreeBasedTable.create();
      input = TreeBasedTable.create();
      state = TreeBasedTable.create();
      output = TreeBasedTable.create();
      labOutputs = new LinkedHashMap<>();
      inTableBox = new JComboBox<>();
      inOrderBox = new JComboBox<>();
      stateTableBox = new JComboBox<>();
      outTableBox = new JComboBox<>();
      varTableBox = new JComboBox<>();
      String [] order = {"1", "2"};
      String [] gain = {"0", "1", "2"};
      String [] dir = {"0", "1"};
      orderTableBox = new JComboBox<String>(order);
      modelTableBox = new JComboBox<String>(gain);
      dirTableBox = new JComboBox<String>(dir);
      shapeTableBox = new JComboBox<String>(gain);
      inTableBox.setBackground(Color.WHITE);
      inTableBox.setForeground(Color.BLACK);
      inOrderBox.setBackground(Color.WHITE);
      inOrderBox.setForeground(Color.BLACK);
      stateTableBox.setBackground(Color.WHITE);
      stateTableBox.setForeground(Color.BLACK);
      outTableBox.setBackground(Color.WHITE);
      outTableBox.setForeground(Color.BLACK);
      varTableBox.setBackground(Color.WHITE);
      varTableBox.setForeground(Color.BLACK);
      orderTableBox.setBackground(Color.WHITE);
      orderTableBox.setForeground(Color.BLACK);
      modelTableBox.setBackground(Color.WHITE);
      modelTableBox.setForeground(Color.BLACK);
      dirTableBox.setBackground(Color.WHITE);
      dirTableBox.setForeground(Color.BLACK);
      shapeTableBox.setBackground(Color.WHITE);
      shapeTableBox.setForeground(Color.BLACK);
      // Setting visibility of components
      loadField.setVisible(false);
      loadFolderButton.setVisible(false);
      loadMessageLabel.setVisible(false);
      loadLabel.setVisible(false);
      loadStatusLabel.setVisible(false);
      downloadField.setVisible(false);
      downloadButton.setVisible(false);
      downloadFolderButton.setVisible(false);
      downloadLabel.setVisible(false);
      getContentPane().setBackground(Color.WHITE);
      processMessageLabel.setVisible(false);
      processDescMessageLabel.setVisible(false);
      // Some JLabels have font colour changes instead of setting visibility due to formatting issues
      inputMessageLabel.setForeground(Color.WHITE);
      valMessageLabel.setForeground(Color.WHITE);
      labMessageLabel.setForeground(Color.WHITE);
      stateMessageLabel.setVisible(false);
      outputMessageLabel.setVisible(false);
      // Setting table colours
      Color blue = new Color(153, 214, 255);
      processDescScroll.getViewport().setBackground(blue);
      inputScroll.getViewport().setBackground(blue);
      valScroll.getViewport().setBackground(blue);
      stateScroll.getViewport().setBackground(blue);
      outputScroll.getViewport().setBackground(blue);
      labScroll.getViewport().setBackground(blue);
      // Initialising table models
      processDescModel = (DefaultTableModel) processDescTable.getModel();
      inModel = (DefaultTableModel) inputTable.getModel();
      valModel = (DefaultTableModel) valTable.getModel();
      stateModel = (DefaultTableModel) stateTable.getModel();
      outModel = (DefaultTableModel) outputTable.getModel();
      labModel = (DefaultTableModel) labTable.getModel();
      // Initialising remaining variables
      processSet = false;
      processDescSet = false;
      inputSet = false;
      stateSet = false;
      outputSet = false;
      valPrinted = false;
      inPrinted = false;
      outPrinted = false;
      statePrinted = false;
      generating = false;
      loaded = false;
      valCount = 1;
      orderCount = 0;
   }

   /*
   * initComponents: Auto-generated method (from NetBeans) that creates the format of the UI
   */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">
   private void initComponents() {

      sideBar = new javax.swing.JPanel();
      homeButton = new javax.swing.JButton();
      processButton = new javax.swing.JButton();
      processConfigLabel = new javax.swing.JLabel();
      inputConfigLabel = new javax.swing.JLabel();
      descButton = new javax.swing.JButton();
      inputButton = new javax.swing.JButton();
      valButton = new javax.swing.JButton();
      stateConfigLabel = new javax.swing.JLabel();
      stateButton = new javax.swing.JButton();
      outputConfigLabel = new javax.swing.JLabel();
      outputButton = new javax.swing.JButton();
      labButton = new javax.swing.JButton();
      genLabel = new javax.swing.JLabel();
      genDataButton = new javax.swing.JButton();
      main = new javax.swing.JPanel();
      homePage = new javax.swing.JPanel();
      welcomeLabel = new javax.swing.JLabel();
      loadButton = new javax.swing.JButton();
      loadField = new javax.swing.JTextField();
      loadFolderButton = new javax.swing.JButton();
      loadMessageLabel = new javax.swing.JLabel();
      loadLabel = new javax.swing.JLabel();
      loadStatusLabel = new javax.swing.JLabel();
      processConfig = new javax.swing.JPanel();
      processTitleLabel = new javax.swing.JLabel();
      dateLabel = new javax.swing.JLabel();
      dateField = new javax.swing.JTextField();
      formatLabel = new javax.swing.JLabel();
      processLabel = new javax.swing.JLabel();
      processField = new javax.swing.JTextField();
      qcsLabel = new javax.swing.JLabel();
      qcsField = new javax.swing.JTextField();
      labLabel = new javax.swing.JLabel();
      labField = new javax.swing.JTextField();
      pulpLabel = new javax.swing.JLabel();
      pulpField = new javax.swing.JTextField();
      ucLabel = new javax.swing.JLabel();
      ucField = new javax.swing.JTextField();
      trimLabel = new javax.swing.JLabel();
      trimField = new javax.swing.JTextField();
      drawLabel = new javax.swing.JLabel();
      drawField = new javax.swing.JTextField();
      submitProcessButton = new javax.swing.JButton();
      processMessageLabel = new javax.swing.JLabel();
      settleField = new javax.swing.JTextField();
      settleLabel = new javax.swing.JLabel();
      processDescConfig = new javax.swing.JPanel();
      processDescLabel = new javax.swing.JLabel();
      descNameLabel = new javax.swing.JLabel();
      descNameField = new javax.swing.JTextField();
      deadtimeReelLabel = new javax.swing.JLabel();
      deadtimeReelField = new javax.swing.JTextField();
      lag1Label = new javax.swing.JLabel();
      lag1Field = new javax.swing.JTextField();
      lag2Label = new javax.swing.JLabel();
      lag2Field = new javax.swing.JTextField();
      processDescAddButton = new javax.swing.JButton();
      processDescMessageLabel = new javax.swing.JLabel();
      processDescSubmitButton = new javax.swing.JButton();
      processDescDeleteButton = new javax.swing.JButton();
      processDescClearButton = new javax.swing.JButton();
      processDescScroll = new javax.swing.JScrollPane();
      processDescTable = new javax.swing.JTable();
      inputConfig = new javax.swing.JPanel();
      inputTitleLabel = new javax.swing.JLabel();
      inputNameLabel = new javax.swing.JLabel();
      inputNameField = new javax.swing.JTextField();
      processDescriptionLabel = new javax.swing.JLabel();
      inputNoiseLabel = new javax.swing.JLabel();
      inputNoiseField = new javax.swing.JTextField();
      inputMvLagLabel = new javax.swing.JLabel();
      inputMvLagField = new javax.swing.JTextField();
      inputAddButton = new javax.swing.JButton();
      inputSubmitButton = new javax.swing.JButton();
      inputDeleteButton = new javax.swing.JButton();
      inputClearButton = new javax.swing.JButton();
      inputScroll = new javax.swing.JScrollPane();
      inputTable = new javax.swing.JTable();
      inputMaxField = new javax.swing.JTextField();
      inputMaxLabel = new javax.swing.JLabel();
      inputMinField = new javax.swing.JTextField();
      inputMinLabel = new javax.swing.JLabel();
      descBox = new javax.swing.JComboBox<>();
      inputMessageLabel = new javax.swing.JLabel();
      sinePeriodField = new javax.swing.JTextField();
      sineAmpField = new javax.swing.JTextField();
      sineAmpLabel = new javax.swing.JLabel();
      sinePeriodLabel = new javax.swing.JLabel();
      valConfig = new javax.swing.JPanel();
      valTitleLabel = new javax.swing.JLabel();
      valAddButton = new javax.swing.JButton();
      valMessageLabel = new javax.swing.JLabel();
      valSubmitButton = new javax.swing.JButton();
      valDeleteButton = new javax.swing.JButton();
      valClearButton = new javax.swing.JButton();
      valScroll = new javax.swing.JScrollPane();
      valTable = new javax.swing.JTable();
      stateConfig = new javax.swing.JPanel();
      stateTitleLabel = new javax.swing.JLabel();
      stateNameLabel = new javax.swing.JLabel();
      stateNameField = new javax.swing.JTextField();
      stateDescriptionLabel = new javax.swing.JLabel();
      stateAddButton = new javax.swing.JButton();
      stateMessageLabel = new javax.swing.JLabel();
      stateSubmitButton = new javax.swing.JButton();
      stateDeleteButton = new javax.swing.JButton();
      stateClearButton = new javax.swing.JButton();
      stateScroll = new javax.swing.JScrollPane();
      stateTable = new javax.swing.JTable();
      stateMaxLabel = new javax.swing.JLabel();
      stateNoiseField = new javax.swing.JTextField();
      stateNoiseLabel = new javax.swing.JLabel();
      stateMaxField = new javax.swing.JTextField();
      stateMinLabel = new javax.swing.JLabel();
      stateMinField = new javax.swing.JTextField();
      stateDescBox = new javax.swing.JComboBox<>();
      outputConfig = new javax.swing.JPanel();
      outputTitleLabel = new javax.swing.JLabel();
      outputNameLabel = new javax.swing.JLabel();
      outputNameField = new javax.swing.JTextField();
      outputMinLabel = new javax.swing.JLabel();
      outputMinField = new javax.swing.JTextField();
      outputMaxLabel = new javax.swing.JLabel();
      outputMaxField = new javax.swing.JTextField();
      outputAddButton = new javax.swing.JButton();
      outputMessageLabel = new javax.swing.JLabel();
      outputSubmitButton = new javax.swing.JButton();
      outputDeleteButton = new javax.swing.JButton();
      outputClearButton = new javax.swing.JButton();
      outputScroll = new javax.swing.JScrollPane();
      outputTable = new javax.swing.JTable();
      outputDescField = new javax.swing.JTextField();
      outputDescLabel = new javax.swing.JLabel();
      outputNoiseField = new javax.swing.JTextField();
      outputNoiseLabel = new javax.swing.JLabel();
      labConfig = new javax.swing.JPanel();
      labTitleLabel = new javax.swing.JLabel();
      labWeightLabel = new javax.swing.JLabel();
      labWeightField = new javax.swing.JTextField();
      labOrderLabel = new javax.swing.JLabel();
      labAsymptoteLabel = new javax.swing.JLabel();
      labAsymptoteField = new javax.swing.JTextField();
      labSubmitButton = new javax.swing.JButton();
      outputBox = new javax.swing.JComboBox<>();
      varBox = new javax.swing.JComboBox<>();
      orderBox = new javax.swing.JComboBox<>();
      labSlopeLabel = new javax.swing.JLabel();
      labSlopeField = new javax.swing.JTextField();
      gainModelLabel = new javax.swing.JLabel();
      modelBox = new javax.swing.JComboBox<>();
      gainDirectionLabel = new javax.swing.JLabel();
      directionBox = new javax.swing.JComboBox<>();
      gainShapeLabel = new javax.swing.JLabel();
      shapeBox = new javax.swing.JComboBox<>();
      labMessageLabel = new javax.swing.JLabel();
      labAddButton = new javax.swing.JButton();
      labDeleteButton = new javax.swing.JButton();
      labClearButton = new javax.swing.JButton();
      labWeightLabel2 = new javax.swing.JLabel();
      labWeightLabel3 = new javax.swing.JLabel();
      labScroll = new javax.swing.JScrollPane();
      labTable = new javax.swing.JTable();
      modelInfoButton = new javax.swing.JButton();
      directionInfoButton = new javax.swing.JButton();
      shapeInfoButton = new javax.swing.JButton();
      generate = new javax.swing.JPanel();
      infoLabel = new javax.swing.JLabel();
      genButton = new javax.swing.JButton();
      progressLabel = new javax.swing.JLabel();
      loadBar = new javax.swing.JProgressBar();
      generateTitleLabel = new javax.swing.JLabel();
      listScrollPane = new javax.swing.JScrollPane();
      listTextArea = new javax.swing.JTextArea();
      timeLabel = new javax.swing.JLabel();
      downloadButton = new javax.swing.JButton();
      downloadField = new javax.swing.JTextField();
      downloadLabel = new javax.swing.JLabel();
      downloadFolderButton = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setBackground(new java.awt.Color(255, 255, 255));

      sideBar.setBackground(new java.awt.Color(51, 153, 255));
      sideBar.setForeground(new java.awt.Color(255, 255, 255));
      sideBar.setPreferredSize(new java.awt.Dimension(165, 87));

      homeButton.setBackground(new java.awt.Color(255, 255, 255));
      homeButton.setForeground(new java.awt.Color(0, 0, 0));
      homeButton.setText("Home");
      homeButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            homeButtonActionPerformed();
         }
      });

      processButton.setBackground(new java.awt.Color(255, 255, 255));
      processButton.setForeground(new java.awt.Color(0, 0, 0));
      processButton.setText("Process Variables");
      processButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processButtonActionPerformed();
         }
      });

      processConfigLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processConfigLabel.setForeground(new java.awt.Color(255, 255, 255));
      processConfigLabel.setText("Process Configuration");

      inputConfigLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputConfigLabel.setForeground(new java.awt.Color(255, 255, 255));
      inputConfigLabel.setText("Input Configuration");

      descButton.setBackground(new java.awt.Color(255, 255, 255));
      descButton.setForeground(new java.awt.Color(0, 0, 0));
      descButton.setText("<html>Process Area<br />Descriptions</html>");
      descButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            descButtonActionPerformed();
         }
      });

      inputButton.setBackground(new java.awt.Color(255, 255, 255));
      inputButton.setForeground(new java.awt.Color(0, 0, 0));
      inputButton.setText("Input Variables");
      inputButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            inputButtonActionPerformed();
         }
      });

      valButton.setBackground(new java.awt.Color(255, 255, 255));
      valButton.setForeground(new java.awt.Color(0, 0, 0));
      valButton.setText("Input Validations");
      valButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            valButtonActionPerformed();
         }
      });

      stateConfigLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateConfigLabel.setForeground(new java.awt.Color(255, 255, 255));
      stateConfigLabel.setText("State Configuration");

      stateButton.setBackground(new java.awt.Color(255, 255, 255));
      stateButton.setForeground(new java.awt.Color(0, 0, 0));
      stateButton.setText("State Variables");
      stateButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            stateButtonActionPerformed();
         }
      });

      outputConfigLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputConfigLabel.setForeground(new java.awt.Color(255, 255, 255));
      outputConfigLabel.setText("Output Configuration");

      outputButton.setBackground(new java.awt.Color(255, 255, 255));
      outputButton.setForeground(new java.awt.Color(0, 0, 0));
      outputButton.setText("Output Variables");
      outputButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            outputButtonActionPerformed();
         }
      });

      labButton.setBackground(new java.awt.Color(255, 255, 255));
      labButton.setForeground(new java.awt.Color(0, 0, 0));
      labButton.setText("Lab Variables");
      labButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            labButtonActionPerformed();
         }
      });

      genLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      genLabel.setForeground(new java.awt.Color(255, 255, 255));
      genLabel.setText("Generate Data");

      genDataButton.setBackground(new java.awt.Color(255, 255, 255));
      genDataButton.setForeground(new java.awt.Color(0, 0, 0));
      genDataButton.setText("Generate");
      genDataButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            genDataButtonActionPerformed();
         }
      });

      javax.swing.GroupLayout sideBarLayout = new javax.swing.GroupLayout(sideBar);
      sideBar.setLayout(sideBarLayout);
      sideBarLayout.setHorizontalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(sideBarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(outputConfigLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addGroup(sideBarLayout.createSequentialGroup()
                                    .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(inputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(inputConfigLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(valButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                      .addComponent(stateConfigLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(homeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addGroup(sideBarLayout.createSequentialGroup()
                                                            .addGap(6, 6, 6)
                                                            .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                  .addComponent(processButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                  .addComponent(stateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                  .addComponent(descButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                      .addComponent(labButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                      .addComponent(outputButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
                                          .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(sideBarLayout.createSequentialGroup()
                                                      .addGap(6, 6, 6)
                                                      .addComponent(genDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                                .addComponent(genLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(18, Short.MAX_VALUE))
                              .addComponent(processConfigLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
      );
      sideBarLayout.setVerticalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(sideBarLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(homeButton)
                        .addGap(33, 33, 33)
                        .addComponent(processConfigLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(processButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputConfigLabel)
                        .addGap(12, 12, 12)
                        .addComponent(inputButton)
                        .addGap(12, 12, 12)
                        .addComponent(valButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stateConfigLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(outputConfigLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(outputButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(genLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(genDataButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      main.setBackground(new java.awt.Color(255, 255, 255));
      main.setLayout(new java.awt.CardLayout());

      homePage.setBackground(new java.awt.Color(255, 255, 255));
      homePage.setForeground(new java.awt.Color(255, 255, 255));

      welcomeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      welcomeLabel.setForeground(new java.awt.Color(0, 0, 0));
      welcomeLabel.setText("Dataset Generator, Pat Dixon (Pulmac Systems), Alia Rezvi, 2023");

      loadButton.setBackground(new java.awt.Color(51, 153, 255));
      loadButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      loadButton.setForeground(new java.awt.Color(255, 255, 255));
      loadButton.setText("Load Configurations");
      loadButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            loadButtonActionPerformed();
         }
      });

      loadField.setBackground(new java.awt.Color(255, 255, 255));
      loadField.setForeground(new java.awt.Color(0, 0, 0));

      loadFolderButton.setBackground(new java.awt.Color(51, 153, 255));
      loadFolderButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      loadFolderButton.setForeground(new java.awt.Color(255, 255, 255));
      loadFolderButton.setText("Select Folder");
      loadFolderButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            loadFolderButtonActionPerformed();
         }
      });

      loadMessageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      loadMessageLabel.setForeground(new java.awt.Color(0, 0, 0));
      loadMessageLabel.setText("Message");

      loadLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      loadLabel.setForeground(new java.awt.Color(0, 0, 0));
      loadLabel.setText("Enter the folder name of your chosen configurations in the /config folder:");

      loadStatusLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      loadStatusLabel.setForeground(new java.awt.Color(0, 0, 0));
      loadStatusLabel.setText("Status");

      javax.swing.GroupLayout homePageLayout = new javax.swing.GroupLayout(homePage);
      homePage.setLayout(homePageLayout);
      homePageLayout.setHorizontalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(homePageLayout.createSequentialGroup()
                        .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(homePageLayout.createSequentialGroup()
                                    .addGap(388, 388, 388)
                                    .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(homePageLayout.createSequentialGroup()
                                                .addComponent(loadField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(loadFolderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addComponent(loadStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(loadMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGroup(homePageLayout.createSequentialGroup()
                                    .addGap(221, 221, 221)
                                    .addComponent(loadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(homePageLayout.createSequentialGroup()
                                    .addGap(362, 362, 362)
                                    .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(19, Short.MAX_VALUE))
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePageLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209))
      );
      homePageLayout.setVerticalGroup(
            homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(homePageLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(welcomeLabel)
                        .addGap(55, 55, 55)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(loadLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(homePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(loadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(loadFolderButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(190, Short.MAX_VALUE))
      );

      main.add(homePage, "card3");

      processConfig.setBackground(new java.awt.Color(255, 255, 255));

      processTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      processTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
      processTitleLabel.setText("Process Configuration");

      dateLabel.setBackground(new java.awt.Color(0, 0, 0));
      dateLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      dateLabel.setForeground(new java.awt.Color(0, 0, 0));
      dateLabel.setText("Start Date");

      dateField.setBackground(new java.awt.Color(255, 255, 255));
      dateField.setForeground(new java.awt.Color(0, 0, 0));

      formatLabel.setBackground(new java.awt.Color(0, 0, 0));
      formatLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      formatLabel.setForeground(new java.awt.Color(0, 0, 0));
      formatLabel.setText("Format: mm/dd/yy");

      processLabel.setBackground(new java.awt.Color(0, 0, 0));
      processLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processLabel.setForeground(new java.awt.Color(0, 0, 0));
      processLabel.setText("Process Period (sec)");

      processField.setBackground(new java.awt.Color(255, 255, 255));
      processField.setForeground(new java.awt.Color(0, 0, 0));

      qcsLabel.setBackground(new java.awt.Color(0, 0, 0));
      qcsLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      qcsLabel.setForeground(new java.awt.Color(0, 0, 0));
      qcsLabel.setText("QCS Period (sec)");

      qcsField.setBackground(new java.awt.Color(255, 255, 255));
      qcsField.setForeground(new java.awt.Color(0, 0, 0));

      labLabel.setBackground(new java.awt.Color(0, 0, 0));
      labLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labLabel.setForeground(new java.awt.Color(0, 0, 0));
      labLabel.setText("Lab Period (sec)");

      labField.setBackground(new java.awt.Color(255, 255, 255));
      labField.setForeground(new java.awt.Color(0, 0, 0));

      pulpLabel.setBackground(new java.awt.Color(0, 0, 0));
      pulpLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      pulpLabel.setForeground(new java.awt.Color(0, 0, 0));
      pulpLabel.setText("PulpEye Period (sec)");

      pulpField.setBackground(new java.awt.Color(255, 255, 255));
      pulpField.setForeground(new java.awt.Color(0, 0, 0));

      ucLabel.setBackground(new java.awt.Color(0, 0, 0));
      ucLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      ucLabel.setForeground(new java.awt.Color(0, 0, 0));
      ucLabel.setText("Uncoupled Moves");

      ucField.setBackground(new java.awt.Color(255, 255, 255));
      ucField.setForeground(new java.awt.Color(0, 0, 0));

      trimLabel.setBackground(new java.awt.Color(0, 0, 0));
      trimLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      trimLabel.setForeground(new java.awt.Color(0, 0, 0));
      trimLabel.setText("Trim (ft)");

      trimField.setBackground(new java.awt.Color(255, 255, 255));
      trimField.setForeground(new java.awt.Color(0, 0, 0));

      drawLabel.setBackground(new java.awt.Color(0, 0, 0));
      drawLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      drawLabel.setForeground(new java.awt.Color(0, 0, 0));
      drawLabel.setText("Draw");

      drawField.setBackground(new java.awt.Color(255, 255, 255));
      drawField.setForeground(new java.awt.Color(0, 0, 0));

      submitProcessButton.setBackground(new java.awt.Color(51, 153, 255));
      submitProcessButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      submitProcessButton.setForeground(new java.awt.Color(255, 255, 255));
      submitProcessButton.setText("Submit");
      submitProcessButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            submitProcessButtonActionPerformed();
         }
      });

      processMessageLabel.setBackground(new java.awt.Color(255, 255, 255));
      processMessageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processMessageLabel.setForeground(new java.awt.Color(0, 0, 0));

      settleField.setBackground(new java.awt.Color(255, 255, 255));
      settleField.setForeground(new java.awt.Color(0, 0, 0));

      settleLabel.setBackground(new java.awt.Color(0, 0, 0));
      settleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      settleLabel.setForeground(new java.awt.Color(0, 0, 0));
      settleLabel.setText("Added Settle (sec)");

      javax.swing.GroupLayout processConfigLayout = new javax.swing.GroupLayout(processConfig);
      processConfig.setLayout(processConfigLayout);
      processConfigLayout.setHorizontalGroup(
            processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(processConfigLayout.createSequentialGroup()
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(processConfigLayout.createSequentialGroup()
                                    .addGap(278, 278, 278)
                                    .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(processConfigLayout.createSequentialGroup()
                                                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(formatLabel))
                                          .addComponent(dateLabel)
                                          .addComponent(labLabel)
                                          .addComponent(labField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(processLabel)
                                          .addComponent(processField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(qcsLabel)
                                          .addComponent(qcsField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(35, 35, 35)
                                    .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(ucLabel)
                                          .addComponent(ucField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(pulpField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(pulpLabel)
                                          .addComponent(trimLabel)
                                          .addComponent(trimField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(drawLabel)
                                          .addComponent(drawField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGroup(processConfigLayout.createSequentialGroup()
                                    .addGap(358, 358, 358)
                                    .addComponent(processTitleLabel))
                              .addGroup(processConfigLayout.createSequentialGroup()
                                    .addGap(407, 407, 407)
                                    .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(settleField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(settleLabel)))
                              .addGroup(processConfigLayout.createSequentialGroup()
                                    .addGap(399, 399, 399)
                                    .addComponent(submitProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(processMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(33, Short.MAX_VALUE))
      );
      processConfigLayout.setVerticalGroup(
            processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(processConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(processTitleLabel)
                        .addGap(75, 75, 75)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(dateLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                              .addComponent(pulpLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(formatLabel)
                              .addComponent(pulpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(ucLabel)
                              .addComponent(processLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(ucField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(processField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(trimLabel)
                              .addComponent(qcsLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(trimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(qcsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(drawLabel)
                              .addComponent(labLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(drawField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(labField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(processConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(submitProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(processMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(211, 211, 211))
      );

      main.add(processConfig, "card2");

      processDescConfig.setBackground(new java.awt.Color(255, 255, 255));

      processDescLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      processDescLabel.setForeground(new java.awt.Color(0, 0, 0));
      processDescLabel.setText("Process Area Descriptions");

      descNameLabel.setBackground(new java.awt.Color(0, 0, 0));
      descNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      descNameLabel.setForeground(new java.awt.Color(0, 0, 0));
      descNameLabel.setText("Name");

      descNameField.setBackground(new java.awt.Color(255, 255, 255));
      descNameField.setForeground(new java.awt.Color(0, 0, 0));

      deadtimeReelLabel.setBackground(new java.awt.Color(0, 0, 0));
      deadtimeReelLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      deadtimeReelLabel.setForeground(new java.awt.Color(0, 0, 0));
      deadtimeReelLabel.setText("Deadtime Reel (min)");

      deadtimeReelField.setBackground(new java.awt.Color(255, 255, 255));
      deadtimeReelField.setForeground(new java.awt.Color(0, 0, 0));

      lag1Label.setBackground(new java.awt.Color(0, 0, 0));
      lag1Label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      lag1Label.setForeground(new java.awt.Color(0, 0, 0));
      lag1Label.setText("Lag 1 Reel (min)");

      lag1Field.setBackground(new java.awt.Color(255, 255, 255));
      lag1Field.setForeground(new java.awt.Color(0, 0, 0));

      lag2Label.setBackground(new java.awt.Color(0, 0, 0));
      lag2Label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      lag2Label.setForeground(new java.awt.Color(0, 0, 0));
      lag2Label.setText("Lag 2 Reel (min)");

      lag2Field.setBackground(new java.awt.Color(255, 255, 255));
      lag2Field.setForeground(new java.awt.Color(0, 0, 0));

      processDescAddButton.setBackground(new java.awt.Color(51, 153, 0));
      processDescAddButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processDescAddButton.setForeground(new java.awt.Color(255, 255, 255));
      processDescAddButton.setText("Add row");
      processDescAddButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processDescAddButtonActionPerformed();
         }
      });

      processDescMessageLabel.setBackground(new java.awt.Color(255, 255, 255));
      processDescMessageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processDescMessageLabel.setForeground(new java.awt.Color(0, 0, 0));

      processDescSubmitButton.setBackground(new java.awt.Color(51, 153, 255));
      processDescSubmitButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processDescSubmitButton.setForeground(new java.awt.Color(255, 255, 255));
      processDescSubmitButton.setText("Submit");
      processDescSubmitButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processDescSubmitButtonActionPerformed();
         }
      });

      processDescDeleteButton.setBackground(new java.awt.Color(255, 51, 51));
      processDescDeleteButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processDescDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
      processDescDeleteButton.setText("Delete row");
      processDescDeleteButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processDescDeleteButtonActionPerformed();
         }
      });

      processDescClearButton.setBackground(new java.awt.Color(255, 51, 51));
      processDescClearButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processDescClearButton.setForeground(new java.awt.Color(255, 255, 255));
      processDescClearButton.setText("Clear table");
      processDescClearButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processDescClearButtonActionPerformed();
         }
      });

      processDescScroll.setBackground(new java.awt.Color(255, 255, 255));
      processDescScroll.setForeground(new java.awt.Color(204, 204, 204));

      processDescTable.setBackground(new java.awt.Color(255, 255, 255));
      processDescTable.setForeground(new java.awt.Color(0, 0, 0));
      processDescTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                  "Name", "Deadtime Reel", "Lag 1 Reel", "Lag 2 Reel"
            }
      ));
      processDescTable.setRowHeight(21);
      processDescTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
      processDescTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
      processDescTable.setShowGrid(true);
      processDescTable.getTableHeader().setReorderingAllowed(false);
      processDescTable.setUpdateSelectionOnSort(false);
      processDescScroll.setViewportView(processDescTable);
      if (processDescTable.getColumnModel().getColumnCount() > 0) {
         processDescTable.getColumnModel().getColumn(1).setHeaderValue("Deadtime Reel");
      }

      javax.swing.GroupLayout processDescConfigLayout = new javax.swing.GroupLayout(processDescConfig);
      processDescConfig.setLayout(processDescConfigLayout);
      processDescConfigLayout.setHorizontalGroup(
            processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(processDescConfigLayout.createSequentialGroup()
                        .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(processDescConfigLayout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(processDescScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(processDescConfigLayout.createSequentialGroup()
                                    .addGap(338, 338, 338)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                          .addComponent(lag1Label)
                                          .addComponent(lag2Label))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(lag1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(lag2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(43, 43, 43)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(processDescConfigLayout.createSequentialGroup()
                                                .addComponent(processDescSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(processDescClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(processDescConfigLayout.createSequentialGroup()
                                                .addComponent(processDescAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(processDescDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addComponent(processDescMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(38, Short.MAX_VALUE))
                  .addGroup(processDescConfigLayout.createSequentialGroup()
                        .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(processDescConfigLayout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                          .addComponent(deadtimeReelLabel)
                                          .addComponent(descNameLabel))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(descNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(deadtimeReelField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGroup(processDescConfigLayout.createSequentialGroup()
                                    .addGap(345, 345, 345)
                                    .addComponent(processDescLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))
      );
      processDescConfigLayout.setVerticalGroup(
            processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(processDescConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(processDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(processDescScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(processDescConfigLayout.createSequentialGroup()
                                    .addGap(84, 84, 84)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(descNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(descNameLabel)
                                          .addComponent(lag1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(lag1Label))
                                    .addGap(18, 18, 18)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(deadtimeReelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(deadtimeReelLabel))
                                          .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lag2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lag2Label))))
                              .addGroup(processDescConfigLayout.createSequentialGroup()
                                    .addGap(85, 85, 85)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(processDescAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(processDescDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(processDescConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(processDescClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(processDescSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(processDescMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(135, Short.MAX_VALUE))
      );

      main.add(processDescConfig, "card2");

      inputConfig.setBackground(new java.awt.Color(255, 255, 255));

      inputTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      inputTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
      inputTitleLabel.setText("Input Configuration ");

      inputNameLabel.setBackground(new java.awt.Color(0, 0, 0));
      inputNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputNameLabel.setForeground(new java.awt.Color(0, 0, 0));
      inputNameLabel.setText("Name");

      inputNameField.setBackground(new java.awt.Color(255, 255, 255));
      inputNameField.setForeground(new java.awt.Color(0, 0, 0));

      processDescriptionLabel.setBackground(new java.awt.Color(0, 0, 0));
      processDescriptionLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      processDescriptionLabel.setForeground(new java.awt.Color(0, 0, 0));
      processDescriptionLabel.setText("Description");

      inputNoiseLabel.setBackground(new java.awt.Color(0, 0, 0));
      inputNoiseLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputNoiseLabel.setForeground(new java.awt.Color(0, 0, 0));
      inputNoiseLabel.setText("Noise");

      inputNoiseField.setBackground(new java.awt.Color(255, 255, 255));
      inputNoiseField.setForeground(new java.awt.Color(0, 0, 0));

      inputMvLagLabel.setBackground(new java.awt.Color(0, 0, 0));
      inputMvLagLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputMvLagLabel.setForeground(new java.awt.Color(0, 0, 0));
      inputMvLagLabel.setText("MV Lag (sec)");

      inputMvLagField.setBackground(new java.awt.Color(255, 255, 255));
      inputMvLagField.setForeground(new java.awt.Color(0, 0, 0));

      inputAddButton.setBackground(new java.awt.Color(51, 153, 0));
      inputAddButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputAddButton.setForeground(new java.awt.Color(255, 255, 255));
      inputAddButton.setText("Add row");
      inputAddButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            inputAddButtonActionPerformed();
         }
      });

      inputSubmitButton.setBackground(new java.awt.Color(51, 153, 255));
      inputSubmitButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputSubmitButton.setForeground(new java.awt.Color(255, 255, 255));
      inputSubmitButton.setText("Submit");
      inputSubmitButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            inputSubmitButtonActionPerformed();
         }
      });

      inputDeleteButton.setBackground(new java.awt.Color(255, 51, 51));
      inputDeleteButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
      inputDeleteButton.setText("Delete row");
      inputDeleteButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            inputDeleteButtonActionPerformed();
         }
      });

      inputClearButton.setBackground(new java.awt.Color(255, 51, 51));
      inputClearButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputClearButton.setForeground(new java.awt.Color(255, 255, 255));
      inputClearButton.setText("Clear table");
      inputClearButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            inputClearButtonActionPerformed();
         }
      });

      inputTable.setBackground(new java.awt.Color(255, 255, 255));
      inputTable.setForeground(new java.awt.Color(0, 0, 0));
      inputTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                  "Name", "Description", "Noise", "MV Lag", "Max", "Min", "Sin Period", "Sin Amplitude", "Order"
            }
      ));
      inputTable.setGridColor(new java.awt.Color(0, 0, 0));
      inputTable.setRowHeight(21);
      inputTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
      inputTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
      inputTable.setShowGrid(true);
      inputTable.getTableHeader().setReorderingAllowed(false);
      inputTable.setUpdateSelectionOnSort(false);
      inputScroll.setViewportView(inputTable);

      inputMaxField.setBackground(new java.awt.Color(255, 255, 255));
      inputMaxField.setForeground(new java.awt.Color(0, 0, 0));

      inputMaxLabel.setBackground(new java.awt.Color(0, 0, 0));
      inputMaxLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputMaxLabel.setForeground(new java.awt.Color(0, 0, 0));
      inputMaxLabel.setText("Max");

      inputMinField.setBackground(new java.awt.Color(255, 255, 255));
      inputMinField.setForeground(new java.awt.Color(0, 0, 0));

      inputMinLabel.setBackground(new java.awt.Color(0, 0, 0));
      inputMinLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputMinLabel.setForeground(new java.awt.Color(0, 0, 0));
      inputMinLabel.setText("Min");

      descBox.setBackground(new java.awt.Color(255, 255, 255));
      descBox.setForeground(new java.awt.Color(0, 0, 0));
      descBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));

      inputMessageLabel.setBackground(new java.awt.Color(0, 0, 0));
      inputMessageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputMessageLabel.setForeground(new java.awt.Color(0, 0, 0));

      sinePeriodField.setBackground(new java.awt.Color(255, 255, 255));
      sinePeriodField.setForeground(new java.awt.Color(0, 0, 0));

      sineAmpField.setBackground(new java.awt.Color(255, 255, 255));
      sineAmpField.setForeground(new java.awt.Color(0, 0, 0));

      sineAmpLabel.setBackground(new java.awt.Color(0, 0, 0));
      sineAmpLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      sineAmpLabel.setForeground(new java.awt.Color(0, 0, 0));
      sineAmpLabel.setText("Sin Amplitude");

      sinePeriodLabel.setBackground(new java.awt.Color(0, 0, 0));
      sinePeriodLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      sinePeriodLabel.setForeground(new java.awt.Color(0, 0, 0));
      sinePeriodLabel.setText("Sin Period (sec)");

      javax.swing.GroupLayout inputConfigLayout = new javax.swing.GroupLayout(inputConfig);
      inputConfig.setLayout(inputConfigLayout);
      inputConfigLayout.setHorizontalGroup(
            inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(inputConfigLayout.createSequentialGroup()
                        .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(inputConfigLayout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addComponent(inputScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(inputConfigLayout.createSequentialGroup()
                                    .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                          .addGroup(inputConfigLayout.createSequentialGroup()
                                                .addGap(361, 361, 361)
                                                .addComponent(inputTitleLabel))
                                          .addGroup(inputConfigLayout.createSequentialGroup()
                                                .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addGroup(inputConfigLayout.createSequentialGroup()
                                                            .addGap(146, 146, 146)
                                                            .addComponent(inputNameLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputConfigLayout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputConfigLayout.createSequentialGroup()
                                                                        .addComponent(processDescriptionLabel)
                                                                        .addGap(4, 4, 4))
                                                                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputConfigLayout.createSequentialGroup()
                                                                        .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                              .addComponent(sinePeriodLabel)
                                                                              .addComponent(inputMaxLabel))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                                                .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addGroup(inputConfigLayout.createSequentialGroup()
                                                            .addComponent(sinePeriodField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(sineAmpLabel))
                                                      .addGroup(inputConfigLayout.createSequentialGroup()
                                                            .addComponent(inputMaxField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(inputMinLabel))
                                                      .addGroup(inputConfigLayout.createSequentialGroup()
                                                            .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                  .addGroup(inputConfigLayout.createSequentialGroup()
                                                                        .addComponent(inputNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                  .addGroup(inputConfigLayout.createSequentialGroup()
                                                                        .addGap(2, 2, 2)
                                                                        .addComponent(descBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                  .addComponent(inputNoiseLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                  .addComponent(inputMvLagLabel, javax.swing.GroupLayout.Alignment.TRAILING))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addComponent(inputMvLagField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(inputNoiseField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(inputMinField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(sineAmpField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(39, 39, 39)))
                                    .addGap(5, 5, 5)
                                    .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(inputConfigLayout.createSequentialGroup()
                                                .addComponent(inputAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(inputDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(inputConfigLayout.createSequentialGroup()
                                                .addComponent(inputSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(inputClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addComponent(inputMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(41, Short.MAX_VALUE))
      );
      inputConfigLayout.setVerticalGroup(
            inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(inputConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(inputTitleLabel)
                        .addGap(38, 38, 38)
                        .addComponent(inputScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(inputConfigLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                    .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(inputAddButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(inputDeleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(12, 12, 12)
                                    .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(inputSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(inputClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(inputMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(108, 108, 108))
                              .addGroup(inputConfigLayout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(inputNoiseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(inputNoiseLabel)
                                          .addComponent(inputNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(inputNameLabel))
                                    .addGap(21, 21, 21)
                                    .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(processDescriptionLabel)
                                          .addComponent(inputMvLagField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(inputMvLagLabel)
                                          .addComponent(descBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(inputMaxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(inputMaxLabel)
                                          .addComponent(inputMinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(inputMinLabel))
                                    .addGap(18, 18, 18)
                                    .addGroup(inputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(sinePeriodField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(sineAmpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(sineAmpLabel)
                                          .addComponent(sinePeriodLabel))
                                    .addGap(58, 99, Short.MAX_VALUE))))
      );

      main.add(inputConfig, "card2");

      valConfig.setBackground(new java.awt.Color(255, 255, 255));

      valTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      valTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
      valTitleLabel.setText("Input Validation Configuration ");

      valAddButton.setBackground(new java.awt.Color(51, 153, 0));
      valAddButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      valAddButton.setForeground(new java.awt.Color(255, 255, 255));
      valAddButton.setText("Add row");
      valAddButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            valAddButtonActionPerformed();
         }
      });

      valMessageLabel.setBackground(new java.awt.Color(255, 255, 255));
      valMessageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      valMessageLabel.setForeground(new java.awt.Color(0, 0, 0));

      valSubmitButton.setBackground(new java.awt.Color(51, 153, 255));
      valSubmitButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      valSubmitButton.setForeground(new java.awt.Color(255, 255, 255));
      valSubmitButton.setText("Submit");
      valSubmitButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            valSubmitButtonActionPerformed();
         }
      });

      valDeleteButton.setBackground(new java.awt.Color(255, 51, 51));
      valDeleteButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      valDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
      valDeleteButton.setText("Delete row");
      valDeleteButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            valDeleteButtonActionPerformed();
         }
      });

      valClearButton.setBackground(new java.awt.Color(255, 51, 51));
      valClearButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      valClearButton.setForeground(new java.awt.Color(255, 255, 255));
      valClearButton.setText("Clear table");
      valClearButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            valClearButtonActionPerformed();
         }
      });

      valTable.setBackground(new java.awt.Color(255, 255, 255));
      valTable.setForeground(new java.awt.Color(0, 0, 0));
      valTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                  "Row"
            }
      ));
      valTable.setRowHeight(21);
      valTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
      valTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
      valTable.setShowGrid(true);
      valTable.getTableHeader().setReorderingAllowed(false);
      valTable.setUpdateSelectionOnSort(false);
      valScroll.setViewportView(valTable);

      javax.swing.GroupLayout valConfigLayout = new javax.swing.GroupLayout(valConfig);
      valConfig.setLayout(valConfigLayout);
      valConfigLayout.setHorizontalGroup(
            valConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, valConfigLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(valTitleLabel)
                        .addGap(316, 316, 316))
                  .addGroup(valConfigLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(valScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, valConfigLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(valAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(valDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(valClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(valConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(valSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(valMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
      );
      valConfigLayout.setVerticalGroup(
            valConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(valConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(valTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(valScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(valConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(valAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(valDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(valSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(valClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))
      );

      main.add(valConfig, "card2");

      stateConfig.setBackground(new java.awt.Color(255, 255, 255));

      stateTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      stateTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
      stateTitleLabel.setText("State Configuration ");

      stateNameLabel.setBackground(new java.awt.Color(0, 0, 0));
      stateNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateNameLabel.setForeground(new java.awt.Color(0, 0, 0));
      stateNameLabel.setText("Name");

      stateNameField.setBackground(new java.awt.Color(255, 255, 255));
      stateNameField.setForeground(new java.awt.Color(0, 0, 0));

      stateDescriptionLabel.setBackground(new java.awt.Color(0, 0, 0));
      stateDescriptionLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateDescriptionLabel.setForeground(new java.awt.Color(0, 0, 0));
      stateDescriptionLabel.setText("Description");

      stateAddButton.setBackground(new java.awt.Color(51, 153, 0));
      stateAddButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateAddButton.setForeground(new java.awt.Color(255, 255, 255));
      stateAddButton.setText("Add row");
      stateAddButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            stateAddButtonActionPerformed();
         }
      });

      stateMessageLabel.setBackground(new java.awt.Color(255, 255, 255));
      stateMessageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateMessageLabel.setForeground(new java.awt.Color(0, 0, 0));

      stateSubmitButton.setBackground(new java.awt.Color(51, 153, 255));
      stateSubmitButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateSubmitButton.setForeground(new java.awt.Color(255, 255, 255));
      stateSubmitButton.setText("Submit");
      stateSubmitButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            stateSubmitButtonActionPerformed();
         }
      });

      stateDeleteButton.setBackground(new java.awt.Color(255, 51, 51));
      stateDeleteButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
      stateDeleteButton.setText("Delete row");
      stateDeleteButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            stateDeleteButtonActionPerformed();
         }
      });

      stateClearButton.setBackground(new java.awt.Color(255, 51, 51));
      stateClearButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateClearButton.setForeground(new java.awt.Color(255, 255, 255));
      stateClearButton.setText("Clear table");
      stateClearButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            stateClearButtonActionPerformed();
         }
      });

      stateTable.setBackground(new java.awt.Color(255, 255, 255));
      stateTable.setForeground(new java.awt.Color(0, 0, 0));
      stateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                  "Name", "Description", "Noise", "Max", "Min"
            }
      ));
      stateTable.setGridColor(new java.awt.Color(0, 0, 0));
      stateTable.setRowHeight(21);
      stateTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
      stateTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
      stateTable.setShowGrid(true);
      stateTable.getTableHeader().setReorderingAllowed(false);
      stateTable.setUpdateSelectionOnSort(false);
      stateScroll.setViewportView(stateTable);

      stateMaxLabel.setBackground(new java.awt.Color(0, 0, 0));
      stateMaxLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateMaxLabel.setForeground(new java.awt.Color(0, 0, 0));
      stateMaxLabel.setText("Max");

      stateNoiseField.setBackground(new java.awt.Color(255, 255, 255));
      stateNoiseField.setForeground(new java.awt.Color(0, 0, 0));

      stateNoiseLabel.setBackground(new java.awt.Color(0, 0, 0));
      stateNoiseLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateNoiseLabel.setForeground(new java.awt.Color(0, 0, 0));
      stateNoiseLabel.setText("Noise");

      stateMaxField.setBackground(new java.awt.Color(255, 255, 255));
      stateMaxField.setForeground(new java.awt.Color(0, 0, 0));

      stateMinLabel.setBackground(new java.awt.Color(0, 0, 0));
      stateMinLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      stateMinLabel.setForeground(new java.awt.Color(0, 0, 0));
      stateMinLabel.setText("Min");

      stateMinField.setBackground(new java.awt.Color(255, 255, 255));
      stateMinField.setForeground(new java.awt.Color(0, 0, 0));

      stateDescBox.setBackground(new java.awt.Color(255, 255, 255));
      stateDescBox.setForeground(new java.awt.Color(0, 0, 0));

      javax.swing.GroupLayout stateConfigLayout = new javax.swing.GroupLayout(stateConfig);
      stateConfig.setLayout(stateConfigLayout);
      stateConfigLayout.setHorizontalGroup(
            stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(stateConfigLayout.createSequentialGroup()
                        .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                              .addComponent(stateMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(stateConfigLayout.createSequentialGroup()
                                          .addGap(367, 367, 367)
                                          .addComponent(stateTitleLabel))
                                    .addGroup(stateConfigLayout.createSequentialGroup()
                                          .addGap(43, 43, 43)
                                          .addComponent(stateScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(stateConfigLayout.createSequentialGroup()
                                          .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(stateConfigLayout.createSequentialGroup()
                                                      .addGap(152, 152, 152)
                                                      .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(stateNameLabel)
                                                            .addComponent(stateNoiseLabel))
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                      .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(stateConfigLayout.createSequentialGroup()
                                                                  .addComponent(stateNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                  .addGap(50, 50, 50)
                                                                  .addComponent(stateDescriptionLabel))
                                                            .addGroup(stateConfigLayout.createSequentialGroup()
                                                                  .addComponent(stateNoiseField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                  .addComponent(stateMaxLabel)))
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stateConfigLayout.createSequentialGroup()
                                                      .addContainerGap()
                                                      .addComponent(stateMinLabel)
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                      .addComponent(stateMinField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addGap(19, 19, 19)))
                                          .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(stateConfigLayout.createSequentialGroup()
                                                      .addComponent(stateMaxField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addGap(85, 85, 85)
                                                      .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(stateAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(stateSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                      .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(stateDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(stateClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(stateDescBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGap(15, 15, 15))))
                        .addContainerGap(41, Short.MAX_VALUE))
      );
      stateConfigLayout.setVerticalGroup(
            stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(stateConfigLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(stateTitleLabel)
                        .addGap(38, 38, 38)
                        .addComponent(stateScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(stateConfigLayout.createSequentialGroup()
                                    .addGap(109, 109, 109)
                                    .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(stateAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(stateDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(stateSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(stateClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGroup(stateConfigLayout.createSequentialGroup()
                                    .addGap(73, 73, 73)
                                    .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(stateNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(stateNameLabel)
                                          .addComponent(stateDescriptionLabel)
                                          .addComponent(stateDescBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(stateNoiseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(stateNoiseLabel))
                                          .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(stateMaxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(stateMaxLabel)))
                                    .addGap(18, 18, 18)
                                    .addGroup(stateConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(stateMinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(stateMinLabel))))
                        .addGap(18, 18, 18)
                        .addComponent(stateMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(50, Short.MAX_VALUE))
      );

      main.add(stateConfig, "card2");

      outputConfig.setBackground(new java.awt.Color(255, 255, 255));

      outputTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      outputTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
      outputTitleLabel.setText("Output Configuration ");

      outputNameLabel.setBackground(new java.awt.Color(0, 0, 0));
      outputNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputNameLabel.setForeground(new java.awt.Color(0, 0, 0));
      outputNameLabel.setText("Name");

      outputNameField.setBackground(new java.awt.Color(255, 255, 255));
      outputNameField.setForeground(new java.awt.Color(0, 0, 0));

      outputMinLabel.setBackground(new java.awt.Color(0, 0, 0));
      outputMinLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputMinLabel.setForeground(new java.awt.Color(0, 0, 0));
      outputMinLabel.setText("Min");

      outputMinField.setBackground(new java.awt.Color(255, 255, 255));
      outputMinField.setForeground(new java.awt.Color(0, 0, 0));

      outputMaxLabel.setBackground(new java.awt.Color(0, 0, 0));
      outputMaxLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputMaxLabel.setForeground(new java.awt.Color(0, 0, 0));
      outputMaxLabel.setText("Max");

      outputMaxField.setBackground(new java.awt.Color(255, 255, 255));
      outputMaxField.setForeground(new java.awt.Color(0, 0, 0));

      outputAddButton.setBackground(new java.awt.Color(51, 153, 0));
      outputAddButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputAddButton.setForeground(new java.awt.Color(255, 255, 255));
      outputAddButton.setText("Add row");
      outputAddButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            outputAddButtonActionPerformed();
         }
      });

      outputMessageLabel.setBackground(new java.awt.Color(255, 255, 255));
      outputMessageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputMessageLabel.setForeground(new java.awt.Color(0, 0, 0));

      outputSubmitButton.setBackground(new java.awt.Color(51, 153, 255));
      outputSubmitButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputSubmitButton.setForeground(new java.awt.Color(255, 255, 255));
      outputSubmitButton.setText("Submit");
      outputSubmitButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            outputSubmitButtonActionPerformed();
         }
      });

      outputDeleteButton.setBackground(new java.awt.Color(255, 51, 51));
      outputDeleteButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
      outputDeleteButton.setText("Delete row");
      outputDeleteButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            outputDeleteButtonActionPerformed();
         }
      });

      outputClearButton.setBackground(new java.awt.Color(255, 51, 51));
      outputClearButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputClearButton.setForeground(new java.awt.Color(255, 255, 255));
      outputClearButton.setText("Clear table");
      outputClearButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            outputClearButtonActionPerformed();
         }
      });

      outputTable.setBackground(new java.awt.Color(255, 255, 255));
      outputTable.setForeground(new java.awt.Color(0, 0, 0));
      outputTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                  "Name", "Description", "Noise", "Max", "Min"
            }
      ));
      outputTable.setRowHeight(21);
      outputTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
      outputTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
      outputTable.setShowGrid(true);
      outputTable.getTableHeader().setReorderingAllowed(false);
      outputTable.setUpdateSelectionOnSort(false);
      outputScroll.setViewportView(outputTable);

      outputDescField.setBackground(new java.awt.Color(255, 255, 255));
      outputDescField.setForeground(new java.awt.Color(0, 0, 0));

      outputDescLabel.setBackground(new java.awt.Color(0, 0, 0));
      outputDescLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputDescLabel.setForeground(new java.awt.Color(0, 0, 0));
      outputDescLabel.setText("Description");

      outputNoiseField.setBackground(new java.awt.Color(255, 255, 255));
      outputNoiseField.setForeground(new java.awt.Color(0, 0, 0));

      outputNoiseLabel.setBackground(new java.awt.Color(0, 0, 0));
      outputNoiseLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      outputNoiseLabel.setForeground(new java.awt.Color(0, 0, 0));
      outputNoiseLabel.setText("Noise");

      javax.swing.GroupLayout outputConfigLayout = new javax.swing.GroupLayout(outputConfig);
      outputConfig.setLayout(outputConfigLayout);
      outputConfigLayout.setHorizontalGroup(
            outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(outputConfigLayout.createSequentialGroup()
                        .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(outputConfigLayout.createSequentialGroup()
                                    .addGap(356, 356, 356)
                                    .addComponent(outputTitleLabel))
                              .addGroup(outputConfigLayout.createSequentialGroup()
                                    .addGap(112, 112, 112)
                                    .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                          .addComponent(outputNameLabel)
                                          .addComponent(outputDescLabel))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(outputDescField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(outputNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                  .addGroup(outputConfigLayout.createSequentialGroup()
                        .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addGroup(outputConfigLayout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(outputScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(outputConfigLayout.createSequentialGroup()
                                    .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(outputConfigLayout.createSequentialGroup()
                                                .addGap(338, 338, 338)
                                                .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                      .addGroup(outputConfigLayout.createSequentialGroup()
                                                            .addComponent(outputMaxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(outputMaxField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                      .addGroup(outputConfigLayout.createSequentialGroup()
                                                            .addComponent(outputMinLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(outputMinField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(44, 44, 44))
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outputConfigLayout.createSequentialGroup()
                                                .addGap(246, 246, 246)
                                                .addComponent(outputNoiseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(outputNoiseField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(126, 126, 126)))
                                    .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(outputConfigLayout.createSequentialGroup()
                                                .addComponent(outputAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(outputDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(outputConfigLayout.createSequentialGroup()
                                                .addComponent(outputSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(outputClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addComponent(outputMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(89, Short.MAX_VALUE))
      );
      outputConfigLayout.setVerticalGroup(
            outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(outputConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(outputTitleLabel)
                        .addGap(38, 38, 38)
                        .addComponent(outputScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(outputConfigLayout.createSequentialGroup()
                                    .addGap(84, 84, 84)
                                    .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(outputNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(outputNameLabel)
                                          .addComponent(outputMaxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(outputMaxLabel))
                                    .addGap(11, 11, 11))
                              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outputConfigLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(outputDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(outputAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(outputSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(outputClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(outputMinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(outputMinLabel)
                              .addComponent(outputDescLabel)
                              .addComponent(outputDescField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(outputMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGroup(outputConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(outputNoiseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(outputNoiseLabel)))
                        .addContainerGap(163, Short.MAX_VALUE))
      );

      main.add(outputConfig, "card2");

      labConfig.setBackground(new java.awt.Color(255, 255, 255));

      labTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      labTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
      labTitleLabel.setText("Output Lab Configuration ");

      labWeightLabel.setBackground(new java.awt.Color(0, 0, 0));
      labWeightLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labWeightLabel.setForeground(new java.awt.Color(0, 0, 0));
      labWeightLabel.setText("Weight");

      labWeightField.setBackground(new java.awt.Color(255, 255, 255));
      labWeightField.setForeground(new java.awt.Color(0, 0, 0));

      labOrderLabel.setBackground(new java.awt.Color(0, 0, 0));
      labOrderLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labOrderLabel.setForeground(new java.awt.Color(0, 0, 0));
      labOrderLabel.setText("Order");

      labAsymptoteLabel.setBackground(new java.awt.Color(0, 0, 0));
      labAsymptoteLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labAsymptoteLabel.setForeground(new java.awt.Color(0, 0, 0));
      labAsymptoteLabel.setText("Asymptote (can be empty)");

      labAsymptoteField.setBackground(new java.awt.Color(255, 255, 255));
      labAsymptoteField.setForeground(new java.awt.Color(0, 0, 0));

      labSubmitButton.setBackground(new java.awt.Color(51, 153, 255));
      labSubmitButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labSubmitButton.setForeground(new java.awt.Color(255, 255, 255));
      labSubmitButton.setText("Submit");
      labSubmitButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      labSubmitButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            labSubmitButtonActionPerformed();
         }
      });

      outputBox.setBackground(new java.awt.Color(255, 255, 255));
      outputBox.setForeground(new java.awt.Color(0, 0, 0));

      varBox.setBackground(new java.awt.Color(255, 255, 255));
      varBox.setForeground(new java.awt.Color(0, 0, 0));

      orderBox.setBackground(new java.awt.Color(255, 255, 255));
      orderBox.setForeground(new java.awt.Color(0, 0, 0));
      orderBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

      labSlopeLabel.setBackground(new java.awt.Color(0, 0, 0));
      labSlopeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labSlopeLabel.setForeground(new java.awt.Color(0, 0, 0));
      labSlopeLabel.setText("Slope (can be empty)");

      labSlopeField.setBackground(new java.awt.Color(255, 255, 255));
      labSlopeField.setForeground(new java.awt.Color(0, 0, 0));

      gainModelLabel.setBackground(new java.awt.Color(0, 0, 0));
      gainModelLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      gainModelLabel.setForeground(new java.awt.Color(0, 0, 0));
      gainModelLabel.setText("Gain Model");

      modelBox.setBackground(new java.awt.Color(255, 255, 255));
      modelBox.setForeground(new java.awt.Color(0, 0, 0));
      modelBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));

      gainDirectionLabel.setBackground(new java.awt.Color(0, 0, 0));
      gainDirectionLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      gainDirectionLabel.setForeground(new java.awt.Color(0, 0, 0));
      gainDirectionLabel.setText("Gain Direction");

      directionBox.setBackground(new java.awt.Color(255, 255, 255));
      directionBox.setForeground(new java.awt.Color(0, 0, 0));
      directionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));

      gainShapeLabel.setBackground(new java.awt.Color(0, 0, 0));
      gainShapeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      gainShapeLabel.setForeground(new java.awt.Color(0, 0, 0));
      gainShapeLabel.setText("Gain Shape");

      shapeBox.setBackground(new java.awt.Color(255, 255, 255));
      shapeBox.setForeground(new java.awt.Color(0, 0, 0));
      shapeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));

      labMessageLabel.setBackground(new java.awt.Color(255, 255, 255));
      labMessageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labMessageLabel.setForeground(new java.awt.Color(0, 0, 0));
      labMessageLabel.setText("Message");

      labAddButton.setBackground(new java.awt.Color(51, 153, 0));
      labAddButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labAddButton.setForeground(new java.awt.Color(255, 255, 255));
      labAddButton.setText("Add row");
      labAddButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      labAddButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            labAddButtonActionPerformed();
         }
      });

      labDeleteButton.setBackground(new java.awt.Color(255, 51, 51));
      labDeleteButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
      labDeleteButton.setText("Delete row");
      labDeleteButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      labDeleteButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            labDeleteButtonActionPerformed();
         }
      });

      labClearButton.setBackground(new java.awt.Color(255, 51, 51));
      labClearButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labClearButton.setForeground(new java.awt.Color(255, 255, 255));
      labClearButton.setText("Clear table");
      labClearButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      labClearButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            labClearButtonActionPerformed();
         }
      });

      labWeightLabel2.setBackground(new java.awt.Color(0, 0, 0));
      labWeightLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labWeightLabel2.setForeground(new java.awt.Color(0, 0, 0));
      labWeightLabel2.setText("Output");

      labWeightLabel3.setBackground(new java.awt.Color(0, 0, 0));
      labWeightLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      labWeightLabel3.setForeground(new java.awt.Color(0, 0, 0));
      labWeightLabel3.setText("Variable");

      labTable.setBackground(new java.awt.Color(255, 255, 255));
      labTable.setForeground(new java.awt.Color(0, 0, 0));
      labTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                  "Ouput", "Variable", "Weight", "Asymptote", "Order", "Slope", "Model", "Direction", "Shape"
            }
      ));
      labTable.setRowHeight(21);
      labTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
      labTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
      labTable.setShowGrid(true);
      labTable.getTableHeader().setReorderingAllowed(false);
      labScroll.setViewportView(labTable);

      modelInfoButton.setBackground(new java.awt.Color(51, 153, 255));
      modelInfoButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      modelInfoButton.setForeground(new java.awt.Color(255, 255, 255));
      modelInfoButton.setText("?");
      modelInfoButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      modelInfoButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            modelInfoButtonActionPerformed();
         }
      });

      directionInfoButton.setBackground(new java.awt.Color(51, 153, 255));
      directionInfoButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      directionInfoButton.setForeground(new java.awt.Color(255, 255, 255));
      directionInfoButton.setText("?");
      directionInfoButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      directionInfoButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            directionInfoButtonActionPerformed();
         }
      });

      shapeInfoButton.setBackground(new java.awt.Color(51, 153, 255));
      shapeInfoButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      shapeInfoButton.setForeground(new java.awt.Color(255, 255, 255));
      shapeInfoButton.setText("?");
      shapeInfoButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      shapeInfoButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            shapeInfoButtonActionPerformed();
         }
      });

      javax.swing.GroupLayout labConfigLayout = new javax.swing.GroupLayout(labConfig);
      labConfig.setLayout(labConfigLayout);
      labConfigLayout.setHorizontalGroup(
            labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(labConfigLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(labConfigLayout.createSequentialGroup()
                                    .addComponent(labScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                              .addGroup(labConfigLayout.createSequentialGroup()
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                          .addComponent(labWeightLabel2)
                                          .addComponent(gainModelLabel)
                                          .addComponent(labWeightLabel)
                                          .addComponent(labOrderLabel))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(labConfigLayout.createSequentialGroup()
                                                .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addComponent(orderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(labWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addGroup(labConfigLayout.createSequentialGroup()
                                                            .addComponent(modelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(modelInfoButton)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labConfigLayout.createSequentialGroup()
                                                .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                      .addGroup(labConfigLayout.createSequentialGroup()
                                                            .addComponent(outputBox, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                                            .addComponent(labWeightLabel3))
                                                      .addGroup(labConfigLayout.createSequentialGroup()
                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                            .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                  .addComponent(labSlopeLabel)
                                                                  .addComponent(labAsymptoteLabel)
                                                                  .addComponent(gainDirectionLabel))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addGroup(labConfigLayout.createSequentialGroup()
                                                            .addComponent(directionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(directionInfoButton))
                                                      .addGroup(labConfigLayout.createSequentialGroup()
                                                            .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                  .addComponent(labAsymptoteField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                  .addComponent(labSlopeField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                  .addComponent(varBox, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(42, 42, 42)
                                                            .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                  .addGroup(labConfigLayout.createSequentialGroup()
                                                                        .addComponent(labSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(labClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                  .addGroup(labConfigLayout.createSequentialGroup()
                                                                        .addComponent(labAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(labDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addContainerGap(95, Short.MAX_VALUE))))))
                  .addGroup(labConfigLayout.createSequentialGroup()
                        .addGap(342, 342, 342)
                        .addComponent(labTitleLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addGroup(labConfigLayout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(gainShapeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shapeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shapeInfoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
      );
      labConfigLayout.setVerticalGroup(
            labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(labConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labTitleLabel)
                        .addGap(43, 43, 43)
                        .addComponent(labScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(labConfigLayout.createSequentialGroup()
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(varBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(labWeightLabel3)
                                          .addComponent(outputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(labWeightLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(labAsymptoteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(labAsymptoteLabel)
                                          .addComponent(labWeightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(labWeightLabel))
                                    .addGap(18, 18, 18)
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(labSlopeLabel)
                                          .addComponent(orderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(labOrderLabel)
                                          .addComponent(labSlopeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(16, 16, 16)
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(directionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(gainDirectionLabel)
                                                .addComponent(directionInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(modelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(gainModelLabel)
                                                .addComponent(modelInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(gainShapeLabel)
                                          .addComponent(shapeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(shapeInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(labMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                              .addGroup(labConfigLayout.createSequentialGroup()
                                    .addGap(42, 42, 42)
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(labAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(labDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(labConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                          .addComponent(labSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(labClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(61, Short.MAX_VALUE))
      );

      main.add(labConfig, "card2");

      generate.setBackground(new java.awt.Color(255, 255, 255));
      generate.setForeground(new java.awt.Color(255, 255, 255));

      infoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      infoLabel.setForeground(new java.awt.Color(0, 0, 0));
      infoLabel.setText("Please ensure that all mandatory varaibles have been entered");

      genButton.setBackground(new java.awt.Color(51, 153, 255));
      genButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      genButton.setForeground(new java.awt.Color(255, 255, 255));
      genButton.setText("Generate Data");
      genButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      genButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            genButtonActionPerformed();
         }
      });

      progressLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      progressLabel.setForeground(new java.awt.Color(0, 0, 0));
      progressLabel.setText("Progress");

      loadBar.setBackground(new java.awt.Color(204, 204, 204));
      loadBar.setForeground(new java.awt.Color(102, 204, 0));

      generateTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      generateTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
      generateTitleLabel.setText("Generate Data");

      listTextArea.setBackground(new java.awt.Color(255, 255, 255));
      listTextArea.setColumns(20);
      listTextArea.setForeground(new java.awt.Color(0, 0, 0));
      listTextArea.setRows(5);
      listScrollPane.setViewportView(listTextArea);

      timeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      timeLabel.setForeground(new java.awt.Color(0, 0, 0));
      timeLabel.setText("Time");

      downloadButton.setBackground(new java.awt.Color(51, 153, 255));
      downloadButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      downloadButton.setForeground(new java.awt.Color(255, 255, 255));
      downloadButton.setText("Download Configurations");
      downloadButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      downloadButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            downloadButtonActionPerformed();
         }
      });

      downloadField.setBackground(new java.awt.Color(255, 255, 255));
      downloadField.setForeground(new java.awt.Color(0, 0, 0));

      downloadLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      downloadLabel.setForeground(new java.awt.Color(0, 0, 0));
      downloadLabel.setText("Please enter a folder name:");

      downloadFolderButton.setBackground(new java.awt.Color(51, 153, 255));
      downloadFolderButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      downloadFolderButton.setForeground(new java.awt.Color(255, 255, 255));
      downloadFolderButton.setText("Select Folder");
      downloadFolderButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
      downloadFolderButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            downloadFolderButtonActionPerformed();
         }
      });

      javax.swing.GroupLayout generateLayout = new javax.swing.GroupLayout(generate);
      generate.setLayout(generateLayout);
      generateLayout.setHorizontalGroup(
            generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(generateLayout.createSequentialGroup()
                        .addGroup(generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(generateLayout.createSequentialGroup()
                                    .addGap(289, 289, 289)
                                    .addGroup(generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                                          .addGroup(generateLayout.createSequentialGroup()
                                                .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addGroup(generateLayout.createSequentialGroup()
                                                            .addGap(18, 18, 18)
                                                            .addComponent(downloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                      .addGroup(generateLayout.createSequentialGroup()
                                                            .addGap(72, 72, 72)
                                                            .addGroup(generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                  .addComponent(downloadFolderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                  .addComponent(downloadField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generateLayout.createSequentialGroup()
                                                            .addGap(55, 55, 55)
                                                            .addComponent(downloadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                              .addGroup(generateLayout.createSequentialGroup()
                                    .addGroup(generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(generateLayout.createSequentialGroup()
                                                .addGap(321, 321, 321)
                                                .addGroup(generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                      .addComponent(loadBar, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(progressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                          .addGroup(generateLayout.createSequentialGroup()
                                                .addGap(406, 406, 406)
                                                .addComponent(genButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGroup(generateLayout.createSequentialGroup()
                                                .addGap(392, 392, 392)
                                                .addComponent(generateTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      generateLayout.setVerticalGroup(
            generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(generateLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(generateTitleLabel)
                        .addGap(49, 49, 49)
                        .addComponent(infoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(generateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGroup(generateLayout.createSequentialGroup()
                                    .addComponent(downloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(downloadLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(downloadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(downloadFolderButton)))
                        .addGap(28, 28, 28)
                        .addComponent(genButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(loadBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(progressLabel)
                        .addGap(18, 18, 18)
                        .addComponent(timeLabel)
                        .addContainerGap(98, Short.MAX_VALUE))
      );

      main.add(generate, "card3");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addComponent(sideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE))
      );
      layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(sideBar, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                  .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );

      pack();
      setLocationRelativeTo(null);
   }// </editor-fold>


   // dialog: Method that displays a dialog box showing a given text with a given title name
   private void dialog(String text, String name){
      /*
       * Below code was adapted from this tutorial: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
       */
      JOptionPane.showMessageDialog(main, text, name, JOptionPane.PLAIN_MESSAGE);
      // End of code reference
   }

   // display: Method that displays a given panel
   private void display(JPanel panel){
      /*
       * Below code was adapted from this tutorial (code shown at 4:16): https://www.youtube.com/watch?v=L-UPOw1nHCI
       */
      main.removeAll();
      main.add(panel);
      main.repaint();
      main.revalidate();
      // End of code reference
   }


   // insert: Method that inserts a given data array into a given table model
   private void insert(DefaultTableModel model, Object[] data){
      /*
       * Below code was adapted from this tutorial (code shown at 3:54): https://www.youtube.com/watch?v=F0Zq2fAUpXg
       */
      model.insertRow(model.getRowCount(), data);
      // End of code reference
   }

   // delete: Method that deletes a row from a given table and model
   private void delete(DefaultTableModel model, JTable table){
      /*
       * Below code was adapted from this tutorial (code shown at 2:23): https://www.youtube.com/watch?v=Tg62AxNRir4
       */
      if (table.getSelectedRowCount() == 1) {
         model.removeRow(table.getSelectedRow());
         // End of code reference
         if (table.equals(inputTable)){
            inOrderBox.removeItem(orderCount + "");
            orderCount--;
         }
      }
   }

   // clear: Method that clears a given table model
   private void clear(DefaultTableModel model){
      /*
       * Below code was adapted from the first answer in this website: https://stackoverflow.com/questions/6232355/deleting-all-the-rows-in-a-jtable
       */
      model.setRowCount(0);
      // End of code reference
   }

   // setBox: Method that sets a given dropdown box to a given column number from a given table
   private void setBox(JTable table, int col, JComboBox<String> box){
      /*
       * Below code was adapted from this tutorial (code shown at 3:50): https://www.youtube.com/watch?v=Ab35TXpVSmo
       */
      table.getColumnModel().getColumn(col).setCellEditor(new DefaultCellEditor(box));
      // End of code reference
   }

   // End of UI methods

   /*
    * homeButtonActionPerformed: Method when clicking on the Home button on the sidebar
    */
   private void homeButtonActionPerformed() {
      display(homePage);
   }

   // The following set of methods are for the Home page

   /*
    * loadButtonActionPerformed: Method when clicking on the Load Configurations button
    */
   private void loadButtonActionPerformed() {
      // Sets the load related components visible
      loadField.setVisible(true);
      loadFolderButton.setVisible(true);
      loadLabel.setVisible(true);
   }

   /*
    * loadFolderButtonActionPerformed: Method when clicking on the Select Folder button
    */
   private void loadFolderButtonActionPerformed() {
      // Searches for the folder that the user selected
      loadMessageLabel.setVisible(true);
      try{
         File file = new File("config/" + loadField.getText());
         if (file.exists()){
            load();
         }
         else
            loadMessageLabel.setText("Folder not found in config");
      }
      catch (Exception e){
         loadMessageLabel.setText("Folder name is not in a correct format");
      }
   }


   // load: Method for loading configurations
   private void load(){
      // Creates temporary data tables and clears all current UI tables
      Table<Integer, Integer, String> tempInput = TreeBasedTable.create();
      Table<Integer, Integer, String> tempProcess = TreeBasedTable.create();
      Table<Integer, Integer, String> tempDesc = TreeBasedTable.create();
      Table<Integer, Integer, String> tempState = TreeBasedTable.create();
      Table<Integer, Integer, String> tempOutput = TreeBasedTable.create();
      Table<Integer, Integer, String> tempLab = TreeBasedTable.create();
      clear(processDescModel);
      clear(inModel);
      orderCount = 0;
      clear(stateModel);
      clear(valModel);
      valModel.setColumnCount(1);
      valCount = 1;
      clear(outModel);
      clear(labModel);

      try{
         // Temporary data tables read the config data to make adding the data in UI tables easier
         read(tempInput, "input");
         read(tempProcess, "process");
         read(tempDesc, "processDesc");
         read(tempState, "state");
         read(tempOutput, "output");
         read(tempLab, "labOutputs");
         loadMessageLabel.setText("Configs found");
         valPrinted = true;
         loadStatusLabel.setVisible(true);
         loadStatusLabel.setText("Preparing data, please wait...");
         // Process variables are written to the fields
         dateField.setText(tempProcess.get(1, 2));
         // A few fields required multiple parsing for the correct format
         processField.setText(String.valueOf((int) Double.parseDouble(tempProcess.get(2, 2))));
         qcsField.setText(String.valueOf((int) Double.parseDouble(tempProcess.get(3, 2))));
         labField.setText(String.valueOf((int) Double.parseDouble(tempProcess.get(4, 2))));
         pulpField.setText(String.valueOf((int) Double.parseDouble(tempProcess.get(5, 2))));
         settleField.setText(String.valueOf((int) Double.parseDouble(tempProcess.get(6, 2))));
         ucField.setText(String.valueOf((int) Double.parseDouble(tempProcess.get(7, 2))));
         trimField.setText(tempProcess.get(8, 2));
         drawField.setText(tempProcess.get(9, 2));

         // Data is placed into UI tables and dropdown boxes
         for (int i = 2; i <= tempDesc.columnKeySet().size(); i++){
            insert(processDescModel, new Object[] {tempDesc.get(1, i), tempDesc.get(2, i), tempDesc.get(3, i), tempDesc.get(4, i)});
         }
         for (int i = 2; i <= tempInput.columnKeySet().size(); i++){
            orderCount++;
            inOrderBox.addItem(orderCount + "");
            insert(inModel, new Object[] {tempInput.get(1, i), tempInput.get(2, i),
                  tempInput.get(6, i), tempInput.get(7, i), tempInput.get(8, i), tempInput.get(9, i), tempInput.get(10, i),
                  tempInput.get(11, i), tempInput.get(12, i)});

         }
         if (!(tempInput.get(13, 1) == null)){
            for (int i = 2; i <= tempInput.columnKeySet().size(); i++){
               valModel.addColumn(tempInput.get(1, i));
            }
            for (int r = 13; r <= tempInput.rowKeySet().size(); r++){
               Object[] list = new Object [valModel.getColumnCount()];
               list[0] = valCount;
               for (int i = 1; i < tempInput.columnKeySet().size(); i++){
                  list[i] = "";
               }
               insert(valModel, list);
               valCount ++;
            }
            for (int r = 13; r <= tempInput.rowKeySet().size(); r++){
               for (int i = 2; i <= tempInput.columnKeySet().size(); i++){
                  if (tempInput.get(r, i) == null)
                     tempInput.put(r, i, "");
                  valModel.setValueAt(tempInput.get(r, i), r-13, i-1);
               }
            }
         }
         for (int i = 2; i <= tempState.columnKeySet().size(); i++){
            insert(stateModel, new Object[] {tempState.get(1, i), tempState.get(2, i),
                  tempState.get(6, i), tempState.get(7, i), tempState.get(8, i)});
         }
         for (int i = 2; i <= tempOutput.columnKeySet().size(); i++){
            insert(outModel, new Object[] {tempOutput.get(1, i), tempOutput.get(2, i), tempOutput.get(3, i), tempOutput.get(4, i)
                  , tempOutput.get(5, i)});
         }
         output = tempOutput;
         // Lab output tables require specific preparation
         for (int i = 2; i <= output.columnKeySet().size(); i++){
            Table<Integer, Integer, String> t = TreeBasedTable.create();
            labOutputs.put(output.get(1, i), t);
         }
         for (String key : labOutputs.keySet()) {
            Table<Integer, Integer, String> table = labOutputs.get(key);
            table.put(1, 2, "Weight");
            table.put(1, 3, "Asymptote");
            table.put(1, 4, "Order");
            table.put(1, 5, "Slope");
            table.put(1, 6, "Gainmodel");
            table.put(1, 7, "Direction");
            table.put(1, 8, "Gainshape");
         }
         // Blank values are preferred to null values to prevent errors
         for (int r = 11; r <= tempLab.rowKeySet().size(); r++){
            for (int i = 1; i <= tempLab.columnKeySet().size(); i++){
               if (tempLab.get(r, i) == null)
                  tempLab.put(r, i, "");
            }
         }
         // Following block of code reads the data table for 'labOutputs.csv', which is not in the final format
         int name = 1;
         boolean append = false;
         boolean error = false;
         for (int i = name + 1; i <= tempLab.rowKeySet().size(); i++){
            if (append){
               i++;
               append = false;
            }
            if (tempLab.get(i, 1).equals("") || tempLab.get(i, 1).equals("\"\"")){
               name = i + 1;
               append = true;
               continue;
            }
            String key = tempLab.get(name, 1);
            String slope = tempLab.get(i, 5);
            String asymptote = tempLab.get(i, 3);
            String weight = tempLab.get(i, 2);
            // Some validations are applied here
            if (weight.isEmpty()){
               loadStatusLabel.setText("Error in labOutputs.csv: Weight cannot be empty");
               error = true;
               break;
            }
            else if (Doubles.tryParse(weight) == null || (!asymptote.isEmpty() && Doubles.tryParse(asymptote) == null) ||
                  (!slope.isEmpty() && Doubles.tryParse(slope) == null)){
               loadStatusLabel.setText("Error in labOutputs.csv: Asymptote and slope values must be a number");
               error = true;
               break;
            }
            else{
               insert(labModel, new Object[] {key, tempLab.get(i, 1),
                     weight, asymptote, tempLab.get(i, 4), slope,
                     tempLab.get(i, 6), tempLab.get(i, 7), tempLab.get(i, 8)});
            }
         }
         if (!error){
            loadStatusLabel.setText("Data prepared, please press submit on each page before generating");
            loaded = true;
         }

      }
      catch (Exception e){
         dialog("Error: " + e, "Load Error");
         e.printStackTrace();
      }
   }

   // read: Method for reading a CSV file into a data table given the data table and file name
   private void read(Table<Integer, Integer, String> table, String name){
      try {
         /*
          * Below code was adapted from the first answer in this website: https://stackoverflow.com/questions/42170837/how-to-read-a-csv-file-into-an-array-list-in-java
          */
         File file = new File("config/" + loadField.getText() + "/" + name + ".csv");
         List<String[]> csv = new ArrayList<>();
         BufferedReader br = new BufferedReader(new FileReader(file));
         String line;
         while ((line = br.readLine()) != null) {
            csv.add(line.split(","));
         }
         // End of code reference
         br.close();
         int r = 0;
         int c = 0;
         for (String[] row : csv) {
            r++;
            for (String value : row) {
               c++;
               // Without the toString() call, the value would not be correct
               table.put(r, c, value.toString());
            }
            c = 0;
         }
      } catch (Exception e) {
         loadMessageLabel.setText("Error, ensure a file named " + name +  ".csv exists in the folder");
         e.printStackTrace();
      }
   }

   /*
    * processButtonActionPerformed: Method when clicking on the Process Variables button on the sidebar
    */
   private void processButtonActionPerformed() {
      display(processConfig);
   }

   // checkDate: Method that checks if a given date is in the correct format that the dataset would operate on
   private boolean checkDate(String date){
      try {
         // The following two lines of code was adapted from this tutorial: https://howtodoinjava.com/java/date-time/java-localdatetime-class/
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
         LocalDate.parse(date, formatter);
         return true;
      }
      catch (Exception e){
         return false;
      }
   }

   /*
    * submitProcessButtonActionPerformed: Method when clicking on the Submit button on the Process Configuration
    */
   private void submitProcessButtonActionPerformed() {
      // Prepaes variables to be validated
      String date = dateField.getText().trim();
      String draw = drawField.getText().trim();
      String lab = labField.getText().trim();
      String processPeriod = processField.getText().trim();
      String pulp = pulpField.getText().trim();
      String qcs = qcsField.getText().trim();
      String trim = trimField.getText().trim();
      String uc = ucField.getText().trim();
      String settle = settleField.getText().trim();
      processMessageLabel.setVisible(true);

      // Applies validation checks
      if (date.isEmpty() || draw.isEmpty() || lab.isEmpty() || processPeriod.isEmpty() || pulp.isEmpty() || qcs.isEmpty() ||
            trim.isEmpty() || uc.isEmpty()){
         processMessageLabel.setText("Fields cannot be empty");
      }
      else if (Ints.tryParse(lab) == null || Ints.tryParse(processPeriod) == null || Ints.tryParse(pulp) == null
            || Ints.tryParse(qcs) == null || Ints.tryParse(uc) == null || Ints.tryParse(settle) == null
            || Doubles.tryParse(trim) == null || Doubles.tryParse(draw) == null)
         processMessageLabel.setText("Values fields must be a number, only trim and draw can be a decimal");
      else if (Integer.parseInt(qcs) % Integer.parseInt(processPeriod) != 0){
         processMessageLabel.setText("QCS period is not a multiple of the Process period");
      }
      else if (Integer.parseInt(pulp) % Integer.parseInt(processPeriod) != 0){
         processMessageLabel.setText("Pulpeye period is not a multiple of the Process period");
      }
      else if (Integer.parseInt(lab) % Integer.parseInt(processPeriod) != 0){
         processMessageLabel.setText("Lab period is not a multiple of the Process period");
      }
      else if (!checkDate(date)){
         processMessageLabel.setText("Start date is in an incorrect format");
      }
      else{
         // Values are placed into the final HashMap
         processVariables.put("Process", (double) 0);
         startDate = date;
         if (!processVariables.isEmpty() && !processVariables.get("Process").equals(process))
            processSet = false;
         processVariables.clear();
         processVariables.put("Process", Double.parseDouble(processPeriod));
         processVariables.put("QCS", Double.parseDouble(qcs));
         processVariables.put("Lab", Double.parseDouble(lab));
         processVariables.put("Pulpeye", Double.parseDouble(pulp));
         processVariables.put("Uncoupled", Double.parseDouble(uc));
         processVariables.put("Trim", Double.parseDouble(trim));
         processVariables.put("Draw", Double.parseDouble(draw));
         processVariables.put("Settle", Double.parseDouble(settle));
         processVariables.put("Coupled", (double) 0);
         processMessageLabel.setText("Values submitted");
         process = processVariables.get("Process");
      }
   }

   // The following set of methods are for validation checks of JTable data

   // checkDuplicates: Method for checking if a table has any duplicate names
   private boolean checkDuplicates(DefaultTableModel table){
      List<String> list = new ArrayList<>();
      for (int i = 0; i < table.getRowCount(); i++) {
         list.add(table.getValueAt(i,0).toString());
      }
      Set<String> set = new HashSet<>(list);
      return set.size() == list.size();
   }

   // checkEmpty: Method for checking if a table has empty values
   private boolean checkEmpty(DefaultTableModel table){
      for (int i = 0; i < table.getRowCount(); i++) {
         for (int j = 0; j < table.getColumnCount(); j++) {
            String value = table.getValueAt(i,j).toString();
            if (value.trim().isEmpty())
               return false;
         }
      }
      return true;
   }

   // checkRange: Method for checking if a given table model has the given max value larger than the given min value
   private boolean checkRange(DefaultTableModel model, int max, int min){
      for (int i = 0; i < model.getRowCount(); i++){
         if (Double.parseDouble(model.getValueAt(i, max).toString().trim()) < Double.parseDouble(model.getValueAt(i, min).toString().trim())){
            return false;
         }
      }
      return true;
   }

   // checkDouble: Method for checking if a table has values that can be converted into doubles
   private boolean checkDouble(DefaultTableModel table){
      int count = 2;
      // Specific conditions depend on the table type
      if (table.equals(processDescModel) || table.equals(valModel))
         count = 1;
      for (int i = 0; i < table.getRowCount(); i++) {
         for (int j = count; j < table.getColumnCount(); j++) {
            String value = table.getValueAt(i,j).toString().trim();
            if (table.equals(valModel) && table.getValueAt(i,j).toString().isEmpty())
               continue;
            else if(Doubles.tryParse(value) == null)
               return false;
            else if(table.equals(processDescModel) && (j == 1 || j == 2 || j == 3) && ((Double.parseDouble(value) * 10) % 1) != 0)
               return false;
         }
      }
      return true;
   }

   /*
    * descButtonActionPerformed: Method when clicking on the Process Area Descriptions button on the sidebar
    */
   private void descButtonActionPerformed() {
      if (process == null)
         dialog("Please fill out the process configuration", "Input Error");
      else{
         display(processDescConfig);
      }
   }

   // The following set of methods are for the Process Area Description page

   /*
    * processDescClearButtonActionPerformed: Method when clicking on the Clear table button
    */
   private void processDescClearButtonActionPerformed() {
      clear(processDescModel);
   }

   /*
    * processDescDeleteButtonActionPerformed: Method when clicking on the Delete row button
    */
   private void processDescDeleteButtonActionPerformed() {
      delete(processDescModel, processDescTable);
   }

   /*
    * processDescAddButtonActionPerformed: Method when clicking on the Add row button
    */
   private void processDescAddButtonActionPerformed() {
      insert(processDescModel, new Object[] {descNameField.getText(), deadtimeReelField.getText(), lag1Field.getText(), lag2Field.getText()});
   }

   /*
    * processDescSubmitButtonActionPerformed: Method when clicking on the Submit button
    */
   private void processDescSubmitButtonActionPerformed() {
      processDescMessageLabel.setVisible(true);
      int row = processDescModel.getRowCount();
      boolean multiple = true;
      // The deadtime column values (converted into hours) must be a multiple of the process period
      try{
         for (int i = 0; i < row; i++){
            if (((double) processDescModel.getValueAt(i, 1) * 60) % process != 0){
               multiple = false;
               break;
            }
         }
      }
      catch(Exception e){
         processDescMessageLabel.setText("Value fields must be a number");
      }
      // Applies validation checks
      if (!checkEmpty(processDescModel)){
         processDescMessageLabel.setText("Fields cannot be empty");
      }
      else if (!checkDouble(processDescModel)){
         processDescMessageLabel.setText("Value fields must be a number with maximum one decimal place");
      }
      else if (!multiple){
         processDescMessageLabel.setText("Deadtime Reel (* 60) must be a multiple of the Process period");
      }
      else if (!checkDuplicates(processDescModel)){
         processDescMessageLabel.setText("Names can't have duplicates");
      }
      else if (processDescModel.getRowCount() == 0){
         processDescMessageLabel.setText("No entries added");
      }
      else{
         desc.clear();
         for (int i = 0; i < row; i++){
            desc.put(i+1, "Name", processDescModel.getValueAt(i, 0).toString().trim());
            desc.put(i+1, "Deadtime Reel", processDescModel.getValueAt(i, 1).toString().trim());
            desc.put(i+1, "Lag 1", processDescModel.getValueAt(i, 2).toString().trim());
            desc.put(i+1, "Lag 2",  processDescModel.getValueAt(i, 3).toString().trim());
         }
         processDescMessageLabel.setText("Submitted successfully");
         processSet = true;
         processDescSet = false;
      }
   }

   /*
    * inputButtonActionPerformed: Method when clicking on the Input Variables button on the sidebar
    */
   private void inputButtonActionPerformed() {
      if (desc.isEmpty()){
         dialog("Please fill out the previous process area descriptions", "Input Error");
      }
      else{
         display(inputConfig);
         // The page cannot be printed twice to prevent dropdown boxes from duplicating values
         if (!inPrinted){
            setInputs();
            inPrinted = true;
         }
         else if (!processDescSet){
            descBox.removeAllItems();
            inTableBox.removeAllItems();
            setInputs();
         }
      }
   }

   /*
    * setInputs: Method when that prepares the dropdown boxes in Input Configuration
    */
   private void setInputs(){
      for (int i = 1; i <= desc.rowKeySet().size(); i ++){
         descBox.addItem(desc.get(i, "Name"));
      }
      for (int i = 1; i <= desc.rowKeySet().size(); i ++){
         inTableBox.addItem(desc.get(i, "Name"));
      }
      setBox(inputTable, 1, inTableBox);
      setBox(inputTable, 8, inOrderBox);
   }

   // The following set of methods are for the Input Configuration page

   /*
    * inputAddButtonActionPerformed: Method when clicking on the Add row button
    */
   private void inputAddButtonActionPerformed() {
      orderCount++;
      inOrderBox.addItem(orderCount + "");
      insert(inModel, new Object[] {inputNameField.getText(), descBox.getSelectedItem().toString(),
            inputNoiseField.getText(), inputMvLagField.getText(), inputMaxField.getText(), inputMinField.getText(),
            sinePeriodField.getText(), sineAmpField.getText(),inOrderBox.getItemAt(orderCount-1)});
   }

   /*
    * inputDeleteButtonActionPerformed: Method when clicking on the Delete row button
    */
   private void inputDeleteButtonActionPerformed() {
      delete(inModel, inputTable);
   }

   /*
    * inputClearButtonActionPerformed: Method when clicking on the Clear table button
    */
   private void inputClearButtonActionPerformed() {
      inOrderBox.removeAllItems();
      clear(inModel);
      orderCount = 0;
   }

   /*
    * inputSubmitButtonActionPerformed: Method when clicking on the Submit button
    */
   private void inputSubmitButtonActionPerformed() {
      inputMessageLabel.setForeground(Color.black);
      int row = inModel.getRowCount();
      List<Integer> list = new ArrayList<>();
      // The order values must be ascending
      for (int i = 0; i < row; i++){
         list.add(Integer.parseInt(inModel.getValueAt(i, 8).toString().trim()));
      }
      boolean ordered = true;
      /*
       * Below code was adapted from this website: https://www.freecodecamp.org/news/how-to-sort-a-list-in-java/
       */
      Collections.sort(list);
      // End of code reference
      for (int i = 0; i < list.size() - 1; i ++){
         if (list.get(i) != (list.get(i + 1) - 1)){
            ordered = false;
            break;
         }
      }
      boolean sine = true;
      try{
         // The sine period must be larger than the process period
         for (int i = 0; i < row; i++){
            if (Double.parseDouble(inModel.getValueAt(i, 6).toString().trim()) < process){
               sine = false;
               break;
            }
         }
      }
      catch(Exception e){
         inputMessageLabel.setText("Fields cannot be empty and values must be a number");
      }
      // Applies validation checks
      if (!checkEmpty(inModel)){
         inputMessageLabel.setText("Fields cannot be empty");
      }
      else if (!checkDouble(inModel)){
         inputMessageLabel.setText("Value fields must be a number");
      }
      else if (inModel.getRowCount() == 0){
         inputMessageLabel.setText("No entries added");
      }
      else if (!ordered){
         inputMessageLabel.setText("Order values must be uniquely ordered");
      }
      else if (!checkRange(inModel, 4, 5)){
         inputMessageLabel.setText("Max values must be larger than Min values");
      }
      else if (!sine){
         inputMessageLabel.setText("Sin Period must be larger than Process Period");
      }
      else if (!checkDuplicates(inModel)){
         inputMessageLabel.setText("Names must be unique");
      }
      else{
         input.clear();
         for (int i = 0; i < row; i++){
            input.put(1, i+2, inModel.getValueAt(i, 0).toString().trim());
            input.put(2, i+2, inModel.getValueAt(i, 1).toString().trim());
            // The process area descriptions are used to fill out the next three rows
            for (int j = 1; j <= desc.rowKeySet().size(); j++){
               if (input.get(2, i+2).equals(desc.get(j, "Name"))){
                  input.put(3, i+2, desc.get(j, "Deadtime Reel"));
                  input.put(4, i+2, desc.get(j, "Lag 1"));
                  input.put(5, i+2, desc.get(j, "Lag 2"));
               }
            }
            input.put(6, i+2, inModel.getValueAt(i, 2).toString().trim());
            input.put(7, i+2, inModel.getValueAt(i, 3).toString().trim());
            input.put(8, i+2, inModel.getValueAt(i, 4).toString().trim());
            input.put(9, i+2, inModel.getValueAt(i, 5).toString().trim());
            input.put(10, i+2, inModel.getValueAt(i, 6).toString().trim());
            input.put(11, i+2, inModel.getValueAt(i, 7).toString().trim());
            input.put(12, i+2, inModel.getValueAt(i, 8).toString().trim());
         }
         input.put(1, 1, "Inputs");
         input.put(2, 1, "Description");
         input.put(3, 1, "Deadtime Reel");
         input.put(4, 1, "Lag1 Reel");
         input.put(5, 1, "Lag2 Reel");
         input.put(6, 1, "Noise");
         input.put(7, 1, "MV Lag");
         input.put(8, 1, "Max");
         input.put(9, 1, "Min");
         input.put(10, 1, "Sin Period");
         input.put(11, 1, "Sin Amplitude");
         input.put(12, 1, "Order");
         inputMessageLabel.setText("Submitted successfully");
         inputMessageLabel.setVisible(true);
         inputSet = false;
      }
   }


   /*
    * valButtonActionPerformed: Method when clicking on the Input Validations button on the sidebar
    */
   private void valButtonActionPerformed() {
      if (input.isEmpty())
         dialog("Please fill out the previous input configuration", "Validation Error");
      else{
         display(valConfig);
         /*
          * Below line of code was adapted from the first answer in this website: https://stackoverflow.com/questions/953972/java-jtable-setting-column-width
          */
         valTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         // End of code reference
         // Columns are created based on the input configuration
         if (!valPrinted){
            for (int i = 2; i < input.columnKeySet().size() + 1; i++){
               valModel.addColumn(input.get(1, i));
            }
            valPrinted = true;
         }
      }
   }

   // The following set of methods are for the Input Validation page

   /*
    * processDescAddButtonActionPerformed: Method when clicking on the Add row button
    */
   private void valAddButtonActionPerformed() {
      // Adds empty values per row
      Object[] list = new Object [valModel.getColumnCount()];
      list[0] = valCount;
      for (int i = 1; i < input.columnKeySet().size(); i++){
         list[i] = "";
      }
      insert(valModel, list);
      valCount ++;
   }

   /*
    * valDeleteButtonActionPerformed: Method when clicking on the Delete row button
    */
   private void valDeleteButtonActionPerformed() {
      int current = valTable.getSelectedRow();
      int row = (int) valModel.getValueAt(current, 0);
      // Validation row numbers below must be adjusted
      for (int i = current + 1; i < valModel.getRowCount(); i++){
         valModel.setValueAt(row, i, 0);
         row++;
      }
      valModel.removeRow(current);
      valCount = valModel.getRowCount() + 1;
   }

   /*
    * valClearButtonActionPerformed: Method when clicking on the Clear table button
    */
   private void valClearButtonActionPerformed() {
      clear(valModel);
      valCount = 1;
   }

   /*
    * valSubmitButtonActionPerformed: Method when clicking on the Submit button
    */
   private void valSubmitButtonActionPerformed() {
      valMessageLabel.setForeground(Color.black);
      // Only validation check is to see if values can be parsed as doubles
      if (!checkDouble(valModel)){
         valMessageLabel.setText("Value fields must be a number");
      }
      else{
         int rowNumber = valModel.getRowCount();
         processVariables.put("Coupled", (double) rowNumber);
         // Validations are placed just below input configurations
         for (int i = 13; i < 13 + rowNumber; i++){
            input.row(i).clear();
            for (int j = 1; j <= valModel.getColumnCount(); j++){
               input.put(i, j, valModel.getValueAt(i-13, j-1).toString().trim());
            }
         }
         valMessageLabel.setText("Submitted successfully");
      }
   }

   /*
    * stateButtonActionPerformed: Method when clicking on the State Variables button on the sidebar
    */
   private void stateButtonActionPerformed() {
      if (desc.isEmpty()){
         dialog("Please fill out the process area descriptions", "State Error");
      }
      else{
         display(stateConfig);
         // The page cannot be printed twice to prevent dropdown boxes from duplicating values
         if (!statePrinted){
            setState();
            statePrinted = true;
         }
         else if (!processDescSet){
            stateDescBox.removeAllItems();
            stateTableBox.removeAllItems();
            setState();
         }
      }
   }

   /*
    * setState: Method when that prepares the dropdown boxes in State Configuration
    */
   public void setState(){
      for (int i = 1; i < desc.rowKeySet().size() + 1; i ++){
         stateDescBox.addItem(desc.get(i, "Name"));
         stateTableBox.addItem(desc.get(i, "Name"));
      }
      setBox(stateTable, 1, stateTableBox);
   }

   // The following set of methods are for the State Configuration page

   /*
    * stateAddButtonActionPerformed: Method when clicking on the Add row button
    */
   private void stateAddButtonActionPerformed() {
      insert(stateModel, new Object[] {stateNameField.getText(), stateDescBox.getSelectedItem().toString(),
            stateNoiseField.getText(), stateMaxField.getText(), stateMinField.getText()});
   }

   /*
    * stateClearButtonActionPerformed: Method when clicking on the Clear table button
    */
   private void stateClearButtonActionPerformed() {
      clear(stateModel);
   }

   /*
    * stateDeleteButtonActionPerformed: Method when clicking on the Delete row button
    */
   private void stateDeleteButtonActionPerformed() {
      delete(stateModel, stateTable);
   }

   /*
    * stateSubmitButtonActionPerformed: Method when clicking on the Submit button
    */
   private void stateSubmitButtonActionPerformed() {
      stateMessageLabel.setVisible(true);
      // Applies validation checks
      if (!checkEmpty(stateModel)){
         stateMessageLabel.setText("Fields cannot be empty");
      }
      else if (!checkDouble(stateModel)){
         stateMessageLabel.setText("Value fields must be a number");
      }
      else if (stateModel.getRowCount() == 0){
         stateMessageLabel.setText("No entries added");
      }
      else if (!checkDuplicates(stateModel)){
         stateMessageLabel.setText("Names must be unique");
      }
      else if (!checkRange(stateModel, 3, 4)){
         stateMessageLabel.setText("Max values must be larger than Min values");
      }
      else{
         state.clear();
         for (int i = 0; i < stateModel.getRowCount(); i++){
            state.put(1, i+2, stateModel.getValueAt(i, 0).toString().trim());
            state.put(2, i+2, stateModel.getValueAt(i, 1).toString().trim());
            for (int j = 1; j <= desc.rowKeySet().size(); j++){
               if (state.get(2, i+2).equals(desc.get(j, "Name"))){
                  state.put(3, i+2, desc.get(j, "Deadtime Reel"));
                  state.put(4, i+2, desc.get(j, "Lag 1"));
                  state.put(5, i+2, desc.get(j, "Lag 2"));
               }
            }
            state.put(6, i+2, stateModel.getValueAt(i, 2).toString().trim());
            state.put(7, i+2, stateModel.getValueAt(i, 3).toString().trim());
            state.put(8, i+2, stateModel.getValueAt(i, 4).toString().trim());
         }
         state.put(1, 1, "State");
         state.put(2, 1, "Description");
         state.put(3, 1, "Deadtime Reel (min)");
         state.put(4, 1, "Lag 1 Reel (min)");
         state.put(5, 1, "Lag 2 Reel(min)");
         state.put(6, 1, "Noise");
         state.put(7, 1, "Max");
         state.put(8, 1, "Min");
         stateMessageLabel.setText("Submitted successfully");
         stateSet = false;
         processDescSet = true;
      }
   }

   /*
    * outputButtonActionPerformed: Method when clicking on the Output Variables button on the sidebar
    */
   private void outputButtonActionPerformed() {
      // This page has no dependencies, so it can be displayed at any time
      display(outputConfig);
   }

   // The following set of methods are for the Output Configuration page

   /*
    * outputDeleteButtonActionPerformed: Method when clicking on the Delete row button
    */
   private void outputDeleteButtonActionPerformed() {
      delete(outModel, outputTable);
   }

   /*
    * outputClearButtonActionPerformed: Method when clicking on the Clear table button
    */
   private void outputClearButtonActionPerformed() {
      clear(outModel);
   }


   /*
    * outputAddButtonActionPerformed: Method when clicking on the Add row button
    */
   private void outputAddButtonActionPerformed() {
      insert(outModel, new Object[] {outputNameField.getText(), outputDescField.getText(), outputNoiseField.getText(), outputMaxField.getText(), outputMinField.getText()});
   }

   /*
    * outputSubmitButtonActionPerformed: Method when clicking on the Submit button
    */
   private void outputSubmitButtonActionPerformed() {
      outputMessageLabel.setVisible(true);
      // Applies validation checks
      if (!checkEmpty(outModel)){
         outputMessageLabel.setText("Fields cannot be empty");
      }
      else if (!checkDouble(outModel)){
         outputMessageLabel.setText("Value fields must be a number");
      }
      else if (outModel.getRowCount() == 0){
         outputMessageLabel.setText("No entries added");
      }
      else if (!checkDuplicates(outModel)){
         outputMessageLabel.setText("Names must be unique");
      }
      else if (!checkRange(outModel, 3, 4)){
         outputMessageLabel.setText("Max values must be larger than Min values");
      }
      else{
         if (!loaded){
            output.clear();
            for (int i = 0; i < outModel.getRowCount(); i++){
               output.put(1, i+2, outModel.getValueAt(i, 0).toString().trim());
               output.put(2, i+2, outModel.getValueAt(i, 1).toString().trim());
               output.put(3, i+2, outModel.getValueAt(i, 2).toString().trim());
               output.put(4, i+2, outModel.getValueAt(i, 3).toString().trim());
               output.put(5, i+2, outModel.getValueAt(i, 4).toString().trim());
            }
            setOutput();
         }
         else
            loaded = false;
         outputMessageLabel.setText("Submitted successfully");
         outputSet = false;
      }
   }

   /*
    * labButtonActionPerformed: Method when clicking on the Lab Variables button on the sidebar
    */
   private void labButtonActionPerformed() {
      if (input.isEmpty())
         dialog("Please fill out the input configurations", "Output Error");
      else if (state.isEmpty())
         dialog("Please fill out the state configurations", "Output Error");
      else if (output.isEmpty())
         dialog("Please fill out the previous output configurations", "Output Error");
      else{
         display(labConfig);
         // The page cannot be printed twice to prevent dropdown boxes from duplicating values
         if (!outPrinted){
            setOutputTable();
            setBox(labTable, 4, orderTableBox);
            setBox(labTable, 6, modelTableBox);
            setBox(labTable, 7, dirTableBox);
            setBox(labTable, 8, shapeTableBox);
            outPrinted = true;
         }
         if (!inputSet || !stateSet || !outputSet){
            outputBox.removeAllItems();
            outTableBox.removeAllItems();
            varBox.removeAllItems();
            varTableBox.removeAllItems();
            setOutputTable();
         }
      }
   }

   // setOutputTable: Method that prepares the dropdown boxes for Lab Configuration
   private void setOutputTable(){
      for (int i = 2; i <= output.columnKeySet().size(); i ++){
         outputBox.addItem(output.get(1, i));
         outTableBox.addItem(output.get(1, i));
      }
      for (int i = 2; i <= input.columnKeySet().size(); i ++){
         varBox.addItem(input.get(1, i));
         varTableBox.addItem(input.get(1, i));
      }
      for (int i = 2; i <= state.columnKeySet().size(); i ++){
         varBox.addItem(state.get(1, i));
         varTableBox.addItem(state.get(1, i));
      }
      setBox(labTable, 0, outTableBox);
      setBox(labTable, 1, varTableBox);
   }

   // The following set of methods are for the Lab Configuration page

   /*
    * modelInfoButtonActionPerformed: Method when clicking on question mark next to the Model field
    */
   private void modelInfoButtonActionPerformed() {
      dialog("<html>0 = Polynomial<br/>1 = Exponential<br/>2 = Sigmoid</html>", "Gainmodel Values");
   }

   /*
    * directionInfoButtonActionPerformed: Method when clicking on question mark next to the Direction field
    */
   private void directionInfoButtonActionPerformed() {
      dialog("<html>0 = Down<br/>1 = Up</html>", "Gaindirection Values");
   }

   /*
    * shapeInfoButtonActionPerformed: Method when clicking on question mark next to the Shape field
    */
   private void shapeInfoButtonActionPerformed() {
      dialog("<html>0 = Flatten<br/>1 = Swing<br/>2 = Asymptote</html>", "Gainshape Values");
   }

   /*
    * labAddButtonActionPerformed: Method when clicking on the Add row button
    */
   private void labAddButtonActionPerformed() {
      labMessageLabel.setForeground(Color.BLACK);
      String slope = labSlopeField.getText();
      String asymptote = labAsymptoteField.getText();
      String weight = labWeightField.getText();
      if (weight.isEmpty())
         labMessageLabel.setText("Weight cannot be empty");
      else if (Doubles.tryParse(weight) == null || (!asymptote.isEmpty() && Doubles.tryParse(asymptote) == null) ||
            (!slope.isEmpty() && Doubles.tryParse(slope) == null))
         labMessageLabel.setText("Values must be a number");
      else{
         insert(labModel, new Object[] {outputBox.getSelectedItem().toString(), varBox.getSelectedItem().toString(),
               labWeightField.getText(), asymptote, orderBox.getSelectedItem().toString(), slope,
               modelBox.getSelectedItem().toString(), directionBox.getSelectedItem().toString(), shapeBox.getSelectedItem().toString()});
      }
   }

   /*
    * labDeleteButtonActionPerformed: Method when clicking on the Delete row button
    */
   private void labDeleteButtonActionPerformed() {
      delete(labModel, labTable);
   }

   /*
    * labClearButtonActionPerformed: Method when clicking on the Clear table button
    */
   private void labClearButtonActionPerformed() {
      clear(labModel);
   }


   /*
    * labSubmitButtonActionPerformed: Method when clicking on the Submit button
    */
   private void labSubmitButtonActionPerformed() {
      labMessageLabel.setForeground(Color.BLACK);
      boolean ready = true;
      // The data tables are created
      for (String key : labOutputs.keySet()) {
         Table<Integer, Integer, String> table = labOutputs.get(key);
         table.clear();
         table.put(1, 2, "");
         table.put(1, 2, "Weight");
         table.put(1, 3, "Asymptote");
         table.put(1, 4, "Order");
         table.put(1, 5, "Slope");
         table.put(1, 6, "Gainmodel");
         table.put(1, 7, "Direction");
         table.put(1, 8, "Gainshape");
      }
      for (int i = 0; i < labModel.getRowCount(); i++){
         for (String key : labOutputs.keySet()) {
            if (key.equals(labModel.getValueAt(i, 0).toString())){
               Table<Integer, Integer, String> table = labOutputs.get(key);
               int row = table.rowKeySet().size() + 1;
               table.put(row, 1, labModel.getValueAt(i, 1).toString().trim());
               table.put(row, 2, labModel.getValueAt(i, 2).toString().trim());
               table.put(row, 3, labModel.getValueAt(i, 3).toString().trim());
               table.put(row, 4, labModel.getValueAt(i, 4).toString().trim());
               table.put(row, 5, labModel.getValueAt(i, 5).toString().trim());
               table.put(row, 6, labModel.getValueAt(i, 6).toString().trim());
               table.put(row, 7, labModel.getValueAt(i, 7).toString().trim());
               table.put(row, 8, labModel.getValueAt(i, 8).toString().trim());
            }
         }
      }
      // It is easier to do validations for each output name once they are in its own table
      for (String key : labOutputs.keySet()) {
         Table<Integer, Integer, String> table = labOutputs.get(key);
         if (table.get(2, 1) == null){
            labMessageLabel.setText(key + " has no variables");
            ready = false;
            break;
         }
         int totalWeight = 0;
         for (int i = 2; i <= table.rowKeySet().size(); i++){
            totalWeight = totalWeight + Integer.parseInt(table.get(i, 2));
         }
         if (totalWeight != 100){
            labMessageLabel.setText(key + " weight values don't add up to 100");
            ready = false;
         }
      }
      if (ready){
         labMessageLabel.setText("Submitted successfully, ready to generate data");
         outputSet = true;
         stateSet = true;
         inputSet = true;
      }
   }

   // setOutput: Method that prepares the output data table
   private void setOutput(){
      output.put(1, 1, "Outputs");
      output.put(2, 1, "Description");
      output.put(2, 1, "Noise");
      output.put(3, 1, "Max");
      output.put(4, 1, "Min");
      for (int i = 2; i <= output.columnKeySet().size(); i++){
         Table<Integer, Integer, String> t = TreeBasedTable.create();
         labOutputs.put(output.get(1, i), t);
      }
   }

   /*
    * genDataButtonActionPerformed: Method when clicking on the Generate Data button on the sidebar
    */
   private void genDataButtonActionPerformed() {
      if (input.isEmpty())
         dialog("Please fill out the input configurations", "Input Error");
      else if (state.isEmpty())
         dialog("Please fill out the state configurations", "State Error");
      else if (output.isEmpty())
         dialog("Please fill out the output configurations", "Output Error");
      else if (!processSet)
         dialog("Please fill out the process area descriptions and all configurations again since the process configurations have changed", "Input Error");
      else if (!processDescSet)
         dialog("Please fill out the input configurations again since the process area descriptions have changed", "Input Error");
      else if (!inputSet)
         dialog("Please fill out the lab configurations again since the input configurations have changed", "Output Error");
      else if (!stateSet)
         dialog("Please fill out the lab configurations again since the state configurations have changed", "Output Error");
      else if (!outputSet)
         dialog("Please fill out the lab configurations again since the output configurations have changed", "Output Error");
      else{
         display(generate);
         if (!generating){
            infoLabel.setText("Please ensure that all mandatory variables have been entered");
            // Hides some of the components that will be required later
            listTextArea.setVisible(false);
            progressLabel.setVisible(false);
            loadBar.setVisible(false);
            timeLabel.setVisible(false);
            downloadButton.setVisible(false);
            downloadField.setVisible(false);
            downloadLabel.setVisible(false);
            downloadFolderButton.setVisible(false);
         }
      }
   }

   // The following set of methods are for the Generate Data page

   /*
    * genButtonActionPerformed: Method when clicking on the Generate button
    */
   private void genButtonActionPerformed() {
      progressLabel.setVisible(false);
      loadBar.setVisible(false);
      List<String> list = new ArrayList<>();

      for (int i = 2; i <= input.columnKeySet().size(); i ++){
         list.add(input.get(1, i));
      }
      for (int i = 2; i <= state.columnKeySet().size(); i ++){
         list.add(state.get(1, i));
      }
      // The array below contains hard-coded variables in 'Generator.java' that must be present
      String[] variables = {"MV_SWFreeness", "MV_HWFreeness", "MV_OCCFreeness", "MV_HeadboxPressure", "MV_SliceOpening",
            "MV_MachineSpeed", "MV_SWSpecificEnergy", "MV_HWSpecificEnergy", "MV_OCCSpecificEnergy", "MV_SWPct", "MV_HWPct", "MV_OCCPct", "PulpEye_BlendFreeness", "PulpEye_BlendCrill",
            "QCS_BoneDryWeight", "QCS_BasisWeight", "QCS_Moisture", "QCS_Caliper", "MV_WireSpeed", "MV_SWFlow",
            "MV_HWFlow", "MV_OCCFlow", "MV_ThinStockConsistency", "MV_ThinStockFlow", "MV_PressLoad", "MV_SteamPressure",
            "MV_JettoWire", "PulpEye_SWCrill", "PulpEye_HWCrill", "PulpEye_OCCCrill"};
      listTextArea.setText("");
      listTextArea.setVisible(true);
      /*
       * Below code was adapted from the first answer in this website: https://stackoverflow.com/questions/20755432/how-to-check-if-an-arraylist-contain-2-values
       */
      for (String var : variables) {
         if (!list.contains(var)) {
            // End of code reference
            listTextArea.append(var + "\n");
         }
      }
      if (listTextArea.getText().isEmpty()){
         // Generation can start
         listTextArea.setVisible(false);
         genButton.setVisible(false);
         infoLabel.setText("Generating data");
         progressLabel.setVisible(true);
         loadBar.setValue(0);
         loadBar.setVisible(true);
         progressLabel.setText("Calculating input variables...");
         LocalTime start = LocalTime.now();
         generating = true;
         run(start);
         infoLabel.setText("");
      }
      else
         infoLabel.setText("The following variables are missing from inputs and state");
   }

   // run: Method that generates the dataset taking in the start time as a parameter
   private void run(LocalTime start){
      /*
       * Below code was adapted from the tutorial in this website: https://www.geeksforgeeks.org/swingworker-in-java/
       */
      {
         // Boolean allows for the doInBackground() status to be checked and String is the type of the data sent to the process() method
         SwingWorker<Boolean, String> sw = new SwingWorker<Boolean, String>() {

            // doInBackground: Method that performs a sequence of actions in a SwingWorker thread
            @Override
            protected Boolean doInBackground(){
               try{
                  Generator gen = new Generator(input, output, labOutputs, state, processVariables, startDate);
                  publish("Calculating input variables...");
                  gen.createInputs();
                  publish("Calculating state variables...");
                  gen.calcState();
                  publish("Calculating QCS variables...");
                  gen.calcQCS();
                  publish("Calculating output lab variables...");
                  gen.calcLab();
                  publish("Creating final dataset...");
                  gen.createDataset();
                  // Returns true so the get() method can be checked to see if it was successful
                 return true;
               }
               catch(Exception e){
                  progressLabel.setText("Error:" + e);
                  dialog("Error: " + e, "Generation Error");
                  e.printStackTrace();
                  return false;
               }
            }

            // process: Method that performs updates with a value passed on from the publish() method in doInBackground()
            @Override
            protected void process(List s) {
               progressLabel.setText(s.get(s.size() - 1).toString());
               loadBar.setValue(loadBar.getValue() + 20);
            }

            // done: Method that performs actions once doInBackground() completes
            @Override
            protected void done() {
               try {
                  // Only runs if successful
                  if (get()){
                     /*
                      * Below code was adapted from this tutorial (under Method 2 :- Using the LocalTime and ChronoUnit class): https://www.geeksforgeeks.org/java-program-to-calculate-difference-between-two-time-periods/
                      */
                     LocalTime finish = LocalTime.now();
                     long hours = ChronoUnit.HOURS.between(start, finish);
                     long minutes = ChronoUnit.MINUTES.between(start, finish) % 60;
                     long seconds = ChronoUnit.SECONDS.between(start, finish) % 60;
                     String time = hours + " hours " + minutes + " minutes " + seconds + " seconds";
                     // End of code reference (Time difference reference)
                     progressLabel.setText("Dataset generated in the /data folder");
                     timeLabel.setVisible(true);
                     timeLabel.setText("Time taken: " + time);
                     genButton.setVisible(true);
                     generating = false;
                     downloadButton.setVisible(true);
                  }
               } catch (InterruptedException | ExecutionException e) {
                  progressLabel.setText("Error: " + e);
                  dialog("Error: " + e, "Generation Error");
                  e.printStackTrace();
                  loadBar.setVisible(false);
                  genButton.setVisible(true);
                  downloadButton.setVisible(false);
               }
            }

         };
         sw.execute();
      }
      // End of code reference (SwingWorker tutorial reference)
   }


   /*
    * downloadButtonActionPerformed: Method when clicking on the Download Configurations button
    */
   private void downloadButtonActionPerformed() {
      // Components are only visible if the user wants to download
      downloadField.setVisible(true);
      downloadLabel.setVisible(true);
      downloadFolderButton.setVisible(true);
   }

   /*
    * downloadFolderButtonActionPerformed: Method when clicking on the Select Folder button
    */
   private void downloadFolderButtonActionPerformed() {
      // Creates the folder that the user entered
      try{
         File file = new File("config/" + downloadField.getText());
         if (!file.exists()){
            file.mkdir();
            download();
         }
         else
            infoLabel.setText("Folder already exists in config");
      }
      catch (Exception e){
         infoLabel.setText("Folder name is not in a correct format");
      }
   }

   // write: Method for writing to a CSV file given the data table and file name
   public void write(Table<Integer, Integer, String> table, String name){
      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter("config/" + downloadField.getText() +"/" + name + ".csv"));
         /*
          * Below code was adapted from the question in this website: https://stackoverflow.com/questions/38524942/guava-table-to-csv
          */
         CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
         printer.printRecords(table.rowMap().values().stream().map(x -> x.values()).collect(Collectors.toList()));
         // End of code reference
         printer.flush();
         writer.close();
      } catch (IOException e) {
         dialog("Error: " + e, "Download error");
         e.printStackTrace();
      }
   }

   // download: Method for downloading configurations
   private void download(){
      try{
         // Some tables can be directly translated
         write(input, "input");
         write(state, "state");
         write(output, "output");

         // Process area description data table has a different format in this class
         Table<Integer, Integer, String> processDesc = TreeBasedTable.create();
         processDesc.put(1, 1, "Description");
         processDesc.put(2, 1, "Deadtime Reel (min)");
         processDesc.put(3, 1, "Lag 1 Reel (min)");
         processDesc.put(4, 1, "Lag 2 Reel (min)");
         for (int j = 1; j < desc.rowKeySet().size() + 1; j++){
            processDesc.put(1, j+1, desc.get(j, "Name"));
            processDesc.put(2, j+1, desc.get(j, "Deadtime Reel"));
            processDesc.put(3, j+1, desc.get(j, "Lag 1"));
            processDesc.put(4, j+1, desc.get(j, "Lag 2"));
         }
         write(processDesc, "processDesc");

         Table<Integer, Integer, String> process = TreeBasedTable.create();
         process.put(1, 1, "Start Date");
         process.put(2, 1, "Process Period (sec)");
         process.put(3, 1, "QCS Period (sec)");
         process.put(4, 1, "Lab Period (sec)");
         process.put(5, 1, "PulpEye Period (sec)");
         process.put(6, 1, "Added Settle Time (sec)");
         process.put(7, 1, "Uncoupled Moves");
         process.put(8, 1, "Trim (ft)");
         process.put(9, 1, "Draw");
         process.put(1, 2, startDate);
         process.put(2, 2, processVariables.get("Process").toString());
         process.put(3, 2, processVariables.get("QCS").toString());
         process.put(4, 2, processVariables.get("Lab").toString());
         process.put(5, 2, processVariables.get("Pulpeye").toString());
         process.put(6, 2, processVariables.get("Settle").toString());
         process.put(7, 2, processVariables.get("Uncoupled").toString());
         process.put(8, 2, processVariables.get("Trim").toString());
         process.put(9, 2, processVariables.get("Draw").toString());
         write(process, "process");

         Table<Integer, Integer, String> lab = TreeBasedTable.create();
         lab.put(1, 2, "Weight");
         lab.put(1, 3, "Asymptote");
         lab.put(1, 4, "Order");
         lab.put(1, 5, "Slope");
         lab.put(1, 6, "Gainmodel");
         lab.put(1, 7, "Direction");
         lab.put(1, 8, "Gainshape");
         int row = 1;
         int current = 0;
         // The lab outputs have a specific format, the below loop goes through the three types of rows possible in 'labOutputs.csv'
         for (String key : labOutputs.keySet()){
            int labRow = 2;
            Table<Integer, Integer, String> table = labOutputs.get(key);
            // Output name row
            lab.put(row, 1, key);
            for (int j = 2; j <= 8; j++){
               lab.put(row, j, "");
            }
            // Data row
            for (int i = row + 1; i < row + table.rowKeySet().size(); i++){
               for (int j = 1; j <= 8; j++){
                  lab.put(i, j, table.get(labRow, j));
               }
               current = i + 1;
               labRow++;
            }
            // Empty row
            for (int j = 1; j <= 8; j++){
               lab.put(current, j, "");
            }
            row = current + 1;
         }
         write(lab, "labOutputs");
         infoLabel.setText("Successfully downloaded");
      }
      catch (Exception e){
         dialog("Error: " + e, "Load Error");
         e.printStackTrace();
      }
   }
}
package cn.hit.view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hpsf.Array;

import cn.hit.controller.Analyzer;
import cn.hit.controller.readDFATable;
import cn.hit.domain.DFATable;
import cn.hit.tool.FileStore;
import cn.hit.tool.StringToTable;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class OutPutJFrame extends JFrame {

  private JPanel contentPane;
  private FileStore fileStore;

  /**
   * Create the frame.
   */
  public OutPutJFrame(FileStore file) {
    this.fileStore = file;
    this.setTitle("´Ê·¨·ÖÎöÆ÷");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 200, 778, 476);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(112, 128, 144));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    final JPanel panel = new JPanel();
    panel.setBounds(20, 57, 723, 361);
    contentPane.add(panel);
    panel.setLayout(null);

    /*
     * ¶¥²¿°´Å¥¼¯ºÏ--------------------------------------------------------------
     */
    JPanel menuPanel = new JPanel();
    menuPanel.setBackground(new Color(112, 128, 144));
    menuPanel.setBounds(10, 10, 731, 30);
    contentPane.add(menuPanel);
    menuPanel.setLayout(null);

    JButton backButton = new JButton("\u8FD4\u56DE");
    backButton.setBounds(10, 0, 115, 30);
    backButton.setForeground(new Color(112, 128, 144));
    backButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
    backButton.setFocusPainted(false);
    backButton.setBackground(new Color(255, 255, 255));
    menuPanel.add(backButton);

    JButton testFileButton = new JButton("\u6D4B\u8BD5\u6837\u4F8B");
    testFileButton.setForeground(new Color(112, 128, 144));
    testFileButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
    testFileButton.setFocusPainted(false);
    testFileButton.setBackground(Color.WHITE);
    testFileButton.setBounds(155, 0, 115, 30);
    menuPanel.add(testFileButton);

    JButton DFAButton = new JButton("DFA\u8F6C\u6362\u8868");
    DFAButton.setForeground(new Color(112, 128, 144));
    DFAButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
    DFAButton.setFocusPainted(false);
    DFAButton.setBackground(Color.WHITE);
    DFAButton.setBounds(310, 0, 115, 30);
    menuPanel.add(DFAButton);

    JButton TokenButton = new JButton("Token\u5E8F\u5217");
    TokenButton.setForeground(new Color(112, 128, 144));
    TokenButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
    TokenButton.setFocusPainted(false);
    TokenButton.setBackground(Color.WHITE);
    TokenButton.setBounds(465, 0, 115, 30);
    menuPanel.add(TokenButton);

    JButton errorButton = new JButton("\u9519\u8BEF\u5217\u8868");
    errorButton.setForeground(new Color(112, 128, 144));
    errorButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
    errorButton.setFocusPainted(false);
    errorButton.setBackground(Color.WHITE);
    errorButton.setBounds(615, 0, 115, 30);
    menuPanel.add(errorButton);


    /*
     * ²âÊÔÓÃÀý----------------------------------------------------------------
     */
    final JPanel testPanel = new JPanel();
    testPanel.setBackground(new Color(255, 250, 250));
    testPanel.setBounds(0, 0, 723, 361);
    panel.add(testPanel);
    testPanel.setLayout(null);

    JLabel testLabel = new JLabel("\u6D4B\u8BD5\u6837\u4F8B");
    testLabel.setBounds(10, 10, 105, 33);
    testLabel.setForeground(new Color(153, 102, 153));
    testLabel.setFont(new Font("¿¬Ìå", Font.BOLD, 20));
    testPanel.add(testLabel);

    JScrollPane testScrollPane = new JScrollPane((Component) null);
    testScrollPane.setBounds(10, 53, 703, 298);
    testScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    testScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    testPanel.add(testScrollPane);

    final JTextArea testTextArea = new JTextArea();
    testScrollPane.setViewportView(testTextArea);
    testTextArea.setLineWrap(true);
    testTextArea.setEnabled(true);
    testTextArea.setBackground(Color.WHITE);
    testTextArea.setText(fileStore.getTestString());

    JButton confirmButton = new JButton("\u6D4B\u8BD5");
    confirmButton.setForeground(Color.WHITE);
    confirmButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
    confirmButton.setFocusPainted(false);
    confirmButton.setBackground(SystemColor.activeCaption);
    confirmButton.setBounds(628, 10, 73, 33);
    testPanel.add(confirmButton);

    /*
     * DFA×ª»»±í--------------------------------------------------------------------------------------
     */
    final JPanel DFAPanel = new JPanel();
    DFAPanel.setBounds(0, 0, 723, 361);
    panel.add(DFAPanel);
    DFAPanel.setBackground(new Color(248, 248, 255));
    DFAPanel.setLayout(null);

    JLabel dfaLabel = new JLabel("DFA\u8F6C\u6362\u8868");
    dfaLabel.setBounds(10, 10, 105, 33);
    dfaLabel.setForeground(new Color(153, 102, 153));
    dfaLabel.setFont(new Font("¿¬Ìå", Font.BOLD, 20));
    DFAPanel.add(dfaLabel);

    final JScrollPane dfaScrollPane = new JScrollPane();
    dfaScrollPane.setBounds(10, 53, 703, 298);
    dfaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    dfaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    DFAPanel.add(dfaScrollPane);

    /*
     * final JTextArea dfaTextArea = new JTextArea(); dfaScrollPane.setViewportView(dfaTextArea);
     * dfaTextArea.setLineWrap(true); dfaTextArea.setEnabled(false);
     * dfaTextArea.setBackground(Color.WHITE);
     */

    /*
     * TokenÐòÁÐ±í-------------------------------------------------------------------------------------
     * ---------
     */
    final JPanel TokenPanel = new JPanel();
    TokenPanel.setBounds(0, 0, 723, 361);
    panel.add(TokenPanel);
    TokenPanel.setBackground(new Color(255, 250, 240));
    TokenPanel.setLayout(null);

    JLabel TokenLabel = new JLabel("Token\u5E8F\u5217");
    TokenLabel.setBounds(10, 10, 114, 24);
    TokenLabel.setForeground(new Color(153, 102, 153));
    TokenLabel.setFont(new Font("¿¬Ìå", Font.BOLD, 20));
    TokenPanel.add(TokenLabel);

    final JScrollPane tokenScrollPane = new JScrollPane((Component) null);
    tokenScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    tokenScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    tokenScrollPane.setBounds(10, 53, 703, 298);
    TokenPanel.add(tokenScrollPane);

    /*
     * final JTextArea tokenTextArea = new JTextArea();
     * tokenScrollPane.setViewportView(tokenTextArea); tokenTextArea.setLineWrap(true);
     * tokenTextArea.setEnabled(false); tokenTextArea.setBackground(Color.WHITE);
     */

    /*
     * ErrorTable-----------------------------------------------------------------------------------
     * ------------
     * 
     */
    final JPanel errorPanel = new JPanel();
    errorPanel.setBounds(0, 0, 723, 361);
    panel.add(errorPanel);
    errorPanel.setBackground(new Color(240, 248, 255));
    errorPanel.setLayout(null);

    JLabel errorLabel = new JLabel("error\u8868");
    errorLabel.setBounds(10, 10, 114, 24);
    errorLabel.setForeground(new Color(153, 102, 153));
    errorLabel.setFont(new Font("¿¬Ìå", Font.BOLD, 20));
    errorPanel.add(errorLabel);

    final JScrollPane errorScrollPane = new JScrollPane((Component) null);
    errorScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    errorScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    errorScrollPane.setBounds(10, 53, 703, 298);
    errorPanel.add(errorScrollPane);

    /*
     * final JTextArea errorTextArea = new JTextArea();
     * errorScrollPane.setViewportView(errorTextArea); errorTextArea.setLineWrap(true);
     * errorTextArea.setEnabled(false); errorTextArea.setBackground(Color.WHITE);
     * errorTextArea.setText(fileStore.getTestString());
     */
    /*
     * ¸÷Table
     */

    final JTable dfaListTable = new JTable();
    dfaListTable.setBounds(10, 53, 703, 298);
    dfaListTable.setBackground(new Color(255, 255, 255));
    dfaListTable.setFillsViewportHeight(true);
    
    final JTable tokenListTable = new JTable();
    tokenListTable.setBounds(10, 53, 703, 298);
    tokenListTable.setBackground(new Color(255, 255, 255));
    tokenListTable.setFillsViewportHeight(true);
    
    final JTable errorListTable = new JTable();
    errorListTable.setBounds(10, 53, 703, 298);
    errorListTable.setBackground(new Color(255, 255, 255));
    errorListTable.setFillsViewportHeight(true);
    
    RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>();
    tokenListTable.setRowSorter(sorter);
    RowSorter<DefaultTableModel> sorter2 = new TableRowSorter<DefaultTableModel>();
    dfaListTable.setRowSorter(sorter2);
    RowSorter<DefaultTableModel> sorter3 = new TableRowSorter<DefaultTableModel>();
    errorListTable.setRowSorter(sorter3);
    
    dfaScrollPane.setViewportView(dfaListTable);
    tokenScrollPane.setViewportView(tokenListTable);;
    errorScrollPane.setViewportView(errorListTable);;

    /*
     * µã»÷¸÷°´Å¥
     */
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFrame mainFrame = new Application();
        mainFrame.setVisible(true);
        dispose();
      }
    });

    testFileButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        testPanel.setVisible(true);
        DFAPanel.setVisible(false);
        TokenPanel.setVisible(false);
        errorPanel.setVisible(false);
        // dfaTextArea.setText(fileStore.getTestString());
      }
    });

    DFAButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        testPanel.setVisible(false);
        DFAPanel.setVisible(true);
        TokenPanel.setVisible(false);
        errorPanel.setVisible(false);
      }
    });

    TokenButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        testPanel.setVisible(false);
        DFAPanel.setVisible(false);
        TokenPanel.setVisible(true);
        errorPanel.setVisible(false);
      }
    });

    errorButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        testPanel.setVisible(false);
        DFAPanel.setVisible(false);
        TokenPanel.setVisible(false);
        errorPanel.setVisible(true);
      }
    });

    confirmButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fileStore.setTestString(testTextArea.getText());
        try {
          run();
          
          dfaListTable.setModel(fileStore.getDfaTableModel());
    
          tokenListTable.setModel(fileStore.getTokenTableModel());
          
          errorListTable.setModel(fileStore.getErrorTableModel());
          
          RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(fileStore.getTokenTableModel());
          tokenListTable.setRowSorter(sorter);
          RowSorter<DefaultTableModel> sorter2 = new TableRowSorter<DefaultTableModel>(fileStore.getDfaTableModel());
          dfaListTable.setRowSorter(sorter2);
          RowSorter<DefaultTableModel> sorter3 = new TableRowSorter<DefaultTableModel>(fileStore.getErrorTableModel());
          errorListTable.setRowSorter(sorter3);
      
          // dfaTextArea.setText(fileStore.getDfaString());
          // tokenTextArea.setText(fileStore.getTokenString());
          // errorTextArea.setText(fileStore.getErrorString());
        } catch (Exception e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        testPanel.setVisible(false);
        DFAPanel.setVisible(false);
        TokenPanel.setVisible(true);
        errorPanel.setVisible(false);
      }
    });
  }

  /*
   * private void run() throws Exception { Analyzer analyzer = new Analyzer();
   * analyzer.lexicalAnalysis(fileStore.getTestString(), fileStore.getFaFile(),
   * fileStore.getStateFile()); List<String> tokenStrings = analyzer.getTokens(); String tokenString
   * = new String(""); for (String string : tokenStrings) { tokenString = tokenString + string +
   * "\n"; }
   * 
   * this.fileStore.setTokenString(tokenString);
   * 
   * List<String> errorStrings = analyzer.getErrors(); String errorString = new String(""); for
   * (String string : errorStrings) { errorString = errorString + string + "\n"; }
   * 
   * this.fileStore.setErrorString(errorString);
   * 
   * String dfaString = new String(""); String[][] dfaTable = new
   * readDFATable(fileStore.getFaFile(), fileStore.getStateFile()).showDFA(); for (int i = 0; i <
   * dfaTable.length; i++) { for (int j = 0; j < dfaTable[i].length; j++) { dfaString = dfaString +
   * dfaTable[i][j] + "\t"; } dfaString = dfaString + "\n"; }
   * this.fileStore.setDfaString(dfaString); }
   */
  private void run() throws Exception {
    Analyzer analyzer = new Analyzer();
    analyzer.lexicalAnalysis(fileStore.getTestString(), fileStore.getFaFile(),
        fileStore.getStateFile());

    fileStore.setTokenTableModel(StringToTable.token(analyzer.getTokens()));
    readDFATable readDFATable = new readDFATable(fileStore.getFaFile(), fileStore.getStateFile());
    fileStore.setDfaTableModel(StringToTable.dfa(readDFATable.getDFA()));
    fileStore.setErrorTableModel(StringToTable.error(analyzer.getErrors()));
  }
}

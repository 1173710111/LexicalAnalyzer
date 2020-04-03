package cn.hit.view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.hit.controller.LexicalAnalyzer;
import cn.hit.tool.FileStore;
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
import java.awt.event.ActionEvent;

public class OutPutJFrame extends JFrame {

  private JPanel contentPane;
  private FileStore fileStore;
  
  /**
   * Create the frame.
   */
  public OutPutJFrame(FileStore file) {
    this.fileStore=file;
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
    
    
    JLabel DFALabel = new JLabel("DFA\u8F6C\u6362\u8868");
    DFALabel.setBounds(10, 10, 114, 24);
    DFALabel.setForeground(new Color(153, 102, 153));
    DFALabel.setFont(new Font("¿¬Ìå", Font.BOLD, 20));
    DFAPanel.add(DFALabel);
    
    JScrollPane dfaScrollPane = new JScrollPane();
    dfaScrollPane.setBounds(10, 53, 703, 298);
    dfaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    dfaScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    DFAPanel.add(dfaScrollPane);
    
    
    
/*
 * TokenÐòÁÐ±í----------------------------------------------------------------------------------------------   
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
    
    JScrollPane tokenScrollPane = new JScrollPane((Component) null);
    tokenScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    tokenScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    tokenScrollPane.setBounds(10, 53, 703, 298);
    TokenPanel.add(tokenScrollPane);
    
    
/*
 * ErrorTable-----------------------------------------------------------------------------------------------
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
    
    JScrollPane errorScrollPane = new JScrollPane((Component) null);
    errorScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    errorScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    errorScrollPane.setBounds(10, 53, 703, 298);
    errorPanel.add(errorScrollPane);
/*
 * ¸÷Table
 */
    final JTable dfaListTable = new JTable();
    dfaListTable.setBackground(new Color(250, 240, 230));
    dfaListTable.setFillsViewportHeight(true);
    RowSorter<DefaultTableModel> sorter2 = new TableRowSorter<DefaultTableModel>(fileStore.getDfaListTbModel());
    dfaListTable.setRowSorter(sorter2); 
    dfaScrollPane.add(dfaListTable);
  
    final JTable tokenListTable=new JTable();
    tokenListTable.setBackground(new Color(250, 240, 230));
    tokenListTable.setFillsViewportHeight(true);
    RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(fileStore.getTokenListTbModel());
    tokenListTable.setRowSorter(sorter); 
    tokenScrollPane.add(tokenListTable);
    
    final JTable errorListTable=new JTable();
    errorListTable.setBackground(new Color(250, 240, 230));
    errorListTable.setFillsViewportHeight(true);
    RowSorter<DefaultTableModel> sorter3 = new TableRowSorter<DefaultTableModel>(fileStore.getErrorListTbModel());
    tokenListTable.setRowSorter(sorter3); 
    errorScrollPane.add(errorListTable);
/*
 * µã»÷¸÷°´Å¥
 */
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFrame mainFrame=new Application();
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
        testTextArea.setText(fileStore.getTestString());
      }
    });

    DFAButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (fileStore.getTokenListTbModel()!=null) 
          dfaListTable.setModel(fileStore.getDfaListTbModel());
        testPanel.setVisible(false);
        DFAPanel.setVisible(true);
        TokenPanel.setVisible(false);
        errorPanel.setVisible(false);
      }
    });
    
    TokenButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (fileStore.getTokenListTbModel()!=null) 
          tokenListTable.setModel(fileStore.getTokenListTbModel());
        testPanel.setVisible(false);
        DFAPanel.setVisible(false);
        TokenPanel.setVisible(true);
        errorPanel.setVisible(false);
      }
    });
    
    errorButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (fileStore.getTokenListTbModel()!=null) 
          errorListTable.setModel(fileStore.getErrorListTbModel());
        testPanel.setVisible(false);
        DFAPanel.setVisible(false);
        TokenPanel.setVisible(false);
        errorPanel.setVisible(true);
      }
    });
    
    confirmButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fileStore.setTestString(testTextArea.getText());
        run();
        testPanel.setVisible(false);
        DFAPanel.setVisible(true);
        errorPanel.setVisible(false);
      }
    });
  }
  
  private void run() {
    LexicalAnalyzer lexicalAnalyzer=new LexicalAnalyzer(fileStore);
    lexicalAnalyzer.run();
    this.fileStore=lexicalAnalyzer.getFileStore();
  }
}

package cn.hit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.hit.tool.FileStore;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
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
  public OutPutJFrame(final FileStore fileStore) {
    this.fileStore=fileStore;
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
    menuPanel.setBounds(57, 10, 660, 30);
    contentPane.add(menuPanel);
    menuPanel.setLayout(null);
    
    JButton backButton = new JButton("\u8FD4\u56DE");
    backButton.setBounds(40, 0, 115, 30);
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
    testFileButton.setBounds(195, 0, 115, 30);
    menuPanel.add(testFileButton);
    
    JButton DFAButton = new JButton("DFA\u8F6C\u6362\u8868");
    DFAButton.setForeground(new Color(112, 128, 144));
    DFAButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
    DFAButton.setFocusPainted(false);
    DFAButton.setBackground(Color.WHITE);
    DFAButton.setBounds(350, 0, 115, 30);
    menuPanel.add(DFAButton);
    
    JButton TokenButton = new JButton("Token\u5E8F\u5217");
    TokenButton.setForeground(new Color(112, 128, 144));
    TokenButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
    TokenButton.setFocusPainted(false);
    TokenButton.setBackground(Color.WHITE);
    TokenButton.setBounds(505, 0, 115, 30);
    menuPanel.add(TokenButton);
    
    
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
        
    JScrollPane scrollPane_1 = new JScrollPane((Component) null);
    scrollPane_1.setBounds(10, 53, 703, 298);
    scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    testPanel.add(scrollPane_1);
    
    final JTextArea testTextArea = new JTextArea();
    scrollPane_1.setViewportView(testTextArea);
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
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 53, 703, 298);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    DFAPanel.add(scrollPane);
    
    final JTextArea DFATextArea = new JTextArea();
    scrollPane.setViewportView(DFATextArea);
    DFATextArea.setLineWrap(true);
    DFATextArea.setEnabled(false);
    DFATextArea.setBackground(Color.WHITE);
    
    
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
    
    JScrollPane scrollPane_2 = new JScrollPane((Component) null);
    scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane_2.setBounds(10, 53, 703, 298);
    TokenPanel.add(scrollPane_2);
    
    final JTextArea tokenTextArea = new JTextArea();
    scrollPane_2.setViewportView(tokenTextArea);
    tokenTextArea.setLineWrap(true);
    tokenTextArea.setEnabled(false);
    tokenTextArea.setBackground(Color.WHITE);

    
/*
 * µã»÷¸÷°´Å¥
 */
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFrame mainFrame=new LaxicalAnalyzer();
        mainFrame.setVisible(true);
        dispose();
      }
    });
    
    testFileButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        testPanel.setVisible(true);
        DFAPanel.setVisible(false);
        TokenPanel.setVisible(false);
        testTextArea.setText(fileStore.getTestString());
      }
    });

    DFAButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        testPanel.setVisible(false);
        DFAPanel.setVisible(true);
        TokenPanel.setVisible(false);
        DFATextArea.setText(fileStore.getDFAString());
      }
    });
    
    TokenButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        testPanel.setVisible(false);
        DFAPanel.setVisible(false);
        TokenPanel.setVisible(true);
        testTextArea.setText(fileStore.getTokenString());
      }
    });
    
    confirmButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fileStore.setTestString(testTextArea.getText());
        run();
        testPanel.setVisible(false);
        DFAPanel.setVisible(true);
        TokenPanel.setVisible(false);
        DFATextArea.setText(fileStore.getDFAString());
      }
    });
  }
  
  private void run() {
    
  }
}

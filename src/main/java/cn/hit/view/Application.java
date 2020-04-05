package cn.hit.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.hit.tool.FileStore;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Application extends JFrame {

  /**
   * the initial view that user can see when run this application.
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private FileStore fileStore=new FileStore();
  
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Application frame = new Application();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public Application() {
    this.setTitle("词法分析器");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 200, 778, 476);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(119, 136, 153));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
   
/*
 * fa转换表文件的选择
 */
    JPanel fAJPanel = new JPanel();
    fAJPanel.setBackground(new Color(255, 250, 240));
    fAJPanel.setBounds(31, 30, 336, 187);
    contentPane.add(fAJPanel);
    fAJPanel.setLayout(null);
    
    JLabel faLabel = new JLabel("FA\u8F6C\u6362\u8868");
    faLabel.setBounds(10, 10, 105, 33);
    faLabel.setForeground(new Color(153, 102, 153));
    faLabel.setFont(new Font("楷体", Font.BOLD, 20));
    fAJPanel.add(faLabel);
    
    final JButton fileButton_fa = new JButton("\u9009\u62E9\u6587\u4EF6");
    fileButton_fa.setBounds(10, 43, 105, 33);
    fileButton_fa.setFont(new Font("微软雅黑", Font.BOLD, 15));
    fileButton_fa.setBackground(SystemColor.activeCaption);
    fileButton_fa.setForeground(SystemColor.textHighlightText);
    fileButton_fa.setFocusPainted(false);
    fAJPanel.add(fileButton_fa);
    
    final JLabel fileNameLabel_fa = new JLabel("");
    fileNameLabel_fa.setBounds(10, 86, 316, 26);
    fAJPanel.add(fileNameLabel_fa);
    
    fileButton_fa.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {  
        JFileChooser chooser = new JFileChooser();             //设置选择器  
         chooser.setMultiSelectionEnabled(false);             //设为单选  
        int returnVal = chooser.showOpenDialog(fileButton_fa);
        if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型  
            String filepath = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径  
            System.out.println(filepath);  
            String faFileString=filepath.replaceAll("\\\\", "/");
            fileStore.setFaFile(new File(faFileString));
            String string=new String("\0");
            for (int i=faFileString.length()-1;i>0;i--) {
              if (faFileString.charAt(i)=='/') {
                break;
              }
              string=faFileString.charAt(i)+string;
            }
            fileNameLabel_fa.setText(string);
        }  
    }  
    });

  
/*
 * 测试用例文件的选择、显示和编辑。    
 */
    JPanel testJPanel = new JPanel();
    testJPanel.setBackground(new Color(255, 250, 250));
    testJPanel.setBounds(394, 30, 336, 381);
    contentPane.add(testJPanel);
    testJPanel.setLayout(null);
    
    JLabel faLabel_1 = new JLabel("\u6D4B\u8BD5\u6837\u4F8B");
    faLabel_1.setBounds(10, 10, 105, 33);
    faLabel_1.setForeground(new Color(153, 102, 153));
    faLabel_1.setFont(new Font("楷体", Font.BOLD, 20));
    testJPanel.add(faLabel_1);
    
    JButton confirmButton_test = new JButton("\u6D4B\u8BD5");
    confirmButton_test.setBounds(125, 43, 105, 33);
    confirmButton_test.setForeground(Color.WHITE);
    confirmButton_test.setFont(new Font("微软雅黑", Font.BOLD, 15));
    confirmButton_test.setFocusPainted(false);
    confirmButton_test.setBackground(SystemColor.activeCaption);
    testJPanel.add(confirmButton_test);
    
    JButton fileButton_test = new JButton("\u9009\u62E9\u6587\u4EF6");
    fileButton_test.setBounds(10, 43, 105, 33);
    fileButton_test.setForeground(Color.WHITE);
    fileButton_test.setFont(new Font("微软雅黑", Font.BOLD, 15));
    fileButton_test.setFocusPainted(false);
    fileButton_test.setBackground(SystemColor.activeCaption);
    testJPanel.add(fileButton_test);
    
    final JTextArea testTextArea = new JTextArea();
    testTextArea.setBounds(1, 1, 297, 266);
    testTextArea.setLineWrap(true);
    testTextArea.setEnabled(false);
    testTextArea.setBackground(Color.WHITE);
    testJPanel.add(testTextArea);
    
    fileButton_test.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {  
        JFileChooser chooser = new JFileChooser();             //设置选择器  
         chooser.setMultiSelectionEnabled(false);             //设为单选  
        int returnVal = chooser.showOpenDialog(fileButton_fa);
        if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型  
            String filepath = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径  
            System.out.println(filepath);  
            String testFileString=filepath.replaceAll("\\\\", "/");
            fileStore.setTestFile(new File(testFileString));
            testTextArea.setText(fileStore.getTestString());
        }  
    }  
    });
    
    confirmButton_test.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e) {
        JFrame outFrame=new OutPutJFrame(fileStore);
        outFrame.setVisible(true);
        dispose();
      }
    });
    
    JScrollPane scrollPane_1 = new JScrollPane(testTextArea);
    scrollPane_1.setBounds(10, 86, 316, 285);
    testJPanel.add(scrollPane_1);
   //分别设置水平和垂直滚动条总是出现
    scrollPane_1.setHorizontalScrollBarPolicy(
    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane_1.setVerticalScrollBarPolicy(
    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
/*
 * 状态表文件的选择
 */
    JPanel stateJPanel = new JPanel();
    stateJPanel.setLayout(null);
    stateJPanel.setBackground(new Color(255, 250, 240));
    stateJPanel.setBounds(31, 224, 336, 187);
    contentPane.add(stateJPanel);
    
    JLabel stateLabel = new JLabel("\u72B6\u6001\u8868");
    stateLabel.setForeground(new Color(153, 102, 153));
    stateLabel.setFont(new Font("楷体", Font.BOLD, 20));
    stateLabel.setBounds(10, 10, 105, 33);
    stateJPanel.add(stateLabel);
    
    JButton fileButton_state = new JButton("\u9009\u62E9\u6587\u4EF6");
    fileButton_state.setForeground(Color.WHITE);
    fileButton_state.setFont(new Font("微软雅黑", Font.BOLD, 15));
    fileButton_state.setFocusPainted(false);
    fileButton_state.setBackground(SystemColor.activeCaption);
    fileButton_state.setBounds(10, 43, 105, 33);
    stateJPanel.add(fileButton_state);
    
    final JLabel fileNameLabel_state = new JLabel("");
    fileNameLabel_state.setBounds(10, 86, 316, 26);
    stateJPanel.add(fileNameLabel_state);
    
    fileButton_state.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {  
        JFileChooser chooser = new JFileChooser();             //设置选择器  
         chooser.setMultiSelectionEnabled(false);             //设为单选  
        int returnVal = chooser.showOpenDialog(fileButton_fa);
        if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型  
            String filepath = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径  
            System.out.println(filepath);  
            String stateFileString=filepath.replaceAll("\\\\", "/");
            fileStore.setStateFile(new File(stateFileString));
            String string=new String("\0");
            for (int i=stateFileString.length()-1;i>0;i--) {
              if (stateFileString.charAt(i)=='/') {
                break;
              }
              string=stateFileString.charAt(i)+string;
            }
            fileNameLabel_state.setText(string);
        }  
    }  
    });
  }

}

package cn.hit.controller;

import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.hit.domain.DFATable;
import cn.hit.domain.DFATableState;
import cn.hit.tool.FileStore;

public class LexicalAnalyzer {

  private JTable table;
  private FileStore fileStore;
  private DefaultTableModel tokenListTbModel;
  private DefaultTableModel dfaListTbModel;
  private DefaultTableModel errorListTbModel;
  String[] keywords = {"char", "long", "short", "float", "double", "const", "boolean", "void",
      "null", "false", "true", "enum", "int", "do", "while", "if", "else", "for", "then", "break",
      "continue", "class", "static", "final", "extends", "new", "return", "signed", "struct",
      "union", "unsigned", "goto", "switch", "case", "default", "auto", "extern", "register",
      "sizeof", "typedef", "volatile"};
  String[] tokens = {"char", "long", "short", "float", "double", "const", "boolean", "void", "null",
      "false", "true", "enum", "int", "do", "while", "if", "else", "for", "then", "break",
      "continue", "class", "static", "final", "extends", "new", "return", "signed", "struct",
      "union", "unsigned", "goto", "switch", "case", "default", "auto", "extern", "register",
      "sizeof", "typedef", "volatile", ">", ">=", "<", "<=", "==", "!=", "|", "&", "||", "&&", "!",
      "^", "+", "-", "*", "/", "%", "++", "--", "+=", "-=", "*=", "/=", ",", "=", ";", "[", "]",
      "(", ")", "{", "}", ".", "\"", "'"

  };

  public LexicalAnalyzer(FileStore fileStore) {
    this.fileStore = fileStore;
  }

  /**
   * do the main tasks.
   */
  public void run() {
    try {

      tokenListTbModel =
          new DefaultTableModel(new Object[][] {}, new String[] {"字符串", "类别", "种别码", "value"});

      dfaListTbModel = new DefaultTableModel(new Object[][] {},
          new String[] {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
      String[][] result = new readDFATable(fileStore.getFaFile(),fileStore.getStateFile()).showDFA();
      for (int i = 1; i < result.length; i++) {
        for (int j = 1; j < result[i].length - 2; j++) {
          result[i][j] = result[i][j].replaceAll("-1", " ");
        }
      }
      for (int i = 0; i < result.length; i++) {
        dfaListTbModel.addRow(new Object[] {result[i][0], result[i][1], result[i][2], result[i][3],
            result[i][4], result[i][5], result[i][6], result[i][7], result[i][8], result[i][9],
            result[i][10], result[i][11], result[i][12], result[i][13], result[i][14],
            result[i][15], result[i][16], result[i][17]});

      }
      RowSorter<DefaultTableModel> sorter1 = new TableRowSorter<DefaultTableModel>(dfaListTbModel);
//出错表格
      errorListTbModel =
          new DefaultTableModel(new Object[][] {}, new String[] {"出错符号", "出错地方", "出错原因"});
      lexicalAnalysis(fileStore.getTestString());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * get tables to show.
   * 
   * @return
   */
  public FileStore getFileStore() {
    this.fileStore.setDfaListTbModel(dfaListTbModel);
    this.fileStore.setErrorListTbModel(errorListTbModel);
    this.fileStore.setTokenListTbModel(tokenListTbModel);
    return fileStore;
  }

  /**
   * 主要函数
   */
  private void lexicalAnalysis(String str) throws Exception {

    while (tokenListTbModel.getRowCount() > 0) {
      tokenListTbModel.removeRow(0);
    }
    while (errorListTbModel.getRowCount() > 0) {
      errorListTbModel.removeRow(0);
    }
    // 将字符串转化为字符数组
    char[] strline = str.toCharArray();
    String currentString = "";
    int currentState = 0; // 当前状态
    int lastState = 0; // 上一状态

    DFATable dfa[] = new readDFATable(fileStore.getFaFile(), fileStore.getStateFile()).addDFA();
    DFATableState DFAstate[] =
        new readDFATable(fileStore.getFaFile(), fileStore.getStateFile()).showDFAState();
    for (int i = 0; i < strline.length; i++) {
      System.out.println(strline[i]);
      currentString = currentString.concat(String.valueOf(strline[i]));
      System.out.println("currentString:" + currentString);// x,
      System.out.println("i : " + i);// x,
      lastState = currentState;

      if (currentString == " " || strline[i] == ' ') {
        currentString = currentString.replaceAll(" ", "");
        if (isKeyword(currentString)) {
          tokenListTbModel
              .addRow(new Object[] {currentString, "关键字", tokenID(currentString), "   "});
        } else {
          tokenListTbModel.addRow(new Object[] {currentString, findTypeByState(lastState, DFAstate),
              tokenID(currentString),
              findTypeByState(lastState, DFAstate).equals("注释")
                  || findTypeByState(lastState, DFAstate).equals("运算符")
                  || findTypeByState(lastState, DFAstate).equals("界符") ? "   " : currentString});
        }
        currentString = "";
        currentState = 0;
        continue;
      }
      currentState = stateChange(strline[i], currentState, dfa);
      System.out.println(" 当前状态  =  " + currentState);
      System.out.println(" 上一状态  =  " + lastState);
      if (currentState == -1 || currentState == -2)// 现在这个字符在线路上混不下去了
      {
        currentString = currentString.substring(0, currentString.length() - 1);
        // 判断当前状态是否为终止状态 如不是 报错
        if (!isFinishByState(lastState, DFAstate)) {
          errorListTbModel.addRow(new Object[] {currentString, "第" + i + "字符", "非法字符"});
          tokenListTbModel.addRow(new Object[] {currentString, "非法字符", "无", "   "});
        } else {
          if (isKeyword(currentString)) {
            tokenListTbModel
                .addRow(new Object[] {currentString, "关键字", tokenID(currentString), "   "});
          } else {
            tokenListTbModel.addRow(new Object[] {currentString,
                findTypeByState(lastState, DFAstate), tokenID(currentString),
                findTypeByState(lastState, DFAstate).equals("注释")
                    || findTypeByState(lastState, DFAstate).equals("运算符")
                    || findTypeByState(lastState, DFAstate).equals("界符") ? "   " : currentString});
          }
        }
        if (currentState == -2) {
          tokenListTbModel.addRow(new Object[] {strline[i], "非法字符", "无", "   "});
          errorListTbModel.addRow(new Object[] {strline[i], "第" + i + "字符", "非法字符"});
          i++;
          // errorListTbModel.addRow(new Object[] {"/*", "注释未封闭"});
        }
        currentString = "";
        currentState = 0;
        i--;
      }
      // 最后一个符号
      if (i == strline.length - 1) {
        if (!isFinishByState(lastState, DFAstate)) {
          errorListTbModel.addRow(new Object[] {currentString, "第" + i + "字符", "非法字符"});
          tokenListTbModel.addRow(new Object[] {currentString, "非法字符", "无", "   "});
        } else {
          if (isKeyword(currentString)) {
            tokenListTbModel
                .addRow(new Object[] {currentString, "关键字", tokenID(currentString), "   "});
          } else {
            tokenListTbModel.addRow(new Object[] {currentString,
                findTypeByState(currentState, DFAstate), tokenID(currentString),
                findTypeByState(currentState, DFAstate).equals("注释")
                    || findTypeByState(currentState, DFAstate).equals("运算符")
                    || findTypeByState(currentState, DFAstate).equals("界符")
                        ? "   "
                        : currentString});
          }
        }
        if (currentState == -2) {
          tokenListTbModel.addRow(new Object[] {strline[i], "非法字符", "无", "   "});
          errorListTbModel.addRow(new Object[] {strline[i], "第" + i + "字符", "非法字符"});
          i++;
          // errorListTbModel.addRow(new Object[] {"/*", "注释未封闭"});
        }
      }

    }

  }

  public int stateChange(char currentChar, int currentState, DFATable[] dfa) {
    System.out.println("=====currentState = " + currentState);
    boolean isInput = false;

    for (int i = 0; i < dfa.length; i++) {
      if (isList(dfa[i].getInput(), currentChar)) {
        isInput = true;// 存在该输入
        if (dfa[i].getState() == currentState && dfa[i].getNextState() != -1)// 当前状态一样 输入有 下一状态不为空
                                                                             // 依然在当前自动机中
        {
          return dfa[i].getNextState();
        }

      }
    }
    if (isInput == false) {
      return -2;
    }
    // 在状态9 10 待太久没有结束 注释未封闭 报错
    return -1;
  }

  public static boolean isList(String[] arr, char currentChar) {
    for (int i = 0; i < arr.length; i++) {
      // System.out.print(" arr[i] "+arr[i]);
      if (arr[i].equals(String.valueOf(currentChar))) {
        return true;
      }
    }
    return false;
  }

  // 根据state 返回该状态的类型
  public String findTypeByState(int state, DFATableState[] dfaState) {
    for (int i = 0; i < dfaState.length; i++) {
      if (dfaState[i].getState() == state) {
        return dfaState[i].getType();
      }
    }
    return "error";
  }

  // 根据state 返回该状态的是否为终止状态
  public boolean isFinishByState(int state, DFATableState[] dfaState) {
    for (int i = 0; i < dfaState.length; i++) {
      if (dfaState[i].getState() == state) {
        return dfaState[i].isFinish();
      }
    }
    return true;
  }

  // 判断当前即将要输出的字符串是否为关键字
  public boolean isKeyword(String str) {
    for (int i = 0; i < keywords.length; i++) {
      if (keywords[i].equals(str)) {
        return true;
      }
    }
    return false;
  }

  public int tokenID(String str) {
    boolean flag = false;
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].equals(str)) {
        flag = true;
        return i;
      }
    }
    if (flag == false) {
      tokens = Arrays.copyOf(tokens, tokens.length + 1);
      tokens[tokens.length - 1] = str;
      return tokens.length;
    }
    return -1;
  }


}

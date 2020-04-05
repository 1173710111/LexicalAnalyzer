package cn.hit.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hit.domain.DFATable;
import cn.hit.domain.DFATableState;

public class Analyser {
  private List<String> errors = new ArrayList<String>();
  private List<String> tokens = new ArrayList<String>();
  private String[] keywords = {"char", "long", "short", "float", "double", "const", "boolean",
      "void", "null", "false", "true", "enum", "int", "do", "while", "if", "else", "for", "then",
      "break", "continue", "class", "static", "final", "extends", "new", "return", "signed",
      "struct", "union", "unsigned", "goto", "switch", "case", "default", "auto", "extern",
      "register", "sizeof", "typedef", "volatile"};
  
    private String[] tokenStrings = { "char", "long", "short", "float", "double", "const",
    "boolean", "void", "null", "false", "true", "enum", "int", "do", "while", "if", "else", "for",
    "then", "break", "continue", "class", "static", "final", "extends", "new", "return", "signed",
    "struct", "union", "unsigned", "goto", "switch", "case", "default", "auto", "extern",
    "register", "sizeof", "typedef", "volatile", ">", ">=", "<", "<=", "==", "!=", "|", "&", "||",
    "&&", "!", "^", "+", "-", "*", "/", "%", "++", "--", "+=", "-=", "*=", "/=", ",", "=", ";",
    "[", "]", "(", ")", "{", "}", ".", "\"", "'"
    
    };
   

  public List<String> getErrors() {
    return errors;
  }

  public List<String> getTokens() {
    return tokens;
  }

  // �ʷ�����
  public void lexicalAnalysis(String str, File faFile, File stateFile) throws Exception {
    tokens.clear();
    errors.clear();

    // ���ַ���ת��Ϊ�ַ����飬����ɨ��
    char[] strline = str.toCharArray();
    String curString = "";
    int curState = 0;// ��ǰ״̬
    int lastState = 0;// ��һ��״̬
    // ��ȡת�����״̬��
    readDFATable reader = new readDFATable(faFile, stateFile);
    DFATable dfa[] = reader.getDFA();
    DFATableState DFAstate[] = reader.getDFAState();
   

    for (int i = 0; i < strline.length; i++) {
      curString = curString.concat(String.valueOf(strline[i]));
      lastState = curState;
      if (curString == " " || strline[i] == ' ') {
        curString = curString.replaceAll(" ", "");
        // �Ƿ�Ϊ�ؼ���
        if (isKeyword(curString)) {
          String tempString = curString + "    <" + curString + ",_>";
          // System.out.println(tempString);
          tokens.add(tempString);
        } else {
          if (getType(curState, DFAstate).equals("ע��") || getType(curState, DFAstate).equals("�����")
              || getType(curState, DFAstate).equals("���")) {
            String tempString = curString + "    <" + getType(curState, DFAstate) +"-"+tokenID(curString) + ",_>";
           
            tokens.add(tempString);
          } else {
            String tempString =
                curString + "    <" + getType(curState, DFAstate)  +"-"+tokenID(curString)+ "," + curString + ">";
             
            tokens.add(tempString);
          }
        }
        curString = "";
        curState = 0;
        continue;
      }
      // ���״̬
      curState = stateChange(strline[i], curState, dfa);
      // ��ֹ״̬���쳣����
      if (curState == -1 || curState == -2) {
        curString = curString.substring(0, curString.length() - 1);
        if (!isFinishByState(lastState, DFAstate)) {
          String tempStringt = curString + "    <�Ƿ��ַ�,_>";
          String tempStringe = curString + "    ��" + i + "�ַ��ǷǷ��ַ�";
          tokens.add(tempStringt);
          errors.add(tempStringe);
        } else {
          if (isKeyword(curString)) {
            String tempString = curString + "    <" + curString + ",_>";
            tokens.add(tempString);
          } else {
            if (getType(lastState, DFAstate).equals("ע��")
                || getType(lastState, DFAstate).equals("�����")
                || getType(lastState, DFAstate).equals("���")) {
              String tempString = curString + "    <" + getType(lastState, DFAstate)  +"-"+tokenID(curString)+ ",_>";
              tokens.add(tempString);
            } else {
              if (lastState!=0) {
                String tempString =
                  curString + "    <" + getType(lastState, DFAstate)  +"-"+tokenID(curString)+ "," + curString + ">";
                tokens.add(tempString);
              }
              
            }
          }

        }
        if (curState == -2) {
          String tempStringt = strline[i] + "    <�Ƿ��ַ�,_>";
          String tempStringe = strline[i] + "    ��" + i + "�ַ��ǷǷ��ַ�";
          tokens.add(tempStringt);
          errors.add(tempStringe);
          i++;
        }
        curString = "";
        curState = 0;
        i--;
      }
      // ���һ������
      if (i == strline.length - 1) {
        if (!isFinishByState(lastState, DFAstate)) {
          String tempStringt = curString + "    <�Ƿ��ַ�,_>";
          String tempStringe = curString + "    ��" + i + "�ַ��ǷǷ��ַ�";
           
          tokens.add(tempStringt);
          errors.add(tempStringe);
        } else if (isKeyword(curString)) {
          String tempString = curString + "    <" + curString + ",_>";
           
          tokens.add(tempString);
        } else {
          if (getType(curState, DFAstate).equals("ע��") || getType(curState, DFAstate).equals("�����")
              || getType(curState, DFAstate).equals("���")) {
            String tempString = curString + "    <" + getType(curState, DFAstate) +"-"+tokenID(curString)+ ",_>";
             
            tokens.add(tempString);
          } else {
            String tempString =
                curString + "    <" + getType(curState, DFAstate) +"-"+tokenID(curString)+"," + curString + ">";
             
            tokens.add(tempString);
          }
        }

        if (curState == -2) {
          String tempStringt = strline[i] + "    <�Ƿ��ַ�,_>";
          String tempStringe = strline[i] + "    ��" + i + "�ַ��ǷǷ��ַ�";
           
          tokens.add(tempStringt);
          errors.add(tempStringe);
          i++;
        }
      }
    }

  }

  private boolean isKeyword(String string) {
    for (int i = 0; i < keywords.length; i++) {
      if (keywords[i].equals(string)) {
        return true;
      }
    }
    return false;
  }

  private String getType(int state, DFATableState[] dfaState) {
    
    for (int i = 0; i < dfaState.length; i++) {
      if (dfaState[i].getState() == state) {
        return dfaState[i].getType();
      }
    }
    return "error";
  }

  private int stateChange(char curchar, int curstate, DFATable[] dfa) {
    boolean isInput = false;

    for (int i = 0; i < dfa.length; i++) {
      if (isList(dfa[i].getInput(), curchar)) {
        isInput = true;// ���ڸ�����
        if (dfa[i].getState() == curstate && dfa[i].getNextState() != -1)// ��ǰ״̬һ�� ������ ��һ״̬��Ϊ��
                                                                         // ��Ȼ�ڵ�ǰ�Զ�����
        {
          return dfa[i].getNextState();
        }

      }
    }
    if (isInput == false) {
      return -2;
    }
    // ��״̬9 10 ��̫��û�н��� ע��δ��� ����
    return -1;
  }

  private boolean isList(String[] arr, char curchar) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].equals(String.valueOf(curchar))) {
        return true;
      }
    }
    return false;
  }

  // ����state ���ظ�״̬���Ƿ�Ϊ��ֹ״̬
  private boolean isFinishByState(int state, DFATableState[] dfaState) {
    for (int i = 0; i < dfaState.length; i++) {
      if (dfaState[i].getState() == state) {
        return dfaState[i].isFinish();
      }
    }
    return true;
  }
  
  private int tokenID(String str){
    boolean flag=false;
    for(int i=0;i<tokenStrings.length;i++)
    {
        if(tokenStrings[i].equals(str)){
            flag=true;
            return i;
        }
    }
    if(flag==false)
    {
        tokenStrings=Arrays.copyOf(tokenStrings, tokenStrings.length+1);
        tokenStrings[tokenStrings.length-1]=str;
        return tokenStrings.length;
    }
    return -1;
}
}

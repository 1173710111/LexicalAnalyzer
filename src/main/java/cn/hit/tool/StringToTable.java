package cn.hit.tool;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import cn.hit.domain.DFATable;

public class StringToTable {
 
  public static DefaultTableModel token(List<String> tokenList) {
    DefaultTableModel tokenListTbModel = new DefaultTableModel(new Object[][] {},new String[] {"",""});
    for (String string:tokenList) {
      String[] token=string.split("    ");
      tokenListTbModel.addRow(new Object[] {token[0],token[1]});
    }
    return tokenListTbModel;
}
  public static DefaultTableModel error(List<String> errorList) {
    DefaultTableModel errorListTbModel = new DefaultTableModel(new Object[][] {},new String[] {"错误符号","出错位置"});
    for (String string:errorList) {
      String[] error=string.split("    ");
      errorListTbModel.addRow(new Object[] {error[0],error[1]});
    }
    return errorListTbModel;
}
  public static DefaultTableModel dfa(DFATable[] dfaTables) {
    String[] tableHead= {"状态","类型","是否结束","输入","下一状态"};
    DefaultTableModel dfaTableModel=new DefaultTableModel(new Object[][] {},tableHead);
    for (DFATable dfaTable:dfaTables) {
      String[] dfaStrings=new String[5];
      dfaStrings[0]=dfaTable.getState()+"";
      dfaStrings[1]=dfaTable.getType();
      String string="";
      if (dfaTable.isFinish())
        string=string+"Y";
      else string=string+"N";
      dfaStrings[2]=string;
      string = "";
      for (String tmpString:dfaTable.getInput()) {
        string=string+tmpString+" ";
      }
      dfaStrings[3]=string;
      dfaStrings[4]=dfaTable.getNextState()+"";
     
      dfaTableModel.addRow(new Object[] {dfaStrings[0],dfaStrings[1],dfaStrings[2],dfaStrings[3],dfaStrings[4]});
    }
    return dfaTableModel; 
  }

}

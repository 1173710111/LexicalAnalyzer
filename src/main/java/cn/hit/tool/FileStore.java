package cn.hit.tool;

import java.io.File;

import javax.swing.table.DefaultTableModel;

public class FileStore {
  private File faFile;
  private File stateFile;
  private File testFile;
  private String testString;
  private DefaultTableModel tokenListTbModel;
  private DefaultTableModel dfaListTbModel;
  private DefaultTableModel errorListTbModel;
  
  public FileStore() {
  }

  public File getFaFile() {
    return faFile;
  }

  public void setFaFile(File faFile) {
    this.faFile = faFile;
  }

  public File getStateFile() {
    return stateFile;
  }

  public void setStateFile(File stateFile) {
    this.stateFile = stateFile;
  }

  public File getTestFile() {
    return testFile;
  }

  public void setTestFile(File testFile) {
    this.testFile = testFile;
    this.testString=FileReadTool.getFileContext(testFile);
  }
  
  public void setTestString(String testString) {
    this.testString = testString;
  }

  public String getTestString() {
    return testString;
  }

  public DefaultTableModel getTokenListTbModel() {
    return tokenListTbModel;
  }

  public void setTokenListTbModel(DefaultTableModel tokenListTbModel) {
    this.tokenListTbModel = tokenListTbModel;
  }

  public DefaultTableModel getDfaListTbModel() {
    return dfaListTbModel;
  }

  public void setDfaListTbModel(DefaultTableModel dfaListTbModel) {
    this.dfaListTbModel = dfaListTbModel;
  }

  public DefaultTableModel getErrorListTbModel() {
    return errorListTbModel;
  }

  public void setErrorListTbModel(DefaultTableModel errorListTbModel) {
    this.errorListTbModel = errorListTbModel;
  }
  

}

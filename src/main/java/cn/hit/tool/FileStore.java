package cn.hit.tool;

import java.io.File;

import javax.swing.table.DefaultTableModel;

public class FileStore {
  private File faFile;
  private File stateFile;
  private File testFile;
  private String testString;
  private String dfaString;
  private String stateString;
  private String tokenString;
  private String errorString;
  private DefaultTableModel dfaTableModel;
  private DefaultTableModel tokenTableModel;
  private DefaultTableModel errorTableModel;

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
    setTestString(FileReadTool.getFileContext(testFile));
  }

  public String getTestString() {
    return testString;
  }

  public void setTestString(String testString) {
    this.testString = testString;
  }

  public String getTokenString() {
    return this.tokenString;
  }
  
  public void setTokenString(String tokenString) {
    this.tokenString = tokenString;
  }
  

  public String getDfaString() {
    return dfaString;
  }

  public void setDfaString(String dfaString) {
    this.dfaString = dfaString;
  }

  public String getStateString() {
    return stateString;
  }

  public void setStateString(String stateString) {
    this.stateString = stateString;
  }

  public String getErrorString() {
    return errorString;
  }

  public void setErrorString(String errorString) {
    this.errorString = errorString;
  }

  public DefaultTableModel getDfaTableModel() {
    return dfaTableModel;
  }

  public void setDfaTableModel(DefaultTableModel dfaTableModel) {
    this.dfaTableModel = dfaTableModel;
  }

  public DefaultTableModel getTokenTableModel() {
    return tokenTableModel;
  }

  public void setTokenTableModel(DefaultTableModel tokenTableModel) {
    this.tokenTableModel = tokenTableModel;
  }

  public DefaultTableModel getErrorTableModel() {
    return errorTableModel;
  }

  public void setErrorTableModel(DefaultTableModel errorTableModel) {
    this.errorTableModel = errorTableModel;
  }


  
}
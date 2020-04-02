package cn.hit.tool;

import java.io.File;

public class FileStore {
  private File faFile;
  private File stateFile;
  private File testFile;
  private String testString;
  private String dfaString;
  private String stateString;
  private String tokenString;
  
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
  }

  public String getTestString() {
    return testString;
  }

  public void setTestString(String testString) {
    this.testString = testString;
  }
  
  public String getDFAString() {
    String string =new String();
    return string;
  }
  
  public String getTokenString() {
    String string=new String();
    return string;
  }
}

package cn.hit.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReadTool {
  private String result;
  public FileReadTool(File file) {
      this.FileRead(file);
  }
  
  private void FileRead(File file) {
      StringBuilder result=new StringBuilder();
      try {
        BufferedReader br=new BufferedReader(new FileReader(file));
        String string=null;
        while ((string=br.readLine())!=null) {
          result.append(string+System.lineSeparator());
        }
        br.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      this.result=result.toString();
  }
  
  public String getFileContext() {
    return this.result;
  }

}

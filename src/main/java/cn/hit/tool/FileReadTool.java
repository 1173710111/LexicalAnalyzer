package cn.hit.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReadTool {
  private static String result;
  
  private static void FileRead(File file) {
      StringBuilder ans=new StringBuilder();
      try {
        BufferedReader br=new BufferedReader(new FileReader(file));
        String string=null;
        while ((string=br.readLine())!=null) {
          ans.append(string+System.lineSeparator());
        }
        br.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      result=ans.toString();
  }
  
  public static String getFileContext(File file) {
    FileRead(file);
    return result;
  }

}

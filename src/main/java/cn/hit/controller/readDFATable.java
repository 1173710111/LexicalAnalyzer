package cn.hit.controller;
/*
 * public class readDFATable {
 * 
 * 
 * //获得DFA转换表的值 public DFATable[] getDFA() { DFATable dfa[] = new DFATable[663];
 * return dfa; } //获得状态表 public DFATableState[] getDFAState() { DFATableState
 * state[]= new DFATableState[39]; return state; } }
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.*;

import cn.hit.domain.DFATableState;
import cn.hit.domain.DFATable;

@SuppressWarnings("deprecation")
public class readDFATable {
	private File faFile;
	private File stateFile;

	public readDFATable(File faFile, File stateFile) {
		this.faFile = faFile;
		this.stateFile = stateFile;
	}
	
	// 文件处理
	public static String[][] getFile(File file) throws IOException {
		ArrayList<String[]> resultList = new ArrayList<String[]>();
		int rowSize = 0;

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

		POIFSFileSystem fs = new POIFSFileSystem(in);
		// 创建一个excel文件
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFCell cell = null;

		// 按行执行，从第0行开始
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);
			for (int index = 0; index <= sheet.getLastRowNum(); index++) {
				HSSFRow row = sheet.getRow(index);
				if (row == null) {
					continue;
				}

				int tempRow = row.getLastCellNum() + 1;
				if (tempRow > rowSize) {
					rowSize = tempRow;
				}

				String[] strings = new String[rowSize];
				Arrays.fill(strings, "");
				Boolean ifHashString = false;
				
				for (short cellnum = 0; cellnum <= row.getLastCellNum(); cellnum++) {
					String string = "";
					cell = row.getCell(cellnum);
					if (cell != null) {
						// 这里不是很确定,方法被取消了，应该是自动Unicode处理了
						// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case STRING:
							string = cell.getStringCellValue();
							break;
						case NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									string = new SimpleDateFormat("yyyy-MM-dd").format(date);
								} else {
									string = "";
								}
							} else {
								string = new DecimalFormat("0").format(cell.getNumericCellValue());
							}
							break;
						case FORMULA:
							if (!cell.getStringCellValue().equals("")) {
								string = cell.getStringCellValue();
							} else {
								string = cell.getStringCellValue() + 1;
							}
							break;
						case BLANK:
							break;
						case ERROR:
							string = "";
							break;
						case BOOLEAN:
							string = (cell.getBooleanCellValue() == true ? "Y" : "N");
							break;
						default:
							string = "";

						}
					}

					if (cellnum == 0 && string.trim().equals("")) {
						break;
					}
					strings[cellnum] = handleBlankString(string);
					ifHashString = true;
				}
				if (ifHashString) {
					resultList.add(strings);
				}
			}

		}

		in.close();
		String[][] resultStrings = new String[resultList.size()][rowSize];
		for (int i = 0; i < resultStrings.length; i++) {
			resultStrings[i] = resultList.get(i);
		}
		return resultStrings;
	}

	private static String handleBlankString(String string) {
		if (string == null) {
			return "";
		}
		int length = string.length();
		for (int i = 0; i < length; i++) {
			if (string.charAt(i) != 0x2) {
				break;
			}
			length--;
		}
		return string.substring(0, length);
	}

	public DFATable[] getDFA() throws IOException {
		//File file = new File("1.xls");
		File file = faFile;
		
		String[][] strings = getFile(file);
		int length = strings.length;
		int flag = 0;
		for (int i = 1; i < length; i++) 
          for (int j = 0; j < strings[i].length - 2; j++)
            flag++;
		DFATable dfa[] = new DFATable[flag];
		flag=0;
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < strings[i].length - 2; j++) {
				dfa[flag] = new DFATable();
				dfa[flag].setState(Integer.parseInt(strings[i][0]));
				//System.out.print(strings[i][0]);
				String[] s = null;
				s = strings[0][j].split(" ");
				dfa[flag].setInput(s);
				dfa[flag].setNextState(Integer.parseInt(strings[i][j]));
				flag++;
			}
		}
		for (int i = 0; i < dfa.length; i++) {
			
		  if(dfa[i].getState()==1) {
				dfa[i].setType("标志符");
			}
			if(dfa[i].getState()>=2 && dfa[i].getState()<=7) {
				dfa[i].setType("常数");
			}
			if(dfa[i].getState()>=8 && dfa[i].getState()<=11) {
				dfa[i].setType("注释");
			}
			if(dfa[i].getState()>=12 && dfa[i].getState()<=18 || dfa[i].getState()>=20 && dfa[i].getState()<=28) {
				dfa[i].setType("运算符");
			}
			if(dfa[i].getState()==29||dfa[i].getState()==19) {
				dfa[i].setType("界符");
			}
		}
		return dfa;

	}

	public String[][] showDFA() throws Exception {
		File file = faFile;
		//File file = new File("1.xls");
		String[][] result = getFile(file);
		return result;
	}

	public DFATableState[] getDFAState() throws Exception {
		File file = stateFile;
		String[][] result = getFile(file);
		int rowLength = result.length;

		DFATableState state[] = new DFATableState[39];
		for (int i = 0; i < rowLength; i++) {
			state[i] = new DFATableState();
			state[i].setState(Integer.parseInt(result[i][0]));
			state[i].setFinish(result[i][1].equals("1") ? true : false);
			state[i].setType(result[i][2]);
			// System.out.println(state[i].getState()+" "+state[i].getType()+"
			// "+state[i].isFinish());
		}
		return state;
	}
}

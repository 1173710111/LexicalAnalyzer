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

import cn.hit.domain.DFATable;
import cn.hit.domain.DFATableState;

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

		//解析Excel表格
		POIFSFileSystem fs = new POIFSFileSystem(in);
		// 构建一个新的文档
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFCell cell = null;

		// 按页执行
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);//工作页
			// 按行执行，直到最后一行
			for (int index = 0; index <= sheet.getLastRowNum(); index++) {
				HSSFRow row = sheet.getRow(index);
				if (row == null) {
					continue;
				}

				int tempRow = row.getLastCellNum() + 1;
				if (tempRow > rowSize) {//保证宽度一致
					rowSize = tempRow;
				}
				//按列读入，属性相同更易处理，并且二维数组先处理列更方便
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
						case NUMERIC://数值型-整数、小数、日期
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
						case FORMULA://公式
							//如果为公式生成的数据无值
							if (!cell.getStringCellValue().equals("")) {
								string = cell.getStringCellValue();
							} else {
								string = cell.getStringCellValue() + "";
							}
							break;
						case BLANK://空单元格-没有值
							break;
						case ERROR://错误单元格
							string = "";
							break;
						case BOOLEAN:
							string = (cell.getBooleanCellValue() == true ? "Y" : "N");
							break;
						default:
							string = "";
							break;
						}
					}
					//TRIM去掉两边空白符
					if (cellnum == 0 && string.trim().equals("")) {
						break;
					}
					//处理一下字符串右边的空格，并标记是否已有数据
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
		for (int i = length - 1; i >= 0; i--) {
			if (string.charAt(i) != 0x2) {
				break;
			}
			length--;
		}
		return string.substring(0, length);
	}

	public DFATable[] getDFA() throws Exception {
		//File file = new File("1.xls");
		File file = faFile;
		DFATable dfa[] = new DFATable[817];
		String[][] strings = getFile(file);
		int length = strings.length;

		int flag = 0;
		for (int i = 1; i < length; i++) {
			for (int j = 1; j < strings[i].length - 2; j++) {
				dfa[flag] = new DFATable();
				dfa[flag].setState(Integer.parseInt(strings[i][0]));
				String[] s = null;
				s = strings[0][j].split(" ");
				dfa[flag].setInput(s);
				dfa[flag].setNextState(Integer.parseInt(strings[i][j]));
				flag++;
				
			}
		}
		//System.out.println(flag);
		for (int i = 0; i < dfa.length; i++) {
			DFATableState[] stateTables=getDFAState();
			for (int j=0;j<stateTables.length;j++) {
			  if (stateTables[j].getState()==dfa[i].getState()) {
			    dfa[i].setType(stateTables[j].getType());
			    dfa[i].setFinish(stateTables[j].isFinish());
			  }
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

		DFATableState state[] = new DFATableState[43];
		for (int i = 0; i < rowLength; i++) {
			state[i] = new DFATableState();
			state[i].setState(Integer.parseInt(result[i][0]));
			state[i].setFinish(result[i][1].equals("1") ? true : false);
			state[i].setType(result[i][2]);
		}
		return state;
	}
}

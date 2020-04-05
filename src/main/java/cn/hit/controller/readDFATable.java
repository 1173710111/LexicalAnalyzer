package cn.hit.controller;
/*
 * public class readDFATable {
 * 
 * 
 * //���DFAת�����ֵ public DFATable[] getDFA() { DFATable dfa[] = new DFATable[663];
 * return dfa; } //���״̬�� public DFATableState[] getDFAState() { DFATableState
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
	
	// �ļ�����
	public static String[][] getFile(File file) throws IOException {
		ArrayList<String[]> resultList = new ArrayList<String[]>();
		int rowSize = 0;

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

		//����Excel���
		POIFSFileSystem fs = new POIFSFileSystem(in);
		// ����һ���µ��ĵ�
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFCell cell = null;

		// ��ҳִ��
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);//����ҳ
			// ����ִ�У�ֱ�����һ��
			for (int index = 0; index <= sheet.getLastRowNum(); index++) {
				HSSFRow row = sheet.getRow(index);
				if (row == null) {
					continue;
				}

				int tempRow = row.getLastCellNum() + 1;
				if (tempRow > rowSize) {//��֤���һ��
					rowSize = tempRow;
				}
				//���ж��룬������ͬ���״������Ҷ�ά�����ȴ����и�����
				String[] strings = new String[rowSize];
				Arrays.fill(strings, "");
				Boolean ifHashString = false;
				for (short cellnum = 0; cellnum <= row.getLastCellNum(); cellnum++) {
					String string = "";
					cell = row.getCell(cellnum);
					if (cell != null) {
						// ���ﲻ�Ǻ�ȷ��,������ȡ���ˣ�Ӧ�����Զ�Unicode������
						// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case STRING:
							string = cell.getStringCellValue();
							break;
						case NUMERIC://��ֵ��-������С��������
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
						case FORMULA://��ʽ
							//���Ϊ��ʽ���ɵ�������ֵ
							if (!cell.getStringCellValue().equals("")) {
								string = cell.getStringCellValue();
							} else {
								string = cell.getStringCellValue() + "";
							}
							break;
						case BLANK://�յ�Ԫ��-û��ֵ
							break;
						case ERROR://����Ԫ��
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
					//TRIMȥ�����߿հ׷�
					if (cellnum == 0 && string.trim().equals("")) {
						break;
					}
					//����һ���ַ����ұߵĿո񣬲�����Ƿ���������
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

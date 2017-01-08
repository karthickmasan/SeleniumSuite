package com.aeps.selenium.framework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Read_XLS {
	
	public String fileName;
	public FileInputStream ipstr = null;
	public FileOutputStream opstr = null;
	private XSSFWorkbook wb = null;
	private XSSFSheet ws = null;
	List<String> list = new ArrayList<String>();

	public Read_XLS(String fileName, String relativePath) {
		this.fileName = fileName;
		try {
			ipstr = new FileInputStream(relativePath+fileName);
			wb = new XSSFWorkbook(ipstr);
			ws = wb.getSheetAt(0);
			ipstr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// To retrieve No Of Rows from .xls file's sheets.
	public int retrieveNoOfRows(String wsName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return 0;
		else {
			ws = wb.getSheetAt(sheetIndex);
			int rowCount = ws.getLastRowNum() + 1;
			return rowCount;
		}
	}

	// To retrieve No Of Columns from .cls file's sheets.
	public int retrieveNoOfCols(String wsName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return 0;
		else {
			ws = wb.getSheetAt(sheetIndex);
			int colCount = ws.getRow(0).getLastCellNum();
			return colCount;
		}
	}

	// To retrieve SuiteToRun and CaseToRun flag of test suite and test case.
	public String retrieveToRunFlag(String wsName, String colName, String rowName) {

		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return null;
		else {
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);
			int colNumber = -1;
			int rowNumber = -1;

			XSSFRow Suiterow = ws.getRow(0);

			for (int i = 0; i < colNum; i++) {
				if (Suiterow.getCell(i).getStringCellValue().equals(colName.trim())) {
					colNumber = i;
				}
			}

			if (colNumber == -1) {
				return "";
			}

			for (int j = 0; j < rowNum; j++) {
				XSSFRow Suitecol = ws.getRow(j);
				if (Suitecol.getCell(0).getStringCellValue().equals(rowName.trim())) {
					rowNumber = j;
				}
			}

			if (rowNumber == -1) {
				return "";
			}

			XSSFRow row = ws.getRow(rowNumber);
			XSSFCell cell = row.getCell(colNumber);
			if (cell == null) {
				return "";
			}
			String value = cellToString(cell);
			return value;
		}
	}

	// To retrieve DataToRun flag of test data.
	public String[] retrieveToRunFlagTestData(String wsName, String colName) {

		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return null;
		else {
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);
			int colNumber = -1;

			XSSFRow Suiterow = ws.getRow(0);
			String data[] = new String[rowNum - 1];
			for (int i = 0; i < colNum; i++) {
				if (Suiterow.getCell(i).getStringCellValue().equals(colName.trim())) {
					colNumber = i;
				}
			}

			if (colNumber == -1) {
				return null;
			}

			for (int j = 0; j < rowNum - 1; j++) {
				XSSFRow Row = ws.getRow(j + 1);
				if (Row == null) {
					data[j] = "";
				} else {
					XSSFCell cell = Row.getCell(colNumber);
					if (cell == null) {
						data[j] = "";
					} else {
						String value = cellToString(cell);
						data[j] = value;
					}
				}
			}

			return data;
		}
	}

	// To retrieve test data from test case data sheets.
	public List<String> retrieveTestData(String wsName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return null;
		else {
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);

			for (int i = 0; i < rowNum; i++) {
				XSSFRow row = ws.getRow(i);
				for (int j = 0; j < colNum; j++) {
					if (row == null) {
						list = null;
					} else {
						XSSFCell cell = row.getCell(j);

						if (cell == null) {
							list = null;
						} else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String value = cellToString(cell);
							System.out.println(value);
							list.add(value);
						}
					}
				}
			}
			return list;
		}

	}

	// To retrieve test data from test case data sheets.
	public Object[][] retrieveTestData1(String wsName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return null;
		else {
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);

			Object data[][] = new Object[rowNum - 1][colNum];

			for (int i = 0; i < rowNum - 1; i++) {
				XSSFRow row = ws.getRow(i + 1);
				for (int j = 0; j < colNum; j++) {
					if (row == null) {
						data[i][j] = "";
					} else {
						XSSFCell cell = row.getCell(j);

						if (cell == null) {
							data[i][j] = "";
						} else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String value = cellToString(cell);
							data[i][j] = value;

						}
					}
				}
			}
			return data;
		}

	}

	public static String cellToString(XSSFCell cell) {
		int type;
		Object result;
		type = cell.getCellType();
		switch (type) {
		case 0:
			result = cell.getNumericCellValue();
			break;

		case 1:
			result = cell.getStringCellValue();
			break;

		default:
			throw new RuntimeException("Unsupportd cell.");
		}
		return result.toString();
	}

	// To write result In test data and test case list sheet.
	public boolean writeResult(String wsName, String colName, int rowNumber, String Result) {
		try {
			int sheetIndex = wb.getSheetIndex(wsName);
			if (sheetIndex == -1)
				return false;
			int colNum = retrieveNoOfCols(wsName);
			int colNumber = -1;

			XSSFRow Suiterow = ws.getRow(0);
			for (int i = 0; i < colNum; i++) {
				if (Suiterow.getCell(i).getStringCellValue().equals(colName.trim())) {
					colNumber = i;
				}
			}

			if (colNumber == -1) {
				return false;
			}

			XSSFRow Row = ws.getRow(rowNumber);
			XSSFCell cell = Row.getCell(colNumber);
			if (cell == null)
				cell = Row.createCell(colNumber);

			cell.setCellValue(Result);

			opstr = new FileOutputStream(fileName);
			wb.write(opstr);
			opstr.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// To write result In test suite list sheet.
	public boolean writeResult(String wsName, String colName, String rowName, String Result) {
		try {
			int rowNum = retrieveNoOfRows(wsName);
			int rowNumber = -1;
			int sheetIndex = wb.getSheetIndex(wsName);
			if (sheetIndex == -1)
				return false;
			int colNum = retrieveNoOfCols(wsName);
			int colNumber = -1;

			XSSFRow Suiterow = ws.getRow(0);
			for (int i = 0; i < colNum; i++) {
				if (Suiterow.getCell(i).getStringCellValue().equals(colName.trim())) {
					colNumber = i;
				}
			}

			if (colNumber == -1) {
				return false;
			}

			for (int i = 0; i < rowNum - 1; i++) {
				XSSFRow row = ws.getRow(i + 1);
				XSSFCell cell = row.getCell(0);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String value = cellToString(cell);
				if (value.equals(rowName)) {
					rowNumber = i + 1;
					break;
				}
			}

			XSSFRow Row = ws.getRow(rowNumber);
			XSSFCell cell = Row.getCell(colNumber);
			if (cell == null)
				cell = Row.createCell(colNumber);

			cell.setCellValue(Result);

			opstr = new FileOutputStream(fileName);
			wb.write(opstr);
			opstr.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

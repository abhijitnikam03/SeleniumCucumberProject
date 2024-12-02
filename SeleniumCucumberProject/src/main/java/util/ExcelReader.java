package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * ################# This NALExcelReader util class can be used to read/write with .xls format only. #########
 * ############################### It won't work with .xlsx extension ################################
 */
public class ExcelReader {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	public static int totalRow;

	public List<Map<String, String>> getdata(String path, String sheetName)
			throws EncryptedDocumentException, IOException {
		Workbook workbook = WorkbookFactory.create(new File(path));
		Sheet sheet = workbook.getSheet(sheetName);
		workbook.close();
		return readsheet(sheet);

	}

	private List<Map<String, String>> readsheet(Sheet sheet) {

		Row row;
		Cell cell;

		totalRow = sheet.getLastRowNum();
		List<Map<String, String>> excelRow = new ArrayList<Map<String, String>>();

		for (int currentRow = 1; currentRow <= totalRow; currentRow++) {

			row = sheet.getRow(currentRow);
			int totalColumn = row.getLastCellNum();
			LinkedHashMap<String, String> columnmapdata = new LinkedHashMap<String, String>();

			for (int currentcolumn = 0; currentcolumn < totalColumn; currentcolumn++) {
				cell = row.getCell(currentcolumn);
				String coulunheadername = sheet.getRow(sheet.getFirstRowNum()).getCell(currentcolumn)
						.getStringCellValue();
				columnmapdata.put(coulunheadername, cell.getStringCellValue());
			}
			excelRow.add(columnmapdata);
		}
		return excelRow;

	}

	public int countRow() {
		return totalRow;
	}
}
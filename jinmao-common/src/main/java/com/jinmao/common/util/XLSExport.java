package com.jinmao.common.util;

import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @描述 : 导出Excel工具类
 * @创建者：liushengsong
 * @创建时间： 2014-5-5下午5:33:25
 * 
 */
public class XLSExport {

	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private String fileName;

	/**
	 * @描述 : 初始化Excel
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:33:49
	 * 
	 * @param fileName
	 * @param workbook
	 */
	public XLSExport(String fileName) {
		this.fileName = fileName;
		this.workbook = new HSSFWorkbook();
		this.sheet = workbook.createSheet();
	}

	/**
	 * @描述 : 导出Excel文件
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:36:51
	 * 
	 * @param out
	 */
	public void exportXLS() {
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @描述 : 增加一行
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:37:15
	 * 
	 * @param index
	 */
	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
	}

	/**
	 * @描述 : 设置单元格
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:37:23
	 * 
	 * @param index
	 * @param value
	 */
	@SuppressWarnings("deprecation")
	public void setCell(int index, String value) {
		HSSFCell cell = this.row.createCell((short) index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	/**
	 * @描述 : 设置带样式的单元格
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:38:05
	 * 
	 * @param index
	 * @param value
	 */
	@SuppressWarnings("deprecation")
	public void setCell(int index, Calendar value) {
		HSSFCell cell = this.row.createCell((short) index);
		cell.setCellValue(value.getTime());
		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		cellStyle.setDataFormat(HSSFDataFormat
				.getBuiltinFormat("yyyyMMddHHmmssssssssssssss")); // 设置cell样式为定制的日期格式
		cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式
	}

	/**
	 * @描述 : 设置单元格
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:38:41
	 * 
	 * @param index
	 * @param value
	 */
	@SuppressWarnings("deprecation")
	public void setCell(int index, int value) {
		HSSFCell cell = this.row.createCell((short) index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	/**
	 * @描述 : 设置单元格
	 * @创建者：liushengsong
	 * @创建时间： 2014-5-5下午5:40:04
	 * 
	 * @param index
	 * @param value
	 */
	@SuppressWarnings("deprecation")
	public void setCell(int index, double value) {
		HSSFCell cell = this.row.createCell((short) index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		HSSFDataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(" #,##0.00 ")); // 设置cell样式为定制的浮点数格式
		cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
	}

	public static void main(String[] args) {
		XLSExport x = new XLSExport("/opt/xls/");
		x.createRow(0);
		x.setCell(0, "a");
		x.exportXLS();
	}

}

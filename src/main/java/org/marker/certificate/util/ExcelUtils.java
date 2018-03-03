package org.marker.certificate.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * Excel 工具类
 * @author marker
 *
 *
 * Created by ROOT on 2016/11/2.
 */
public class ExcelUtils {
    /** 日志记录 */
    public static Logger logger = LoggerFactory.getLogger( ExcelUtils.class);


    /**
     * 判断是xls文件还是xlsx文件
     * @param is Excel文件输入流
     * @param suffix 文件后缀
     * @return
     * @throws IOException
     */
    public static Workbook createWorkBook(InputStream is, String suffix)
            throws IOException {
        if (suffix.endsWith("xls")) {
            return new HSSFWorkbook(is);
        }
        if (suffix.endsWith("xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }


    public static Workbook createWorkBook(String filepath)
            throws IOException {
        InputStream is = new FileInputStream(filepath);
        String suffix = getSuffix(filepath);
        return createWorkBook(is, suffix);
    }

    /**
     * 获取文件名称后缀
     * @param path
     * @return 后缀
     */
    public static String getSuffix(String path){
        if (path.lastIndexOf(".") != -1 && path.lastIndexOf(".") != 0) {
            return path.substring(path.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }


    /**
     * 导入Excel Sheet 头部校验
     * @param sheet
     * @param rowNumber 行
     * @param header 头部数组
     * @return
     */
    public static boolean validateHeader(Sheet sheet, int rowNumber, String[] header) {
        Row row = sheet.getRow(rowNumber);
        if(row == null ){
            logger.info("row is null");
            return false;
        }
        if(header == null){
            logger.info("header is null");
            return false;
        }
        int size = header.length;

        for(int i = 0 ; i <size; i++){
            Cell cell = row.getCell(i);
            if(cell == null){
                logger.info("sheet row({}) cell({})", rowNumber , i );
                return false;
            }
            String cellValue = cell.getStringCellValue();
            if(!header[i].equals(cellValue)){// 不相同
//                logger.info("sheet row({}) cell({})={} != {} .",rowNumber,i, header[i] ,cellValue );
                return false;
            }
        }
        return true;
    }


    /**
     * 获取值
     * @param cell Excel 单元格
     * @return
     */
    public static String getValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    public static Date getDate(Cell cell) {
        if (cell == null) {
            return null;
        }
        return cell.getDateCellValue();
    }


    public static Cell sheetAddCell(int col, Object value, Row row) {
        return sheetAddCell(col, value, row, true);
    }

    public static Cell sheetAddCell(int col, Object value, Row row , boolean isStyle) {
        Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        if(isStyle){
            addCellStyle(cell.getCellStyle());// 处理样式
        } else {
//            CellStyle cellStyle = cell.getCellStyle();
//            cellStyle.setBorderBottom(CellStyle.BORDER_NONE);
//            cellStyle.setBorderTop(CellStyle.BORDER_NONE);
//            cellStyle.setBorderLeft(CellStyle.BORDER_NONE);
//            cellStyle.setBorderRight(CellStyle.BORDER_NONE);
        }
        if(value instanceof Integer || value instanceof Long){
            cell.setCellValue(value.toString());
        }else{
            cell.setCellValue((String)value);
        }
        return cell;
    }

    /**
     * 添加表格样式
     * @author marker
     * @param headcellstyle
     */
    public static void addCellStyle(CellStyle headcellstyle){
        headcellstyle.setAlignment(CellStyle.ALIGN_CENTER);
        headcellstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headcellstyle.setBorderBottom(CellStyle.BORDER_THIN);
        headcellstyle.setBorderTop(CellStyle.BORDER_THIN);
        headcellstyle.setBorderLeft(CellStyle.BORDER_THIN);
        headcellstyle.setBorderRight(CellStyle.BORDER_THIN);
        headcellstyle.setWrapText(true);
    }

    /**
     * 获取行，没有就创建
     * @param sheet
     * @param index
     * @return
     */
    public static Row getRow(Sheet sheet, int index) {
        Row row = sheet.getRow(index);
        if(row == null){
            row = sheet.createRow(index);
        }
        row.setHeight((short) 600);
        return row;
    }


    /**
     * 清理边框
     * @param wb
     * @param cell cell
     */
    public static void clearBorder(Workbook wb, Cell cell) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setBorderBottom(CellStyle.BORDER_NONE);
        cellStyle.setBorderTop(CellStyle.BORDER_NONE);
        cellStyle.setBorderLeft(CellStyle.BORDER_NONE);
        cellStyle.setBorderRight(CellStyle.BORDER_NONE);
        cell.setCellStyle(cellStyle);
    }


    /**
     *
     * @param row
     * @param wb
     */
    public static CellStyle clearBorder(Workbook wb, Row row) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setBorderBottom(CellStyle.BORDER_NONE);
        cellStyle.setBorderTop(CellStyle.BORDER_NONE);
        cellStyle.setBorderLeft(CellStyle.BORDER_NONE);
        cellStyle.setBorderRight(CellStyle.BORDER_NONE);
        return cellStyle;
    }
}

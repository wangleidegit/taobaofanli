package com.taobao.fanli.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDEA.
 * User:王磊
 * Date:2016/6/23
 * Time:10:45
 * Use:对上传的excel进行分版本处理
 */
public class ExcelUtils {
    // 对外提供读取excel文件的接口
    public static List<List<Object>> readExcel(File file) throws IOException {
        String fName = file.getName();
        String extension = fName.lastIndexOf(".") == -1 ? "" : fName
                .substring(fName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {// 2003
            return read2003Excel(file);
        } else if ("xlsx".equals(extension)) {// 2007
            return read2007Excel(file);
        } else {
            throw new IOException("不支持的文件类型:" + extension);
        }
    }
    /**
     * 读取2003excel
     *
     * @param file
     * @return
     */
    private static List<List<Object>> read2003Excel(File file)
            throws IOException {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        Object val = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化日期字符串
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int i = sheet.getFirstRowNum(); i < sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            if ((row.getCell(1)==null|| StringUtils.isBlank(row.getCell(1).toString()))&&(row.getCell(0)==null|| StringUtils.isBlank(row.getCell(0).toString()))){
                continue;
            }
            List<Object> objList = new ArrayList<Object>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                //特殊判断赋值
                    if((cell == null||cell.equals(""))  && j==0 && row.getCell(1)!=null){
                        val = "no";
                        objList.add(val);
                        continue;
                    }
                    if (cell == null) {
                        val = null;
                        objList.add(val);
                        continue;
                }

                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_STRING:
                        val = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            val= sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                            //System.out.println("plplpl");

                        } else{
                            val = ""+decimalFormat.format(cell.getNumericCellValue());
                            //System.out.println("ssss");
                        }
                        break;
                   /* val = ""+decimalFormat.format(cell.getNumericCellValue());
                    break;*/
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        val = cell.getBooleanCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        val = "";
                        break;
                    default:
                        val = cell.toString();
                        break;
                }
                objList.add(val);
            }
            dataList.add(objList);
        }
        return dataList;
    }
    /**
     * 读取excel表头
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String[] readExcelHead(File file) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        row = sheet.getRow(0);
        String[] buff = new String[row.getLastCellNum()];
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            cell = row.getCell(i);
            buff[i] = cell.getStringCellValue();
        }
        return buff;
    }
    /**
     * 读取2007excel
     *
     * @param file
     * @return
     */
    private static List<List<Object>> read2007Excel(File file)
            throws IOException {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;
        Object val = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化日期字符串
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int i = sheet.getFirstRowNum(); i < sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> objList = new ArrayList<Object>();

            if ((row.getCell(1)==null||StringUtils.isBlank(row.getCell(1).toString())) &&(row.getCell(0)==null|| StringUtils.isBlank(row.getCell(0).toString()))){
                continue;
            }
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                //特殊判断赋值
                if(cell == null  && j==0 && row.getCell(1)!=null){
                    val = "no";
                    objList.add(val);
                    continue;
                }

                if (cell == null) {
                    val = null;
                    objList.add(val);
                    continue;
                }
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        val = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            val= sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                            //System.out.println("plplpl");

                        } else{
                            val = ""+decimalFormat.format(cell.getNumericCellValue());
                            //System.out.println("ssss");
                        }
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        val = cell.getBooleanCellValue();
                        break;
                    case  XSSFCell.CELL_TYPE_BLANK:
                        val = "";
                        break;
                    default:
                        val = cell.toString();
                        break;
                }
                objList.add(val);
            }
            dataList.add(objList);
        }
        return dataList;
    }
}

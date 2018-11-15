package com.yicheejia.common.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;

import org.apache.commons.configuration.Configuration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yicheejia.common.exception.RRException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * excel工具类
 *
 *
 * @since 2018-03-24
 */
public class ExcelUtils {

    /**
     * Excel导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param list          数据List
     * @param pojoClass     对象Class
     */
    public static void exportExcel(HttpServletResponse response, String fileName, Collection<?> list,
                                     Class<?> pojoClass) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * Excel导出，先sourceList转换成List<targetClass>，再导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param sourceList    原数据List
     * @param targetClass   目标对象Class
     */
    public static void exportExcelToTarget(HttpServletResponse response, String fileName, Collection<?> sourceList,
                                     Class<?> targetClass) throws Exception {
        List targetList = new ArrayList<>(sourceList.size());
        for(Object source : sourceList){
            Object target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        exportExcel(response, fileName, targetList, targetClass);
    }
    /**
     * Excel导入功能
     * @param file
     * @param titleRows  表格标题行数,默认0
     * @param headerRows 表头行数,默认1
     * @param pojoClass  目标对象Class
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RRException("excel文件不能为空");
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
        return list;
    }
    
   /**
    * Excel导入功能--账单核对中导入POS流水
    * @param file
    * @return
    */
    public static Map<String, Object> readExcel(MultipartFile file){
	      List<String> list = null;
	      Map<String, Object> map = new HashMap<>();
	      
	      try { 
	        //同时支持Excel 2003、2007 
	        FileInputStream inputStream=(FileInputStream) file.getInputStream();
	        Workbook workbook = WorkbookFactory.create(inputStream); //这种方式 Excel 2003/2007/2010 都是可以处理的 
	       //存储数据容器 
	        list = new ArrayList<String>();
	       
	        //默认只有一个sheet
	        	Sheet sheet = workbook.getSheetAt(0); 
	        	//获取交易日期
	        	Row row2 = sheet.getRow(4); 
	        	Cell cell2=row2.getCell(0);
	        	String payDate =  cell2.getStringCellValue();
	        	map.put("payDate", payDate);
	        	//遍历行
	        	for(int r=11;r<r+1;r++){
	        		//用来存储每行数据的容器 
	        		Row row = sheet.getRow(r); 
	        		if (row==null) {
	        			map.put("list", list);
	        			inputStream.close();     
						return map;
					}
	        		String cellValue = ""; //用来存储数据
	        		Cell cell=row.getCell(0);
	        		cellValue = cell.getStringCellValue();
	        		list.add(cellValue);
					}
	      }catch (Exception e) { 
	         throw new RRException("上传文件格式不正确");
	      }
	      
	    return map;
	}
    /**
     * Excel导入功能--确认收款中导入银行统一模板
     * @param file
     * @return
     */
    public static List<String[]> readZExcel(MultipartFile file){
    	List<String[]> list = null;
    	try { 
    		//同时支持Excel 2003、2007 
    		FileInputStream inputStream=(FileInputStream) file.getInputStream();
    		Workbook workbook = WorkbookFactory.create(inputStream); //这种方式 Excel 2003/2007/2010 都是可以处理的 
    		//存储数据容器 
    		list = new ArrayList<String[]>();
    		DecimalFormat df = new DecimalFormat("0.00");
    		//默认只有一个sheet
    		Sheet sheet = workbook.getSheetAt(0); 
    		//遍历行，从第二行开始
    		for(int r=1;r<r+1;r++){
    			//用来存储每行数据的容器 
    			Row row = sheet.getRow(r); 
    			if (row==null) {
    				inputStream.close();     
    				return list;
    			}
    			String[] model = new String[7]; 
    			Cell cell0 =row.getCell(1);//支付类别
    			Cell cell =row.getCell(2);//交易时间
    			Cell cell1=row.getCell(3);//贷方发生额（收入）
    			Cell cell2=row.getCell(4);//对方户名
    			Cell cell3=row.getCell(5);//对方账号
    			Cell cell4=row.getCell(6);//备注
    			Cell cell5=row.getCell(7);//账户明细编号-交易流水号
//    			Date theDate = cell.getDateCellValue();
//    			SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
//    			SimpleDateFormat dff = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    			model[0] = cell0.getStringCellValue();
//    			model[1] = dff.format(theDate);
    			model[1] = cell.getStringCellValue();
    			model[2] = String.valueOf(df.format(cell1.getNumericCellValue()));
    			model[3] = cell2.getStringCellValue();
    			model[4] = cell3.getStringCellValue();
    			model[5] = cell4.getStringCellValue();
    			model[6] = cell5.getStringCellValue();
    			list.add(model);
    		}
    	}catch (Exception e) { 
    		throw new RRException("上传文件格式不正确");
    	}
    	
    	return list;
    }
    /**
     * Excel导入功能--账单核对中导入建行流水
     * @param file
     * @return
     */
    public static List<String[]> readJExcel(MultipartFile file){
    	List<String[]> list = null;
    	try { 
    		//同时支持Excel 2003、2007 
    		FileInputStream inputStream=(FileInputStream) file.getInputStream();
    		Workbook workbook = WorkbookFactory.create(inputStream); //这种方式 Excel 2003/2007/2010 都是可以处理的 
    		//存储数据容器 
    		list = new ArrayList<String[]>();
    		DecimalFormat df = new DecimalFormat("0.00");  
    		//默认只有一个sheet
    		Sheet sheet = workbook.getSheetAt(0); 
    		//遍历行，从第二行开始
    		for(int r=1;r<r+1;r++){
    			//用来存储每行数据的容器 
    			Row row = sheet.getRow(r); 
    			if (row==null) {
    				return list;
    			}
    			String[] model = new String[2]; 
    			Cell cell=row.getCell(3);
    			Cell cell2=row.getCell(11);
    			model[0]=String.valueOf(df.format(cell.getNumericCellValue()));
    			model[1]=cell2.getStringCellValue();
    			list.add(model);
    		}
    		inputStream.close();     
    	}catch (Exception e) { 
    		throw new RRException("文件上传格式不正确");
    	}
    	
    	return list;
    }
    /**
     * excel下载模板
     */
    public static void downloadTemplate(HttpServletResponse response,HttpServletRequest request,String templeteName) throws IOException {
        OutputStream outp = null;
        FileInputStream in = null;
        try {
            String fileName = templeteName; //要下载的模板文件
            if(templeteName!=null){
                if(!templeteName.endsWith(".xls")){
                    fileName = templeteName + ".xls";
                }
            }
//            String ctxPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "template" + File.separator;
            Configuration config = ConfigUtil.getConfig();
    		String ctxPath = config.getString("downPath")+ File.separator ;
            String filedownload = ctxPath + fileName;
            fileName = URLEncoder.encode(fileName, "UTF-8");
            // 要下载的模板所在的绝对路径
            response.reset();
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outp = response.getOutputStream();
            in = new FileInputStream(filedownload);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }
            outp.flush();
        } catch (Exception e) {
        	throw new RRException(e.getMessage());
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
            if (outp != null) {
                outp.close();
                outp = null;
            }
        }
    }
}

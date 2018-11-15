package com.yicheejia.modules.order.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.order.dao.SalePromotionDao;
import com.yicheejia.modules.order.entity.SalePromotionEntity;
import com.yicheejia.modules.order.service.SalePromotionService;


@Service("salePromotionService")
public class SalePromotionServiceImpl extends ServiceImpl<SalePromotionDao, SalePromotionEntity> implements SalePromotionService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {

        Page<SalePromotionEntity> page = new Query<SalePromotionEntity>(params).getPage();
        List<SalePromotionEntity> records = baseMapper.querySalePromotionList(page,params);
        page.setRecords(records);
        return new LayuiPage(page.getRecords(), page.getTotal());
    }
    
	@Override
	public void inportsaleorder(String fileName , MultipartFile file) {

		List<SalePromotionEntity> salePromotionList = new ArrayList<SalePromotionEntity>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new RRException("上传文件格式不正确");
        }
		boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
		try {
			InputStream is = file.getInputStream();
			Workbook wb = null;
	        if (isExcel2003) {
	            wb = new HSSFWorkbook(is);
	        } else {
	            wb = new XSSFWorkbook(is);
	        }
			//获取sheet1
	        Sheet sheet = wb.getSheetAt(0);
	        //设置单元格类型
//	        cell.setCellType(CellType.STRING);
	        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
	            Row row = sheet.getRow(r);
	            if (row == null){
	                continue;
	            }
	 
	            SalePromotionEntity salePromotionEntity = new SalePromotionEntity();
	            
	            //订单编号
	            String saleCode = row.getCell(0).getStringCellValue();
	            //商品总金额
	            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
	            String goodsPrice = row.getCell(1).getStringCellValue();
	            //抵扣金额
	            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
	            String deductionPrice = row.getCell(2).getStringCellValue();
	            //有效期止
	            Date endDate = row.getCell(3).getDateCellValue();
	            //支付方式
	            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
	            String payType = row.getCell(4).getStringCellValue();
	            //支付金额
	            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
	            String payMoney = row.getCell(5).getStringCellValue();
	            //购买方式
	            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
	            String buyType = row.getCell(6).getStringCellValue();
	            //支付流水号
	            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
	            String payNumber = row.getCell(7).getStringCellValue();
	            //名称
	            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
	            String saleName = row.getCell(8).getStringCellValue();
	            //姓名
	            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
	            String saleContact = row.getCell(9).getStringCellValue();
	            //电话
	            row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
	            String saleMobile = row.getCell(10).getStringCellValue();
	            //地址
	            row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
	            String saleAdd = row.getCell(11).getStringCellValue();
	            //留言
	            row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
	            String saleComment = row.getCell(12).getStringCellValue();
	            
//	            DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//	            Date date = format.parse(endDate);
	            
	            salePromotionEntity.setSaleCode(saleCode);
	            salePromotionEntity.setGoodsPrice(new BigDecimal(goodsPrice));
	            salePromotionEntity.setDeductionPrice(new BigDecimal(deductionPrice));
	            salePromotionEntity.setEndDate(endDate);
	            salePromotionEntity.setPayType(payType);
	            salePromotionEntity.setPayMoney(new BigDecimal(payMoney));
	            salePromotionEntity.setBuyType(buyType);
	            salePromotionEntity.setPayNumber(payNumber);
	            salePromotionEntity.setSaleName(saleName);
	            salePromotionEntity.setSaleContact(saleContact);
	            salePromotionEntity.setSaleMobile(saleMobile);
	            salePromotionEntity.setSaleAdd(saleAdd);
	            salePromotionEntity.setSaleComment(saleComment);
	            
	            salePromotionEntity.setInsertTime(new Date());
	            salePromotionEntity.setIsUsed("0");
	            
//	            baseMapper.insert(salePromotionEntity);
	 
	            salePromotionList.add(salePromotionEntity);
	        }
	        baseMapper.insertSalePromotionList(salePromotionList);
	        
		} catch (Exception e) {
			e.printStackTrace();
			throw new RRException("模板数据异常！");
		}
	}

	@Override
	public void updateBySaleId(Map<String, Object> params) {

		baseMapper.updateBySaleId(params);
		
	}

	@Override
	public List<Map<String, Object>> getPromotionList(Map<String, Object> params) {
		//数据库模板导入的是汉字，处理一下
		if(params.get("buyType").equals("1")) {
			params.put("buyType","全款");
		}
		else if(params.get("buyType").equals("2"))
		{
			params.put("buyType","分期");
		}
		else if(params.get("buyType").equals("3"))
		{
			params.put("buyType","优壹");
		}
		
		return baseMapper.getPromotionList(params);
	}

	/**
	 * 只获取智跑抵用券
	 */
	@Override
	public List<Map<String, Object>> getZPPromotionList(Map<String, Object> params) {
		//数据库模板导入的是汉字，处理一下
		params.put("buyType","智跑");
		return baseMapper.getZPPromotionList(params);
	}

	@Override
	public List<Map<String, Object>> getPromotionListByOrderId(Map<String, Object> params) {
		return baseMapper.getPromotionListByOrderId(params);
	}

}

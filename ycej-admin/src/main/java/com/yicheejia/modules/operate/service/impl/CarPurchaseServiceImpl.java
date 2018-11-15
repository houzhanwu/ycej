package com.yicheejia.modules.operate.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.EtchedBorder;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.utils.SerialNoSerUtils;
import com.yicheejia.common.utils.redis.SerialType;
import com.yicheejia.modules.operate.dao.CarPurchaseDao;
import com.yicheejia.modules.operate.entity.CarPurchaseEntity;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.entity.PurchDetailList;
import com.yicheejia.modules.operate.entity.PurchaseDetailEntity;
import com.yicheejia.modules.operate.service.CarPurchaseService;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.operate.service.PurchaseDetailService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.yicheejia.modules.upload.service.YcejFileService;


@Service("carPurchaseService")
public class CarPurchaseServiceImpl extends ServiceImpl<CarPurchaseDao, CarPurchaseEntity> implements CarPurchaseService {

	@Autowired
	private PurchaseDetailService purchaseDetailService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private YcejFileService ycejFileService;
	@Autowired
	SerialNoSerUtils serialNoSerUtils; 
	protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	EntityWrapper<CarPurchaseEntity> wrapper= new EntityWrapper<>();
    	//供应商名称
    	Object supplier_id = params.get("supplier_id");
    	//采购日期
    	Object purch_date = params.get("purch_date");
        Page<CarPurchaseEntity> page = new Query<CarPurchaseEntity>(params).getPage();
        page.setRecords(baseMapper.queryCarPurchase(page,params));
        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	

	@Override
	public void updateCarPurchase(CarPurchaseEntity carPurchase) {
		try {
			//财务审核
			carPurchase.setUpdateId(ShiroUtils.getUserEntity().getName());
			carPurchase.setUpdateTime(new Date());
//			carPurchase.setIsPay(1);//财务提交审核通过，表示已付款
			baseMapper.updateById(carPurchase);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RRException("审核失败！");
		}
			
	}
	//只操作工作流
	@Transactional
	@Override
	//@ExecuteTask(id = "purchId" ,processKey=WfConstants.PROCESS_PURCHASE_KEY,args = {"supplierName"})
	public R stock(CarPurchaseEntity carPurchase){
		//add by fkm 20180915 新增采购时选择保证金车时财务确认后流程结束begin
		//业务场景为保证金车为门店展车，销售时再次走采购，但车已经在库，所以提交申请单财务确认流程结束
		String action = "";
		try {
			Integer carSource = carPurchase.getCarFrom();//车辆来源
			if( carSource ==  2){
				List<PurchaseDetailEntity> list = carPurchase.getDetailList();
				Map<String, Object> map = new HashMap<String, Object>();
				for(PurchaseDetailEntity detail : list){
					String vin = detail.getVin();//采购明细一批都为保证金车
					if(vin != null){
						map.put("vin", vin);
						List<InventoryEntity> inventoryList = inventoryService.selectByMap(map);//车架号只能一条
						if( inventoryList.size() > 0){//保证金车
							InventoryEntity inventoryEntity = new InventoryEntity();
							inventoryEntity.setCarSource(1);//修改为全款车
							inventoryEntity.setInventoryId(inventoryList.get(0).getInventoryId());
							inventoryEntity.setUpdateTime(new Date());
							inventoryService.updateById(inventoryEntity);
							//stock(carPurchase);
							carPurchase.setStatus(5);//已完成
							carPurchase.setCarFrom(1);
							baseMapper.updateById(carPurchase);
							action = "-1";
						}
					}
				}
			}
			//add by fkm 20180915 新增采购时选择保证金车时财务确认后流程结束 end
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RRException("审核失败！");
		}
		return R.ok().put("action", action);//填写备注信息
	}
	
	@Transactional
	@Override
	public void holeSave(CarPurchaseEntity carPurchase) {
		//新增暂存时保存状态为暂存
		String purchNo=serialNoSerUtils.getSerialNumber(SerialType.purchase_no,"P");
		carPurchase.setPurchNo(purchNo);
		carPurchase.setIsPay(0);//新增采购单时默认为未付款
		carPurchase.setInsertTime(new Date());
		carPurchase.setInsertId(ShiroUtils.getUserEntity().getName());
		carPurchase.setUpdateTime(new Date());
		baseMapper.insert(carPurchase);
		if (carPurchase.getDetailList()!=null) {
			for (PurchaseDetailEntity purchaseDetailEntity : carPurchase.getDetailList()) {
				purchaseDetailEntity.setPurchId(carPurchase.getPurchId().toString());
				//新增
//				purchaseDetailService.insert(purchaseDetailEntity);
				purchaseDetailEntity.setPurchDetailId(null);
				purchaseDetailEntity.setAddPurchaseDetail("0");
				purchaseDetailService.insert(purchaseDetailEntity);
				System.err.println(purchaseDetailEntity.getPurchDetailId());
			}
		}
	}
	
	@Override
	@Transactional
	public void updateAll(CarPurchaseEntity carPurchase) {
		//修改暂存不需要更改状态
		 
		String update_id=ShiroUtils.getUserEntity().getName();
    	carPurchase.setUpdateId(update_id);
    	carPurchase.setUpdateTime(new Date());
		baseMapper.updateById(carPurchase);
		if (carPurchase.getDetailList()!=null) {
			for (PurchaseDetailEntity purchaseDetailEntity : carPurchase.getDetailList()) {
				purchaseDetailEntity.setPurchId(carPurchase.getPurchId().toString());
				//addPurchaseDetail是前台传递的标识，用来区分是修改还是新增
				if (!"1".equals(purchaseDetailEntity.getAddPurchaseDetail())) {
					//修改
						purchaseDetailService.updateById(purchaseDetailEntity);
				}else {
					//新增
					purchaseDetailEntity.setPurchDetailId(null);
					purchaseDetailEntity.setAddPurchaseDetail("0");
					purchaseDetailService.insert(purchaseDetailEntity);
					System.err.println(purchaseDetailEntity.getPurchDetailId());
				}
				
			}
		}
	}
	@Transactional
	@Override
	public void insertReturnId(CarPurchaseEntity carPurchase) {
		//新增提交状态改为财务待审核
		carPurchase.setStatus(2);//这里状态先写死，后面再统一写到静态常量中
		String update_id=ShiroUtils.getUserEntity().getName();
		carPurchase.setInsertId(update_id);
		carPurchase.setInsertTime(new Date());
		//生成订单编号
		String purchNo=serialNoSerUtils.getSerialNumber(SerialType.purchase_no,"P");
		carPurchase.setPurchNo(purchNo);
		carPurchase.setIsPay(0);//新增采购单时默认为未付款
		carPurchase.setUpdateTime(new Date());
		baseMapper.insert(carPurchase);
		if (carPurchase.getDetailList()!=null) {
			for (PurchaseDetailEntity purchaseDetailEntity : carPurchase.getDetailList()) {
				purchaseDetailEntity.setPurchId(carPurchase.getPurchId().toString());
				//新增
				purchaseDetailService.insert(purchaseDetailEntity);
			}
		}
	}
	
	@Override
	public void tiJiaoUpdate(CarPurchaseEntity carPurchase) {
		
		if (carPurchase.getPurchId()!=null) {
			//修改提交，更新状态为财务待审核
			String update_id=ShiroUtils.getUserEntity().getName();
			carPurchase.setUpdateId(update_id);
			carPurchase.setUpdateTime(new Date());
			//更新状态为财务待审核，这里先写死，后期统一改为静态常量
			carPurchase.setStatus(2);
			baseMapper.updateById(carPurchase);
			if (carPurchase.getDetailList()!=null) {
				for (PurchaseDetailEntity purchaseDetailEntity : carPurchase.getDetailList()) {
					purchaseDetailEntity.setPurchId(carPurchase.getPurchId().toString());
					if (!"1".equals(purchaseDetailEntity.getAddPurchaseDetail())) {
						//修改
						purchaseDetailService.updateById(purchaseDetailEntity);
					}else {
						//新增
						purchaseDetailEntity.setPurchDetailId(null);
						purchaseDetailEntity.setAddPurchaseDetail("0");
						purchaseDetailService.insert(purchaseDetailEntity);
					}
				}
			}
		}else {
			//新增提交状态改为财务待审核
			String update_id=ShiroUtils.getUserEntity().getName();
			carPurchase.setInsertId(update_id);
			carPurchase.setInsertTime(new Date());
			String purchNo=serialNoSerUtils.getSerialNumber(SerialType.purchase_no,"P");
			carPurchase.setPurchNo(purchNo);
			carPurchase.setUpdateTime(new Date());
			baseMapper.insert(carPurchase);
			if (carPurchase.getDetailList()!=null && carPurchase.getDetailList().size()>0) {
				for (PurchaseDetailEntity purchaseDetailEntity : carPurchase.getDetailList()) {
					purchaseDetailEntity.setPurchId(carPurchase.getPurchId().toString());
					//新增
					purchaseDetailEntity.setPurchDetailId(null);
					purchaseDetailEntity.setAddPurchaseDetail("0");
					purchaseDetailService.insert(purchaseDetailEntity);
				}
			}
		}
		
	}


	
	@Override
	@Transactional
	public void putStock(CarPurchaseEntity carPurchase) {
		//入库操作
		String update_id=ShiroUtils.getUserEntity().getName();
    	carPurchase.setUpdateId(update_id);
    	carPurchase.setUpdateTime(new Date());
    	//将采购状态更新为已入库
    	baseMapper.updateById(carPurchase);
    	if (carPurchase.getDetailList()!=null && carPurchase.getDetailList().size()>0) {
    		for (PurchaseDetailEntity purchaseDetail : carPurchase.getDetailList()) {
    			//根据采购明细获取附件主键，获取到附加路径，保存到库存表中
    			InventoryEntity inventoryEntity =new InventoryEntity();
    			if (StringUtils.isNotBlank(purchaseDetail.getFileId())) {
    				PurchaseDetailEntity purchase=purchaseDetailService.selectById(purchaseDetail.getPurchDetailId());
    				YcejFileEntity fileEntity=ycejFileService.selectById(purchase.getFileId());
    				inventoryEntity.setCertificateAdd(fileEntity.getFilePath());//上传的合格证地址
				}
    			//将采购明细保存到库存表中
    			inventoryEntity.setCarBrand(purchaseDetail.getBrandId());//品牌ID
    			inventoryEntity.setCarManufacturer(purchaseDetail.getManufacturerId());//厂家ID
    			inventoryEntity.setCarSeries(purchaseDetail.getSeriesId());//车系ID
    			inventoryEntity.setCarModel(purchaseDetail.getModelId());//车型ID
    			inventoryEntity.setCarColour(purchaseDetail.getColour());//车身颜色
    			inventoryEntity.setInteriorColor(purchaseDetail.getInteriorColor());//内饰颜色
    			inventoryEntity.setVin(purchaseDetail.getVin());//车架号
    			inventoryEntity.setEngineId(purchaseDetail.getEngineNo());//发动机号
    			inventoryEntity.setManufactureDate(purchaseDetail.getProductDate());//出厂日期
    			inventoryEntity.setStorageDate(carPurchase.getInboundDate());//入库日期
    			inventoryEntity.setInsertId(ShiroUtils.getUserEntity().getUserId().intValue());//操作人
    			inventoryEntity.setReceiptPrice(purchaseDetail.getPurchInvoicePrice());//采购发票价
    			inventoryEntity.setCostPrice(purchaseDetail.getPurchCostPrice());//采购成本价
    			inventoryEntity.setSalePrice(purchaseDetail.getSugSalePrice());//活动销售价
    			inventoryEntity.setCarSource(carPurchase.getCarFrom());//车辆来源
    			inventoryEntity.setDinsurance(carPurchase.getStoreInfo());//店保信息
    			inventoryEntity.setCarStatus(1);//默认状态为车在库
    			inventoryEntity.setWarehouseId(carPurchase.getWarehouse());//仓库
    			inventoryEntity.setSupplierId(Integer.valueOf(carPurchase.getSupplierId()));//供应商id
    			//插入数据前需要现在仓库里查询车架号是否已存在
    			if(StringUtils.isNotBlank(purchaseDetail.getVin())) {
    				Map<String, Object> map = new HashMap<>();
    				map.put("vin", purchaseDetail.getVin());
    				List<InventoryEntity> inventoryList;
						inventoryList = inventoryService.selectByMap(map);
						if (inventoryList.size()>0) {
							logger.error("车架号已存在！");
							throw new RRException("车架号已存在,请核对后再输入");
						}
				}
    			inventoryService.insert(inventoryEntity);
    			//更新采购明细表,并保存库存主键
    			purchaseDetail.setInventoryId(String.valueOf(inventoryEntity.getInventoryId()));
    			purchaseDetailService.updateById(purchaseDetail);
    		}
		}
	}


	/**
	 * 车辆查询中根据主键获取采购单详细信息
	 */
	@Override
	public CarPurchaseEntity selectDetailById(String purch_id) {
		// TODO Auto-generated method stub
		return baseMapper.selectDetailById(purch_id);
	}

}

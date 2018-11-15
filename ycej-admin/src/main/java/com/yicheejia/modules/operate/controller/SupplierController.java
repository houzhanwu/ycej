package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.StartFlow;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.common.validator.group.AddGroup;
import com.yicheejia.common.validator.group.UpdateGroup;
import com.yicheejia.modules.operate.entity.SupplierFileEntity;
import com.yicheejia.modules.operate.service.SupplierCarbrandService;
import com.yicheejia.modules.operate.service.SupplierChangeService;
import com.yicheejia.modules.operate.service.SupplierFileService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.entity.SupplierCarbrandEntity;
import com.yicheejia.modules.operate.entity.SupplierChangeEntity;
import com.yicheejia.modules.operate.entity.SupplierEntity;
import com.yicheejia.modules.operate.service.SupplierService;
import com.yicheejia.modules.order.entity.OrderFileEntity;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 供应商信息表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
@RestController
@RequestMapping("operate/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplierFileService supplierFileService;
    @Autowired
    private SupplierCarbrandService supplierCarbrandService;
    @Autowired
    private SupplierChangeService supplierChangeService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:supplier:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = supplierService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{supplierId}")
    @RequiresPermissions("operate:supplier:info")
    public R info(@PathVariable("supplierId") Integer supplierId){
        SupplierEntity supplier = supplierService.selectById(supplierId);

//        SupplierFileEntity fileEntity = supplierFileService.selectBySupplierId(supplierId);
        //根据供应商id获取附件列表
        List<SupplierFileEntity> list = supplierFileService.selectListBySupplierId(supplierId);
        //根据供应商id获取车辆品牌列表
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("supplierId", supplierId);
        List<SupplierCarbrandEntity> brandEntityList=supplierCarbrandService.selectDataByMap(columnMap);
        supplier.setCarBrandList(brandEntityList);
        return R.ok().put("supplier", supplier).put("list", list);
    }

    /**
     * 保存
     */
    @SysLog(value = "供应商保存",id="supplierId")
    @RequestMapping("/save")
    @RequiresPermissions("operate:supplier:save")
    @Transactional(rollbackFor = Exception.class)
    public R save(@RequestBody SupplierEntity supplier){
        ValidatorUtils.validateEntity(supplier, AddGroup.class);

        supplier.setSupplierState(1); //暂存
        supplier.setInsertUser(ShiroUtils.getUserEntity().getName());
        supplier.setInsertTime(new Date());
        supplier.setUpdateTime(DateUtils.getDate());
        supplier.setUpdateUser(ShiroUtils.getUserEntity().getName());
        supplierService.insertByCondition(supplier);

       /* SupplierFileEntity supplierFileEntity = new SupplierFileEntity();
        supplierFileEntity.setSupplierId(supplier.getSupplierId());
        supplierFileEntity.setFileId(supplier.getFileId());
        supplierFileEntity.setLicenseImg(supplier.getLicenseImg());
        supplierFileEntity.setInsUser(ShiroUtils.getUserEntity().getName());
        supplierFileEntity.setInsTime(new Date());
        supplierFileService.insert(supplierFileEntity);*/
        //保存成功后将车辆品牌保存到供应商品牌关联表中
        if (supplier.getCarBrandList()!=null && supplier.getCarBrandList().size()>0 ) {
        	for (SupplierCarbrandEntity carbrandEntity : supplier.getCarBrandList()) {
        		carbrandEntity.setSupplierId(String.valueOf(supplier.getSupplierId()));
        		carbrandEntity.setSupplierName(supplier.getSupplierName());
        		carbrandEntity.setInsertUser(supplier.getInsertUser());
        		carbrandEntity.setUpdateUser(supplier.getInsertUser());
        		carbrandEntity.setInsertTime(DateUtils.getDate());
        		carbrandEntity.setUpdateTime(DateUtils.getDate());
        	}
        	//批量插入
        	supplierCarbrandService.insertBatch(supplier.getCarBrandList());
		}
        //将供应商记录到供应商变更记录表中
        SupplierChangeEntity entity = new SupplierChangeEntity();
        entity.setBranchBank(supplier.getBranchBank());
        entity.setCarBrand(supplier.getCarBrand());
        entity.setCarBrandName(supplier.getCarBrandName());
        entity.setCityId(supplier.getCityId());
        entity.setCityName(supplier.getCityName());
        entity.setComment(supplier.getComment());
        entity.setDueAccountName(supplier.getDueAccountName());
        entity.setVehicleType(Integer.valueOf(supplier.getVehicleType()));
        if (StringUtils.isNotBlank(supplier.getDueAccountType())) {
        	entity.setDueAccountType(Integer.valueOf(supplier.getDueAccountType()));
		}
        entity.setDueBank(supplier.getDueBank());
        entity.setDueBankCode(supplier.getDueBankCode());
        entity.setInsertTime(DateUtils.getDate());
        entity.setInsertUser(ShiroUtils.getUserEntity().getName());
        entity.setProvinceId(supplier.getProvinceId());
        entity.setProvinceName(supplier.getProvinceName());
        entity.setSupplierAdd(supplier.getSupplierAdd());
        entity.setSupplierBail(supplier.getSupplierBail());
        entity.setSupplierBankcard(supplier.getSupplierBankcard());
        entity.setSupplierCarnum(supplier.getSupplierCarnum());
        entity.setSupplierContact(supplier.getSupplierContact());
        entity.setSupplierId(String.valueOf(supplier.getSupplierId()));
        entity.setSupplierMoblie(supplier.getSupplierMoblie());
        entity.setSupplierName(supplier.getSupplierName());
        entity.setSupplierState(supplier.getSupplierState());
        if (StringUtils.isNotBlank(supplier.getSupplierType())) {
        	entity.setSupplierType(Integer.valueOf(supplier.getSupplierType()));
		}
        entity.setUpdateTime(DateUtils.getDate());
        entity.setUpdateUser(ShiroUtils.getUserEntity().getName());
        if (StringUtils.isNotBlank(supplier.getVehicleType())) {
        	entity.setVehicleType(Integer.valueOf(supplier.getVehicleType()));
		}
        supplierChangeService.insert(entity);
        return R.ok().put("supplier",supplier);
    }

    /**
     * 修改
     */
    @SysLog("供应商修改")
    @RequestMapping("/update")
    @RequiresPermissions("operate:supplier:update")
    @Transactional(rollbackFor = Exception.class)
    public R update(@RequestBody SupplierEntity supplier){
        ValidatorUtils.validateEntity(supplier, UpdateGroup.class);
        //供应商品牌关联表中删除对应数据
        Map<String, Object> map = new HashMap<>();
        map.put("supplier_id", supplier.getSupplierId());
        supplierCarbrandService.deleteByMap(map);
        supplierService.updateById(supplier);
        //更新成功后将车辆品牌保存到供应商品牌关联表中
        if (supplier.getCarBrandList()!=null && supplier.getCarBrandList().size()>0 ) {
        	for (SupplierCarbrandEntity carbrandEntity : supplier.getCarBrandList()) {
        		carbrandEntity.setSupplierId(String.valueOf(supplier.getSupplierId()));
        		carbrandEntity.setSupplierName(supplier.getSupplierName());
        		carbrandEntity.setInsertUser(supplier.getInsertUser());
        		carbrandEntity.setUpdateUser(supplier.getInsertUser());
        		carbrandEntity.setInsertTime(DateUtils.getDate());
        		carbrandEntity.setUpdateTime(DateUtils.getDate());
        	}
        	//批量插入
        	supplierCarbrandService.insertBatch(supplier.getCarBrandList());
		}
        //根据供应商id获取供应商变更记录
        Map<String, Object>	columnMap = new HashMap<>();
        columnMap.put("supplier_id", supplier.getSupplierId());
        List<SupplierChangeEntity> changeEntityList = supplierChangeService.selectByMap(columnMap);
        SupplierChangeEntity entity = changeEntityList.get(0);
      //将供应商记录到供应商变更记录表中
//        SupplierChangeEntity entity = new SupplierChangeEntity();
        entity.setBranchBank(supplier.getBranchBank());
        entity.setCarBrand(supplier.getCarBrand());
        entity.setCarBrandName(supplier.getCarBrandName());
        entity.setCityId(supplier.getCityId());
        entity.setCityName(supplier.getCityName());
        entity.setComment(supplier.getComment());
        entity.setDueAccountName(supplier.getDueAccountName());
        if (StringUtils.isNotBlank(supplier.getDueAccountType())) {
        	entity.setDueAccountType(Integer.valueOf(supplier.getDueAccountType()));
		}
        entity.setVehicleType(Integer.valueOf(supplier.getVehicleType()));
        entity.setDueBank(supplier.getDueBank());
        entity.setDueBankCode(supplier.getDueBankCode());
        entity.setProvinceId(supplier.getProvinceId());
        entity.setProvinceName(supplier.getProvinceName());
        entity.setSupplierAdd(supplier.getSupplierAdd());
        entity.setSupplierBail(supplier.getSupplierBail());
        entity.setSupplierBankcard(supplier.getSupplierBankcard());
        entity.setSupplierCarnum(supplier.getSupplierCarnum());
        entity.setSupplierContact(supplier.getSupplierContact());
        entity.setSupplierId(String.valueOf(supplier.getSupplierId()));
        entity.setSupplierMoblie(supplier.getSupplierMoblie());
        entity.setSupplierName(supplier.getSupplierName());
        entity.setSupplierState(supplier.getSupplierState());
        if (StringUtils.isNotBlank(supplier.getSupplierType())) {
        	entity.setSupplierType(Integer.valueOf(supplier.getSupplierType()));
		}
        entity.setUpdateTime(DateUtils.getDate());
        entity.setUpdateUser(ShiroUtils.getUserEntity().getName());
        if (StringUtils.isNotBlank(supplier.getVehicleType())) {
        	entity.setVehicleType(Integer.valueOf(supplier.getVehicleType()));
		}
        supplierChangeService.updateById(entity);
       /* List<SupplierFileEntity> fileEntity = supplierFileService.selectBySupplierId(supplier.getSupplierId());
        

        SupplierFileEntity supplierFileEntity = new SupplierFileEntity();
        supplierFileEntity.setSupplierId(supplier.getSupplierId());
        supplierFileEntity.setLicenseImg(supplier.getLicenseImg());
        supplierFileEntity.setFileId(supplier.getFileId());
        //更新或插入
        if(fileEntity != null){
            supplierFileService.updateSupplierFile(supplierFileEntity);
        }else {
            supplierFileService.insert(supplierFileEntity);
        }
        */
        return R.ok().put("supplier",supplier);
    }

    /**
     * 提交
     */
    @SysLog("供应商提交")
    @RequestMapping("/submit")
    @StartFlow(id="supplierId",processKey = WfConstants.PROCESS_SUPPLIER_AUDIT_KEY)
    @RequiresPermissions("operate:supplier:update")
    @Transactional(rollbackFor = Exception.class)
    public R submit(@RequestBody SupplierEntity supplier){
    	
    	//将供应商记录到供应商变更记录表中
        SupplierChangeEntity entity = new SupplierChangeEntity();
        entity.setBranchBank(supplier.getBranchBank());
        entity.setCarBrand(supplier.getCarBrand());
        entity.setCarBrandName(supplier.getCarBrandName());
        entity.setCityId(supplier.getCityId());
        entity.setCityName(supplier.getCityName());
        entity.setComment(supplier.getComment());
        entity.setVehicleType(Integer.valueOf(supplier.getVehicleType()));
        entity.setDueAccountName(supplier.getDueAccountName());
        if (StringUtils.isNotBlank(supplier.getDueAccountType())) {
        	entity.setDueAccountType(Integer.valueOf(supplier.getDueAccountType()));
		}
        entity.setDueBank(supplier.getDueBank());
        entity.setDueBankCode(supplier.getDueBankCode());
        entity.setProvinceId(supplier.getProvinceId());
        entity.setProvinceName(supplier.getProvinceName());
        entity.setSupplierAdd(supplier.getSupplierAdd());
        entity.setSupplierBail(supplier.getSupplierBail());
        entity.setSupplierBankcard(supplier.getSupplierBankcard());
        entity.setSupplierCarnum(supplier.getSupplierCarnum());
        entity.setSupplierContact(supplier.getSupplierContact());
        entity.setSupplierId(String.valueOf(supplier.getSupplierId()));
        entity.setSupplierMoblie(supplier.getSupplierMoblie());
        entity.setSupplierName(supplier.getSupplierName());
        entity.setSupplierState(supplier.getSupplierState());
        if (StringUtils.isNotBlank(supplier.getSupplierType())) {
        	entity.setSupplierType(Integer.valueOf(supplier.getSupplierType()));
		}
        entity.setUpdateTime(DateUtils.getDate());
        entity.setUpdateUser(ShiroUtils.getUserEntity().getName());
    	
        if(supplier.getSupplierId() != null){
            ValidatorUtils.validateEntity(supplier, AddGroup.class);

            supplierService.updateById(supplier);
            Map<String, Object> map = new HashMap<>();
            map.put("supplier_id", supplier.getSupplierId());
            supplierCarbrandService.deleteByMap(map);
          //批量插入
            //更新成功后将车辆品牌保存到供应商品牌关联表中
            if (supplier.getCarBrandList()!=null && supplier.getCarBrandList().size()>0 ) {
            	for (SupplierCarbrandEntity carbrandEntity : supplier.getCarBrandList()) {
            		carbrandEntity.setSupplierId(String.valueOf(supplier.getSupplierId()));
            		carbrandEntity.setSupplierName(supplier.getSupplierName());
            		carbrandEntity.setInsertUser(supplier.getInsertUser());
            		carbrandEntity.setUpdateUser(supplier.getInsertUser());
            		carbrandEntity.setInsertTime(DateUtils.getDate());
            		carbrandEntity.setUpdateTime(DateUtils.getDate());
            	}
    		}
        	supplierCarbrandService.insertBatch(supplier.getCarBrandList());
        	
        	if (StringUtils.isBlank(supplier.getFlag())) {
        		//根据供应商id获取供应商变更记录
                Map<String, Object>	columnMap = new HashMap<>();
                columnMap.put("supplier_id", supplier.getSupplierId());
                List<SupplierChangeEntity> changeEntityList = supplierChangeService.selectByMap(columnMap);
                SupplierChangeEntity changeEntity = changeEntityList.get(0);
                entity.setId(changeEntity.getId());
                supplierChangeService.updateById(entity);
			}else{
				//新增供应商变更记录
	        	entity.setInsertTime(DateUtils.getDate());
	            entity.setInsertUser(ShiroUtils.getUserEntity().getName());
	        	supplierChangeService.insert(entity);
			}
            //附件
            SupplierFileEntity supplierFileEntity = new SupplierFileEntity();
            supplierFileEntity.setSupplierId(supplier.getSupplierId());
            supplierFileEntity.setLicenseImg(supplier.getLicenseImg());
            supplierFileEntity.setFileId(supplier.getFileId());
            supplierFileService.updateSupplierFile(supplierFileEntity);
        }else{
            ValidatorUtils.validateEntity(supplier, AddGroup.class);
            supplier.setSupplierState(2);//待审核
            supplier.setInsertTime(new Date());
            supplier.setInsertUser(ShiroUtils.getUserEntity().getName());
            supplier.setUpdateTime(DateUtils.getDate());
            supplier.setUpdateUser(ShiroUtils.getUserEntity().getName());
            supplierService.insert(supplier);
            //更新成功后将车辆品牌保存到供应商品牌关联表中
            if (supplier.getCarBrandList()!=null && supplier.getCarBrandList().size()>0 ) {
            	for (SupplierCarbrandEntity carbrandEntity : supplier.getCarBrandList()) {
            		carbrandEntity.setSupplierId(String.valueOf(supplier.getSupplierId()));
            		carbrandEntity.setSupplierName(supplier.getSupplierName());
            		carbrandEntity.setInsertUser(supplier.getInsertUser());
            		carbrandEntity.setUpdateUser(supplier.getInsertUser());
            		carbrandEntity.setInsertTime(DateUtils.getDate());
            		carbrandEntity.setUpdateTime(DateUtils.getDate());
            	}
    		}
        	supplierCarbrandService.insertBatch(supplier.getCarBrandList());
        	//新增供应商变更记录
        	entity.setInsertTime(DateUtils.getDate());
            entity.setInsertUser(ShiroUtils.getUserEntity().getName());
        	supplierChangeService.insert(entity);
        	//附件
           /* SupplierFileEntity supplierFileEntity = new SupplierFileEntity();
            supplierFileEntity.setSupplierId(supplier.getSupplierId());
            supplierFileEntity.setFileId(supplier.getFileId());
            supplierFileEntity.setLicenseImg(supplier.getLicenseImg());
            supplierFileEntity.setInsTime(new Date());
            supplierFileEntity.setInsUser(ShiroUtils.getUserEntity().getName());
            supplierFileService.insert(supplierFileEntity);*/
        }

        return R.ok().put(WfConstants.REMARK, supplier.getRemark());
    }

    /**
     * 审批
     */
    @SysLog("供应商审批")
    @RequestMapping("/audit")
    @ExecuteTask(id="supplierId",processKey = WfConstants.PROCESS_SUPPLIER_AUDIT_KEY)
    public R audit(@RequestBody SupplierEntity supplier){
        ValidatorUtils.validateEntity(supplier, AddGroup.class);

        supplierService.updateById(supplier);

        return R.ok().put(WfConstants.ACTION,supplier.getSupplierState() == 1 ? 0:1).put(WfConstants.REMARK, supplier.getRemark());
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:supplier:delete")
    public R delete(@RequestBody Integer[] supplierIds){
			supplierService.deleteBatchIds(Arrays.asList(supplierIds));

        return R.ok();
    }
    /**
     * 根据供应商查询附件列表
     */
    @RequestMapping("/queryList")
    //@RequiresPermissions("order:orderfile:list")
    public R queryList(@RequestParam Map<String, Object> params){
    	List<SupplierFileEntity> list = supplierFileService.queryList(params);

        return R.ok().put("supplierFileList", list);
    }
    /**
     * 供应商变更
     */
    @RequestMapping("/supplierchange")
    @RequiresPermissions("operate:supplier:update")
    @Transactional
    public R supplierChange(@RequestBody SupplierEntity supplier){

        ValidatorUtils.validateEntity(supplier, UpdateGroup.class);
        //供应商品牌关联表中删除对应数据
        Map<String, Object> map = new HashMap<>();
        map.put("supplier_id", supplier.getSupplierId());
        supplierCarbrandService.deleteByMap(map);
        supplierService.updateById(supplier);
        //更新成功后将车辆品牌保存到供应商品牌关联表中
        if (supplier.getCarBrandList()!=null && supplier.getCarBrandList().size()>0 ) {
        	for (SupplierCarbrandEntity carbrandEntity : supplier.getCarBrandList()) {
        		carbrandEntity.setSupplierId(String.valueOf(supplier.getSupplierId()));
        		carbrandEntity.setSupplierName(supplier.getSupplierName());
        		carbrandEntity.setInsertUser(supplier.getInsertUser());
        		carbrandEntity.setUpdateUser(supplier.getInsertUser());
        		carbrandEntity.setInsertTime(DateUtils.getDate());
        		carbrandEntity.setUpdateTime(DateUtils.getDate());
        	}
        	//批量插入
        	supplierCarbrandService.insertBatch(supplier.getCarBrandList());
		}
      //将供应商记录到供应商变更记录表中
        SupplierChangeEntity entity = new SupplierChangeEntity();
        entity.setBranchBank(supplier.getBranchBank());
        entity.setCarBrand(supplier.getCarBrand());
        entity.setCarBrandName(supplier.getCarBrandName());
        entity.setCityId(supplier.getCityId());
        entity.setCityName(supplier.getCityName());
        entity.setComment(supplier.getComment());
        entity.setDueAccountName(supplier.getDueAccountName());
        if (StringUtils.isNotBlank(supplier.getDueAccountType())) {
        	entity.setDueAccountType(Integer.valueOf(supplier.getDueAccountType()));
		}
        entity.setDueBank(supplier.getDueBank());
        entity.setVehicleType(Integer.valueOf(supplier.getVehicleType()));
        entity.setDueBankCode(supplier.getDueBankCode());
        entity.setInsertTime(DateUtils.getDate());
        entity.setInsertUser(ShiroUtils.getUserEntity().getName());
        entity.setProvinceId(supplier.getProvinceId());
        entity.setProvinceName(supplier.getProvinceName());
        entity.setSupplierAdd(supplier.getSupplierAdd());
        entity.setSupplierBail(supplier.getSupplierBail());
        entity.setSupplierBankcard(supplier.getSupplierBankcard());
        entity.setSupplierCarnum(supplier.getSupplierCarnum());
        entity.setSupplierContact(supplier.getSupplierContact());
        entity.setSupplierId(String.valueOf(supplier.getSupplierId()));
        entity.setSupplierMoblie(supplier.getSupplierMoblie());
        entity.setSupplierName(supplier.getSupplierName());
        entity.setSupplierState(supplier.getSupplierState());
        if (StringUtils.isNotBlank(supplier.getSupplierType())) {
        	entity.setSupplierType(Integer.valueOf(supplier.getSupplierType()));
		}
        entity.setUpdateTime(DateUtils.getDate());
        entity.setUpdateUser(ShiroUtils.getUserEntity().getName());
        if (StringUtils.isNotBlank(supplier.getVehicleType())) {
        	entity.setVehicleType(Integer.valueOf(supplier.getVehicleType()));
		}
        supplierChangeService.insert(entity);
        //附件
        /*List<SupplierFileEntity> fileEntity = supplierFileService.selectBySupplierId(supplier.getSupplierId());
        SupplierFileEntity supplierFileEntity = new SupplierFileEntity();
        supplierFileEntity.setSupplierId(supplier.getSupplierId());
        supplierFileEntity.setLicenseImg(supplier.getLicenseImg());
        supplierFileEntity.setFileId(supplier.getFileId());
        //更新或插入
        if(fileEntity != null){
            supplierFileService.updateSupplierFile(supplierFileEntity);
        }else {
            supplierFileService.insert(supplierFileEntity);
        }*/
        
        return R.ok().put("supplierId",supplier.getSupplierId());
    
    }
    /**
     * 查看供应商变更记录
     */
    @RequestMapping("/getchangelist")
    @RequiresPermissions("operate:supplier:list")
    public R getChangeList(@RequestParam Map<String, Object> params){
    		List<SupplierChangeEntity> changeList = supplierChangeService.selectAllPage(params);
    	return R.ok().put("changeList", changeList);
    }
}

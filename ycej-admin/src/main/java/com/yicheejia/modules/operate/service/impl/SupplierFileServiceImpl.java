package com.yicheejia.modules.operate.service.impl;

import com.yicheejia.modules.operate.dao.SupplierFileDao;
import com.yicheejia.modules.operate.entity.SupplierEntity;
import com.yicheejia.modules.operate.entity.SupplierFileEntity;
import com.yicheejia.modules.operate.service.SupplierFileService;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.entity.YcejFileEntity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;




@Service("supplierFileService")
public class SupplierFileServiceImpl extends ServiceImpl<SupplierFileDao, SupplierFileEntity> implements SupplierFileService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<SupplierFileEntity> page = this.selectPage(
                new Query<SupplierFileEntity>(params).getPage(),
                new EntityWrapper<SupplierFileEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    @Override
    public int updateSupplierFile(SupplierFileEntity fileEntity) {
        return baseMapper.updateSupplierFile(fileEntity);
    }

    @Override
    public List<SupplierFileEntity> selectBySupplierId(Integer supplierId) {
        return baseMapper.selectBySupplierId(supplierId);
    }

	@Override
	public void insertReturnId(YcejFileEntity ycejFileEntity, Map<String, Object> params) {
		String customerId = params.get("customerId")+"";
		if(StringUtils.isBlank(customerId) || "undefined".equals(customerId)){
			throw new RRException("请先暂存！");
		}
		SysUserEntity user=ShiroUtils.getUserEntity();
		SupplierFileEntity supplierFileEntity = new SupplierFileEntity();
		try {
			String fileType = params.get("fileType").toString();
			supplierFileEntity.setFileId(Integer.valueOf(ycejFileEntity.getFileId()));
			supplierFileEntity.setInsTime(DateUtils.getDate());
			supplierFileEntity.setInsUser(user.getName());
			supplierFileEntity.setLicenseImg(ycejFileEntity.getFilePath());
			supplierFileEntity.setSupplierId(Integer.valueOf(customerId));
			supplierFileEntity.setFileType(fileType);
			params.put("supplierId", customerId);
			params.put("fileType", fileType);
			List<SupplierFileEntity> list = baseMapper.queryList(params);
			if (list.size()>0) {
				//同一个供应商，同一个附件类型只让上传一次，后面都更新
				supplierFileEntity.setSupplierFileId(list.get(0).getSupplierFileId());
				baseMapper.updateById(supplierFileEntity);
			}else {
				baseMapper.insert(supplierFileEntity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<SupplierFileEntity> queryList(Map<String, Object> params) {
		return baseMapper.queryList(params);
	}
	/**
	 * 根据供应商id获取附件列表
	 */
	@Override
	public List<SupplierFileEntity> selectListBySupplierId(Integer supplierId) {
		// TODO Auto-generated method stub
		return baseMapper.selectListBySupplierId(supplierId);
	}

}

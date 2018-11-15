package com.yicheejia.modules.financialmanage.entity;

import java.util.List;
import java.util.Map;

/**
 * POS流水确认到账 接收前台数据实体类
 * @author 梁占豪
 *
 */
public class MoneyConfirmEntity {
	
	//支付明细数据集合
	private List<YcejFinanceOrderPaydetailEntity> orderPayDetailList;
	//导入数据集合
	private List<ImportDataEntity> importDataList;
	//支付明细实体类
	private YcejFinanceOrderPaydetailEntity orderPaydetailEntity;
	//导入数据实体类
	private ImportDataEntity importDataEntity;
	//收款备注
	private String remark;
	//参数合集
	private Map<String, Object> map;
	public List<YcejFinanceOrderPaydetailEntity> getOrderPayDetailList() {
		return orderPayDetailList;
	}
	public void setOrderPayDetailList(List<YcejFinanceOrderPaydetailEntity> orderPayDetailList) {
		this.orderPayDetailList = orderPayDetailList;
	}
	public List<ImportDataEntity> getImportDataList() {
		return importDataList;
	}
	public void setImportDataList(List<ImportDataEntity> importDataList) {
		this.importDataList = importDataList;
	}
	public YcejFinanceOrderPaydetailEntity getOrderPaydetailEntity() {
		return orderPaydetailEntity;
	}
	public void setOrderPaydetailEntity(YcejFinanceOrderPaydetailEntity orderPaydetailEntity) {
		this.orderPaydetailEntity = orderPaydetailEntity;
	}
	public ImportDataEntity getImportDataEntity() {
		return importDataEntity;
	}
	public void setImportDataEntity(ImportDataEntity importDataEntity) {
		this.importDataEntity = importDataEntity;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}

package com.yicheejia.modules.operate.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 采购明细请求list数据封装
 * @author 梁占豪
 *
 */
public class PurchDetailList implements Serializable {

	private List<PurchaseDetailEntity> detailList;

	public List<PurchaseDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PurchaseDetailEntity> detailList) {
		this.detailList = detailList;
	}
	public PurchDetailList(){
		
	}
}

package com.yicheejia.modules.financialmanage.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * pos流水导入
 * @author 梁占豪
 * @date 2018-7-16
 */
public class ImportPayDetailBean {
	@Excel(name = "内容")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}

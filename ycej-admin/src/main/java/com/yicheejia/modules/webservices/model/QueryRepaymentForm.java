package com.yicheejia.modules.webservices.model;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "支付查询表单")
public class QueryRepaymentForm {
	//输入参数
	@ApiModelProperty(value = "订单编号")
    @NotBlank(message="订单号不能为空")
    private String order_no;
	
	@ApiModelProperty(value = "门店编号")
    @NotBlank(message="门店编号不能为空")
    private String shop_id;
	

	@ApiModelProperty(value = "签名类型")
    @NotBlank(message="签名类型不能为空")
    private String sign_type;
	

	@ApiModelProperty(value = "签名")
    //@NotBlank(message="签名不能为空")
    private String signature;
	
	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	
}

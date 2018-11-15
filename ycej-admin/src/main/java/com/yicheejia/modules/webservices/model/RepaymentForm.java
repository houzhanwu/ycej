package com.yicheejia.modules.webservices.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "支付表单")
public class RepaymentForm {
	@ApiModelProperty(value = "订单编号")
    @NotBlank(message="订单号不能为空")
    private String order_no;
	
	@ApiModelProperty(value = "门店编号")
    @NotBlank(message="门店编号不能为空")
    private String shop_id;
	
	@ApiModelProperty(value = "支付类型")
	@NotBlank(message="支付类型不能为空")
	private String pay_type;
	
	@ApiModelProperty(value = "扣款时间")
	@NotBlank(message="扣款时间不能为空")
	private String pay_date;
	
	@ApiModelProperty(value = "支付卡号")
	//@NotBlank(message="支付卡号不能为空")
	private String pay_cardNo;
	
	@ApiModelProperty(value = "交易金额")
	//@NotBlank(message="交易金额不能为空")
	private BigDecimal pay_amount;
	
	@ApiModelProperty(value = "交易参考号")
	@NotBlank(message="交易参考号不能为空")
	private String out_trade_no;
	
	@ApiModelProperty(value = "签名类型")
    @NotBlank(message="签名类型不能为空")
    private String sign_type;
	
	@ApiModelProperty(value = "签名")
    //@NotBlank(message="签名不能为空")
    private String signature;
	
	
	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

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

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public String getPay_cardNo() {
		return pay_cardNo;
	}

	public void setPay_cardNo(String pay_cardNo) {
		this.pay_cardNo = pay_cardNo;
	}

	public BigDecimal getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(BigDecimal pay_amount) {
		this.pay_amount = pay_amount;
	}
	
}

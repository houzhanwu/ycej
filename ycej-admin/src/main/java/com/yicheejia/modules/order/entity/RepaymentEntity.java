/**
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.yicheejia.modules.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 支付返回实体
 * 
 */
public class RepaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderNo;//订单号
	private String  payDetail;//支付类别
	private BigDecimal sum;//需支付金额
	private BigDecimal paySum;//已支付金额
	private String paycardno;
	private String sysRefNo;
	private String id;
	private Integer payStatu;
	private String status;
	
	
	public Integer getPayStatu() {
		return payStatu;
	}
	public void setPayStatu(Integer payStatu) {
		this.payStatu = payStatu;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPaycardno() {
		return paycardno;
	}
	public void setPaycardno(String paycardno) {
		this.paycardno = paycardno;
	}
	public String getSysRefNo() {
		return sysRefNo;
	}
	public void setSysRefNo(String sysRefNo) {
		this.sysRefNo = sysRefNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getPayDetail() {
		return payDetail;
	}
	public void setPayDetail(String payDetail) {
		this.payDetail = payDetail;
	}
	public BigDecimal getPaySum() {
		return paySum;
	}
	public void setPaySum(BigDecimal paySum) {
		this.paySum = paySum;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

package com.yicheejia.modules.insure.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 保险明细表
 * 
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
@TableName("ycej_insure_detail")
public class InsureDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String insureDetailId;
	/**
	 * 保险主键
	 */
	private String insureId;
	/**
	 * 交强险保单号
	 */
	private String strongNo;
	/**
	 * 交强险金额
	 */
	private BigDecimal strongAmount;
	/**
	 * 车船税
	 */
	private BigDecimal vehicleAmount;
	/**
	 * 交强起期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date strongBegdate;
	/**
	 * 交强止期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date strongEndate;
	/**
	 * 商业险保单号
	 */
	private String commercialNo;
	/**
	 * 商业险金额
	 */
	private BigDecimal commercialAmount;
	/**
	 * 商业起期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date commercialBegdate;
	/**
	 * 商业止期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date commercialEndate;
	/**
	 * 车损金额
	 */
	private BigDecimal carLoss;
	/**
	 * 免赔金额
	 */
	private BigDecimal deductibles;
	/**
	 * 盗抢
	 */
	private BigDecimal robbery;
	/**
	 * 三责险
	 */
	private BigDecimal threeLiability;
	/**
	 * 驾驶人责任险
	 */
	private BigDecimal driveAmount;
	/**
	 * 乘客责任险
	 */
	private BigDecimal passengerAmount;
	/**
	 * 不计免赔-车损
	 */
	private BigDecimal noCar;
	/**
	 * 不计免赔-三责
	 */
	private BigDecimal noThree;
	/**
	 * 不计免赔-驾驶人
	 */
	private BigDecimal noDrive;
	/**
	 * 不计免赔-乘客
	 */
	private BigDecimal noPassenger;
	/**
	 * 无法找到第三方特约险
	 */
	private BigDecimal notThird;
	/**
	 * 划痕险
	 */
	private BigDecimal scratching;
	/**
	 * 玻璃破碎险
	 */
	private BigDecimal glass;
	/**
	 * 1国产，2进口
	 */
	private Integer glassIsout;
	/**
	 * 自燃险
	 */
	private BigDecimal pyrophoricity;
	/**
	 * 不计免赔-盗抢
	 */
	private BigDecimal noRobbery;
	/**
	 * 不计免赔-划痕
	 */
	private BigDecimal noScratching;
	/**
	 * 不计免赔-自燃
	 */
	private BigDecimal noPyrophoricity;
	/**
	 * 不计免赔-发动机涉水
	 */
	private BigDecimal noWading;
	/**
	 * 
	 */
	private String insertId;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private String updateId;
	/**
	 * 
	 */
	private Date updateTime;
	
	/**
	 * 设置：主键
	 */
	public void setInsureDetailId(String insureDetailId) {
		this.insureDetailId = insureDetailId;
	}
	/**
	 * 获取：主键
	 */
	public String getInsureDetailId() {
		return insureDetailId;
	}
	/**
	 * 设置：保险主键
	 */
	public void setInsureId(String insureId) {
		this.insureId = insureId;
	}
	/**
	 * 获取：保险主键
	 */
	public String getInsureId() {
		return insureId;
	}
	/**
	 * 设置：交强险保单号
	 */
	public void setStrongNo(String strongNo) {
		this.strongNo = strongNo;
	}
	/**
	 * 获取：交强险保单号
	 */
	public String getStrongNo() {
		return strongNo;
	}
	/**
	 * 设置：交强险金额
	 */
	public void setStrongAmount(BigDecimal strongAmount) {
		this.strongAmount = strongAmount;
	}
	/**
	 * 获取：交强险金额
	 */
	public BigDecimal getStrongAmount() {
		return strongAmount;
	}
	/**
	 * 设置：车船税
	 */
	public void setVehicleAmount(BigDecimal vehicleAmount) {
		this.vehicleAmount = vehicleAmount;
	}
	/**
	 * 获取：车船税
	 */
	public BigDecimal getVehicleAmount() {
		return vehicleAmount;
	}
	/**
	 * 设置：交强起期
	 */
	public void setStrongBegdate(Date strongBegdate) {
		this.strongBegdate = strongBegdate;
	}
	/**
	 * 获取：交强起期
	 */
	public Date getStrongBegdate() {
		return strongBegdate;
	}
	/**
	 * 设置：交强止期
	 */
	public void setStrongEndate(Date strongEndate) {
		this.strongEndate = strongEndate;
	}
	/**
	 * 获取：交强止期
	 */
	public Date getStrongEndate() {
		return strongEndate;
	}
	/**
	 * 设置：商业险保单号
	 */
	public void setCommercialNo(String commercialNo) {
		this.commercialNo = commercialNo;
	}
	/**
	 * 获取：商业险保单号
	 */
	public String getCommercialNo() {
		return commercialNo;
	}
	/**
	 * 设置：商业险金额
	 */
	public void setCommercialAmount(BigDecimal commercialAmount) {
		this.commercialAmount = commercialAmount;
	}
	/**
	 * 获取：商业险金额
	 */
	public BigDecimal getCommercialAmount() {
		return commercialAmount;
	}
	/**
	 * 设置：商业起期
	 */
	public void setCommercialBegdate(Date commercialBegdate) {
		this.commercialBegdate = commercialBegdate;
	}
	/**
	 * 获取：商业起期
	 */
	public Date getCommercialBegdate() {
		return commercialBegdate;
	}
	/**
	 * 设置：商业止期
	 */
	public void setCommercialEndate(Date commercialEndate) {
		this.commercialEndate = commercialEndate;
	}
	/**
	 * 获取：商业止期
	 */
	public Date getCommercialEndate() {
		return commercialEndate;
	}
	/**
	 * 设置：车损金额
	 */
	public void setCarLoss(BigDecimal carLoss) {
		this.carLoss = carLoss;
	}
	/**
	 * 获取：车损金额
	 */
	public BigDecimal getCarLoss() {
		return carLoss;
	}
	/**
	 * 设置：免赔金额
	 */
	public void setDeductibles(BigDecimal deductibles) {
		this.deductibles = deductibles;
	}
	/**
	 * 获取：免赔金额
	 */
	public BigDecimal getDeductibles() {
		return deductibles;
	}
	/**
	 * 设置：盗抢
	 */
	public void setRobbery(BigDecimal robbery) {
		this.robbery = robbery;
	}
	/**
	 * 获取：盗抢
	 */
	public BigDecimal getRobbery() {
		return robbery;
	}
	/**
	 * 设置：三责险
	 */
	public void setThreeLiability(BigDecimal threeLiability) {
		this.threeLiability = threeLiability;
	}
	/**
	 * 获取：三责险
	 */
	public BigDecimal getThreeLiability() {
		return threeLiability;
	}
	/**
	 * 设置：驾驶人责任险
	 */
	public void setDriveAmount(BigDecimal driveAmount) {
		this.driveAmount = driveAmount;
	}
	/**
	 * 获取：驾驶人责任险
	 */
	public BigDecimal getDriveAmount() {
		return driveAmount;
	}
	/**
	 * 设置：乘客责任险
	 */
	public void setPassengerAmount(BigDecimal passengerAmount) {
		this.passengerAmount = passengerAmount;
	}
	/**
	 * 获取：乘客责任险
	 */
	public BigDecimal getPassengerAmount() {
		return passengerAmount;
	}
	/**
	 * 设置：不计免赔-车损
	 */
	public void setNoCar(BigDecimal noCar) {
		this.noCar = noCar;
	}
	/**
	 * 获取：不计免赔-车损
	 */
	public BigDecimal getNoCar() {
		return noCar;
	}
	/**
	 * 设置：不计免赔-三责
	 */
	public void setNoThree(BigDecimal noThree) {
		this.noThree = noThree;
	}
	/**
	 * 获取：不计免赔-三责
	 */
	public BigDecimal getNoThree() {
		return noThree;
	}
	/**
	 * 设置：不计免赔-驾驶人
	 */
	public void setNoDrive(BigDecimal noDrive) {
		this.noDrive = noDrive;
	}
	/**
	 * 获取：不计免赔-驾驶人
	 */
	public BigDecimal getNoDrive() {
		return noDrive;
	}
	/**
	 * 设置：不计免赔-乘客
	 */
	public void setNoPassenger(BigDecimal noPassenger) {
		this.noPassenger = noPassenger;
	}
	/**
	 * 获取：不计免赔-乘客
	 */
	public BigDecimal getNoPassenger() {
		return noPassenger;
	}
	/**
	 * 设置：无法找到第三方特约险
	 */
	public void setNotThird(BigDecimal notThird) {
		this.notThird = notThird;
	}
	/**
	 * 获取：无法找到第三方特约险
	 */
	public BigDecimal getNotThird() {
		return notThird;
	}
	/**
	 * 设置：划痕险
	 */
	public void setScratching(BigDecimal scratching) {
		this.scratching = scratching;
	}
	/**
	 * 获取：划痕险
	 */
	public BigDecimal getScratching() {
		return scratching;
	}
	/**
	 * 设置：玻璃破碎险
	 */
	public void setGlass(BigDecimal glass) {
		this.glass = glass;
	}
	/**
	 * 获取：玻璃破碎险
	 */
	public BigDecimal getGlass() {
		return glass;
	}
	/**
	 * 设置：1国产，2进口
	 */
	public void setGlassIsout(Integer glassIsout) {
		this.glassIsout = glassIsout;
	}
	/**
	 * 获取：1国产，2进口
	 */
	public Integer getGlassIsout() {
		return glassIsout;
	}
	/**
	 * 设置：自燃险
	 */
	public void setPyrophoricity(BigDecimal pyrophoricity) {
		this.pyrophoricity = pyrophoricity;
	}
	/**
	 * 获取：自燃险
	 */
	public BigDecimal getPyrophoricity() {
		return pyrophoricity;
	}
	/**
	 * 设置：不计免赔-盗抢
	 */
	public void setNoRobbery(BigDecimal noRobbery) {
		this.noRobbery = noRobbery;
	}
	/**
	 * 获取：不计免赔-盗抢
	 */
	public BigDecimal getNoRobbery() {
		return noRobbery;
	}
	/**
	 * 设置：不计免赔-划痕
	 */
	public void setNoScratching(BigDecimal noScratching) {
		this.noScratching = noScratching;
	}
	/**
	 * 获取：不计免赔-划痕
	 */
	public BigDecimal getNoScratching() {
		return noScratching;
	}
	/**
	 * 设置：不计免赔-自燃
	 */
	public void setNoPyrophoricity(BigDecimal noPyrophoricity) {
		this.noPyrophoricity = noPyrophoricity;
	}
	/**
	 * 获取：不计免赔-自燃
	 */
	public BigDecimal getNoPyrophoricity() {
		return noPyrophoricity;
	}
	/**
	 * 设置：不计免赔-发动机涉水
	 */
	public void setNoWading(BigDecimal noWading) {
		this.noWading = noWading;
	}
	/**
	 * 获取：不计免赔-发动机涉水
	 */
	public BigDecimal getNoWading() {
		return noWading;
	}
	/**
	 * 设置：
	 */
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：
	 */
	public String getInsertId() {
		return insertId;
	}
	/**
	 * 设置：
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：
	 */
	public String getUpdateId() {
		return updateId;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	@TableField(exist = false)
	private InsureEntity insureEntity;
	@TableField(exist = false)
	private String orderId;//订单主键
	@TableField(exist = false)
	private String insuranceId;//交强保险公司
	@TableField(exist = false)
	private String commercialId;//商业保险公司


	public InsureEntity getInsureEntity() {
		return insureEntity;
	}
	public void setInsureEntity(InsureEntity insureEntity) {
		this.insureEntity = insureEntity;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getCommercialId() {
		return commercialId;
	}
	public void setCommercialId(String commercialId) {
		this.commercialId = commercialId;
	}
	
	
}

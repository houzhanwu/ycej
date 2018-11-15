package com.yicheejia.modules.sms.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:20:35
 */
public class SmsBean {
    @Excel(name = "主键")
    private Integer smsId;
    @Excel(name = "手机号")
    private String mobile;
    @Excel(name = "短信内容")
    private String content;
    @Excel(name = "发送时间")
    private Date sendTime;
    @Excel(name = "发送状态")
    private Integer sResult;
    @Excel(name = "短信ID")
    private Long msgid;
    @Excel(name = "流水号")
    private String custid;

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }
    public Integer getSmsId() {
        return smsId;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobile() {
        return mobile;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSResult(Integer sResult) {
        this.sResult = sResult;
    }
    public Integer getSResult() {
        return sResult;
    }
    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }
    public Long getMsgid() {
        return msgid;
    }
    public void setCustid(String custid) {
        this.custid = custid;
    }
    public String getCustid() {
        return custid;
    }
}

package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-09-28 15:23:41
 */
public class PriceQuestionBean {
    @Excel(name = "")
    private Long id;
    @Excel(name = "提问门店")
    private String shopId;
    @Excel(name = "提问人")
    private String questioner;
    @Excel(name = "提问时间")
    private Date questionTime;
    @Excel(name = "客户姓名")
    private String customer;
    @Excel(name = "联系方式")
    private String phone;
    @Excel(name = "提问内容")
    private String question;
    @Excel(name = "回复人")
    private String replier;
    @Excel(name = "回复时间")
    private Date replyTime;
    @Excel(name = "车价来源")
    private String priceFrom;
    @Excel(name = "回复内容")
    private String reply;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getShopId() {
        return shopId;
    }
    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }
    public String getQuestioner() {
        return questioner;
    }
    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }
    public Date getQuestionTime() {
        return questionTime;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getCustomer() {
        return customer;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }
    public void setReplier(String replier) {
        this.replier = replier;
    }
    public String getReplier() {
        return replier;
    }
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    public Date getReplyTime() {
        return replyTime;
    }
    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }
    public String getPriceFrom() {
        return priceFrom;
    }
    public void setReply(String reply) {
        this.reply = reply;
    }
    public String getReply() {
        return reply;
    }
}

package com.yicheejia.modules.sys.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 系统日志Excel Bean
 *
 *
 * @since 2018-03-24
 */
public class SysLogBean {
    @Excel(name = "ID")
    private Long id;

    @Excel(name = "用户名")
    private String username;

    @Excel(name = "用户操作")
    private String operation;

    @Excel(name = "请求方法")
    private String method;

    @Excel(name = "请求参数")
    private String params;

    @Excel(name = "执行时长(毫秒)")
    private Long time;

    @Excel(name = "IP地址")
    private String ip;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

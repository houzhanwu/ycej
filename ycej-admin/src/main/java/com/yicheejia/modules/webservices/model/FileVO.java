package com.yicheejia.modules.webservices.model;
/**
 * 文件信息
 * @author hunk
 *
 */
public class FileVO {

    private String fileType;
    private String fileUrl;
    private String fileName;
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

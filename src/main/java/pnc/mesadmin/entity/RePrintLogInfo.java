package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：重打记录信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-07-14
 * 修改人：
 * 修改时间：
 */
public class RePrintLogInfo {
    private int ruid;
    private String guid;
    private String pTCode;
    private String rePTCode;
    private String orderType;
    private String barCode;
    private String barType;
    private int printCount;
    private int printCopy;
    private String printerName;
    private String pTFileName;
    private String printData;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getpTCode() {
        return pTCode;
    }

    public void setpTCode(String pTCode) {
        this.pTCode = pTCode;
    }

    public String getRePTCode() {
        return rePTCode;
    }

    public void setRePTCode(String rePTCode) {
        this.rePTCode = rePTCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(int printCount) {
        this.printCount = printCount;
    }

    public int getPrintCopy() {
        return printCopy;
    }

    public void setPrintCopy(int printCopy) {
        this.printCopy = printCopy;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyMan() {
        return lastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        this.lastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBarType() {
        return barType;
    }

    public void setBarType(String barType) {
        this.barType = barType;
    }

    public String getpTFileName() {
        return pTFileName;
    }

    public void setpTFileName(String pTFileName) {
        this.pTFileName = pTFileName;
    }

    public String getPrintData() {
        return printData;
    }

    public void setPrintData(String printData) {
        this.printData = printData;
    }
}

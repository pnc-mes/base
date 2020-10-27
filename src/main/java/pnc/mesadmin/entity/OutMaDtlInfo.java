package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 14:21
 * @Description:
 */
public class OutMaDtlInfo {
    private int ruid;
    private String guid;
    private String outMaGd;
    private String outCode;
    private String maVerGd;
    private float num;
    private float scanNum;
    private String unitName;
    private String storeGd;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;
    private String lgd;
    private String bartype;
    private String batch;


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

    public String getOutMaGd() {
        return outMaGd;
    }

    public void setOutMaGd(String outMaGd) {
        this.outMaGd = outMaGd;
    }

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public float getScanNum() {
        return scanNum;
    }

    public void setScanNum(float scanNum) {
        this.scanNum = scanNum;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getStoreGd() {
        return storeGd;
    }

    public void setStoreGd(String storeGd) {
        this.storeGd = storeGd;
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

    public String getLgd() {
        return lgd;
    }

    public void setLgd(String lgd) {
        this.lgd = lgd;
    }

    public String getBartype() {
        return bartype;
    }

    public void setBartype(String bartype) {
        this.bartype = bartype;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}

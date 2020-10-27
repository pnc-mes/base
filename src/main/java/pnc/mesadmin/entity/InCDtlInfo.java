package pnc.mesadmin.entity;

import pnc.mesadmin.entity.base.BaseModel;

import java.util.Date;

/**
 * Created by PNC on 2017/8/24.
 */
public class InCDtlInfo implements BaseModel{
    private int ruid;
    private String guid;
    private String purChDtlGd;
    private String inChGd;
    private String inCCode;
    private String maVerGd;
    private float num ;
    private float scanNum;
    private String unitName;
    private String aFeed;
    private String storeGd;
    private String isPrint;
    private String f1;
    private String f2;
    private String f3;
    private String f4;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String Remark;

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

    public String getPurChDtlGd() {
        return purChDtlGd;
    }

    public void setPurChDtlGd(String purChDtlGd) {
        this.purChDtlGd = purChDtlGd;
    }

    public String getInChGd() {
        return inChGd;
    }

    public void setInChGd(String inChGd) {
        this.inChGd = inChGd;
    }

    public String getInCCode() {
        return inCCode;
    }

    public void setInCCode(String inCCode) {
        this.inCCode = inCCode;
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

    public String getaFeed() {
        return aFeed;
    }

    public void setaFeed(String aFeed) {
        this.aFeed = aFeed;
    }

    public String getStoreGd() {
        return storeGd;
    }

    public void setStoreGd(String storeGd) {
        this.storeGd = storeGd;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public String getF4() {
        return f4;
    }

    public void setF4(String f4) {
        this.f4 = f4;
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
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}

package pnc.mesadmin.entity;

import pnc.mesadmin.entity.base.BaseModel;

import java.util.Date;

/**
 * Created by PNC on 2017/8/23.
 */
public class PurChDtlInfo implements BaseModel {
    private int ruid;
    private String maVerGd;
    private String guid;
    private String purChGd;
    private String purChCode;
    private Date arrivalTime;
    private float num ;
    private float scanNum;
    private String unitName;
    private String aFeed;
    private String storeGd;
    private String cIEFlag;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String Remark;


    public String getcIEFlag() {
        return cIEFlag;
    }

    public void setcIEFlag(String cIEFlag) {
        this.cIEFlag = cIEFlag;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

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

    public String getPurChGd() {
        return purChGd;
    }

    public void setPurChGd(String purChGd) {
        this.purChGd = purChGd;
    }

    public String getPurChCode() {
        return purChCode;
    }

    public void setPurChCode(String purChCode) {
        this.purChCode = purChCode;
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

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }
}

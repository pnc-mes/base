package pnc.mesadmin.entity;

import pnc.mesadmin.entity.base.BaseModel;

import java.util.Date;

/**
 * Created by PNC on 2017/9/26.
 */
public class RMaNDtlInfo implements BaseModel{
    private int ruid;
    private String guid;
    private String rMaNGd;
    private String rMaNCode;
    private String maVerGd;
    private float num;
    private float scanNum;
    private String unitName;
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

    public String getrMaNGd() {
        return rMaNGd;
    }

    public void setrMaNGd(String rMaNGd) {
        this.rMaNGd = rMaNGd;
    }

    public String getrMaNCode() {
        return rMaNCode;
    }

    public void setrMaNCode(String rMaNCode) {
        this.rMaNCode = rMaNCode;
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
}

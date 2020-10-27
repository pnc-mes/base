package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by test on 2017/10/20.
 */
public class ProdRepInfo {

    private int ruid;

    private String guid;

    private String specName;

    private Date prodDate;

    private Float miNum;

    private Float moNum;

    private Float yield;

    private Float wHours;

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

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Float getMiNum() {
        return miNum;
    }

    public void setMiNum(Float miNum) {
        this.miNum = miNum;
    }

    public Float getMoNum() {
        return moNum;
    }

    public void setMoNum(Float moNum) {
        this.moNum = moNum;
    }

    public Float getYield() {
        return yield;
    }

    public void setYield(Float yield) {
        this.yield = yield;
    }

    public Float getwHours() {
        return wHours;
    }

    public void setwHours(Float wHours) {
        this.wHours = wHours;
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

package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：组件分档信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2018-09-26
 * 修改人：
 * 修改时间：
 */
public class GearInfo {
    private int ruid;
    private String guid;
    private String woGd;
    private String woCode;
    private String batch;
    private String impp;
    private String pmpp;
    private String color;
    private String powerGear;
    private String colorGear;
    private String eLGear;
    private String stackNum;
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

    public String getImpp() {
        return impp;
    }

    public void setImpp(String impp) {
        this.impp = impp;
    }

    public String getPmpp() {
        return pmpp;
    }

    public void setPmpp(String pmpp) {
        this.pmpp = pmpp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWoGd() {
        return woGd;
    }

    public void setWoGd(String woGd) {
        this.woGd = woGd;
    }

    public String getWoCode() {
        return woCode;
    }

    public void setWoCode(String woCode) {
        this.woCode = woCode;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPowerGear() {
        return powerGear;
    }

    public void setPowerGear(String powerGear) {
        this.powerGear = powerGear;
    }

    public String getColorGear() {
        return colorGear;
    }

    public void setColorGear(String colorGear) {
        this.colorGear = colorGear;
    }

    public String geteLGear() {
        return eLGear;
    }

    public void seteLGear(String eLGear) {
        this.eLGear = eLGear;
    }

    public String getStackNum() {
        return stackNum;
    }

    public void setStackNum(String stackNum) {
        this.stackNum = stackNum;
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

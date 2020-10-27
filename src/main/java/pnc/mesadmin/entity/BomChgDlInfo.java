package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：变更记录明细信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-11-17
 * 修改人：
 * 修改时间：
 */
public class BomChgDlInfo {
    private int ruid;
    private String guid;
    private String bomChgGd;
    private String qMaterialCode;
    private String qMaterialName;
    private String qSpecName;
    private float qNum;
    private float qARate;
    private String qConSMode;
    private String qChecked;
    private String hMaterialCode;
    private String hMaterialName;
    private String hSpecName;
    private float hNum;
    private String hConSMode;
    private String hChecked;
    private String optType;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public float getqARate() {
        return qARate;
    }

    public void setqARate(float qARate) {
        this.qARate = qARate;
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

    public String getBomChgGd() {
        return bomChgGd;
    }

    public void setBomChgGd(String bomChgGd) {
        this.bomChgGd = bomChgGd;
    }

    public String getqMaterialCode() {
        return qMaterialCode;
    }

    public void setqMaterialCode(String qMaterialCode) {
        this.qMaterialCode = qMaterialCode;
    }

    public String getqMaterialName() {
        return qMaterialName;
    }

    public void setqMaterialName(String qMaterialName) {
        this.qMaterialName = qMaterialName;
    }

    public String getqSpecName() {
        return qSpecName;
    }

    public void setqSpecName(String qSpecName) {
        this.qSpecName = qSpecName;
    }

    public float getqNum() {
        return qNum;
    }

    public void setqNum(float qNum) {
        this.qNum = qNum;
    }

    public String getqConSMode() {
        return qConSMode;
    }

    public void setqConSMode(String qConSMode) {
        this.qConSMode = qConSMode;
    }

    public String getqChecked() {
        return qChecked;
    }

    public void setqChecked(String qChecked) {
        this.qChecked = qChecked;
    }

    public String gethMaterialCode() {
        return hMaterialCode;
    }

    public void sethMaterialCode(String hMaterialCode) {
        this.hMaterialCode = hMaterialCode;
    }

    public String gethMaterialName() {
        return hMaterialName;
    }

    public void sethMaterialName(String hMaterialName) {
        this.hMaterialName = hMaterialName;
    }

    public String gethSpecName() {
        return hSpecName;
    }

    public void sethSpecName(String hSpecName) {
        this.hSpecName = hSpecName;
    }

    public float gethNum() {
        return hNum;
    }

    public void sethNum(float hNum) {
        this.hNum = hNum;
    }

    public String gethConSMode() {
        return hConSMode;
    }

    public void sethConSMode(String hConSMode) {
        this.hConSMode = hConSMode;
    }

    public String gethChecked() {
        return hChecked;
    }

    public void sethChecked(String hChecked) {
        this.hChecked = hChecked;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
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

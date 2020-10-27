package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工序版本信息实体类
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_specverinfo")
public class SpecVerInfo {
    private int ruid;
    private String guid;
    private String specGd;
    private String specName;
    private String version;
    private String isDef;
    private String opertGd;
    private String status;
    private String devGrGd;
    private String dCVerGd;
    private String afDCVerGd;
    private String skillGGd;
    private String fileGrGd;
    /*private String printTGd;
    private String pinterGd;*/
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String Remark;

    public String getFileGrGd() {
        return fileGrGd;
    }

    public void setFileGrGd(String fileGrGd) {
        this.fileGrGd = fileGrGd;
    }

    public String getSkillGGd() {
        return skillGGd;
    }

    public void setSkillGGd(String skillGGd) {
        this.skillGGd = skillGGd;
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

    public String getSpecGd() {
        return specGd;
    }

    public void setSpecGd(String specGd) {
        this.specGd = specGd;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIsDef() {
        return isDef;
    }

    public void setIsDef(String isDef) {
        this.isDef = isDef;
    }

    public String getOpertGd() {
        return opertGd;
    }

    public void setOpertGd(String opertGd) {
        this.opertGd = opertGd;
    }

    public String getdCVerGd() {
        return dCVerGd;
    }

    public void setdCVerGd(String dCVerGd) {
        this.dCVerGd = dCVerGd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDevGrGd() {
        return devGrGd;
    }

    public void setDevGrGd(String devGrGd) {
        this.devGrGd = devGrGd;
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

    public String getAfDCVerGd() {
        return afDCVerGd;
    }

    public void setAfDCVerGd(String afDCVerGd) {
        this.afDCVerGd = afDCVerGd;
    }



    /*public String getPrintTGd() {
        return printTGd;
    }

    public void setPrintTGd(String printTGd) {
        this.printTGd = printTGd;
    }

    public String getPinterGd() {
        return pinterGd;
    }

    public void setPinterGd(String pinterGd) {
        this.pinterGd = pinterGd;
    }*/
}

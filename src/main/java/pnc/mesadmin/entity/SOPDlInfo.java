package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工序工艺文件信息Model
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_sopdlinfo")
public class SOPDlInfo {
    private int ruid;
    private String guid;
    private String maVerGd;
    private String SOPGd;
    private String specVerGd;
    private String specName;
    private String fileGrGd;
    private String devGrGd;
    private String DCVerGd;
    private String AfDCVerGd;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public String getDevGrGd() {
        return devGrGd;
    }

    public void setDevGrGd(String devGrGd) {
        this.devGrGd = devGrGd;
    }

    public String getDCVerGd() {
        return DCVerGd;
    }

    public void setDCVerGd(String DCVerGd) {
        this.DCVerGd = DCVerGd;
    }

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
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

    public String getSOPGd() {
        return SOPGd;
    }

    public void setSOPGd(String SOPGd) {
        this.SOPGd = SOPGd;
    }

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getFileGrGd() {
        return fileGrGd;
    }

    public void setFileGrGd(String fileGrGd) {
        this.fileGrGd = fileGrGd;
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

    public String getAfDCVerGd() {
        return AfDCVerGd;
    }

    public void setAfDCVerGd(String afDCVerGd) {
        AfDCVerGd = afDCVerGd;
    }
}

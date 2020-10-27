package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工艺版本信息表实体类
 * 创建人：刘福志
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_wfverinfo")
public class WFVerInfo {
    private int ruid;
    private String guid;
    private String wfGd;
    private String wFName;
    private String version;
    private String isDef;
    private String status;
    private String wFJson;
    private String sSpecVerGd;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;
    private String wFType;

    public String getwFName() {
        return wFName;
    }

    public void setwFName(String wFName) {
        this.wFName = wFName;
    }

    public String getwFJson() {
        return wFJson;
    }

    public void setwFJson(String wFJson) {
        this.wFJson = wFJson;
    }

    public String getsSpecVerGd() {
        return sSpecVerGd;
    }

    public void setsSpecVerGd(String sSpecVerGd) {
        this.sSpecVerGd = sSpecVerGd;
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

    public String getWfGd() {
        return wfGd;
    }

    public void setWfGd(String wfGd) {
        this.wfGd = wfGd;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getwFType() {
        return wFType;
    }

    public void setwFType(String wFType) {
        this.wFType = wFType;
    }
}

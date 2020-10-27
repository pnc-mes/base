package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集版本信息Model
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
@TableName(value = "tpm_dcverinfo")
public class DCVerInfo {
    private int ruid;
    private String guid;
    private String dcGd;
    private String dcName;
    private String version;
    private String status;
    private String isDef;
    private String creator;
    private String presetEmailDistributionGd;
    private String presetEmailMessageGd;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getDcGd() {
        return dcGd;
    }

    public void setDcGd(String dcGd) {
        this.dcGd = dcGd;
    }

    public String getDcName() {
        return dcName;
    }

    public void setDcName(String dcName) {
        this.dcName = dcName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDef() {
        return isDef;
    }

    public void setIsDef(String isDef) {
        this.isDef = isDef;
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

    public String getPresetEmailDistributionGd() {
        return presetEmailDistributionGd;
    }

    public void setPresetEmailDistributionGd(String presetEmailDistributionGd) {
        this.presetEmailDistributionGd = presetEmailDistributionGd;
    }

    public String getPresetEmailMessageGd() {
        return presetEmailMessageGd;
    }

    public void setPresetEmailMessageGd(String presetEmailMessageGd) {
        this.presetEmailMessageGd = presetEmailMessageGd;
    }
}

package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * Created by PNC on 2017/6/1.
 */
@TableName(value = "tpm_opertinfo")
public class OpertInfo {
    private Integer ruid;
    private String guid;
    private String optName;
    private String wcgd;
    private String bLGd;
    private String reaCGGd;
    private String specoption;
    private String packopt;
    private String ckweight;
    private String packtype;
    private String badOutSpec;
    private String creator;
    private Date createtime;
    private String lastmodifyman;
    private Date lastmodifytime;
    private String remark;

    public String getbLGd() {
        return bLGd;
    }

    public void setbLGd(String bLGd) {
        this.bLGd = bLGd;
    }

    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getOptname() {
        return optName;
    }

    public void setOptname(String optName) {
        this.optName = optName == null ? null : optName.trim();
    }

    public String getWcgd() {
        return wcgd;
    }

    public void setWcgd(String wcgd) {
        this.wcgd = wcgd == null ? null : wcgd.trim();
    }

    public String getSpecoption() {
        return specoption;
    }

    public void setSpecoption(String specoption) {
        this.specoption = specoption == null ? null : specoption.trim();
    }

    public String getPackopt() {
        return packopt;
    }

    public void setPackopt(String packopt) {
        this.packopt = packopt == null ? null : packopt.trim();
    }

    public String getCkweight() {
        return ckweight;
    }

    public void setCkweight(String ckweight) {
        this.ckweight = ckweight == null ? null : ckweight.trim();
    }

    public String getPacktype() {
        return packtype;
    }

    public void setPacktype(String packtype) {
        this.packtype = packtype == null ? null : packtype.trim();
    }
    public String getBadOutSpec() {
        return badOutSpec;
    }

    public void setBadOutSpec(String badOutSpec) {
        this.badOutSpec = badOutSpec;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLastModifyMan() {
        return lastmodifyman;
    }

    public void setLastModifyMan(String lastmodifyman) {
        this.lastmodifyman = lastmodifyman == null ? null : lastmodifyman.trim();
    }

    public Date getLastModifyTime() {
        return lastmodifytime;
    }

    public void setLastModifyTime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getReaCGGd() {
        return reaCGGd;
    }

    public void setReaCGGd(String reaCGGd) {
        this.reaCGGd = reaCGGd;
    }
}

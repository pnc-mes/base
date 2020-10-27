package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 13:47
 * @Description:
 */
@TableName(value ="tpm_cycleplaninfo")
public class CyclePlanInfo {
    private int ruid;
    private String guid;
    private String cyclePlanName;
    private String status;
    private String description;
    private String reaCodeGd;
    private String fileVerGd;
    private Date starDate;
    private Date endDate;
    private String mTType;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    private String Reference;
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

    public String getCyclePlanName() {
        return cyclePlanName;
    }

    public void setCyclePlanName(String cyclePlanName) {
        this.cyclePlanName = cyclePlanName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReaCodeGd() {
        return reaCodeGd;
    }

    public void setReaCodeGd(String reaCodeGd) {
        this.reaCodeGd = reaCodeGd;
    }

    public String getFileVerGd() {
        return fileVerGd;
    }

    public void setFileVerGd(String fileVerGd) {
        this.fileVerGd = fileVerGd;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getmTType() {
        return mTType;
    }

    public void setmTType(String mTType) {
        this.mTType = mTType;
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

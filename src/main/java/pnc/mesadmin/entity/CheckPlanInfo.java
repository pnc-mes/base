package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:10
 * @Description:
 */
@TableName(value = "tpm_checkplaninfo")
public class    CheckPlanInfo {
    private int ruid;
    private String guid;
    private String checkPlanName;
    private String status;
    private String CheckPlanType;
    private String description;
    private Date starDate;
    private Date endDate;
    private String mTType;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;
    private String Reference;
    private String FileVerGd;


    public String getCheckPlanType() {
        return CheckPlanType;
    }

    public void setCheckPlanType(String checkPlanType) {
        CheckPlanType = checkPlanType;
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

    public String getCheckPlanName() {
        return checkPlanName;
    }

    public void setCheckPlanName(String checkPlanName) {
        this.checkPlanName = checkPlanName;
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

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public String getFileVerGd() {
        return FileVerGd;
    }

    public void setFileVerGd(String fileVerGd) {
        FileVerGd = fileVerGd;
    }
}

package pnc.mesadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * Created by haozan on 2018/9/7.d
 * 次数保养计划信息表实体类
 */
@TableName(value ="tpm_freplaninfo")
public class FrePlanInfo {
    private Integer Ruid;
    private String Guid;
    private String FrePlanName;
    private String Status;
    private String Description;
    private String ReaCodeGd;
    private String FileVerGd;
    private Integer UseNum;
    private String UnitType;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;
    private String Reference;
    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getFrePlanName() {
        return FrePlanName;
    }

    public void setFrePlanName(String frePlanName) {
        FrePlanName = frePlanName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getReaCodeGd() {
        return ReaCodeGd;
    }

    public void setReaCodeGd(String reaCodeGd) {
        ReaCodeGd = reaCodeGd;
    }

    public String getFileVerGd() {
        return FileVerGd;
    }

    public void setFileVerGd(String fileVerGd) {
        FileVerGd = fileVerGd;
    }

    public Integer getUseNum() {
        return UseNum;
    }

    public void setUseNum(Integer useNum) {
        UseNum = useNum;
    }

    public String getUnitType() {
        return UnitType;
    }

    public void setUnitType(String unitType) {
        UnitType = unitType;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }
}

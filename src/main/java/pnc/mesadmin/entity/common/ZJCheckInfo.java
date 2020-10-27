package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * Created by LENOVO on 2018/9/27.
 * 终检信息表实体类
 */
public class ZJCheckInfo {
    private Integer Ruid;
    private String Guid;
    private String WoGd;
    private String WoCode;
    private String MaterialCode;
    private String MaterialName;
    private String MaterialAttr;
    private String Batch;
    private String LineGd;
    private String ShiftGd;
    private String SysGradeName;
    private String GradeName;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;
    private String WorkerCode;

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

    public String getWoGd() {
        return WoGd;
    }

    public void setWoGd(String woGd) {
        WoGd = woGd;
    }

    public String getWoCode() {
        return WoCode;
    }

    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getMaterialAttr() {
        return MaterialAttr;
    }

    public void setMaterialAttr(String materialAttr) {
        MaterialAttr = materialAttr;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getLineGd() {
        return LineGd;
    }

    public void setLineGd(String lineGd) {
        LineGd = lineGd;
    }

    public String getShiftGd() {
        return ShiftGd;
    }

    public void setShiftGd(String shiftGd) {
        ShiftGd = shiftGd;
    }

    public String getSysGradeName() {
        return SysGradeName;
    }

    public void setSysGradeName(String sysGradeName) {
        SysGradeName = sysGradeName;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String gradeName) {
        GradeName = gradeName;
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

    public String getWorkerCode() {
        return WorkerCode;
    }

    public void setWorkerCode(String workerCode) {
        WorkerCode = workerCode;
    }
}

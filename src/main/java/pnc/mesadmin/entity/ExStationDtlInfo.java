package pnc.mesadmin.entity;

import java.util.Date;

//工位执行实体明细信息表
public class ExStationDtlInfo {
    private Integer Ruid;
    private String Guid;
    private String StationGd;
    private String ExecGd;
    private String ExecGGd;
    private String ExecType;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

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

    public String getStationGd() {
        return StationGd;
    }

    public void setStationGd(String stationGd) {
        StationGd = stationGd;
    }

    public String getExecGd() {
        return ExecGd;
    }

    public void setExecGd(String execGd) {
        ExecGd = execGd;
    }

    public String getExecGGd() {
        return ExecGGd;
    }

    public void setExecGGd(String execGGd) {
        ExecGGd = execGGd;
    }

    public String getExecType() {
        return ExecType;
    }

    public void setExecType(String execType) {
        ExecType = execType;
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
}

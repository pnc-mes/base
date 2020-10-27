package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by 郝赞 on 2018/10/16.
 * 设备集成 信息表实体类
 */
public class StationIntegratedInfo {
    private Integer Ruid;
    private String Guid;
    private String StationGd;
    private String DevGd;
    private String DevMapGd;
    private String TriggerType;
    private String CmdText;
    private String CmdType;
    private String CmdVal;
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

    public String getDevGd() {
        return DevGd;
    }

    public void setDevGd(String devGd) {
        DevGd = devGd;
    }

    public String getDevMapGd() {
        return DevMapGd;
    }

    public void setDevMapGd(String devMapGd) {
        DevMapGd = devMapGd;
    }

    public String getTriggerType() {
        return TriggerType;
    }

    public void setTriggerType(String triggerType) {
        TriggerType = triggerType;
    }

    public String getCmdText() {
        return CmdText;
    }

    public void setCmdText(String cmdText) {
        CmdText = cmdText;
    }

    public String getCmdType() {
        return CmdType;
    }

    public void setCmdType(String cmdType) {
        CmdType = cmdType;
    }

    public String getCmdVal() {
        return CmdVal;
    }

    public void setCmdVal(String cmdVal) {
        CmdVal = cmdVal;
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

package pnc.mesadmin.entity;


import java.util.Date;

/**
 * Created by 郝赞 on 2018/10/9.
 */
public class DevExpectLogInfo {
    private Integer Ruid;
    private String Guid;
    private String LineName;
    private String SpecName;
    private String StationName;
    private String DevCode;
    private String DevName;
    private String DevMapCode;
    private String DevMapName;
    private String Msg;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;


    public String getLineName() {
        return LineName;
    }

    public void setLineName(String lineName) {
        LineName = lineName;
    }

    public String getSpecName() {
        return SpecName;
    }

    public void setSpecName(String specName) {
        SpecName = specName;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

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

    public String getDevCode() {
        return DevCode;
    }

    public void setDevCode(String devCode) {
        DevCode = devCode;
    }

    public String getDevName() {
        return DevName;
    }

    public void setDevName(String devName) {
        DevName = devName;
    }

    public String getDevMapCode() {
        return DevMapCode;
    }

    public void setDevMapCode(String devMapCode) {
        DevMapCode = devMapCode;
    }

    public String getDevMapName() {
        return DevMapName;
    }

    public void setDevMapName(String devMapName) {
        DevMapName = devMapName;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
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

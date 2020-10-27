package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by 郝赞 on 2018/10/16.
 */
public class StationAlarmRevInfo {
    private Integer Ruid;
    private String Guid;
    private String StationGd;
    private String StationAlarmGd;
    private String UserGd;
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

    public String getStationAlarmGd() {
        return StationAlarmGd;
    }

    public void setStationAlarmGd(String stationAlarmGd) {
        StationAlarmGd = stationAlarmGd;
    }

    public String getUserGd() {
        return UserGd;
    }

    public void setUserGd(String userGd) {
        UserGd = userGd;
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

package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by 郝赞 on 2018/10/16.
 * 上下料配置信息表实体类
 */
public class StationUDMInfo {

    private Integer Ruid;
    private String Guid;
    private String StationGd;
    private String UDMType;
    private String UDMCarrierType;
    private String DMArea;
    private Integer UDMIndex;
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

    public String getUDMType() {
        return UDMType;
    }

    public void setUDMType(String UDMType) {
        this.UDMType = UDMType;
    }

    public String getUDMCarrierType() {
        return UDMCarrierType;
    }

    public void setUDMCarrierType(String UDMCarrierType) {
        this.UDMCarrierType = UDMCarrierType;
    }

    public String getDMArea() {
        return DMArea;
    }

    public void setDMArea(String DMArea) {
        this.DMArea = DMArea;
    }

    public Integer getUDMIndex() {
        return UDMIndex;
    }

    public void setUDMIndex(Integer UDMIndex) {
        this.UDMIndex = UDMIndex;
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

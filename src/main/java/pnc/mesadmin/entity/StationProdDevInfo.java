package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by haozan on 2018/10/24.
 * 工位 关联 生产 设备表
 */
public class StationProdDevInfo {
    private Integer Ruid;
    private String Guid;
    private String StationGd;
    private String AssDevGd;
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

    public String getAssDevGd() {
        return AssDevGd;
    }

    public void setAssDevGd(String assDevGd) {
        AssDevGd = assDevGd;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }
}

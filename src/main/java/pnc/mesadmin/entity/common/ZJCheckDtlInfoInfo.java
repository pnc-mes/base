package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * Created by LENOVO on 2018/9/12.
 * 终检明细信息表
 */
public class ZJCheckDtlInfoInfo {
    private Integer Ruid;
    private String Guid;
    private String ZJCheckGd;
    private String BadType;
    private String BadCode;
    private String BadName;
    private String LocationCode;
    private String LocationName;
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

    public String getZJCheckGd() {
        return ZJCheckGd;
    }

    public void setZJCheckGd(String ZJCheckGd) {
        this.ZJCheckGd = ZJCheckGd;
    }

    public String getBadType() {
        return BadType;
    }

    public void setBadType(String badType) {
        BadType = badType;
    }

    public String getBadCode() {
        return BadCode;
    }

    public void setBadCode(String badCode) {
        BadCode = badCode;
    }

    public String getBadName() {
        return BadName;
    }

    public void setBadName(String badName) {
        BadName = badName;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
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

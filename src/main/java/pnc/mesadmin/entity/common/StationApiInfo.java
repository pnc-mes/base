package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 工位API配置
 * @author: yin.yang
 * @create: 2018-12-13
 **/
public class StationApiInfo {
    private Integer Ruid;
    private String Guid;
    private String StationGd;
    private String APIUrl;
    //00#进站成功,01#上机成功,02#下机成功,03#出站成功,04#进站失败,05#上机失败,06#下机失败,07#出站失败,08#上料成功,09#下料成功,10#上料失败,11#下料失败
    private String TriggerType;
    //00#产品实体
    private String SysVal;
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

    public String getAPIUrl() {
        return APIUrl;
    }

    public void setAPIUrl(String APIUrl) {
        this.APIUrl = APIUrl;
    }

    public String getTriggerType() {
        return TriggerType;
    }

    public void setTriggerType(String triggerType) {
        TriggerType = triggerType;
    }

    public String getSysVal() {
        return SysVal;
    }

    public void setSysVal(String sysVal) {
        SysVal = sysVal;
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

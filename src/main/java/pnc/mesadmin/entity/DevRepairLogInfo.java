package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 设备维修记录信息表
 * @author: yin.yang
 * @create: 2018-11-09
 **/
public class DevRepairLogInfo {
    private Integer Ruid;
    private String Guid;
    private String DevGd;
    private String DevMalfGd;
    private String DevSGd;
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

    public String getDevGd() {
        return DevGd;
    }

    public void setDevGd(String devGd) {
        DevGd = devGd;
    }

    public String getDevMalfGd() {
        return DevMalfGd;
    }

    public void setDevMalfGd(String devMalfGd) {
        DevMalfGd = devMalfGd;
    }

    public String getDevSGd() {
        return DevSGd;
    }

    public void setDevSGd(String devSGd) {
        DevSGd = devSGd;
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

package pnc.mesadmin.entity;


import java.util.Date;

/**
 * Created by 郝赞 on 2018/10/9.
 */
public class DevMapInfo {

    private Integer Ruid;
    private String Guid;
    private String DevGd;
    private String DevMapCode;
    private String DevMapName;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public String getDevMapName() {
        return DevMapName;
    }

    public void setDevMapName(String devMapName) {
        DevMapName = devMapName;
    }

    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getDevGd() {
        return DevGd;
    }

    public void setDevGd(String devGd) {
        DevGd = devGd;
    }

    public String getDevMapCode() {
        return DevMapCode;
    }

    public void setDevMapCode(String devMapCode) {
        DevMapCode = devMapCode;
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

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }
}

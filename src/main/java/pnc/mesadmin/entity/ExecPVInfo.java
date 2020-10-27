package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 车间现场执行系统权限信息表
 * @author: yin.yang
 * @create: 2018-11-08
 **/
public class ExecPVInfo {
    private Integer Ruid;
    private String Guid;
    private String RoleGd;
    private String OpertMFlag;
    private String PVFlag;
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

    public String getRoleGd() {
        return RoleGd;
    }

    public void setRoleGd(String roleGd) {
        RoleGd = roleGd;
    }

    public String getOpertMFlag() {
        return OpertMFlag;
    }

    public void setOpertMFlag(String opertMFlag) {
        OpertMFlag = opertMFlag;
    }

    public String getPVFlag() {
        return PVFlag;
    }

    public void setPVFlag(String PVFlag) {
        this.PVFlag = PVFlag;
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

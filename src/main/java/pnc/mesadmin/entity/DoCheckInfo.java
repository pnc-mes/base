package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：点检计划任务执行信息表实体类
 * 创建时间：2018-09-11
 * 修改人：
 * 修改时间：
 */
public class DoCheckInfo {
    private int ruid;
    private String guid;
    private String CheckName;
    private String CheckObjGd;
    private Date MaTime;
    private String Status;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCheckName() {
        return CheckName;
    }

    public void setCheckName(String checkName) {
        CheckName = checkName;
    }

    public String getCheckObjGd() {
        return CheckObjGd;
    }

    public void setCheckObjGd(String checkObjGd) {
        CheckObjGd = checkObjGd;
    }

    public Date getMaTime() {
        return MaTime;
    }

    public void setMaTime(Date maTime) {
        MaTime = maTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyMan() {
        return lastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        this.lastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

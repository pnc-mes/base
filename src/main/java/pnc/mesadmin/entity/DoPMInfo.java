package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保养计划任务执行信息表实体类
 * 创建时间：2018-09-11
 * 修改人：
 * 修改时间：
 */
public class DoPMInfo {
    private int ruid;
    private String guid;
    private String PMName;
    private String PMType;
    private String PMObjType;
    private String PMObjGd;
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

    public String getPMName() {
        return PMName;
    }

    public void setPMName(String PMName) {
        this.PMName = PMName;
    }

    public String getPMType() {
        return PMType;
    }

    public void setPMType(String PMType) {
        this.PMType = PMType;
    }

    public String getPMObjType() {
        return PMObjType;
    }

    public void setPMObjType(String PMObjType) {
        this.PMObjType = PMObjType;
    }

    public String getPMObjGd() {
        return PMObjGd;
    }

    public void setPMObjGd(String PMObjGd) {
        this.PMObjGd = PMObjGd;
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

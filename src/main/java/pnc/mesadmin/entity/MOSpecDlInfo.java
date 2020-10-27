package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料上工序信息明细表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-10-17
 * 修改人：
 * 修改时间：
 */
public class MOSpecDlInfo {
    private int ruid;
    private String guid;
    private String mOSpecGd;
    private String assCode;
    private String assSource;
    private String batch;
    private String reML;
    private String ExecGd;
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

    public String getmOSpecGd() {
        return mOSpecGd;
    }

    public void setmOSpecGd(String mOSpecGd) {
        this.mOSpecGd = mOSpecGd;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public String getAssCode() {
        return assCode;
    }

    public void setAssCode(String assCode) {
        this.assCode = assCode;
    }

    public String getAssSource() {
        return assSource;
    }

    public void setAssSource(String assSource) {
        this.assSource = assSource;
    }

    public String getReML() {
        return reML;
    }

    public void setReML(String reML) {
        this.reML = reML;
    }

    public String getExecGd() {
        return ExecGd;
    }

    public void setExecGd(String execGd) {
        ExecGd = execGd;
    }
}

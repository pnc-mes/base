package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线重工日志信息表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-09-28
 * 修改人：
 * 修改时间：
 */
public class OnRWInfo {
    private int ruid;
    private String guid;
    private String batch;
    private String wFVerGd;
    private String wFName;
    private String sSpecVerGd;
    private String sSpecVerName;
    private String tSpecVerGd;
    private String tSpecName;
    private String reaDes;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getwFVerGd() {
        return wFVerGd;
    }

    public void setwFVerGd(String wFVerGd) {
        this.wFVerGd = wFVerGd;
    }

    public String getwFName() {
        return wFName;
    }

    public void setwFName(String wFName) {
        this.wFName = wFName;
    }

    public String getsSpecVerGd() {
        return sSpecVerGd;
    }

    public void setsSpecVerGd(String sSpecVerGd) {
        this.sSpecVerGd = sSpecVerGd;
    }

    public String getsSpecVerName() {
        return sSpecVerName;
    }

    public void setsSpecVerName(String sSpecVerName) {
        this.sSpecVerName = sSpecVerName;
    }

    public String gettSpecVerGd() {
        return tSpecVerGd;
    }

    public void settSpecVerGd(String tSpecVerGd) {
        this.tSpecVerGd = tSpecVerGd;
    }

    public String gettSpecName() {
        return tSpecName;
    }

    public void settSpecName(String tSpecName) {
        this.tSpecName = tSpecName;
    }

    public String getReaDes() {
        return reaDes;
    }

    public void setReaDes(String reaDes) {
        this.reaDes = reaDes;
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

package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工艺工序信息表实体类
 * 创建人：刘福志
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class WFSpecInfo {
    private int ruid;
    private String guid;
    private String wfVerGd;
    private String specVerGd;
    private String eOSpec;
    private String eRSpec;
    private String nSpecVerGd;
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

    public String getWfVerGd() {
        return wfVerGd;
    }

    public void setWfVerGd(String wfVerGd) {
        this.wfVerGd = wfVerGd;
    }

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }

    public String geteOSpec() {
        return eOSpec;
    }

    public void seteOSpec(String eOSpec) {
        this.eOSpec = eOSpec;
    }

    public String geteRSpec() {
        return eRSpec;
    }

    public void seteRSpec(String eRSpec) {
        this.eRSpec = eRSpec;
    }

    public String getnSpecVerGd() {
        return nSpecVerGd;
    }

    public void setnSpecVerGd(String nSpecVerGd) {
        this.nSpecVerGd = nSpecVerGd;
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

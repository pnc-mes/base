package pnc.mesadmin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 乔帅阳 on 2018/7/30.
 */
public class MinTimeWindowInfo implements Serializable {
    private Integer ruid;
    private String guid;
    private String minTimeWindowName;
    private String description;
    private String maVerGd;
    private String wFVerGd;
    private String specVerGd;
    private String devGd;
    private Float minTime;
    private String overTimeAction;
    private String overdueEmailDistributionGd;
    private String overdueEmailMessageGd;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;


    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getMinTimeWindowName() {
        return minTimeWindowName;
    }

    public void setMinTimeWindowName(String minTimeWindowName) {
        this.minTimeWindowName = minTimeWindowName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaVerGd() {
        return maVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        this.maVerGd = maVerGd;
    }

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }

    public String getDevGd() {
        return devGd;
    }

    public void setDevGd(String devGd) {
        this.devGd = devGd;
    }

    public Float getMinTime() {
        return minTime;
    }

    public void setMinTime(Float minTime) {
        this.minTime = minTime;
    }

    public String getOverTimeAction() {
        return overTimeAction;
    }

    public void setOverTimeAction(String overTimeAction) {
        this.overTimeAction = overTimeAction;
    }

    public String getOverdueEmailDistributionGd() {
        return overdueEmailDistributionGd;
    }

    public void setOverdueEmailDistributionGd(String overdueEmailDistributionGd) {
        this.overdueEmailDistributionGd = overdueEmailDistributionGd;
    }

    public String getOverdueEmailMessageGd() {
        return overdueEmailMessageGd;
    }

    public void setOverdueEmailMessageGd(String overdueEmailMessageGd) {
        this.overdueEmailMessageGd = overdueEmailMessageGd;
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

    public String getwFVerGd() {
        return wFVerGd;
    }

    public void setwFVerGd(String wFVerGd) {
        this.wFVerGd = wFVerGd;
    }
}

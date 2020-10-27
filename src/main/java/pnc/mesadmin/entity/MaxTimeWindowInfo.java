package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 10:11
 * @Description:
 */
public class MaxTimeWindowInfo {
    private int ruid;
    private String guid;
    private String maxTimeWindowName;
    private String description;
    private String maVerGd;
    private String wFVerGd;
    private String startSpecVerGd;
    private String endSpecVerGd;
    private float maxTime;
    private float presetTime;
    private String presetEmailDistributionGd;
    private String presetEmailMessageGd;
    private String overTimeAction;
    private String overdueEmailDistributionGd;
    private String overdueEmailMessageGd;
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


    public String getMaxTimeWindowName() {
        return maxTimeWindowName;
    }

    public void setMaxTimeWindowName(String maxTimeWindowName) {
        this.maxTimeWindowName = maxTimeWindowName;
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

    public String getStartSpecVerGd() {
        return startSpecVerGd;
    }

    public void setStartSpecVerGd(String startSpecVerGd) {
        this.startSpecVerGd = startSpecVerGd;
    }

    public String getEndSpecVerGd() {
        return endSpecVerGd;
    }

    public void setEndSpecVerGd(String endSpecVerGd) {
        this.endSpecVerGd = endSpecVerGd;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public float getPresetTime() {
        return presetTime;
    }

    public void setPresetTime(float presetTime) {
        this.presetTime = presetTime;
    }

    public String getPresetEmailDistributionGd() {
        return presetEmailDistributionGd;
    }

    public void setPresetEmailDistributionGd(String presetEmailDistributionGd) {
        this.presetEmailDistributionGd = presetEmailDistributionGd;
    }

    public String getPresetEmailMessageGd() {
        return presetEmailMessageGd;
    }

    public void setPresetEmailMessageGd(String presetEmailMessageGd) {
        this.presetEmailMessageGd = presetEmailMessageGd;
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

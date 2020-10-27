package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 14:03
 * @Description:
 */
public class CyclePlanTiggerRulelnfo {
    private int ruid;
    private String guid;
    private String cyclePlanGd;
    private String timeType;
    private String timeContent;
    private String weekContent;
    private int monthContent;
    private String yearContent;
    private String desContent;
    private String quarterContent;
    private float upTime;
    private float downTime;
    private float presetTime;
    private String presetEmailDistributionGd;
    private String presetEmailMessageGd;
    private String startEmailDistributionGd;
    private String startEmailMessageGd;
    //private String overTimeAction;
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

    public String getCyclePlanGd() {
        return cyclePlanGd;
    }

    public void setCyclePlanGd(String cyclePlanGd) {
        this.cyclePlanGd = cyclePlanGd;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getTimeContent() {
        return timeContent;
    }

    public void setTimeContent(String timeContent) {
        this.timeContent = timeContent;
    }

    public String getWeekContent() {
        return weekContent;
    }

    public void setWeekContent(String weekContent) {
        this.weekContent = weekContent;
    }

    public int getMonthContent() {
        return monthContent;
    }

    public void setMonthContent(int monthContent) {
        this.monthContent = monthContent;
    }

    public String getYearContent() {
        return yearContent;
    }

    public void setYearContent(String yearContent) {
        this.yearContent = yearContent;
    }

    public String getDesContent() {
        return desContent;
    }

    public void setDesContent(String desContent) {
        this.desContent = desContent;
    }

    public String getQuarterContent() {
        return quarterContent;
    }

    public void setQuarterContent(String quarterContent) {
        this.quarterContent = quarterContent;
    }

    public float getUpTime() {
        return upTime;
    }

    public void setUpTime(float upTime) {
        this.upTime = upTime;
    }

    public float getDownTime() {
        return downTime;
    }

    public void setDownTime(float downTime) {
        this.downTime = downTime;
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

    public String getStartEmailDistributionGd() {
        return startEmailDistributionGd;
    }

    public void setStartEmailDistributionGd(String startEmailDistributionGd) {
        this.startEmailDistributionGd = startEmailDistributionGd;
    }

    public String getStartEmailMessageGd() {
        return startEmailMessageGd;
    }

    public void setStartEmailMessageGd(String startEmailMessageGd) {
        this.startEmailMessageGd = startEmailMessageGd;
    }

    /*public String getOverTimeAction() {
        return overTimeAction;
    }

    public void setOverTimeAction(String overTimeAction) {
        this.overTimeAction = overTimeAction;
    }*/

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
}

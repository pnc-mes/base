package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/27 18:08
 * @Description:
 */
public class CurrentGearInfo {
    private int ruid;
    private String guid;
    private String currentName;
    private String startOpt;
    private float startVal;
    private float endVal;
    private String endOpt;
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

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public String getStartOpt() {
        return startOpt;
    }

    public void setStartOpt(String startOpt) {
        this.startOpt = startOpt;
    }

    public float getStartVal() {
        return startVal;
    }

    public void setStartVal(float startVal) {
        this.startVal = startVal;
    }

    public float getEndVal() {
        return endVal;
    }

    public void setEndVal(float endVal) {
        this.endVal = endVal;
    }

    public String getEndOpt() {
        return endOpt;
    }

    public void setEndOpt(String endOpt) {
        this.endOpt = endOpt;
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

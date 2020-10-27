package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 16:18
 * @Description:
 */
public class DevFamilyCheckPlanInfo {
    private int ruid;
    private String guid;
    private String devFamilyGd;
    private String checkPlanGd;

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

    public String getDevFamilyGd() {
        return devFamilyGd;
    }

    public void setDevFamilyGd(String devFamilyGd) {
        this.devFamilyGd = devFamilyGd;
    }

    public String getCheckPlanGd() {
        return checkPlanGd;
    }

    public void setCheckPlanGd(String checkPlanGd) {
        this.checkPlanGd = checkPlanGd;
    }
}

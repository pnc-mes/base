package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 10:52
 * @Description:
 */
public class DevCheckPlanInfo {
    private int ruid;
    private String guid;
    private String devGd;
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

    public String getDevGd() {
        return devGd;
    }

    public void setDevGd(String devGd) {
        this.devGd = devGd;
    }

    public String getCheckPlanGd() {
        return checkPlanGd;
    }

    public void setCheckPlanGd(String checkPlanGd) {
        this.checkPlanGd = checkPlanGd;
    }
}

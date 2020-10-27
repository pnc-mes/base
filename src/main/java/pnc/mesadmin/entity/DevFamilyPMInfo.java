package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 16:13
 * @Description:
 */
public class DevFamilyPMInfo {
    private int ruid;
    private String guid;
    private String devFamilyGd;
    private String pMGd;
    private String pMType;

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

    public String getpMGd() {
        return pMGd;
    }

    public void setpMGd(String pMGd) {
        this.pMGd = pMGd;
    }

    public String getpMType() {
        return pMType;
    }

    public void setpMType(String pMType) {
        this.pMType = pMType;
    }
}

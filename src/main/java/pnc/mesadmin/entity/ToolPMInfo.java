package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 17:46
 * @Description:
 */
public class ToolPMInfo {
    private int ruid;
    private String guid;
    private String toolGd;
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

    public String getToolGd() {
        return toolGd;
    }

    public void setToolGd(String toolGd) {
        this.toolGd = toolGd;
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

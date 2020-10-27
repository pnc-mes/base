package pnc.mesadmin.entity;

/**
 * 工具点检计划信息表
 * 熊伟
 */
public class ToolCheckPlanInfo {
    private int Ruid;
    private String Guid;
    private String ToolGd;
    private String CheckPlanGd;

    public int getRuid() {
        return Ruid;
    }

    public void setRuid(int ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getToolGd() {
        return ToolGd;
    }

    public void setToolGd(String toolGd) {
        ToolGd = toolGd;
    }

    public String getCheckPlanGd() {
        return CheckPlanGd;
    }

    public void setCheckPlanGd(String checkPlanGd) {
        CheckPlanGd = checkPlanGd;
    }
}

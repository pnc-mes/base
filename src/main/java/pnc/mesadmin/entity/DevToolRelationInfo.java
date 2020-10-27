package pnc.mesadmin.entity;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备工具关系表Model
 * 修改人：
 * 修改时间：
 */
public class DevToolRelationInfo {
    private int Ruid;
    private String DevGd;
    private String ToolGd;

    public int getRuid() {
        return Ruid;
    }

    public void setRuid(int ruid) {
        Ruid = ruid;
    }

    public String getDevGd() {
        return DevGd;
    }

    public void setDevGd(String devGd) {
        DevGd = devGd;
    }

    public String getToolGd() {
        return ToolGd;
    }

    public void setToolGd(String toolGd) {
        ToolGd = toolGd;
    }
}

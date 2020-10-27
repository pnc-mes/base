package pnc.mesadmin.entity;

/**
 * 载具点检计划信息表
 * 实体
 */
public class CarrierCheckPlanInfo {
    private int Ruid;
    private String Guid;
    private String CarrierGd;
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

    public String getCarrierGd() {
        return CarrierGd;
    }

    public void setCarrierGd(String carrierGd) {
        CarrierGd = carrierGd;
    }

    public String getCheckPlanGd() {
        return CheckPlanGd;
    }

    public void setCheckPlanGd(String checkPlanGd) {
        CheckPlanGd = checkPlanGd;
    }
}

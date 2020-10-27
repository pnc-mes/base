package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 20:23
 * @Description:
 */
public class CarrierFamilyPMInfo {
    private int ruid;
    private String guid;
    private String carrierFamilyGd;
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

    public String getCarrierFamilyGd() {
        return carrierFamilyGd;
    }

    public void setCarrierFamilyGd(String carrierFamilyGd) {
        this.carrierFamilyGd = carrierFamilyGd;
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

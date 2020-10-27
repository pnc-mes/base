package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 18:42
 * @Description:
 */
public class CarrierreLationInfo {
    private int ruid;
    private String carrierGd;
    private String batch;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getCarrierGd() {
        return carrierGd;
    }

    public void setCarrierGd(String carrierGd) {
        this.carrierGd = carrierGd;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}

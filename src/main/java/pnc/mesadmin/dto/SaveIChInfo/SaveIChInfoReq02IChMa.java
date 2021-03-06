package pnc.mesadmin.dto.SaveIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/7.
 */
public class SaveIChInfoReq02IChMa {
    @JsonProperty("InCDlRd")
    private int InCDlRd;

    @JsonProperty("PurChDlRd")
    private int PurChDlRd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("AFeed")
    private String AFeed;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getInCDlRd() {
        return InCDlRd;
    }

    @JsonIgnore
    public void setInCDlRd(int inCDlRd) {
        InCDlRd = inCDlRd;
    }

    @JsonIgnore
    public int getPurChDlRd() {
        return PurChDlRd;
    }

    @JsonIgnore
    public void setPurChDlRd(int purChDlRd) {
        PurChDlRd = purChDlRd;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }

    @JsonIgnore
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }

    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    @JsonIgnore
    public String getAFeed() {
        return AFeed;
    }

    @JsonIgnore
    public void setAFeed(String AFeed) {
        this.AFeed = AFeed;
    }

    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }

    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

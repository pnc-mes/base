package pnc.mesadmin.dto.SavePurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/6.
 */
public class SavePurchInfoReq02PurMa {
    @JsonProperty("PurChDlRd")
    private int PurChDlRd;
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("StoreRd")
    private int StoreRd;
    @JsonProperty("AFeed")
    private String AFeed;
    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }
    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
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
    public String getMaName() {
        return MaName;
    }
    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
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
    public int getStoreRd() {
        return StoreRd;
    }
    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
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
    public int getPurChDlRd() {
        return PurChDlRd;
    }
    @JsonIgnore
    public void setPurChDlRd(int purChDlRd) {
        PurChDlRd = purChDlRd;
    }
}

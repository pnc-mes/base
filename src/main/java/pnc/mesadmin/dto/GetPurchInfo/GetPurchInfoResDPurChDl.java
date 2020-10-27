package pnc.mesadmin.dto.GetPurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/6.
 */
public class GetPurchInfoResDPurChDl {
    @JsonProperty("PurChDlRd")
    private int PurChDlRd;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("AFeed")
    private String AFeed;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("MaInfo")
    private GetPurchInfoResDPurChDlMa MaInfo;
    @JsonProperty("StoreInfo")
    private GetPurchInfoResDPurChDlStore StoreInfo;
    @JsonIgnore
    public int getPurChDlRd() {
        return PurChDlRd;
    }
    @JsonIgnore
    public void setPurChDlRd(int purChDlRd) {
        PurChDlRd = purChDlRd;
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
    public String getAFeed() {
        return AFeed;
    }
    @JsonIgnore
    public void setAFeed(String AFeed) {
        this.AFeed = AFeed;
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
    public GetPurchInfoResDPurChDlMa getMaInfo() {
        return MaInfo;
    }
    @JsonIgnore
    public void setMaInfo(GetPurchInfoResDPurChDlMa maInfo) {
        MaInfo = maInfo;
    }
    @JsonIgnore
    public GetPurchInfoResDPurChDlStore getStoreInfo() {
        return StoreInfo;
    }
    @JsonIgnore
    public void setStoreInfo(GetPurchInfoResDPurChDlStore storeInfo) {
        StoreInfo = storeInfo;
    }
}

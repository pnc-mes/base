package pnc.mesadmin.dto.GetWIncMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetWIncMaInfoResD {

    @JsonProperty("PurChDtlRd")
    private int PurChDtlRd;

    @JsonProperty("MaInfo")
    private GetWIncMaInfoResDMaInfo MaInfo;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("ScanNum")
    private float ScanNum;

    @JsonProperty("UnCInCNum")
    private float UnCInCNum;

    @JsonProperty("AFeed")
    private String AFeed;

    @JsonProperty("StoreInfo")
    private GetWIncMaInfoResDStoreInfo StoreInfo;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public int getPurChDtlRd() {
        return PurChDtlRd;
    }

    @JsonIgnore
    public void setPurChDtlRd(int purChDtlRd) {
        PurChDtlRd = purChDtlRd;
    }

    @JsonIgnore
    public GetWIncMaInfoResDMaInfo getMaInfo() {
        return MaInfo;
    }

    @JsonIgnore
    public void setMaInfo(GetWIncMaInfoResDMaInfo maInfo) {
        MaInfo = maInfo;
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
    public float getScanNum() {
        return ScanNum;
    }

    @JsonIgnore
    public void setScanNum(float scanNum) {
        ScanNum = scanNum;
    }

    @JsonIgnore
    public float getUnCInCNum() {
        return UnCInCNum;
    }

    @JsonIgnore
    public void setUnCInCNum(float unCInCNum) {
        UnCInCNum = unCInCNum;
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
    public GetWIncMaInfoResDStoreInfo getStoreInfo() {
        return StoreInfo;
    }

    @JsonIgnore
    public void setStoreInfo(GetWIncMaInfoResDStoreInfo storeInfo) {
        StoreInfo = storeInfo;
    }

    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}

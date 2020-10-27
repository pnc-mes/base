package pnc.mesadmin.dto.GetAllPDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllPDlInfoResD implements Serializable {
    @JsonProperty("PurChDlRd")
    private int PurChDlRd;
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("PurChCode")
    private String PurChCode;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaDes")
    private String MaDes;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("ScanNum")
    private float ScanNum;
    @JsonProperty("UnCInCNum")
    private float UnCInCNum;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("ArrivalTime")
    private String ArrivalTime;
    @JsonProperty("AFeed")
    private String AFeed;
    @JsonProperty("StoreInfo")
    private GetAllPDlInfoResDStore StoreInfo;
    @JsonIgnore
    public String getMaDes() {
        return MaDes;
    }
    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
    }

    @JsonIgnore
    public String getPurChCode() {
        return PurChCode;
    }
    @JsonIgnore
    public void setPurChCode(String purChCode) {
        PurChCode = purChCode;
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
    public String getMaName() {
        return MaName;
    }
    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
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
    public String getAFeed() {
        return AFeed;
    }
    @JsonIgnore
    public void setAFeed(String AFeed) {
        this.AFeed = AFeed;
    }
    @JsonIgnore
    public GetAllPDlInfoResDStore getStoreInfo() {
        return StoreInfo;
    }
    @JsonIgnore
    public void setStoreInfo(GetAllPDlInfoResDStore storeInfo) {
        StoreInfo = storeInfo;
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
    public String getUnitName() {
        return UnitName;
    }
    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
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
    public float getUnCInCNum() {
        return UnCInCNum;
    }
    @JsonIgnore
    public void setUnCInCNum(float unCInCNum) {
        UnCInCNum = unCInCNum;
    }
    @JsonIgnore
    public String getArrivalTime() {
        return ArrivalTime;
    }
    @JsonIgnore
    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }
}

package pnc.mesadmin.dto.GetAllIDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllIDlInfoResD {

    @JsonProperty("InCDlRd")
    private int InCDlRd;
    @JsonProperty("InCCode")
    private String InCCode;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("Num")
    private  float Num;
    @JsonProperty("ScanNum")
    private float ScanNum;
    @JsonProperty("StoreInfo")
    private GetAllIDlInfoResDStore StoreInfo;
    @JsonProperty("AFeed")
    private String AFeed;
    @JsonProperty("IsPrint")
    private String IsPrint;
    @JsonIgnore
    public String getMaDes() {
        return MaDes;
    }
    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
    }

    @JsonIgnore
    public String getInCCode() {
        return InCCode;
    }
    @JsonIgnore
    public void setInCCode(String inCCode) {
        InCCode = inCCode;
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
    public GetAllIDlInfoResDStore getStoreInfo() {
        return StoreInfo;
    }
    @JsonIgnore
    public void setStoreInfo(GetAllIDlInfoResDStore storeInfo) {
        StoreInfo = storeInfo;
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
    public int getInCDlRd() {
        return InCDlRd;
    }
    @JsonIgnore
    public void setInCDlRd(int inCDlRd) {
        InCDlRd = inCDlRd;
    }
    @JsonIgnore
    public String getIsPrint() {
        return IsPrint;
    }
    @JsonIgnore
    public void setIsPrint(String isPrint) {
        IsPrint = isPrint;
    }
}

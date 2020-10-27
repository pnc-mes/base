package pnc.mesadmin.dto.GetIncInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/7.
 */
public class GetIncInfoResDIncDl {
    @JsonProperty("InCDlRd")
    private int InCDlRd;

    @JsonProperty("PurChDlRd")
    private int PurChDlRd;

    @JsonProperty("MaInfo")
    private  GetIncInfoResDIncDlMa MaInfo;

    @JsonProperty("TotalNum")
    private float TotalNum;

    @JsonProperty("TotalScanNum")
    private float TotalScanNum;

    @JsonProperty("UnCInCNum")
    private float UnCInCNum;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("ScanNum")
    private float ScanNum;

    @JsonProperty("AFeed")
    private String AFeed;

    @JsonProperty("StoreInfo")
    private GetIncInfoResDIncDlStore StoreInfo;

    @JsonProperty("UnitName")
    private String UnitName;

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
    public GetIncInfoResDIncDlMa getMaInfo() {
        return MaInfo;
    }

    @JsonIgnore
    public void setMaInfo(GetIncInfoResDIncDlMa maInfo) {
        MaInfo = maInfo;
    }

    @JsonIgnore
    public float getTotalNum() {
        return TotalNum;
    }

    @JsonIgnore
    public void setTotalNum(float totalNum) {
        TotalNum = totalNum;
    }

    @JsonIgnore
    public float getTotalScanNum() {
        return TotalScanNum;
    }

    @JsonIgnore
    public void setTotalScanNum(float totalScanNum) {
        TotalScanNum = totalScanNum;
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
    public GetIncInfoResDIncDlStore getStoreInfo() {
        return StoreInfo;
    }

    @JsonIgnore
    public void setStoreInfo(GetIncInfoResDIncDlStore storeInfo) {
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

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

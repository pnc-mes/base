package pnc.mesadmin.dto.GetCKDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllCKInfo.GetAllCKInfoResD;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKDlInfoResD extends GetAllCKInfoResD {
    @JsonProperty("CkDtlRd")
    private int CkDtlRd;
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
    @JsonProperty("UnScanNum")
    private float UnScanNum;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("AssSource")
    private String AssSource;
    @JsonIgnore
    public int getCkDtlRd() {
        return CkDtlRd;
    }
    @JsonIgnore
    public void setCkDtlRd(int ckDtlRd) {
        CkDtlRd = ckDtlRd;
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
    public String getMaDes() {
        return MaDes;
    }
    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
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
    public float getScanNum() {
        return ScanNum;
    }
    @JsonIgnore
    public void setScanNum(float scanNum) {
        ScanNum = scanNum;
    }
    @JsonIgnore
    public float getUnScanNum() {
        return UnScanNum;
    }
    @JsonIgnore
    public void setUnScanNum(float unScanNum) {
        UnScanNum = unScanNum;
    }
    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }
}

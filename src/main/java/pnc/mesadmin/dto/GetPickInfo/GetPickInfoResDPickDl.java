package pnc.mesadmin.dto.GetPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetPickInfoResDPickDl {
    @JsonProperty("PKDtlRd")
    private int PKDtlRd;

    @JsonProperty("ReMaVerRd")
    private int ReMaVerRd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("StoreInfo")
    private GetPickInfoResDPStore StoreInfo;

    @JsonProperty("SLNum")
    private float SLNum;

    @JsonProperty("StoreNum")
    private float StoreNum;

    @JsonProperty("UNNum")
    private float UNNum;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("ReMaInfo")
    private List<GetPickInfoResDPReMa> ReMaInfo;

    @JsonIgnore
    public int getPKDtlRd() {
        return PKDtlRd;
    }

    @JsonIgnore
    public void setPKDtlRd(int PKDtlRd) {
        this.PKDtlRd = PKDtlRd;
    }

    @JsonIgnore
    public int getReMaVerRd() {
        return ReMaVerRd;
    }

    @JsonIgnore
    public void setReMaVerRd(int reMaVerRd) {
        ReMaVerRd = reMaVerRd;
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
    public GetPickInfoResDPStore getStoreInfo() {
        return StoreInfo;
    }

    @JsonIgnore
    public void setStoreInfo(GetPickInfoResDPStore storeInfo) {
        StoreInfo = storeInfo;
    }

    @JsonIgnore
    public float getSLNum() {
        return SLNum;
    }

    @JsonIgnore
    public void setSLNum(float SLNum) {
        this.SLNum = SLNum;
    }

    @JsonIgnore
    public float getStoreNum() {
        return StoreNum;
    }

    @JsonIgnore
    public void setStoreNum(float storeNum) {
        StoreNum = storeNum;
    }

    @JsonIgnore
    public float getUNNum() {
        return UNNum;
    }

    @JsonIgnore
    public void setUNNum(float UNNum) {
        this.UNNum = UNNum;
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

    @JsonIgnore
    public List<GetPickInfoResDPReMa> getReMaInfo() {
        return ReMaInfo;
    }

    @JsonIgnore
    public void setReMaInfo(List<GetPickInfoResDPReMa> reMaInfo) {
        ReMaInfo = reMaInfo;
    }
}

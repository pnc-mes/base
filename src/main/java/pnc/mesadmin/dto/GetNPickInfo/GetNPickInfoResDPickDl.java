package pnc.mesadmin.dto.GetNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetNPickInfoResDPickDl {
    @JsonProperty("PKDtlRd")
    private int PKDtlRd;
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaDes")
    private String MaDes;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("UnitName")
    private String UnitName;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("StoreInfo")
    private GetNPickInfoResDStore StoreInfo;
    @JsonIgnore
    public GetNPickInfoResDStore getStoreInfo() {
        return StoreInfo;
    }
    @JsonIgnore
    public void setStoreInfo(GetNPickInfoResDStore storeInfo) {
        StoreInfo = storeInfo;
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
    public int getPKDtlRd() {
        return PKDtlRd;
    }
    @JsonIgnore
    public void setPKDtlRd(int PKDtlRd) {
        this.PKDtlRd = PKDtlRd;
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

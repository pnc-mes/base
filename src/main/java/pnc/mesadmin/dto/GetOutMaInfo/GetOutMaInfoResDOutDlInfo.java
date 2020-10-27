package pnc.mesadmin.dto.GetOutMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 14:40
 * @Description:
 */
public class GetOutMaInfoResDOutDlInfo {
    @JsonProperty("OutDtlRd")
    private int OutDtlRd;

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

    @JsonProperty("StoreInfo")
    private GetOutMaInfoResDOutDlStoreInfo StoreInfo;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public int getOutDtlRd() {
        return OutDtlRd;
    }
    @JsonIgnore
    public void setOutDtlRd(int outDtlRd) {
        OutDtlRd = outDtlRd;
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
    public float getNum() {
        return Num;
    }
    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }
    @JsonIgnore
    public GetOutMaInfoResDOutDlStoreInfo getStoreInfo() {
        return StoreInfo;
    }
    @JsonIgnore
    public void setStoreInfo(GetOutMaInfoResDOutDlStoreInfo storeInfo) {
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

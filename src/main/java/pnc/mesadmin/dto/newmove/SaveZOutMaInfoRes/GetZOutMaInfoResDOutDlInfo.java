package pnc.mesadmin.dto.newmove.SaveZOutMaInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetOutMaInfo.GetOutMaInfoResDOutDlLInfo;
import pnc.mesadmin.dto.GetOutMaInfo.GetOutMaInfoResDOutDlStoreInfo;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/3 09:47
 * @Description:
 */
public class GetZOutMaInfoResDOutDlInfo {
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

    @JsonProperty("LInfo")
    private GetOutMaInfoResDOutDlLInfo LInfo;

    @JsonProperty("BarType")
    private String BarType;

    @JsonProperty("Batch")
    private String Batch;


    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("F1")
    private String F1;

    @JsonProperty("F2")
    private String F2;

    @JsonProperty("F3")
    private String F3;

    @JsonProperty("F4")
    private String F4;



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
    @JsonIgnore
    public String getF1() {
        return F1;
    }
    @JsonIgnore
    public void setF1(String f1) {
        F1 = f1;
    }
    @JsonIgnore
    public String getF2() {
        return F2;
    }
    @JsonIgnore
    public void setF2(String f2) {
        F2 = f2;
    }
    @JsonIgnore
    public String getF3() {
        return F3;
    }
    @JsonIgnore
    public void setF3(String f3) {
        F3 = f3;
    }
    @JsonIgnore
    public String getF4() {
        return F4;
    }
    @JsonIgnore
    public void setF4(String f4) {
        F4 = f4;
    }
    @JsonIgnore
    public GetOutMaInfoResDOutDlLInfo getLInfo() {
        return LInfo;
    }
    @JsonIgnore
    public void setLInfo(GetOutMaInfoResDOutDlLInfo LInfo) {
        this.LInfo = LInfo;
    }
    @JsonIgnore
    public String getBarType() {
        return BarType;
    }
    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }
    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
}

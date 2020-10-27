package pnc.mesadmin.dto.GetMaTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取用料汇总信息Data
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public class GetMaTotalInfoResD {
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
    private GetMaTotalInfoResDStore StoreInfo;

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
    private List<GetMaTotalInfoResDReMa> ReMaInfo;

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
    public GetMaTotalInfoResDStore getStoreInfo() {
        return StoreInfo;
    }

    @JsonIgnore
    public void setStoreInfo(GetMaTotalInfoResDStore storeInfo) {
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
    public List<GetMaTotalInfoResDReMa> getReMaInfo() {
        return ReMaInfo;
    }

    @JsonIgnore
    public void setReMaInfo(List<GetMaTotalInfoResDReMa> reMaInfo) {
        ReMaInfo = reMaInfo;
    }
}

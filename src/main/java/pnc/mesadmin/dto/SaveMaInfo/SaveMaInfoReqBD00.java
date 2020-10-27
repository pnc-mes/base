package pnc.mesadmin.dto.SaveMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/1.
 */
public class SaveMaInfoReqBD00 implements Serializable{
    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaBarCode")
    private String MaBarCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("MaTRd")
    private String MaTRd;

    @JsonProperty("WFInfo")
    private List<WFInfo> WFInfo;

    @JsonProperty("BOMRd")
    private int BOMRd;

    @JsonProperty("SRRd")
    private int SRRd;

    @JsonProperty("PtRd")
    private int PtRd;

    @JsonProperty("UnitRd")
    private int UnitRd;

    @JsonProperty("CpRd")
    private int CpRd;

    @JsonProperty("MinPQ")
    private float MinPQ;

    @JsonProperty("SuName")
    private String SuName;

    @JsonProperty("SuMaCode")
    private String SuMaCode;

    @JsonProperty("CusMaCode")
    private String CusMaCode;

    @JsonProperty("PDFRd")
    private int PDFRd;

    @JsonProperty("TrayPSpRd")
    private int TrayPSpRd;

    @JsonProperty("BoxPSpRd")
    private int BoxPSpRd;

    @JsonProperty("ReMaterial")
    private String ReMaterial;

    @JsonProperty("Shelflife")
    private String Shelflife;

    @JsonProperty("Interval")
    private int Interval;

    @JsonProperty("SUnit")
    private String SUnit;

    @JsonProperty("IsExem")
    private String IsExem;

    @JsonProperty("IsBMg")
    private String IsBMg;

    @JsonProperty("DisRule")
    private String DisRule;

    @JsonProperty("MinPack")
    private float MinPack;

    @JsonProperty("MinSNum")
    private float MinSNum;

    @JsonProperty("MaxSNum")
    private float MaxSNum;

    @JsonProperty("MaPerionRd")
    private Integer MaPerionRd;

    @JsonIgnore
    public Integer getMaPerionRd() {
        return MaPerionRd;
    }
    @JsonIgnore
    public void setMaPerionRd(Integer maPerionRd) {
        MaPerionRd = maPerionRd;
    }

    @JsonProperty("ReMaInfo")
    private List<SaveMaInfoReqBD00ReMaInfo> ReMaInfo;

    @JsonProperty("MaPropertyInfo")
    private List<SaveMaInfoReqBD00MaPropertyInfo> MaPropertyInfo;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("Brand")
    private String Brand;

    @JsonProperty("OrderNum")
    private String OrderNum;

    @JsonProperty("IsBatch")
    private String IsBatch;

    public static class WFInfo{
        @JsonProperty("WfVerRd")
        private Integer WfVerRd;
        @JsonIgnore
        public Integer getWfVerRd() {
            return WfVerRd;
        }
        @JsonIgnore
        public void setWfVerRd(Integer wfVerRd) {
            WfVerRd = wfVerRd;
        }
    }

    @JsonIgnore
    public List<SaveMaInfoReqBD00.WFInfo> getWFInfo() {
        return WFInfo;
    }

    @JsonIgnore
    public void setWFInfo(List<SaveMaInfoReqBD00.WFInfo> WFInfo) {
        this.WFInfo = WFInfo;
    }

    @JsonIgnore
    public int getPtRd() {
        return PtRd;
    }

    @JsonIgnore
    public void setPtRd(int ptRd) {
        PtRd = ptRd;
    }

    @JsonIgnore
    public int getCpRd() {
        return CpRd;
    }

    @JsonIgnore
    public void setCpRd(int cpRd) {
        CpRd = cpRd;
    }

    @JsonIgnore
    public List<SaveMaInfoReqBD00MaPropertyInfo> getMaPropertyInfo() {
        return MaPropertyInfo;
    }

    @JsonIgnore
    public void setMaPropertyInfo(List<SaveMaInfoReqBD00MaPropertyInfo> maPropertyInfo) {
        MaPropertyInfo = maPropertyInfo;
    }

    @JsonIgnore
    public String getMaBarCode() {
        return MaBarCode;
    }

    @JsonIgnore
    public void setMaBarCode(String maBarCode) {
        MaBarCode = maBarCode;
    }

    @JsonIgnore
    public String getIsBatch() {
        return IsBatch;
    }

    @JsonIgnore
    public void setIsBatch(String isBatch) {
        IsBatch = isBatch;
    }

    @JsonIgnore
    public String getBrand() {
        return Brand;
    }

    @JsonIgnore
    public void setBrand(String brand) {
        Brand = brand;
    }

    @JsonIgnore
    public String getOrderNum() {
        return OrderNum;
    }

    @JsonIgnore
    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
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
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }

    @JsonIgnore
    public int getBOMRd() {
        return BOMRd;
    }

    @JsonIgnore
    public void setBOMRd(int BOMRd) {
        this.BOMRd = BOMRd;
    }

    @JsonIgnore
    public int getSRRd() {
        return SRRd;
    }

    @JsonIgnore
    public void setSRRd(int SRRd) {
        this.SRRd = SRRd;
    }

    @JsonIgnore
    public int getUnitRd() {
        return UnitRd;
    }

    @JsonIgnore
    public void setUnitRd(int unitRd) {
        UnitRd = unitRd;
    }

    @JsonIgnore
    public int getPDFRd() {
        return PDFRd;
    }

    @JsonIgnore
    public void setPDFRd(int PDFRd) {
        this.PDFRd = PDFRd;
    }

    @JsonIgnore
    public String getReMaterial() {
        return ReMaterial;
    }

    @JsonIgnore
    public void setReMaterial(String reMaterial) {
        ReMaterial = reMaterial;
    }

    @JsonIgnore
    public String getShelflife() {
        return Shelflife;
    }

    @JsonIgnore
    public void setShelflife(String shelflife) {
        Shelflife = shelflife;
    }

    @JsonIgnore
    public int getInterval() {
        return Interval;
    }

    @JsonIgnore
    public void setInterval(int interval) {
        Interval = interval;
    }

    @JsonIgnore
    public String getSUnit() {
        return SUnit;
    }

    @JsonIgnore
    public void setSUnit(String SUnit) {
        this.SUnit = SUnit;
    }

    @JsonIgnore
    public List<SaveMaInfoReqBD00ReMaInfo> getReMaInfo() {
        return ReMaInfo;
    }

    @JsonIgnore
    public void setReMaInfo(List<SaveMaInfoReqBD00ReMaInfo> reMaInfo) {
        ReMaInfo = reMaInfo;
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
    public String getSuMaCode() {
        return SuMaCode;
    }

    @JsonIgnore
    public void setSuMaCode(String suMaCode) {
        SuMaCode = suMaCode;
    }

    @JsonIgnore
    public String getIsExem() {
        return IsExem;
    }

    @JsonIgnore
    public void setIsExem(String isExem) {
        IsExem = isExem;
    }

    @JsonIgnore
    public String getMaTRd() {
        return MaTRd;
    }

    @JsonIgnore
    public void setMaTRd(String maTRd) {
        MaTRd = maTRd;
    }

    @JsonIgnore
    public String getSuName() {
        return SuName;
    }

    @JsonIgnore
    public void setSuName(String suName) {
        SuName = suName;
    }

    @JsonIgnore
    public String getCusMaCode() {
        return CusMaCode;
    }

    @JsonIgnore
    public void setCusMaCode(String cusMaCode) {
        CusMaCode = cusMaCode;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public String getDisRule() {
        return DisRule;
    }

    @JsonIgnore
    public void setDisRule(String disRule) {
        DisRule = disRule;
    }

    @JsonIgnore
    public float getMinPack() {
        return MinPack;
    }

    @JsonIgnore
    public void setMinPack(float minPack) {
        MinPack = minPack;
    }

    @JsonIgnore
    public float getMinSNum() {
        return MinSNum;
    }

    @JsonIgnore
    public void setMinSNum(float minSNum) {
        MinSNum = minSNum;
    }

    @JsonIgnore
    public float getMaxSNum() {
        return MaxSNum;
    }

    @JsonIgnore
    public void setMaxSNum(float maxSNum) {
        MaxSNum = maxSNum;
    }

    @JsonIgnore
    public String getIsBMg() {
        return IsBMg;
    }

    @JsonIgnore
    public void setIsBMg(String isBMg) {
        IsBMg = isBMg;
    }

    @JsonIgnore
    public int getTrayPSpRd() {
        return TrayPSpRd;
    }

    @JsonIgnore
    public void setTrayPSpRd(int trayPSpRd) {
        TrayPSpRd = trayPSpRd;
    }

    @JsonIgnore
    public int getBoxPSpRd() {
        return BoxPSpRd;
    }

    @JsonIgnore
    public void setBoxPSpRd(int boxPSpRd) {
        BoxPSpRd = boxPSpRd;
    }

    @JsonIgnore
    public float getMinPQ() {
        return MinPQ;
    }

    @JsonIgnore
    public void setMinPQ(float minPQ) {
        MinPQ = minPQ;
    }
}

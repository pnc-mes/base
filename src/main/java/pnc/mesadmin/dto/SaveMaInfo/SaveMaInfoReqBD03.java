package pnc.mesadmin.dto.SaveMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/1.
 */
public class SaveMaInfoReqBD03 implements Serializable{

    @JsonProperty("PtRd")
    private int PtRd;

    @JsonProperty("MinPQ")
    private float MinPQ;

    @JsonProperty("MaRd")
    private int MaRd;

    @JsonProperty("MaBarCode")
    private String MaBarCode;

    @JsonProperty("MaPropertyInfo")
    private List<SaveMaInfoReqBD03MaPropertyInfo> MaPropertyInfo;

    @JsonProperty("IsBatch")
    private String IsBatch;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("WfRd")
    private int WfRd;

    @JsonProperty("BOMRd")
    private int BOMRd;

    @JsonProperty("SRRd")
    private int SRRd;

    @JsonProperty("UnitRd")
    private int UnitRd;

    @JsonProperty("SuName")
    private String SuName;

    @JsonProperty("SuMaCode")
    private String SuMaCode;

    @JsonProperty("CusMaCode")
    private String CusMaCode;

    @JsonProperty("Brand")
    private String Brand;

    @JsonProperty("OrderNum")
    private String OrderNum;

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

    @JsonProperty("ReMaInfo")
    private List<SaveMaInfoReqBD03ReMaInfo> ReMaInfo;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getMaBarCode() {
        return MaBarCode;
    }
    @JsonIgnore
    public void setMaBarCode(String maBarCode) {
        MaBarCode = maBarCode;
    }

    @JsonIgnore
    public List<SaveMaInfoReqBD03MaPropertyInfo> getMaPropertyInfo() {
        return MaPropertyInfo;
    }

    @JsonIgnore
    public void setMaPropertyInfo(List<SaveMaInfoReqBD03MaPropertyInfo> maPropertyInfo) {
        MaPropertyInfo = maPropertyInfo;
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
    public int getMaRd() {
        return MaRd;
    }

    @JsonIgnore
    public void setMaRd(int maRd) {
        MaRd = maRd;
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
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }

    @JsonIgnore
    public int getWfRd() {
        return WfRd;
    }

    @JsonIgnore
    public void setWfRd(int wfRd) {
        WfRd = wfRd;
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
    public List<SaveMaInfoReqBD03ReMaInfo> getReMaInfo() {
        return ReMaInfo;
    }

    @JsonIgnore
    public void setReMaInfo(List<SaveMaInfoReqBD03ReMaInfo> reMaInfo) {
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
    @JsonIgnore
    public int getPtRd() {
        return PtRd;
    }
    @JsonIgnore
    public void setPtRd(int ptRd) {
        PtRd = ptRd;
    }
}

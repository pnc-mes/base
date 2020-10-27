package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/1.
 */
public class GetMaInfoResD implements Serializable{

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaBarCode")
    private String MaBarCode;

    @JsonProperty("IsBatch")
    private String IsBatch;

    @JsonProperty("MaPropertyInfo")
    private List<GetMaInfoResDMaPropertyInfo> MaPropertyInfo;

    @JsonProperty("MaterialType")
    private String MaterialType;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("MaType")
    private GetMaInfoResDMaType MaType;

    @JsonProperty("WFInfo")
    private List<GetMaInfoResDWFInfo> WFInfo;

    @JsonProperty("BOMInfo")
    private GetMaInfoResDBOMInfo BOMInfo;

    @JsonProperty("SRInfo")
    private GetMaInfoResDSRInfo SRInfo;

    @JsonProperty("UnitInfo")
    private GetMaInfoResDUnitInfo UnitInfo;

    @JsonProperty("CPInfo")
    private GetMaInfoResDCPInfo CPInfo;

    @JsonProperty("SuName")
    private String SuName;

    @JsonProperty("CusMaCode")
    private String CusMaCode;

    @JsonProperty("SuMaCode")
    private String SuMaCode;

    @JsonProperty("PDFInfo")
    private GetMaInfoResDPDFInfo PDFInfo;

    @JsonProperty("TrayPackInfo")
    private GetMaInfoResDTrayPackInfo TrayPackInfo;

    @JsonProperty("BoxPackInfo")
    private GetMaInfoResDBoxPackInfo BoxPackInfo;

    @JsonProperty("PtInfo")
    private GetMaInfoResDPtInfo PtInfo;

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

    @JsonProperty("DisRule")
    private String DisRule;

    @JsonProperty("MinPQ")
    private float MinPQ;

    @JsonProperty("MinSNum")
    private float MinSNum;

    @JsonProperty("MaxSNum")
    private float MaxSNum;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("DSource")
    private String DSource;

    @JsonProperty("ReMaInfo")
    private List<GetMaInfoResDReMaInfo> ReMaInfo;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("Brand")
    private String Brand;

    @JsonProperty("OrderNum")
    private String OrderNum;

    @JsonProperty("OnLineMaPeriodInfo")
    private MaOnLineMaPeriodInfo OnLineMaPeriodInfo;

    @JsonIgnore
    public MaOnLineMaPeriodInfo getOnLineMaPeriodInfo() {
        return OnLineMaPeriodInfo;
    }
    @JsonIgnore
    public void setOnLineMaPeriodInfo(MaOnLineMaPeriodInfo onLineMaPeriodInfo) {
        OnLineMaPeriodInfo = onLineMaPeriodInfo;
    }

    @JsonIgnore
    public List<GetMaInfoResDMaPropertyInfo> getMaPropertyInfo() {
        return MaPropertyInfo;

    }

    @JsonIgnore
    public void setMaPropertyInfo(List<GetMaInfoResDMaPropertyInfo> maPropertyInfo) {
        MaPropertyInfo = maPropertyInfo;
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
    public String getMaterialType() {
        return MaterialType;
    }

    @JsonIgnore
    public void setMaterialType(String materialType) {
        MaterialType = materialType;
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
    public String getIsDef() {
        return IsDef;
    }

    @JsonIgnore
    public void setIsDef(String isDef) {
        IsDef = isDef;
    }

    @JsonIgnore
    public List<GetMaInfoResDWFInfo> getWFInfo() {
        return WFInfo;
    }

    @JsonIgnore
    public void setWFInfo(List<GetMaInfoResDWFInfo> WFInfo) {
        this.WFInfo = WFInfo;
    }

    @JsonIgnore
    public GetMaInfoResDBOMInfo getBOMInfo() {
        return BOMInfo;
    }

    @JsonIgnore
    public void setBOMInfo(GetMaInfoResDBOMInfo BOMInfo) {
        this.BOMInfo = BOMInfo;
    }

    @JsonIgnore
    public GetMaInfoResDSRInfo getSRInfo() {
        return SRInfo;
    }

    @JsonIgnore
    public void setSRInfo(GetMaInfoResDSRInfo SRInfo) {
        this.SRInfo = SRInfo;
    }

    @JsonIgnore
    public GetMaInfoResDUnitInfo getUnitInfo() {
        return UnitInfo;
    }

    @JsonIgnore
    public void setUnitInfo(GetMaInfoResDUnitInfo unitInfo) {
        UnitInfo = unitInfo;
    }

    @JsonIgnore
    public GetMaInfoResDPDFInfo getPDFInfo() {
        return PDFInfo;
    }

    @JsonIgnore
    public void setPDFInfo(GetMaInfoResDPDFInfo PDFInfo) {
        this.PDFInfo = PDFInfo;
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
    public List<GetMaInfoResDReMaInfo> getReMaInfo() {
        return ReMaInfo;
    }

    @JsonIgnore
    public void setReMaInfo(List<GetMaInfoResDReMaInfo> reMaInfo) {
        ReMaInfo = reMaInfo;
    }

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public GetMaInfoResDMaType getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(GetMaInfoResDMaType maType) {
        MaType = maType;
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
    public GetMaInfoResDTrayPackInfo getTrayPackInfo() {
        return TrayPackInfo;
    }

    @JsonIgnore
    public void setTrayPackInfo(GetMaInfoResDTrayPackInfo trayPackInfo) {
        TrayPackInfo = trayPackInfo;
    }

    @JsonIgnore
    public GetMaInfoResDBoxPackInfo getBoxPackInfo() {
        return BoxPackInfo;
    }

    @JsonIgnore
    public void setBoxPackInfo(GetMaInfoResDBoxPackInfo boxPackInfo) {
        BoxPackInfo = boxPackInfo;
    }

    @JsonIgnore
    public GetMaInfoResDCPInfo getCPInfo() {
        return CPInfo;
    }

    @JsonIgnore
    public void setCPInfo(GetMaInfoResDCPInfo CPInfo) {
        this.CPInfo = CPInfo;
    }

    @JsonIgnore
    public String getDSource() {
        return DSource;
    }

    @JsonIgnore
    public void setDSource(String DSource) {
        this.DSource = DSource;
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
    public float getMinPQ() {
        return MinPQ;
    }
    @JsonIgnore
    public void setMinPQ(float minPQ) {
        MinPQ = minPQ;
    }
    @JsonIgnore
    public GetMaInfoResDPtInfo getPtInfo() {
        return PtInfo;
    }
    @JsonIgnore
    public void setPtInfo(GetMaInfoResDPtInfo ptInfo) {
        PtInfo = ptInfo;
    }
}


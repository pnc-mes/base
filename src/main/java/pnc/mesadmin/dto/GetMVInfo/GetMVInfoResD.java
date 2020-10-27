package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVInfoResD implements Serializable{

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("MaType")
    private List<GetMVInfoResDMaType> MaType;

    @JsonProperty("WFInfo")
    private GetMVInfoResDWFInfo WFInfo;

    @JsonProperty("BOMInfo")
    private GetMVInfoResDBOMInfo BOMInfo;

    @JsonProperty("SRInfo")
    private GetMVInfoResDSRInfo SRInfo;

    @JsonProperty("UnitInfo")
    private GetMVInfoResDUnitInfo UnitInfo;

    @JsonProperty("SPInfo")
    private GetMVInfoResDSPInfo SPInfo;

    @JsonProperty("PDFInfo")
    private GetMVInfoResDPDFInfo PDFInfo;

    @JsonProperty("ReMaterial")
    private String ReMaterial;

    @JsonProperty("SuMaCode")
    private String SuMaCode;

    @JsonProperty("Shelflife")
    private String Shelflife;

    @JsonProperty("Interval")
    private int Interval;

    @JsonProperty("SUnit")
    private String SUnit;

    @JsonProperty("IsExem")
    private String IsExem;

    @JsonProperty("PackInfo")
    private GetMVInfoResDPackInfo PackInfo;

    @JsonProperty("ReMaInfo")
    private List<GetMVInfoResDReMaInfo> ReMaInfo;

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
    public List<GetMVInfoResDMaType> getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(List<GetMVInfoResDMaType> maType) {
        MaType = maType;
    }

    @JsonIgnore
    public GetMVInfoResDWFInfo getWFInfo() {
        return WFInfo;
    }

    @JsonIgnore
    public void setWFInfo(GetMVInfoResDWFInfo WFInfo) {
        this.WFInfo = WFInfo;
    }

    @JsonIgnore
    public GetMVInfoResDBOMInfo getBOMInfo() {
        return BOMInfo;
    }

    @JsonIgnore
    public void setBOMInfo(GetMVInfoResDBOMInfo BOMInfo) {
        this.BOMInfo = BOMInfo;
    }

    @JsonIgnore
    public GetMVInfoResDSRInfo getSRInfo() {
        return SRInfo;
    }

    @JsonIgnore
    public void setSRInfo(GetMVInfoResDSRInfo SRInfo) {
        this.SRInfo = SRInfo;
    }

    @JsonIgnore
    public GetMVInfoResDUnitInfo getUnitInfo() {
        return UnitInfo;
    }

    @JsonIgnore
    public void setUnitInfo(GetMVInfoResDUnitInfo unitInfo) {
        UnitInfo = unitInfo;
    }

    @JsonIgnore
    public GetMVInfoResDSPInfo getSPInfo() {
        return SPInfo;
    }

    @JsonIgnore
    public void setSPInfo(GetMVInfoResDSPInfo SPInfo) {
        this.SPInfo = SPInfo;
    }

    @JsonIgnore
    public GetMVInfoResDPDFInfo getPDFInfo() {
        return PDFInfo;
    }

    @JsonIgnore
    public void setPDFInfo(GetMVInfoResDPDFInfo PDFInfo) {
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
    public GetMVInfoResDPackInfo getPackInfo() {
        return PackInfo;
    }

    @JsonIgnore
    public void setPackInfo(GetMVInfoResDPackInfo packInfo) {
        PackInfo = packInfo;
    }

    @JsonIgnore
    public List<GetMVInfoResDReMaInfo> getReMaInfo() {
        return ReMaInfo;
    }

    @JsonIgnore
    public void setReMaInfo(List<GetMVInfoResDReMaInfo> reMaInfo) {
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
}

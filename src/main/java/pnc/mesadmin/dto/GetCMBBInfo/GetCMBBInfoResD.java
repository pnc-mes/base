package pnc.mesadmin.dto.GetCMBBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：公共接口 获取批次信息返回的Data
 * 创建人：张亮亮
 * 创建时间：2017-07-04
 * 修改人：
 * 修改时间：
 */
public class GetCMBBInfoResD {

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("MaType")
    private String MaType;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("MaStatus")
    private String MaStatus;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("CanNum")
    private float CanNum;

    @JsonProperty("IQCStatus")
    private String IQCStatus;

    @JsonProperty("WFInfo")
    private GetCMBBInfoResDWFInfo WFInfo;

    @JsonProperty("UnitInfo")
    private GetCMBBInfoResDUnitInfo UnitInfo;

    @JsonProperty("Status")
    private String Status;

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
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
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
    public String getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(String maType) {
        MaType = maType;
    }

    @JsonIgnore
    public float getCanNum() {
        return CanNum;
    }

    @JsonIgnore
    public void setCanNum(float canNum) {
        CanNum = canNum;
    }

    @JsonIgnore
    public String getMaStatus() {
        return MaStatus;
    }

    @JsonIgnore
    public void setMaStatus(String maStatus) {
        MaStatus = maStatus;
    }

    @JsonIgnore
    public String getIQCStatus() {
        return IQCStatus;
    }

    @JsonIgnore
    public void setIQCStatus(String IQCStatus) {
        this.IQCStatus = IQCStatus;
    }

    @JsonIgnore
    public GetCMBBInfoResDUnitInfo getUnitInfo() {
        return UnitInfo;
    }

    @JsonIgnore
    public void setUnitInfo(GetCMBBInfoResDUnitInfo unitInfo) {
        UnitInfo = unitInfo;
    }

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public GetCMBBInfoResDWFInfo getWFInfo() {
        return WFInfo;
    }

    @JsonIgnore
    public void setWFInfo(GetCMBBInfoResDWFInfo WFInfo) {
        this.WFInfo = WFInfo;
    }
}

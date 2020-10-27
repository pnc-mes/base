package pnc.mesadmin.dto.GetCMWInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/10/18.
 */
public class GetCMWInfoResD {

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaType")
    private String MaType;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("MaStatus")
    private String MaStatus;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("WIPNum")
    private float WIPNum;

    @JsonProperty("UnCBatNum")
    private float UnCBatNum;

    @JsonProperty("FinishNum")
    private float FinishNum;

    @JsonProperty("BInfo")
    private List<GetCMWInfoResDBInfo> BInfo;

    @JsonProperty("WFInfo")
    private GetCMWInfoResDWFInfo WFInfo;

    @JsonProperty("UnitInfo")
    private GetCMWInfoResDUnitInfo UnitInfo;

    @JsonProperty("Status")
    private String Status;

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
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
    public String getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(String maType) {
        MaType = maType;
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
    public String getMaStatus() {
        return MaStatus;
    }

    @JsonIgnore
    public void setMaStatus(String maStatus) {
        MaStatus = maStatus;
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
    public float getWIPNum() {
        return WIPNum;
    }

    @JsonIgnore
    public void setWIPNum(float WIPNum) {
        this.WIPNum = WIPNum;
    }

    @JsonIgnore
    public float getUnCBatNum() {
        return UnCBatNum;
    }

    @JsonIgnore
    public void setUnCBatNum(float unCBatNum) {
        UnCBatNum = unCBatNum;
    }

    @JsonIgnore
    public float getFinishNum() {
        return FinishNum;
    }

    @JsonIgnore
    public void setFinishNum(float finishNum) {
        FinishNum = finishNum;
    }

    @JsonIgnore
    public List<GetCMWInfoResDBInfo> getBInfo() {
        return BInfo;
    }

    @JsonIgnore
    public void setBInfo(List<GetCMWInfoResDBInfo> BInfo) {
        this.BInfo = BInfo;
    }

    @JsonIgnore
    public GetCMWInfoResDWFInfo getWFInfo() {
        return WFInfo;
    }

    @JsonIgnore
    public void setWFInfo(GetCMWInfoResDWFInfo WFInfo) {
        this.WFInfo = WFInfo;
    }

    @JsonIgnore
    public GetCMWInfoResDUnitInfo getUnitInfo() {
        return UnitInfo;
    }

    @JsonIgnore
    public void setUnitInfo(GetCMWInfoResDUnitInfo unitInfo) {
        UnitInfo = unitInfo;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
}

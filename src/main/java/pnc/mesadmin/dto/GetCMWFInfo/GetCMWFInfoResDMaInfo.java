package pnc.mesadmin.dto.GetCMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/6/28.
 */
public class GetCMWFInfoResDMaInfo implements Serializable{
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

    @JsonProperty("IsBatch")
    private String IsBatch;

    @JsonProperty("UnitInfo")
    private GetCMWFInfoResDMUnitInfo UnitInfo;

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
    public GetCMWFInfoResDMUnitInfo getUnitInfo() {
        return UnitInfo;
    }
    @JsonIgnore
    public void setUnitInfo(GetCMWFInfoResDMUnitInfo unitInfo) {
        UnitInfo = unitInfo;
    }

    @JsonIgnore
    public String getIsBatch() {
        return IsBatch;
    }

    @JsonIgnore
    public void setIsBatch(String isBatch) {
        IsBatch = isBatch;
    }
}

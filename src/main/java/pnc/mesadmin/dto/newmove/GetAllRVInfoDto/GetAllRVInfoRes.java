package pnc.mesadmin.dto.newmove.GetAllRVInfoDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetAllRVInfoRes implements Serializable {
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("WoCode")
    private String WoCode;
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("MaterialAttr")
    private String MaterialAttr;
    @JsonProperty("LineRd")
    private Integer LineRd;
    @JsonProperty("ShiftRd")
    private Integer ShiftRd;
    @JsonProperty("StartDate")
    private String StartDate;
    @JsonProperty("EndDate")
    private String EndDate;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
    public String getMaterialAttr() {
        return MaterialAttr;
    }

    @JsonIgnore
    public void setMaterialAttr(String materialAttr) {
        MaterialAttr = materialAttr;
    }

    @JsonIgnore
    public Integer getLineRd() {
        return LineRd;
    }

    @JsonIgnore
    public void setLineRd(Integer lineRd) {
        LineRd = lineRd;
    }

    @JsonIgnore
    public Integer getShiftRd() {
        return ShiftRd;
    }

    @JsonIgnore
    public void setShiftRd(Integer shiftRd) {
        ShiftRd = shiftRd;
    }

    @JsonIgnore
    public String getStartDate() {
        return StartDate;
    }

    @JsonIgnore
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    @JsonIgnore
    public String getEndDate() {
        return EndDate;
    }

    @JsonIgnore
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}

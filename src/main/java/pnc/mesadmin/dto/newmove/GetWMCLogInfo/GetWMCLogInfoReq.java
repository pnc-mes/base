package pnc.mesadmin.dto.newmove.GetWMCLogInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by 郝赞 on 2018/12/19.
 */
public class GetWMCLogInfoReq implements Serializable {

    @JsonProperty("StartDate")
    private String StartDate;
    @JsonProperty("EndDate")
    private String EndDate;
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("StartCode")
    private String StartCode;
    @JsonProperty("EndCode")
    private String EndCode;
    @JsonProperty("WMCStatus")
    private String WMCStatus;
    @JsonProperty("LineGd")
    private String LineGd;

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
    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
    @JsonIgnore
    public String getStartCode() {
        return StartCode;
    }
    @JsonIgnore
    public void setStartCode(String startCode) {
        StartCode = startCode;
    }
    @JsonIgnore
    public String getEndCode() {
        return EndCode;
    }
    @JsonIgnore
    public void setEndCode(String endCode) {
        EndCode = endCode;
    }
    @JsonIgnore
    public String getWMCStatus() {
        return WMCStatus;
    }
    @JsonIgnore
    public void setWMCStatus(String WMCStatus) {
        this.WMCStatus = WMCStatus;
    }
    @JsonIgnore
    public String getLineGd() {
        return LineGd;
    }
    @JsonIgnore
    public void setLineGd(String lineGd) {
        LineGd = lineGd;
    }
}

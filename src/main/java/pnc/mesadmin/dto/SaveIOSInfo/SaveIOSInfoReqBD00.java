package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/14.
 */
public class SaveIOSInfoReqBD00 implements Serializable{

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("LineGd")
    private String LineGd;

    @JsonProperty("SpecVGd")
    private String SpecVGd;

    @JsonProperty("StationGd")
    private String StationGd;

    @JsonProperty("DoRCInfo")
    private List<SaveIOSInfoReqBD01DoRCInfo> DoRCInfo;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public String getLineGd() {
        return LineGd;
    }

    @JsonIgnore
    public void setLineGd(String lineGd) {
        LineGd = lineGd;
    }

    @JsonIgnore
    public String getSpecVGd() {
        return SpecVGd;
    }

    @JsonIgnore
    public void setSpecVGd(String specVGd) {
        SpecVGd = specVGd;
    }

    @JsonIgnore
    public String getStationGd() {
        return StationGd;
    }

    @JsonIgnore
    public void setStationGd(String stationGd) {
        StationGd = stationGd;
    }

    @JsonIgnore
    public List<SaveIOSInfoReqBD01DoRCInfo> getDoRCInfo() {
        return DoRCInfo;
    }

    @JsonIgnore
    public void setDoRCInfo(List<SaveIOSInfoReqBD01DoRCInfo> doRCInfo) {
        DoRCInfo = doRCInfo;
    }
}

package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/8/29.
 */
public class SaveIOSInfoReqBD03 {

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("LineGd")
    private String LineGd;

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("DevRd")
    private int DevRd;

    @JsonProperty("StationGd")
    private String StationGd;

    @JsonProperty("DoDCInfo")
    private List<SaveIOSInfoReqBD03DoDCInfo> DoDCInfo;

    @JsonProperty("DoRCInfo")
    private List<SaveIOSInfoReqBD03DoRCInfo> DoRCInfo;

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
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public int getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(int devRd) {
        DevRd = devRd;
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
    public List<SaveIOSInfoReqBD03DoDCInfo> getDoDCInfo() {
        return DoDCInfo;
    }

    @JsonIgnore
    public void setDoDCInfo(List<SaveIOSInfoReqBD03DoDCInfo> doDCInfo) {
        DoDCInfo = doDCInfo;
    }

    @JsonIgnore
    public List<SaveIOSInfoReqBD03DoRCInfo> getDoRCInfo() {
        return DoRCInfo;
    }

    @JsonIgnore
    public void setDoRCInfo(List<SaveIOSInfoReqBD03DoRCInfo> doRCInfo) {
        DoRCInfo = doRCInfo;
    }
}

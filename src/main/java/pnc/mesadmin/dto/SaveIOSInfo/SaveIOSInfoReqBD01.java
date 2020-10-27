package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/14.
 */
public class SaveIOSInfoReqBD01 implements Serializable{

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("LineGd")
    private String LineGd;

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("DevGd")
    private String DevGd;

    @JsonProperty("StationGd")
    private String StationGd;

    @JsonProperty("DoMaInfo")
    private List<SaveIOSInfoReqBD01DoMaInfo> DoMaInfo;

    @JsonProperty("DoDCInfo")
    private List<SaveIOSInfoReqBD01DoDCInfo> DoDCInfo;

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
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
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
    public String getDevGd() {
        return DevGd;
    }

    @JsonIgnore
    public void setDevGd(String devGd) {
        DevGd = devGd;
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
    public List<SaveIOSInfoReqBD01DoMaInfo> getDoMaInfo() {
        return DoMaInfo;
    }

    @JsonIgnore
    public void setDoMaInfo(List<SaveIOSInfoReqBD01DoMaInfo> doMaInfo) {
        DoMaInfo = doMaInfo;
    }

    @JsonIgnore
    public List<SaveIOSInfoReqBD01DoDCInfo> getDoDCInfo() {
        return DoDCInfo;
    }

    @JsonIgnore
    public void setDoDCInfo(List<SaveIOSInfoReqBD01DoDCInfo> doDCInfo) {
        DoDCInfo = doDCInfo;
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

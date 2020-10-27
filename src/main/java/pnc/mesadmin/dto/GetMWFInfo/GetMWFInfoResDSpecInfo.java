package pnc.mesadmin.dto.GetMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/28.
 */
public class  GetMWFInfoResDSpecInfo implements Serializable {
    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("FileGrInfo")
    private GetMWFInfoResDFileGrInfo FileGrInfo;

    @JsonProperty("Device")
    private GetMWFInfoResDDevice Device;

    @JsonProperty("DC")
    private GetMWFInfoResDDC DC;

    @JsonProperty("DCEnd")
    private GetMWFInfoResDEndDC DCEnd;



    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }

    @JsonIgnore
    public GetMWFInfoResDFileGrInfo getFileGrInfo() {
        return FileGrInfo;
    }

    @JsonIgnore
    public void setFileGrInfo(GetMWFInfoResDFileGrInfo fileGrInfo) {
        FileGrInfo = fileGrInfo;
    }

    @JsonIgnore
    public GetMWFInfoResDDevice getDevice() {
        return Device;
    }

    @JsonIgnore
    public void setDevice(GetMWFInfoResDDevice device) {
        Device = device;
    }

    @JsonIgnore
    public GetMWFInfoResDDC getDC() {
        return DC;
    }

    @JsonIgnore
    public void setDC(GetMWFInfoResDDC DC) {
        this.DC = DC;
    }
    @JsonIgnore
    public GetMWFInfoResDEndDC getDCEnd() {
        return DCEnd;
    }
    @JsonIgnore
    public void setDCEnd(GetMWFInfoResDEndDC DCEnd) {
        this.DCEnd = DCEnd;
    }
}

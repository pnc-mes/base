package pnc.mesadmin.dto.GetCMBBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/9/27.
 */
public class GetCMBBInfoResDWFInfo {

    @JsonProperty("WFRd")
    private int WFRd;

    @JsonProperty("WFVerRd")
    private int WFVerRd;

    @JsonProperty("WFName")
    private String WFName;

    @JsonProperty("CSpecInfo")
    private GetCMBBInfoResDWCSpecInfo CSpecInfo;

    @JsonProperty("SpecInfo")
    private List<GetCMBBInfoResDWSpecInfo> SpecInfo;

    @JsonIgnore
    public int getWFRd() {
        return WFRd;
    }

    @JsonIgnore
    public void setWFRd(int WFRd) {
        this.WFRd = WFRd;
    }

    @JsonIgnore
    public int getWFVerRd() {
        return WFVerRd;
    }

    @JsonIgnore
    public void setWFVerRd(int WFVerRd) {
        this.WFVerRd = WFVerRd;
    }

    @JsonIgnore
    public String getWFName() {
        return WFName;
    }

    @JsonIgnore
    public void setWFName(String WFName) {
        this.WFName = WFName;
    }

    @JsonIgnore
    public GetCMBBInfoResDWCSpecInfo getCSpecInfo() {
        return CSpecInfo;
    }

    @JsonIgnore
    public void setCSpecInfo(GetCMBBInfoResDWCSpecInfo CSpecInfo) {
        this.CSpecInfo = CSpecInfo;
    }

    @JsonIgnore
    public List<GetCMBBInfoResDWSpecInfo> getSpecInfo() {
        return SpecInfo;
    }

    @JsonIgnore
    public void setSpecInfo(List<GetCMBBInfoResDWSpecInfo> specInfo) {
        SpecInfo = specInfo;
    }
}

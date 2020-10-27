package pnc.mesadmin.dto.GetCMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/6/28.
 */
public class GetCMWFInfoResDWFInfo implements Serializable{

    @JsonProperty("WFRd")
    private int WFRd;
    @JsonProperty("WFVerRd")
    private int WFVerRd;
    @JsonProperty("WFName")
    private String WFName;
    @JsonProperty("SpecInfo")
    private List<GetCMWFInfoResDWSpecInfo> SpecInfo;
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
    public List<GetCMWFInfoResDWSpecInfo> getSpecInfo() {
        return SpecInfo;
    }
    @JsonIgnore
    public void setSpecInfo(List<GetCMWFInfoResDWSpecInfo> specInfo) {
        SpecInfo = specInfo;
    }
}

package pnc.mesadmin.dto.GetCMWInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/10/18.
 */
public class GetCMWInfoResDWFInfo {

    @JsonProperty("WFVerRd")
    private int WFVerRd;

    @JsonProperty("WFName")
    private String WFName;

    @JsonProperty("SpecInfo")
    private List<GetCMWInfoResDWSpecInfo> SpecInfo;

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
    public List<GetCMWInfoResDWSpecInfo> getSpecInfo() {
        return SpecInfo;
    }

    @JsonIgnore
    public void setSpecInfo(List<GetCMWInfoResDWSpecInfo> specInfo) {
        SpecInfo = specInfo;
    }
}

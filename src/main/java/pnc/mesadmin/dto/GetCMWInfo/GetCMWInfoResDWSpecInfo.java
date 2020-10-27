package pnc.mesadmin.dto.GetCMWInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/10/18.
 */
public class GetCMWInfoResDWSpecInfo {

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("SpecStatus")
    private String SpecStatus;

    @JsonProperty("DevGInfo")
    private List<GetCMWInfoResDWSDevGInfo> DevGInfo;

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
    public String getSpecStatus() {
        return SpecStatus;
    }

    @JsonIgnore
    public void setSpecStatus(String specStatus) {
        SpecStatus = specStatus;
    }

    @JsonIgnore
    public List<GetCMWInfoResDWSDevGInfo> getDevGInfo() {
        return DevGInfo;
    }

    @JsonIgnore
    public void setDevGInfo(List<GetCMWInfoResDWSDevGInfo> devGInfo) {
        DevGInfo = devGInfo;
    }
}

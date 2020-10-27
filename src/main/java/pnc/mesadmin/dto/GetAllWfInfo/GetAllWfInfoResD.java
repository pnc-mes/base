package pnc.mesadmin.dto.GetAllWfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/9.
 */
public class GetAllWfInfoResD implements Serializable{

    @JsonProperty("WfRd")
    private int WfRd;

    @JsonProperty("WfName")
    private String WfName;

    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonProperty("Version")
    private String Version;

    @JsonIgnore
    public int getWfRd() {
        return WfRd;
    }

    @JsonIgnore
    public void setWfRd(int wfRd) {
        WfRd = wfRd;
    }

    @JsonIgnore
    public String getWfName() {
        return WfName;
    }

    @JsonIgnore
    public void setWfName(String wfName) {
        WfName = wfName;
    }

    @JsonIgnore
    public int getWfVerRd() {
        return WfVerRd;
    }

    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }
}

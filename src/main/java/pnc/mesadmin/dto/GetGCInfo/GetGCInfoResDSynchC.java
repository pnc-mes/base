package pnc.mesadmin.dto.GetGCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/3/20.
 */
public class GetGCInfoResDSynchC implements Serializable {
    @JsonProperty("SynchName")
    private String SynchName;

    @JsonProperty("Status")
    private String Status;

    @JsonIgnore
    public String getSynchName() {
        return SynchName;
    }
    @JsonIgnore
    public void setSynchName(String synchName) {
        SynchName = synchName;
    }
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
}

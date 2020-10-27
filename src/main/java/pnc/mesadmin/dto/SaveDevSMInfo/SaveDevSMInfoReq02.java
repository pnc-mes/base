package pnc.mesadmin.dto.SaveDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2017/8/21.
 */
public class SaveDevSMInfoReq02 implements Serializable {
    @JsonProperty("DSMRd")
    private int DSMRd;

    @JsonProperty("ModelName")
    private String ModelName;
    @JsonProperty("DevSRd")
    private int DevSRd;
    @JsonProperty("DevSMInfo")
    private List<SaveDevSMInfoReq02DevSMInfo> DevSMInfo;

    @JsonIgnore
    public int getDSMRd() {
        return DSMRd;
    }
    @JsonIgnore
    public void setDSMRd(int DSMRd) {
        this.DSMRd = DSMRd;
    }

    @JsonIgnore
    public String getModelName() {
        return ModelName;
    }

    @JsonIgnore
    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    @JsonIgnore
    public List<SaveDevSMInfoReq02DevSMInfo> getDevSMInfo() {
        return DevSMInfo;
    }

    @JsonIgnore
    public void setDevSMInfo(List<SaveDevSMInfoReq02DevSMInfo> devSMInfo) {
        DevSMInfo = devSMInfo;
    }
    @JsonIgnore

    public int getDevSRd() {
        return DevSRd;
    }
    @JsonIgnore
    public void setDevSRd(int devSRd) {
        DevSRd = devSRd;
    }
}

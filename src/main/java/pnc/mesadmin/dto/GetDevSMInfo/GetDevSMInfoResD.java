package pnc.mesadmin.dto.GetDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liufuzhi on 2017/9/14.
 */
public class GetDevSMInfoResD implements Serializable {
    @JsonProperty("DSMRd")
    private int DSMRd;

    @JsonProperty("ModelName")
    private String ModelName;

    @JsonProperty("DSInfo")
    private GetDevSMInfoResDDSInfo DSInfo;

    @JsonProperty("DevSMInfo")
    private List<GetDevSMInfoResDDevSMInfo> DevSMInfo;

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
    public List<GetDevSMInfoResDDevSMInfo> getDevSMInfo() {
        return DevSMInfo;
    }

    @JsonIgnore
    public void setDevSMInfo(List<GetDevSMInfoResDDevSMInfo> devSMInfo) {
        DevSMInfo = devSMInfo;
    }
    @JsonIgnore
    public GetDevSMInfoResDDSInfo getDSInfo() {
        return DSInfo;
    }
    @JsonIgnore
    public void setDSInfo(GetDevSMInfoResDDSInfo DSInfo) {
        this.DSInfo = DSInfo;
    }
}

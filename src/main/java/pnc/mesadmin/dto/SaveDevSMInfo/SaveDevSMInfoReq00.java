package pnc.mesadmin.dto.SaveDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liufuzhi on 2017/9/14.
 */
public class SaveDevSMInfoReq00 implements Serializable {
    @JsonProperty("ModelName")
    private String ModelName;
    @JsonProperty("DevSRd")
    private String DevSRd;
    @JsonProperty("DevSMInfo")
    private List<SaveDevSMInfoReq00DevSMInfo> DevSMInfo;

    @JsonIgnore
    public String getModelName() {
        return ModelName;
    }

    @JsonIgnore
    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    @JsonIgnore
    public List<SaveDevSMInfoReq00DevSMInfo> getDevSMInfo() {
        return DevSMInfo;
    }

    @JsonIgnore
    public void setDevSMInfo(List<SaveDevSMInfoReq00DevSMInfo> devSMInfo) {
        DevSMInfo = devSMInfo;
    }
    @JsonIgnore
    public String getDevSRd() {
        return DevSRd;
    }
    @JsonIgnore
    public void setDevSRd(String devSRd) {
        DevSRd = devSRd;
    }
}

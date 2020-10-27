package pnc.mesadmin.dto.GetAllDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/21.
 */
public class GetAllDevSMInfoResD implements Serializable {
    @JsonProperty("DSMRd")
    private int DSMRd;
    @JsonProperty("ModelName")
    private String ModelName;

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
}

package pnc.mesadmin.dto.GetToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/18 11:20
 * @Description:
 */
public class GetToolInfoResDModel implements Serializable {
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

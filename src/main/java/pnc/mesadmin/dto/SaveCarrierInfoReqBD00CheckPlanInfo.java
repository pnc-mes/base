package pnc.mesadmin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SaveCarrierInfoReqBD00CheckPlanInfo implements Serializable {
    @JsonProperty("CheckPlanRd")
    private Integer CheckPlanRd;
    @JsonProperty("CheckPlanName")
    private String CheckPlanName;

    @JsonIgnore
    public Integer getCheckPlanRd() {
        return CheckPlanRd;
    }
    @JsonIgnore
    public void setCheckPlanRd(Integer checkPlanRd) {
        CheckPlanRd = checkPlanRd;
    }
    @JsonIgnore
    public String getCheckPlanName() {
        return CheckPlanName;
    }
    @JsonIgnore
    public void setCheckPlanName(String checkPlanName) {
        CheckPlanName = checkPlanName;
    }
}

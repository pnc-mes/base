package pnc.mesadmin.dto.GetFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by haozan on 2018/9/7.
 */
public class ReaInfo {
    @JsonProperty("ReaRd")
    private Integer ReaRd;
    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonIgnore
    public Integer getReaRd() {
        return ReaRd;
    }
    @JsonIgnore
    public void setReaRd(Integer reaRd) {
        ReaRd = reaRd;
    }
    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }
    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
    }
}

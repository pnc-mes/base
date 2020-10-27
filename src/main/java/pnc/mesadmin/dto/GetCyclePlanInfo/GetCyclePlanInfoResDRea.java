package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDRea implements Serializable {
    @JsonProperty("ReaRd")
    private int ReaRd;
    @JsonProperty("ReaDes")
    private String ReaDes;
    @JsonIgnore
    public int getReaRd() {
        return ReaRd;
    }
    @JsonIgnore
    public void setReaRd(int reaRd) {
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

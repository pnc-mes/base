package pnc.mesadmin.dto.GetRCGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/8/11.
 */
public class GetRCGInfoResDRCInfo implements Serializable{

    @JsonProperty("ReaRd")
    private int ReaRd;

    @JsonProperty("ReaCode")
    private String ReaCode;

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
    public String getReaCode() {
        return ReaCode;
    }
    @JsonIgnore
    public void setReaCode(String reaCode) {
        ReaCode = reaCode;
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

package pnc.mesadmin.dto.GetSpecBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/16.
 */
public class GetSpecBInfoResDRCG {

    @JsonProperty("ReaCode")
    private String ReaCode;

    @JsonProperty("ReaDes")
    private String ReaDes;

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

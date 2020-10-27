package pnc.mesadmin.dto.SaveIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/12.
 */
public class SaveIQCBInfoReqBD00Badinfo {
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

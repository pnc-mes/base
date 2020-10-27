package pnc.mesadmin.dto.SaveIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/12.
 */
public class SaveIQCBInfoReqBD02BadInfo {
    @JsonProperty("IQCBadRd")
    private int IQCBadRd;
    @JsonProperty("ReaCode")
    private String ReaCode;
    @JsonProperty("ReaDes")
    private String ReaDes;
    @JsonIgnore
    public int getIQCBadRd() {
        return IQCBadRd;
    }
    @JsonIgnore
    public void setIQCBadRd(int IQCBadRd) {
        this.IQCBadRd = IQCBadRd;
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
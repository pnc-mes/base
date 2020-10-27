package pnc.mesadmin.dto.SaveRePrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveRePrInfoReqBD01RePrInfo {

    @JsonProperty("ReCode")
    private String ReCode;

    @JsonIgnore
    public String getReCode() {
        return ReCode;
    }

    @JsonIgnore
    public void setReCode(String reCode) {
        ReCode = reCode;
    }
}

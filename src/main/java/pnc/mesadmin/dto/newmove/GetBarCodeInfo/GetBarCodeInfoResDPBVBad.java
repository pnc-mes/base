package pnc.mesadmin.dto.newmove.GetBarCodeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBarCodeInfoResDPBVBad {
    @JsonProperty("BadCode")
    private String BadCode;

    @JsonProperty("BadName")
    private String BadName;

    @JsonIgnore
    public String getBadCode() {
        return BadCode;
    }

    @JsonIgnore
    public void setBadCode(String badCode) {
        BadCode = badCode;
    }

    @JsonIgnore
    public String getBadName() {
        return BadName;
    }

    @JsonIgnore
    public void setBadName(String badName) {
        BadName = badName;
    }
}

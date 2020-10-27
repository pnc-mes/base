package pnc.mesadmin.dto.newmove.GetBarCodeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBarCodeInfoResDYBVBad {
    @JsonProperty("BadType")
    private String BadType;

    @JsonProperty("BadCode")
    private String BadCode;

    @JsonProperty("BadName")
    private String BadName;

    @JsonProperty("LocationCode")
    private String LocationCode;

    @JsonProperty("LocationName")
    private String LocationName;

    @JsonIgnore
    public String getBadType() {
        return BadType;
    }

    @JsonIgnore
    public void setBadType(String badType) {
        BadType = badType;
    }

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

    @JsonIgnore
    public String getLocationCode() {
        return LocationCode;
    }

    @JsonIgnore
    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    @JsonIgnore
    public String getLocationName() {
        return LocationName;
    }

    @JsonIgnore
    public void setLocationName(String locationName) {
        LocationName = locationName;
    }
}

package pnc.mesadmin.dto.GetStationDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetAllStationInfoResB implements Serializable {
    @JsonProperty("StationRd")
    private Integer StationRd;
    @JsonProperty("StationName")
    private String StationName;

    @JsonIgnore
    public Integer getStationRd() {
        return StationRd;
    }

    @JsonIgnore
    public void setStationRd(Integer stationRd) {
        StationRd = stationRd;
    }

    @JsonIgnore
    public String getStationName() {
        return StationName;
    }

    @JsonIgnore
    public void setStationName(String stationName) {
        StationName = stationName;
    }
}

package pnc.mesadmin.dto.GetStationDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetStationInfoRes implements Serializable {
    @JsonProperty("StationRd")
    private Integer StationRd;

    @JsonIgnore
    public Integer getStationRd() {
        return StationRd;
    }

    @JsonIgnore
    public void setStationRd(Integer stationRd) {
        StationRd = stationRd;
    }
}

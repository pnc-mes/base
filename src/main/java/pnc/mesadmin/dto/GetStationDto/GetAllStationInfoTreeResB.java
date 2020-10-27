package pnc.mesadmin.dto.GetStationDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class GetAllStationInfoTreeResB implements Serializable {
    @JsonProperty("LineRd")
    private Integer LineRd;
    @JsonProperty("LineName")
    private String LineName;
    @JsonProperty("StationList")
    private List<GetAllTreeStationInfoListResB> StationList;

    @JsonIgnore
    public Integer getLineRd() {
        return LineRd;
    }

    @JsonIgnore
    public void setLineRd(Integer lineRd) {
        LineRd = lineRd;
    }

    @JsonIgnore
    public String getLineName() {
        return LineName;
    }

    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }

    @JsonIgnore
    public List<GetAllTreeStationInfoListResB> getStationList() {
        return StationList;
    }

    @JsonIgnore
    public void setStationList(List<GetAllTreeStationInfoListResB> stationList) {
        StationList = stationList;
    }
}

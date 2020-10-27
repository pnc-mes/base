package pnc.mesadmin.dto.GetAllStationTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by haozan on 2018/11/12.
 */
public class GetAllStationTInfoResD implements Serializable {

    @JsonProperty("LineRd")
    private Integer LineRd;

    @JsonProperty("LineName")
    private String LineName;

    @JsonProperty("SpecVerRd")
    private Integer SpecVerRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("StationRd")
    private Integer StationRd;

    @JsonProperty("StationName")
    private String StationName;

    @JsonProperty("ExecList")
    private List<ExecList> ExecList;

    public static class ExecList{
        @JsonProperty("ExecRd")
        private Integer ExecRd;

        @JsonProperty("ExecName")
        private String ExecName;

        @JsonIgnore
        public Integer getExecRd() {
            return ExecRd;
        }
        @JsonIgnore
        public void setExecRd(Integer execRd) {
            ExecRd = execRd;
        }
        @JsonIgnore
        public String getExecName() {
            return ExecName;
        }
        @JsonIgnore
        public void setExecName(String execName) {
            ExecName = execName;
        }
    }

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
    public Integer getSpecVerRd() {
        return SpecVerRd;
    }
    @JsonIgnore
    public void setSpecVerRd(Integer specVerRd) {
        SpecVerRd = specVerRd;
    }
    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }
    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }
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
    @JsonIgnore
    public List<GetAllStationTInfoResD.ExecList> getExecList() {
        return ExecList;
    }
    @JsonIgnore
    public void setExecList(List<GetAllStationTInfoResD.ExecList> execList) {
        ExecList = execList;
    }
}

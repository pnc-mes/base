package pnc.mesadmin.dto.GetOTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2018/11/24.
 */
public class GetOTInfoResBDDoExcp {
    @JsonProperty("LineName")
    private String LineName;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("StationName")
    private String StationName;

    @JsonProperty("Msg")
    private String Msg;

    @JsonProperty("Execor")
    private String Execor;

    @JsonProperty("OptTime")
    private String OptTime;
    @JsonIgnore
    public String getLineName() {
        return LineName;
    }
    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
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
    public String getStationName() {
        return StationName;
    }
    @JsonIgnore
    public void setStationName(String stationName) {
        StationName = stationName;
    }
    @JsonIgnore
    public String getMsg() {
        return Msg;
    }
    @JsonIgnore
    public void setMsg(String msg) {
        Msg = msg;
    }
    @JsonIgnore
    public String getExecor() {
        return Execor;
    }
    @JsonIgnore
    public void setExecor(String execor) {
        Execor = execor;
    }
    @JsonIgnore
    public String getOptTime() {
        return OptTime;
    }
    @JsonIgnore
    public void setOptTime(String optTime) {
        OptTime = optTime;
    }
}

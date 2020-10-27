package pnc.mesadmin.dto.SaveDExLogInfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/10/22 17:51
 * @Description:
 */
public class SaveDExLogInfoReq00 implements Serializable{
    @JsonProperty(value = "LineGd")
    @JSONField(name="LineGd")
    private String LineGd;

    @JsonProperty(value = "SpecVerGd")
    @JSONField(name="SpecVerGd")
    private String SpecVerGd;

    @JsonProperty(value = "StationGd")
    @JSONField(name="StationGd")
    private String StationGd;

    @JsonProperty(value = "DevGd")
    @JSONField(name="DevGd")
    private String DevGd;

    @JsonProperty(value = "DevMapGd")
    @JSONField(name="DevMapGd")
    private String DevMapGd;

    @JsonProperty(value = "Msg")
    @JSONField(name="Msg")
    private String Msg;


    @JsonIgnore
    public String getLineGd() {
        return LineGd;
    }
    @JsonIgnore
    public void setLineGd(String lineGd) {
        LineGd = lineGd;
    }
    @JsonIgnore
    public String getSpecVerGd() {
        return SpecVerGd;
    }
    @JsonIgnore
    public void setSpecVerGd(String specVerGd) {
        SpecVerGd = specVerGd;
    }
    @JsonIgnore
    public String getStationGd() {
        return StationGd;
    }
    @JsonIgnore
    public void setStationGd(String stationGd) {
        StationGd = stationGd;
    }
    @JsonIgnore
    public String getDevGd() {
        return DevGd;
    }
    @JsonIgnore
    public void setDevGd(String devGd) {
        DevGd = devGd;
    }
    @JsonIgnore
    public String getDevMapGd() {
        return DevMapGd;
    }
    @JsonIgnore
    public void setDevMapGd(String devMapGd) {
        DevMapGd = devMapGd;
    }
    @JsonIgnore
    public String getMsg() {
        return Msg;
    }
    @JsonIgnore
    public void setMsg(String msg) {
        Msg = msg;
    }
}

package pnc.mesadmin.dto.GYMonitor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Description: mesadmin
 * Created By panjunfeng on 2018/11/9
 */
public class SaveGYYJReqBD00 {
    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("SpecVerGd")
    private String SpecVerGd;

    @JsonProperty("LineGd")
    private String LineGd;

    @JsonProperty("DevGd")
    private String DevGd;

    @JsonProperty("CollectParam")
    private List<SaveGYYJReqBD00CollectParam> CollectParam;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
    public String getLineGd() {
        return LineGd;
    }

    @JsonIgnore
    public void setLineGd(String lineGd) {
        LineGd = lineGd;
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
    public List<SaveGYYJReqBD00CollectParam> getCollectParam() {
        return CollectParam;
    }

    @JsonIgnore
    public void setCollectParam(List<SaveGYYJReqBD00CollectParam> collectParam) {
        CollectParam = collectParam;
    }
}

package pnc.mesadmin.dto.newmove.AddSL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by haozan on 2018/11/11.
 */
public class SaveAddDownSL {

    @JsonProperty("ExecGd")
    private String ExecGd;

    @JsonProperty("LineGd")
    private String LineGd;

    @JsonProperty("SpecVGd")
    private String SpecVGd;

    @JsonProperty("DevGd")
    private String DevGd;

    //(00#全部下料 01#只下料当前作业人或设备上的料）
    @JsonProperty("Flag")
    private String Flag;

    @JsonProperty("MaBatch")
    private List<String> MaBatch;

    @JsonIgnore
    public String getExecGd() {
        return ExecGd;
    }

    @JsonIgnore
    public void setExecGd(String execGd) {
        ExecGd = execGd;
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
    public String getSpecVGd() {
        return SpecVGd;
    }

    @JsonIgnore
    public void setSpecVGd(String specVGd) {
        SpecVGd = specVGd;
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
    public String getFlag() {
        return Flag;
    }

    @JsonIgnore
    public void setFlag(String flag) {
        Flag = flag;
    }

    @JsonIgnore
    public List<String> getMaBatch() {
        return MaBatch;
    }

    @JsonIgnore
    public void setMaBatch(List<String> maBatch) {
        MaBatch = maBatch;
    }
}

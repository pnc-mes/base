package pnc.mesadmin.dto.newmove.AddSL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by haozan on 2018/11/11.
 */
public class SaveAddSL {

    @JsonProperty("ExecGd")
    private String ExecGd;

    @JsonProperty("LineGd")
    private String LineGd;

    @JsonProperty("SpecVGd")
    private String SpecVGd;

    @JsonProperty("DevGd")
    private String DevGd;

    @JsonProperty("ProBatch")
    private String ProBatch;

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
    public String getProBatch() {
        return ProBatch;
    }
    @JsonIgnore
    public void setProBatch(String proBatch) {
        ProBatch = proBatch;
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

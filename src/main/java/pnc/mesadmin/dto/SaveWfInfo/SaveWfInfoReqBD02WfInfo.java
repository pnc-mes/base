package pnc.mesadmin.dto.SaveWfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/11.
 */
public class SaveWfInfoReqBD02WfInfo implements Serializable{

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("OSpec")
    private List<SaveWfInfoReqBD02WOSpec> OSpec;

    @JsonProperty("RSpec")
    private List<SaveWfInfoReqBD02WRSpec> RSpec;

    @JsonProperty("NSpecVerRd")
    private int NSpecVerRd;

    @JsonProperty("SeSpecType")
    private String SeSpecType;

    @JsonProperty("Pos")
    private int Pos;

    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public List<SaveWfInfoReqBD02WOSpec> getOSpec() {
        return OSpec;
    }

    @JsonIgnore
    public void setOSpec(List<SaveWfInfoReqBD02WOSpec> OSpec) {
        this.OSpec = OSpec;
    }

    @JsonIgnore
    public List<SaveWfInfoReqBD02WRSpec> getRSpec() {
        return RSpec;
    }

    @JsonIgnore
    public void setRSpec(List<SaveWfInfoReqBD02WRSpec> RSpec) {
        this.RSpec = RSpec;
    }

    @JsonIgnore
    public int getNSpecVerRd() {
        return NSpecVerRd;
    }

    @JsonIgnore
    public void setNSpecVerRd(int NSpecVerRd) {
        this.NSpecVerRd = NSpecVerRd;
    }

    @JsonIgnore
    public String getSeSpecType() {
        return SeSpecType;
    }

    @JsonIgnore
    public void setSeSpecType(String seSpecType) {
        SeSpecType = seSpecType;
    }

    @JsonIgnore
    public int getPos() {
        return Pos;
    }

    @JsonIgnore
    public void setPos(int pos) {
        Pos = pos;
    }
}

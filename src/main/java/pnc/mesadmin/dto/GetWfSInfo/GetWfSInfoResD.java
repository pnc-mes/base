package pnc.mesadmin.dto.GetWfSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/9.
 */
public class GetWfSInfoResD implements Serializable{

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("SeSpecType")
    private String SeSpecType;

    @JsonProperty("Condition")
    private List<GetWfSInfoResDCondition> Condition;

    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
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
    public String getSeSpecType() {
        return SeSpecType;
    }

    @JsonIgnore
    public void setSeSpecType(String seSpecType) {
        SeSpecType = seSpecType;
    }

    @JsonIgnore
    public List<GetWfSInfoResDCondition> getConfition() {
        return Condition;
    }

    @JsonIgnore
    public void setConfition(List<GetWfSInfoResDCondition> confition) {
        Condition = confition;
    }
}

package pnc.mesadmin.dto.GetToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class GetToolInfoResDToolBZInfo implements Serializable {

    @JsonProperty("Reference")
    private String Reference;

    @JsonProperty("BZDtl")
    private List<GetToolInfoResDToolBZDtl> BZDtl;
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
    @JsonIgnore
    public List<GetToolInfoResDToolBZDtl> getBZDtl() {
        return BZDtl;
    }
    @JsonIgnore
    public void setBZDtl(List<GetToolInfoResDToolBZDtl> BZDtl) {
        this.BZDtl = BZDtl;
    }
}

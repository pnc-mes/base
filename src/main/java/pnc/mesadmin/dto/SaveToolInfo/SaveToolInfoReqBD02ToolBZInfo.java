package pnc.mesadmin.dto.SaveToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveToolInfoReqBD02ToolBZInfo implements Serializable {


    @JsonProperty("Reference")
    private String Reference;

    @JsonProperty("BZDtl")
    private List<SaveToolInfoReqBD02ToolBZDtl> BZDtl;
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
    @JsonIgnore
    public List<SaveToolInfoReqBD02ToolBZDtl> getBZDtl() {
        return BZDtl;
    }
    @JsonIgnore
    public void setBZDtl(List<SaveToolInfoReqBD02ToolBZDtl> BZDtl) {
        this.BZDtl = BZDtl;
    }
}

package pnc.mesadmin.dto.SaveToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveToolInfoReqToolBZInfo implements Serializable {


    @JsonProperty("Reference")
    private String Reference;

    @JsonProperty("BZDtl")
    private List<SaveToolInfoReqToolBZDtl> BZDtl;
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }



    @JsonIgnore
    public List<SaveToolInfoReqToolBZDtl> getBZDtl() {
        return BZDtl;
    }
    @JsonIgnore
    public void setBZDtl(List<SaveToolInfoReqToolBZDtl> BZDtl) {
        this.BZDtl = BZDtl;
    }
}

package pnc.mesadmin.dto.SaveDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveDevInfoReqBD02DevBZInfo implements Serializable {

    @JsonProperty("Reference")
    private String Reference;


    @JsonProperty("BZDtl")
    private List<SaveDevInfoReqBD02BZDtl> BZDtl;
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
    @JsonIgnore

    public List<SaveDevInfoReqBD02BZDtl> getBZDtl() {
        return BZDtl;
    }
    @JsonIgnore
    public void setBZDtl(List<SaveDevInfoReqBD02BZDtl> BZDtl) {
        this.BZDtl = BZDtl;
    }
}

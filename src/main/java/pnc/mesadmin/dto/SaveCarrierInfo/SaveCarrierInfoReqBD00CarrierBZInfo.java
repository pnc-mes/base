package pnc.mesadmin.dto.SaveCarrierInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveCarrierInfoReqBD00CarrierBZInfo implements Serializable {


    @JsonProperty("Reference")
    private String Reference;

    @JsonProperty("BZDtl")
    private List<SaveCarrierInfoReqBD00BZDtl> BZDtl;
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
    @JsonIgnore
    public List<SaveCarrierInfoReqBD00BZDtl> getBZDtl() {
        return BZDtl;
    }
    @JsonIgnore
    public void setBZDtl(List<SaveCarrierInfoReqBD00BZDtl> BZDtl) {
        this.BZDtl = BZDtl;
    }
}

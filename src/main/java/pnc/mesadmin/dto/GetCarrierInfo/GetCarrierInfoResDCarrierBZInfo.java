package pnc.mesadmin.dto.GetCarrierInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class GetCarrierInfoResDCarrierBZInfo implements Serializable {


    @JsonProperty("Reference")
    private String Reference;

    @JsonProperty("BZDtl")
    private List<GetCarrierInfoResDBZDtl> BZDtl;
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
    @JsonIgnore
    public List<GetCarrierInfoResDBZDtl> getBZDtl() {
        return BZDtl;
    }
    @JsonIgnore
    public void setBZDtl(List<GetCarrierInfoResDBZDtl> BZDtl) {
        this.BZDtl = BZDtl;
    }
}

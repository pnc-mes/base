package pnc.mesadmin.dto.GetDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class GetDevInfoResDDevBZInfo implements Serializable {
    @JsonProperty("Reference")
    private String  Reference;


    @JsonProperty("BZDtl")
    private List<GetDevInfoResDDevBZInfoBZDtl> BZDtl;

    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
    @JsonIgnore
    public List<GetDevInfoResDDevBZInfoBZDtl> getBZDtl() {
        return BZDtl;
    }
    @JsonIgnore
    public void setBZDtl(List<GetDevInfoResDDevBZInfoBZDtl> BZDtl) {
        this.BZDtl = BZDtl;
    }
}

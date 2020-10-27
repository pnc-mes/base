package pnc.mesadmin.dto.SaveDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveDevInfoReqBD00DevBZInfo implements Serializable {

    @JsonProperty("Reference")
    private String Reference;


    @JsonProperty("BZDtl")
    private List<SaveDevInfoReqDevBZInfoBZDtl> BZDtl;
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
    @JsonIgnore

    public List<SaveDevInfoReqDevBZInfoBZDtl> getBZDtl() {
        return BZDtl;
    }
    @JsonIgnore
    public void setBZDtl(List<SaveDevInfoReqDevBZInfoBZDtl> BZDtl) {
        this.BZDtl = BZDtl;
    }
}

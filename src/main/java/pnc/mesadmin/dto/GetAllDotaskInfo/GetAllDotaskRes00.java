package pnc.mesadmin.dto.GetAllDotaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetAllDotaskRes00 implements Serializable {
    @JsonProperty("PMType")
    private String PMType;
    @JsonProperty("PMObjType")
    private String PMObjType;
    @JsonProperty("PMObjRd")
    private Integer PMObjRd;
    @JsonProperty("Status")
    private String Status;

    @JsonIgnore
    public String getPMType() {
        return PMType;
    }

    @JsonIgnore
    public void setPMType(String PMType) {
        this.PMType = PMType;
    }

    @JsonIgnore
    public String getPMObjType() {
        return PMObjType;
    }

    @JsonIgnore
    public void setPMObjType(String PMObjType) {
        this.PMObjType = PMObjType;
    }

    @JsonIgnore

    public Integer getPMObjRd() {
        return PMObjRd;
    }

    @JsonIgnore
    public void setPMObjRd(Integer PMObjRd) {
        this.PMObjRd = PMObjRd;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
}

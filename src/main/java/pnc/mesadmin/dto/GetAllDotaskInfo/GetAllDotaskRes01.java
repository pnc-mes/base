package pnc.mesadmin.dto.GetAllDotaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GetAllDotaskRes01 implements Serializable {
    @JsonProperty("CheckObjRd")
    private Integer CheckObjRd;
    @JsonProperty("Status")
    private String Status;

    @JsonIgnore
    public Integer getCheckObjRd() {
        return CheckObjRd;
    }

    @JsonIgnore
    public void setCheckObjRd(Integer checkObjRd) {
        CheckObjRd = checkObjRd;
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

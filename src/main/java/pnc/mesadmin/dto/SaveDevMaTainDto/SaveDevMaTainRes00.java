package pnc.mesadmin.dto.SaveDevMaTainDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SaveDevMaTainRes00 implements Serializable {
    @JsonProperty("DevRd")
    private Integer DevRd;

    @JsonProperty("DevMalfRd")
    private Integer DevMalfRd;

    @JsonProperty("DevSRd")
    private Integer DevSRd;

    @JsonProperty("Remark")
    private String Remark;


    @JsonIgnore
    public Integer getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(Integer devRd) {
        DevRd = devRd;
    }

    @JsonIgnore
    public Integer getDevMalfRd() {
        return DevMalfRd;
    }

    @JsonIgnore
    public void setDevMalfRd(Integer devMalfRd) {
        DevMalfRd = devMalfRd;
    }

    @JsonIgnore
    public Integer getDevSRd() {
        return DevSRd;
    }

    @JsonIgnore
    public void setDevSRd(Integer devSRd) {
        DevSRd = devSRd;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

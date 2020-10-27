package pnc.mesadmin.dto.GetAllURInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/23.
 */
public class GetAllURInfoResD implements Serializable {
    @JsonProperty("PMorRd")
    private int PMorRd;

    @JsonProperty("PMor")
    private String PMor;

    @JsonProperty("PMorType")
    private String PMorType;

    @JsonIgnore
    public int getPMorRd() {
        return PMorRd;
    }

    @JsonIgnore
    public void setPMorRd(int PMorRd) {
        this.PMorRd = PMorRd;
    }

    @JsonIgnore
    public String getPMor() {
        return PMor;
    }

    @JsonIgnore
    public void setPMor(String PMor) {
        this.PMor = PMor;
    }

    @JsonIgnore
    public String getPMorType() {
        return PMorType;
    }

    @JsonIgnore
    public void setPMorType(String PMorType) {
        this.PMorType = PMorType;
    }
}

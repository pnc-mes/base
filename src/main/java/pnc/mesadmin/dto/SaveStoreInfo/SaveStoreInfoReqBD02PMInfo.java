package pnc.mesadmin.dto.SaveStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/22.
 */
public class SaveStoreInfoReqBD02PMInfo implements Serializable {
    @JsonProperty("PMorRd")
    private int PMorRd;

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
    public String getPMorType() {
        return PMorType;
    }

    @JsonIgnore
    public void setPMorType(String PMorType) {
        this.PMorType = PMorType;
    }
}

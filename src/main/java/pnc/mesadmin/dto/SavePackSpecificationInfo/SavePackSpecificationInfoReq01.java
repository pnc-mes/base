package pnc.mesadmin.dto.SavePackSpecificationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/26.
 */
public class SavePackSpecificationInfoReq01 implements Serializable {

    @JsonProperty("MPRd")
    private String MPRd;


    @JsonIgnore
    public String getMPRd() {
        return MPRd;
    }
    @JsonIgnore
    public void setMPRd(String MPRd) {
        this.MPRd = MPRd;
    }
}


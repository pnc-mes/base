package pnc.mesadmin.dto.GetPackSpecificationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetPackSpecificationInfoReq00 implements Serializable{

    @JsonProperty("MPRd")
    private int MPRd;

    @JsonIgnore
    public int getMPRd() {
        return MPRd;
    }
    @JsonIgnore
    public void setMPRd(int MPRd) {
        this.MPRd = MPRd;
    }
}

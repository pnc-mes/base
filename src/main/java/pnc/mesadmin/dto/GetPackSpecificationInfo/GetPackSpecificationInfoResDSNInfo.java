package pnc.mesadmin.dto.GetPackSpecificationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetPackSpecificationInfoResDSNInfo implements Serializable{

    @JsonProperty("SNRd")
    private int SNRd;

    @JsonProperty("SNName")
    private String SNName;

    @JsonIgnore
    public int getSNRd() {
        return SNRd;
    }
    @JsonIgnore
    public void setSNRd(int SNRd) {
        this.SNRd = SNRd;
    }
    @JsonIgnore
    public String getSNName() {
        return SNName;
    }
    @JsonIgnore
    public void setSNName(String SNName) {
        this.SNName = SNName;
    }
}

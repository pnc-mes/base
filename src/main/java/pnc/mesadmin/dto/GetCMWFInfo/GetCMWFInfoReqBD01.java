package pnc.mesadmin.dto.GetCMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/6/28.
 */
public class GetCMWFInfoReqBD01 implements Serializable{
    @JsonProperty("WoCode")
    private String WoCode;

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }
    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
    }
}

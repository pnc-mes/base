package pnc.mesadmin.dto.GetCMWInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/18.
 */
public class GetCMWInfoReqBD00 {

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

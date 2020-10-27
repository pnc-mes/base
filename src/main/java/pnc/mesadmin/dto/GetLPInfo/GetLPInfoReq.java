package pnc.mesadmin.dto.GetLPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/10/18.
 */
public class GetLPInfoReq implements Serializable {

    @JsonProperty("LanCode")
    private String LanCode;

    @JsonIgnore
    public String getLanCode() {
        return LanCode;
    }
    @JsonIgnore
    public void setLanCode(String lanCode) {
        LanCode = lanCode;
    }
}

package pnc.mesadmin.dto.GetCKTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKTInfoResD {
    @JsonProperty("CkTCode")
    private String CkTCode;

    @JsonProperty("CkTName")
    private String CkTName;
    @JsonIgnore
    public String getCkTCode() {
        return CkTCode;
    }
    @JsonIgnore
    public void setCkTCode(String ckTCode) {
        CkTCode = ckTCode;
    }
    @JsonIgnore
    public String getCkTName() {
        return CkTName;
    }
    @JsonIgnore
    public void setCkTName(String ckTName) {
        CkTName = ckTName;
    }
}

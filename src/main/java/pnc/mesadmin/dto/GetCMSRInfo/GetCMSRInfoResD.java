package pnc.mesadmin.dto.GetCMSRInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/10.
 */
public class GetCMSRInfoResD {

    @JsonProperty("SRCode")
    private String SRCode;

    @JsonIgnore
    public String getSRCode() {
        return SRCode;
    }

    @JsonIgnore
    public void setSRCode(String SRCode) {
        this.SRCode = SRCode;
    }
}

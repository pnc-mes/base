package pnc.mesadmin.dto.SaveRePrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/14.
 */
public class SaveRePrInfoReqBD00RePrInfo {

    @JsonProperty("PTCode")
    private String PTCode;

    @JsonIgnore
    public String getPTCode() {
        return PTCode;
    }

    @JsonIgnore
    public void setPTCode(String PTCode) {
        this.PTCode = PTCode;
    }
}

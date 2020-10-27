package pnc.mesadmin.dto.SaveRMInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liufuzhi on 2018/1/24.
 */
public class SaveRMInfoResD {
    @JsonProperty("RetRd")
    private int RetRd;

    @JsonProperty("RetCode")
    private String RetCode;

    @JsonIgnore
    public int getRetRd() {
        return RetRd;
    }

    @JsonIgnore
    public void setRetRd(int retRd) {
        RetRd = retRd;
    }

    @JsonIgnore
    public String getRetCode() {
        return RetCode;
    }

    @JsonIgnore
    public void setRetCode(String retCode) {
        RetCode = retCode;
    }
}

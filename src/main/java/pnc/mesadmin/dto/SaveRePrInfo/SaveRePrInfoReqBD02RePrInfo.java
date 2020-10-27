package pnc.mesadmin.dto.SaveRePrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2018/8/28.
 */
public class SaveRePrInfoReqBD02RePrInfo {

    @JsonProperty("BarCode")
    private String BarCode;

    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }

    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
}

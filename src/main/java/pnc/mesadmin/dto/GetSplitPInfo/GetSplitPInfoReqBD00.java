package pnc.mesadmin.dto.GetSplitPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/26.
 */
public class GetSplitPInfoReqBD00 implements Serializable {

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

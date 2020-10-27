package pnc.mesadmin.dto.newmove.NewAddInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 删除
 */
public class NewRmInfoRes {
    @JsonProperty("BarCode")
    private String BarCode;

    @JsonProperty("OutCode")
    private String OutCode;
    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }
    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
    @JsonIgnore
    public String getOutCode() {
        return OutCode;
    }
    @JsonIgnore
    public void setOutCode(String outCode) {
        OutCode = outCode;
    }
}

package pnc.mesadmin.dto.GetWIncMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2018/1/28.
 */
public class GetWIncMaInfoReqBD00 {

    @JsonProperty("PurChCode")
    private String PurChCode;

    @JsonIgnore
    public String getPurChCode() {
        return PurChCode;
    }

    @JsonIgnore
    public void setPurChCode(String purChCode) {
        PurChCode = purChCode;
    }
}

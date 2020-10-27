package pnc.mesadmin.dto.newmove.MStoreDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-27
 **/
public class WInOutMaResponse {
    //出入单号
    @JsonProperty("WInOutMaCode")
    private String WInOutMaCode;

    @JsonIgnore
    public String getWInOutMaCode() {
        return WInOutMaCode;
    }

    @JsonIgnore
    public void setWInOutMaCode(String WInOutMaCode) {
        this.WInOutMaCode = WInOutMaCode;
    }
}

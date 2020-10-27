package pnc.mesadmin.dto.GetDWExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: haozan
 * @Date: 2018/9/3 17:31
 * @Description:
 */
public class GetDWExpandInfoReq00 implements Serializable {
    @JsonProperty("MTRd")
    private String MTRd;

    @JsonIgnore
    public String getMTRd() {
        return MTRd;
    }
    @JsonIgnore
    public void setMTRd(String MTRd) {
        this.MTRd = MTRd;
    }
}

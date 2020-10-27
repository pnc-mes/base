package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:24
 * @Description:
 */
public class GetCyclePlanInfoReq00 implements Serializable {
    @JsonProperty("CyclePlanRd")
    private int CyclePlanRd;

    @JsonIgnore
    public int getCyclePlanRd() {
        return CyclePlanRd;
    }
    @JsonIgnore
    public void setCyclePlanRd(int cyclePlanRd) {
        CyclePlanRd = cyclePlanRd;
    }
}

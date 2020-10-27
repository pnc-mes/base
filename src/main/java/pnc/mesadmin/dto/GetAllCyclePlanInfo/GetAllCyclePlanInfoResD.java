package pnc.mesadmin.dto.GetAllCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 14:41
 * @Description:
 */
public class GetAllCyclePlanInfoResD implements Serializable {
    @JsonProperty("CyclePlanRd")
    private int CyclePlanRd;

    @JsonProperty("CyclePlanName")
    private String CyclePlanName;
    @JsonIgnore
    public int getCyclePlanRd() {
        return CyclePlanRd;
    }
    @JsonIgnore
    public void setCyclePlanRd(int cyclePlanRd) {
        CyclePlanRd = cyclePlanRd;
    }
    @JsonIgnore
    public String getCyclePlanName() {
        return CyclePlanName;
    }
    @JsonIgnore
    public void setCyclePlanName(String cyclePlanName) {
        CyclePlanName = cyclePlanName;
    }
}

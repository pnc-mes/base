package pnc.mesadmin.dto.GetFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: haozan
 * @Date: 2018/9/7
 * @Description:
 */
public class GetFrePlanInfoReq00 implements Serializable {

    @JsonProperty("FrePlanRd")
    private Integer FrePlanRd;

    @JsonIgnore
    public Integer getFrePlanRd() {
        return FrePlanRd;
    }
    @JsonIgnore
    public void setFrePlanRd(Integer frePlanRd) {
        FrePlanRd = frePlanRd;
    }
}

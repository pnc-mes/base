package pnc.mesadmin.dto.GetAllFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: haozan
 * @Date: 2018/9/7
 * @Description:
 */
public class GetAllFrePlanInfoResD {
    @JsonProperty("FrePlanRd")
    private Integer FrePlanRd;

    @JsonProperty("FrePlanName")
    private String FrePlanName;
    @JsonIgnore
    public Integer getFrePlanRd() {
        return FrePlanRd;
    }
    @JsonIgnore
    public void setFrePlanRd(Integer frePlanRd) {
        FrePlanRd = frePlanRd;
    }
    @JsonIgnore
    public String getFrePlanName() {
        return FrePlanName;
    }
    @JsonIgnore
    public void setFrePlanName(String frePlanName) {
        FrePlanName = frePlanName;
    }


}

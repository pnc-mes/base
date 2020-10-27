package pnc.mesadmin.dto.GetAllCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:51
 * @Description:
 */
public class GetAllCheckPlanInfoResD {
    @JsonProperty("CheckPlanRd")
    private int CheckPlanRd;

    @JsonProperty("CheckPlanName")
    private String CheckPlanName;
    @JsonIgnore
    public int getCheckPlanRd() {
        return CheckPlanRd;
    }
    @JsonIgnore
    public void setCheckPlanRd(int checkPlanRd) {
        CheckPlanRd = checkPlanRd;
    }
    @JsonIgnore
    public String getCheckPlanName() {
        return CheckPlanName;
    }
    @JsonIgnore
    public void setCheckPlanName(String checkPlanName) {
        CheckPlanName = checkPlanName;
    }
}

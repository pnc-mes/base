package pnc.mesadmin.dto.GetDevFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 11:15
 * @Description:
 */
public class GetDevFInfoResDCheckPlan {
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

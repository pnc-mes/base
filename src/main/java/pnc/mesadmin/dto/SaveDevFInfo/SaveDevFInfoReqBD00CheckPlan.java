package pnc.mesadmin.dto.SaveDevFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 13:52
 * @Description:
 */
public class SaveDevFInfoReqBD00CheckPlan {
    @JsonProperty("CheckPlanRd")
    private int CheckPlanRd;

    @JsonIgnore
    public int getCheckPlanRd() {
        return CheckPlanRd;
    }

    @JsonIgnore
    public void setCheckPlanRd(int checkPlanRd) {
        CheckPlanRd = checkPlanRd;
    }
}

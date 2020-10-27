package pnc.mesadmin.dto.SaveDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 13:52
 * @Description:
 */
public class SaveDevInfoReqBD02CheckPlan {
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

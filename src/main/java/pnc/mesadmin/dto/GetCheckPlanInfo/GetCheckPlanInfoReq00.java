package pnc.mesadmin.dto.GetCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:24
 * @Description:
 */
public class GetCheckPlanInfoReq00 implements Serializable {
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

package pnc.mesadmin.dto.SaveCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCheckPlanInfoReq01 implements Serializable {
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

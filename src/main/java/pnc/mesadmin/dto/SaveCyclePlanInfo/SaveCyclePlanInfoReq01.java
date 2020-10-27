package pnc.mesadmin.dto.SaveCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCyclePlanInfoReq01 implements Serializable {
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

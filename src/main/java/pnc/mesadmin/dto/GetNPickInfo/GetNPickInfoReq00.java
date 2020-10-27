package pnc.mesadmin.dto.GetNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/10/24.
 */
public class GetNPickInfoReq00 {
    @JsonProperty("PickRd")
    private int PickRd;
    @JsonIgnore
    public int getPickRd() {
        return PickRd;
    }
    @JsonIgnore
    public void setPickRd(int pickRd) {
        PickRd = pickRd;
    }
}

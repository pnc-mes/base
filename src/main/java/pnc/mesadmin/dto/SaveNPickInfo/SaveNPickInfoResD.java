package pnc.mesadmin.dto.SaveNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/10/24.
 */
public class SaveNPickInfoResD {
    @JsonProperty("PickRd")
    private int PickRd;

    @JsonProperty("PickCode")
    private String PickCode;

    @JsonIgnore
    public int getPickRd() {
        return PickRd;
    }

    @JsonIgnore
    public void setPickRd(int pickRd) {
        PickRd = pickRd;
    }

    @JsonIgnore
    public String getPickCode() {
        return PickCode;
    }

    @JsonIgnore
    public void setPickCode(String pickCode) {
        PickCode = pickCode;
    }
}

package pnc.mesadmin.dto.SaveDPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * Created by panjunfeng on 2017/11/3.
 */
public class SaveDPInfoResD {

    @JsonProperty("PackCode")
    private String PackCode;

    @JsonIgnore
    public String getPackCode() {
        return PackCode;
    }

    @JsonIgnore
    public void setPackCode(String packCode) {
        PackCode = packCode;
    }
}

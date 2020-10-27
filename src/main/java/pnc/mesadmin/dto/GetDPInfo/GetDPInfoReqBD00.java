package pnc.mesadmin.dto.GetDPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * Created by panjunfeng on 2017/11/3.
 */
public class GetDPInfoReqBD00 {

    @JsonProperty("BarCode")
    private String BarCode;

    @JsonProperty("DPType")
    private String DPType;

    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }

    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    @JsonIgnore
    public String getDPType() {
        return DPType;
    }

    @JsonIgnore
    public void setDPType(String DPType) {
        this.DPType = DPType;
    }
}

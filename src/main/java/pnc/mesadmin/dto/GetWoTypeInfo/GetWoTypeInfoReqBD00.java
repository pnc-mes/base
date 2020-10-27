package pnc.mesadmin.dto.GetWoTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单信息，根据工单ID
 * 创建人：ZC
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class GetWoTypeInfoReqBD00 implements Serializable{

    @JsonProperty("WTRd")
    private int WTRd;

    @JsonIgnore
    public int getWTRd() {
        return WTRd;
    }
    @JsonIgnore
    public void setWTRd(int WTRd) {
        this.WTRd = WTRd;
    }
}

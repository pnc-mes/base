package pnc.mesadmin.dto.GetWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单列表返回的WOTInfo
 * 创建人：张亮亮
 * 创建时间：2017-08-23
 * 修改人：
 * 修改时间：
 */
public class GetWOInfoResBDWOT implements Serializable{

    @JsonProperty("WTRd")
    private int WTRd;

    @JsonProperty("WTName")
    private String WTName;

    @JsonIgnore
    public int getWTRd() {
        return WTRd;
    }
    @JsonIgnore
    public void setWTRd(int WTRd) {
        this.WTRd = WTRd;
    }
    @JsonIgnore
    public String getWTName() {
        return WTName;
    }
    @JsonIgnore
    public void setWTName(String WTName) {
        this.WTName = WTName;
    }
}

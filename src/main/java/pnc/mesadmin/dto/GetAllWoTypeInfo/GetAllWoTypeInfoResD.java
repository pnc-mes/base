package pnc.mesadmin.dto.GetAllWoTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单信息列表返回的Body
 * 创建人：ZC
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class GetAllWoTypeInfoResD implements Serializable{

    @JsonProperty("WTRd")
    private int WTRd;

    @JsonProperty("WTName")
    private String WTName;

    @JsonIgnore
    public String getWTName() {
        return WTName;
    }
    @JsonIgnore
    public void setWTName(String WTName) {
        this.WTName = WTName;
    }
    @JsonIgnore
    public int getWTRd() {
        return WTRd;
    }
    @JsonIgnore
    public void setWTRd(int WTRd) {
        this.WTRd = WTRd;
    }
}

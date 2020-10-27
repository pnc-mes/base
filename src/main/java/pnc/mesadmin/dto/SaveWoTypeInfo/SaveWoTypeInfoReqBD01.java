package pnc.mesadmin.dto.SaveWoTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：
 * 创建人：ZC
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class SaveWoTypeInfoReqBD01 implements Serializable{

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

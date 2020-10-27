package pnc.mesadmin.dto.GetWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单列表返回的WFInfo
 * 创建人：潘俊峰
 * 创建时间：2018-09-04
 * 修改人：
 * 修改时间：
 */
public class GetWOInfoResBDWF implements Serializable{

    @JsonProperty("WFVerRd")
    private int WFVerRd;

    @JsonProperty("WFName")
    private String WFName;

    @JsonIgnore
    public int getWFVerRd() {
        return WFVerRd;
    }

    @JsonIgnore
    public void setWFVerRd(int WFVerRd) {
        this.WFVerRd = WFVerRd;
    }

    @JsonIgnore
    public String getWFName() {
        return WFName;
    }

    @JsonIgnore
    public void setWFName(String WFName) {
        this.WFName = WFName;
    }
}

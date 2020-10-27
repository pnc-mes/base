package pnc.mesadmin.dto.GetAllWCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工作中心列表返回的Data
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
public class GetAllWCInfoResD implements Serializable{

    @JsonProperty("WCRd")
    private int WCRd;

    @JsonProperty("WCName")
    private String WCName;

    @JsonProperty("FaName")
    private String FaName;

    @JsonIgnore
    public int getWCRd() {
        return WCRd;
    }

    @JsonIgnore
    public void setWCRd(int WCRd) {
        this.WCRd = WCRd;
    }

    @JsonIgnore
    public String getWCName() {
        return WCName;
    }

    @JsonIgnore
    public void setWCName(String WCName) {
        this.WCName = WCName;
    }

    @JsonIgnore
    public String getFaName() {
        return FaName;
    }

    @JsonIgnore
    public void setFaName(String faName) {
        FaName = faName;
    }
}

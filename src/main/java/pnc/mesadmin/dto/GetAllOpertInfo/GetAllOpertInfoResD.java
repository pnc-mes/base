package pnc.mesadmin.dto.GetAllOpertInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：作业列表DTO返回业务数据实体类Data
 * 创建人：赵超
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class GetAllOpertInfoResD implements Serializable{

    @JsonProperty("OpertRd")
    private int OpertRd;

    @JsonProperty("OptName")
    private String OptName;

    @JsonProperty("WCName")
    private String WCName;

    @JsonIgnore
    public int getOpertRd() {
        return OpertRd;
    }

    @JsonIgnore
    public void setOpertRd(int opertRd) {
        OpertRd = opertRd;
    }

    @JsonIgnore
    public String getOptName() {
        return OptName;
    }

    @JsonIgnore
    public void setOptName(String optName) {
        OptName = optName;
    }

    @JsonIgnore
    public String getWCName() {
        return WCName;
    }

    @JsonIgnore
    public void setWCName(String WCName) {
        this.WCName = WCName;
    }
}

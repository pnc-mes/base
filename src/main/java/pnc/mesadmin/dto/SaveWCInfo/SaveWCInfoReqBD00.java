package pnc.mesadmin.dto.SaveWCInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：新增工作中心
 * 创建人：张亮亮
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class SaveWCInfoReqBD00 implements Serializable {

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("WCName")
    private String WCName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getFaRd() {
        return FaRd;
    }

    @JsonIgnore
    public void setFaRd(int faRd) {
        FaRd = faRd;
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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

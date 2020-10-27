package pnc.mesadmin.dto.SaveReaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：新增原因代码DTO
 * 创建人：张亮亮
 * 创建时间：2017-5-31
 * 修改人：
 * 修改时间：
 */
public class SaveReaInfoReq00 implements Serializable{

    @JsonProperty("ReaTID")
    private String ReaTID;

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("ReaCode")
    private String ReaCode;

    @JsonIgnore
    public String getReaCode() {
        return ReaCode;
    }

    @JsonIgnore
    public void setReaCode(String reaCode) {
        ReaCode = reaCode;
    }

    @JsonIgnore
    public String getReaTID() {
        return ReaTID;
    }

    @JsonIgnore
    public void setReaTID(String reaTID) {
        ReaTID = reaTID;
    }

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
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

package pnc.mesadmin.dto.SaveDevFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存设备家族信息DTO请求业务数据实体类BD00
 * 创建人：刘福志
 * 创建时间：2017-8-16
 * 修改人：
 * 修改时间：
 */
public class SaveDevFInfoReqBD00 implements Serializable {
    @JsonProperty("DevFName")
    private String DevFName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("CheckPlanInfo")
    private List<SaveDevFInfoReqBD00CheckPlan> CheckPlanInfo;

    @JsonProperty("PMInfo")
    private List<SaveDevFInfoReqBD00PM> PMInfo;


    @JsonIgnore
    public String getDevFName() {
        return DevFName;
    }

    @JsonIgnore
    public void setDevFName(String devFName) {
        DevFName = devFName;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public List<SaveDevFInfoReqBD00CheckPlan> getCheckPlanInfo() {
        return CheckPlanInfo;
    }
    @JsonIgnore
    public void setCheckPlanInfo(List<SaveDevFInfoReqBD00CheckPlan> checkPlanInfo) {
        CheckPlanInfo = checkPlanInfo;
    }
    @JsonIgnore
    public List<SaveDevFInfoReqBD00PM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<SaveDevFInfoReqBD00PM> PMInfo) {
        this.PMInfo = PMInfo;
    }
}

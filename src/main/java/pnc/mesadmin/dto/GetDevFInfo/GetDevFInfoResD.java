package pnc.mesadmin.dto.GetDevFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备家族信息DTO返回业务实体类data
 * 创建人：刘福志
 * 创建时间：2017-8-16
 * 修改人：
 * 修改时间：
 */
public class GetDevFInfoResD implements Serializable {
    @JsonProperty("DevFRd")
    private int DevFRd;

    @JsonProperty("DevFName")
    private String DevFName;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("CheckPlanInfo")
    private List<GetDevFInfoResDCheckPlan>  CheckPlanInfo;

    @JsonProperty("PMInfo")
    private List<GetDevFInfoResDPM>  PMInfo;


    @JsonIgnore
    public int getDevFRd() {
        return DevFRd;
    }

    @JsonIgnore
    public void setDevFRd(int devFRd) {
        DevFRd = devFRd;
    }

    @JsonIgnore
    public String getDevFName() {
        return DevFName;
    }

    @JsonIgnore
    public void setDevFName(String devFName) {
        DevFName = devFName;
    }

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public List<GetDevFInfoResDCheckPlan> getCheckPlanInfo() {
        return CheckPlanInfo;
    }
    @JsonIgnore
    public void setCheckPlanInfo(List<GetDevFInfoResDCheckPlan> checkPlanInfo) {
        CheckPlanInfo = checkPlanInfo;
    }
    @JsonIgnore
    public List<GetDevFInfoResDPM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<GetDevFInfoResDPM> PMInfo) {
        this.PMInfo = PMInfo;
    }
}

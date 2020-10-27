package pnc.mesadmin.dto.GetToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCheckPlanInfoDto;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取工具信息DTO返回实体类Body
 * 创建人：郝赞
 * 创建时间：2018-6-12
 * 修改人：
 * 修改时间：
 */
public class GetToolInfoResD implements Serializable{

    @JsonProperty("ToolRd")
    private Integer ToolRd;

    @JsonProperty("ToolName")
    private String ToolName;

    @JsonProperty("VenderType")
    private String VenderType;

    @JsonProperty("VenderSN")
    private String VenderSN;

    @JsonProperty("Factory")
    private GetToolInfoResDFactory  Factory;

    @JsonProperty("Suppier")
    private GetToolInfoResDSuppier  Suppier;

    @JsonProperty("Model")
    private GetToolInfoResDModel  Model;

    @JsonProperty("ToolFamily")
    private GetToolInfoResDToolFamily  ToolFamily;

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

    @JsonProperty("PMInfo")
    private List<GetToolInfoResDPM>  PMInfo;


    @JsonProperty("ToolBZInfo")
    private GetToolInfoResDToolBZInfo  ToolBZInfo;

    //熊伟开始
    @JsonProperty("CheckPlanInfo")
    private List<GetCheckPlanInfoDto>  CheckPlanInfo;
    //熊伟结束


    @JsonIgnore
    public Integer getToolRd() {
        return ToolRd;
    }

    @JsonIgnore
    public void setToolRd(Integer toolRd) {
        ToolRd = toolRd;
    }

    @JsonIgnore
    public String getToolName() {
        return ToolName;
    }

    @JsonIgnore
    public void setToolName(String toolName) {
        ToolName = toolName;
    }

    @JsonIgnore
    public String getVenderType() {
        return VenderType;
    }

    @JsonIgnore
    public void setVenderType(String venderType) {
        VenderType = venderType;
    }

    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }

    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
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
    public GetToolInfoResDFactory getFactory() {
        return Factory;
    }
    @JsonIgnore
    public void setFactory(GetToolInfoResDFactory factory) {
        Factory = factory;
    }
    @JsonIgnore
    public GetToolInfoResDSuppier getSuppier() {
        return Suppier;
    }
    @JsonIgnore
    public void setSuppier(GetToolInfoResDSuppier suppier) {
        Suppier = suppier;
    }
    @JsonIgnore
    public GetToolInfoResDModel getModel() {
        return Model;
    }
    @JsonIgnore
    public void setModel(GetToolInfoResDModel model) {
        Model = model;
    }
    @JsonIgnore
    public GetToolInfoResDToolFamily getToolFamily() {
        return ToolFamily;
    }
    @JsonIgnore
    public void setToolFamily(GetToolInfoResDToolFamily toolFamily) {
        ToolFamily = toolFamily;
    }
    @JsonIgnore
    public List<GetToolInfoResDPM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<GetToolInfoResDPM> PMInfo) {
        this.PMInfo = PMInfo;
    }
    @JsonIgnore
    public GetToolInfoResDToolBZInfo getToolBZInfo() {
        return ToolBZInfo;
    }
    @JsonIgnore
    public void setToolBZInfo(GetToolInfoResDToolBZInfo toolBZInfo) {
        ToolBZInfo = toolBZInfo;
    }
    @JsonIgnore
    public List<GetCheckPlanInfoDto> getCheckPlanInfo() {
        return CheckPlanInfo;
    }
    @JsonIgnore
    public void setCheckPlanInfo(List<GetCheckPlanInfoDto> checkPlanInfo) {
        CheckPlanInfo = checkPlanInfo;
    }
}

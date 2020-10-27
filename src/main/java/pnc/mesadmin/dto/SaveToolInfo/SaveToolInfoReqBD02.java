package pnc.mesadmin.dto.SaveToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCheckPlanInfoDto;

import java.io.Serializable;
import java.util.List;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存工具信息dto返回实体类Data
 * 创建人：郝赞
 * 创建时间：2018-6-12
 * 修改人：
 * 修改时间：
 */
public class SaveToolInfoReqBD02 implements Serializable{

    @JsonProperty("ToolRd")
    private int ToolRd;

    @JsonProperty("ToolName")
    private String ToolName;

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("VenderType")
    private String VenderType;

    @JsonProperty("VenderSN")
    private String VenderSN;

    @JsonProperty("ToolFamilyRd")
    private int ToolFamilyRd;

    @JsonProperty("DSMRd")
    private int DSMRd;

    @JsonProperty("SupplierRd")
    private int SupplierRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("PMInfo")
    private List<SaveToolInfoReqBD02PM> PMInfo;
    @JsonProperty("CheckPlanInfo")
    private List<GetCheckPlanInfoDto> CheckPlanInfo;

    @JsonProperty("ToolBZInfo")
    private SaveToolInfoReqBD02ToolBZInfo ToolBZInfo;
    @JsonIgnore
    public int getToolRd() {
        return ToolRd;
    }
    @JsonIgnore
    public void setToolRd(int toolRd) {
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
    public int getFaRd() {
        return FaRd;
    }
    @JsonIgnore
    public void setFaRd(int faRd) {
        FaRd = faRd;
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
    public int getToolFamilyRd() {
        return ToolFamilyRd;
    }
    @JsonIgnore
    public void setToolFamilyRd(int toolFamilyRd) {
        ToolFamilyRd = toolFamilyRd;
    }
    @JsonIgnore
    public int getDSMRd() {
        return DSMRd;
    }
    @JsonIgnore
    public void setDSMRd(int DSMRd) {
        this.DSMRd = DSMRd;
    }
    @JsonIgnore
    public int getSupplierRd() {
        return SupplierRd;
    }
    @JsonIgnore
    public void setSupplierRd(int supplierRd) {
        SupplierRd = supplierRd;
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
    public List<SaveToolInfoReqBD02PM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<SaveToolInfoReqBD02PM> PMInfo) {
        this.PMInfo = PMInfo;
    }
    @JsonIgnore
    public SaveToolInfoReqBD02ToolBZInfo getToolBZInfo() {
        return ToolBZInfo;
    }
    @JsonIgnore
    public void setToolBZInfo(SaveToolInfoReqBD02ToolBZInfo toolBZInfo) {
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

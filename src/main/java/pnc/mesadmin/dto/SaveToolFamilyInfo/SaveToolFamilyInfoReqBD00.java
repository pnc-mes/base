package pnc.mesadmin.dto.SaveToolFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存工具家族信息dto返回实体类Data
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public class SaveToolFamilyInfoReqBD00 implements Serializable{

    @JsonProperty("ToolFamilyName")
    private String ToolFamilyName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("FaRd")
    private Integer FaRd;

    @JsonProperty("DSMRd")
    private Integer DSMRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("PMInfo")
    private List<SaveToolFamilyInfoReqBD00PM>  PMInfo;

    @JsonIgnore
    public List<SaveToolFamilyInfoReqBD00PM> getPMInfo() {
        return PMInfo;
    }

    @JsonIgnore
    public void setPMInfo(List<SaveToolFamilyInfoReqBD00PM> PMInfo) {
        this.PMInfo = PMInfo;
    }

    @JsonIgnore
    public String getToolFamilyName() {
        return ToolFamilyName;
    }

    @JsonIgnore
    public void setToolFamilyName(String toolFamilyName) {
        ToolFamilyName = toolFamilyName;
    }

    @JsonIgnore
    public String getDescription() {
        return Description;
    }

    @JsonIgnore
    public void setDescription(String description) {
        Description = description;
    }

    @JsonIgnore
    public Integer getFaRd() {
        return FaRd;
    }

    @JsonIgnore
    public void setFaRd(Integer faRd) {
        FaRd = faRd;
    }

    public Integer getDSMRd() {
        return DSMRd;
    }

    @JsonIgnore
    public void setDSMRd(Integer DSMRd) {
        this.DSMRd = DSMRd;
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

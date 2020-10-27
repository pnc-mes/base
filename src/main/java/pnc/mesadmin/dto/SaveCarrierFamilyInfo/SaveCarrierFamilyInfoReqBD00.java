package pnc.mesadmin.dto.SaveCarrierFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存载具家族信息dto返回实体类Data
 * 创建人：郝赞
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
public class SaveCarrierFamilyInfoReqBD00 implements Serializable{

    @JsonProperty("CarrierFamilyName")
    private String CarrierFamilyName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("DSMRd")
    private int DSMRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("PMInfo")
    private List<SaveCarrierFamilyInfoReqBD00PM> PMInfo;

    @JsonIgnore
    public String getCarrierFamilyName() {
        return CarrierFamilyName;
    }

    @JsonIgnore
    public void setCarrierFamilyName(String carrierFamilyName) {
        CarrierFamilyName = carrierFamilyName;
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
    public int getFaRd() {
        return FaRd;
    }

    @JsonIgnore
    public void setFaRd(int faRd) {
        FaRd = faRd;
    }

    public int getDSMRd() {
        return DSMRd;
    }

    @JsonIgnore
    public void setDSMRd(int DSMRd) {
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
    @JsonIgnore
    public List<SaveCarrierFamilyInfoReqBD00PM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<SaveCarrierFamilyInfoReqBD00PM> PMInfo) {
        this.PMInfo = PMInfo;
    }
}

package pnc.mesadmin.dto.SaveCarrierInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveCarrierInfoReqBD00CheckPlanInfo;

import java.io.Serializable;
import java.util.List;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存载具信息dto返回实体类02
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public class SaveCarrierInfoReqBD02 implements Serializable{

    @JsonProperty("CarrierRd")
    private Integer CarrierRd;

    @JsonProperty("CarrierName")
    private String CarrierName;

    @JsonProperty("VenderSN")
    private String VenderSN;

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("DSMRd")
    private int DSMRd;

    @JsonProperty("CarrierFamilyRd")
    private int CarrierFamilyRd;

    @JsonProperty("SupplierRd")
    private int SupplierRd;


    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("PMInfo")
    private List<SaveCarrierInfoReqBD02PM> PMInfo;


    @JsonProperty("CarrierBZInfo")
    private SaveCarrierInfoReqBD02CarrierBZInfo CarrierBZInfo;

    @JsonProperty("CheckPlanInfo")
    private List<SaveCarrierInfoReqBD00CheckPlanInfo> CheckPlanInfo;

    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }

    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }

    @JsonIgnore
    public Integer getCarrierRd() {
        return CarrierRd;
    }

    @JsonIgnore
    public void setCarrierRd(Integer carrierRd) {
        CarrierRd = carrierRd;
    }

    @JsonIgnore
    public String getCarrierName() {
        return CarrierName;
    }

    @JsonIgnore
    public void setCarrierName(String carrierName) {
        CarrierName = carrierName;
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
    public int getDSMRd() {
        return DSMRd;
    }

    @JsonIgnore
    public void setDSMRd(int DSMRd) {
        this.DSMRd = DSMRd;
    }

    @JsonIgnore
    public int getCarrierFamilyRd() {
        return CarrierFamilyRd;
    }

    @JsonIgnore
    public void setCarrierFamilyRd(int carrierFamilyRd) {
        CarrierFamilyRd = carrierFamilyRd;
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
    public List<SaveCarrierInfoReqBD02PM> getPMInfo() {
        return PMInfo;
    }

    @JsonIgnore
    public void setPMInfo(List<SaveCarrierInfoReqBD02PM> PMInfo) {
        this.PMInfo = PMInfo;
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
    public SaveCarrierInfoReqBD02CarrierBZInfo getCarrierBZInfo() {
        return CarrierBZInfo;
    }
    @JsonIgnore
    public void setCarrierBZInfo(SaveCarrierInfoReqBD02CarrierBZInfo carrierBZInfo) {
        CarrierBZInfo = carrierBZInfo;
    }
    @JsonIgnore
    public List<SaveCarrierInfoReqBD00CheckPlanInfo> getCheckPlanInfo() {
        return CheckPlanInfo;
    }
    @JsonIgnore
    public void setCheckPlanInfo(List<SaveCarrierInfoReqBD00CheckPlanInfo> checkPlanInfo) {
        CheckPlanInfo = checkPlanInfo;
    }
}


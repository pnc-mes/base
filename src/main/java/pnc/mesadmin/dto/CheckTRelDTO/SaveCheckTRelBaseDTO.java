package pnc.mesadmin.dto.CheckTRelDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-25
 **/
public class SaveCheckTRelBaseDTO {
    @JsonProperty("CTRelRd")
    private Integer CTRelRd;
    @JsonProperty("TempRelName")
    private String TempRelName;
    @JsonProperty("TempRelType")
    private String TempRelType;
    @JsonProperty("RelType")
    private String RelType;
    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("SupplierRd")
    private int SupplierRd;
    @JsonProperty("CustomerRd")
    private int CustomerRd;
    @JsonProperty("CDOCRd")
    private Integer CDOCRd;
    @JsonProperty("RelRd")
    private int RelRd;

    @JsonProperty("CTRelDtlInfo")
    private List<CTRelDtlInfo> CTRelDtlInfo;


    public static class CTRelDtlInfo {
        @JsonProperty("CPTRd")
        private Integer CPTRd;
        @JsonProperty("CLevelRd")
        private Integer CLevelRd;
        @JsonProperty("AQLRuleRd")
        private Integer AQLRuleRd;

        @JsonIgnore
        public Integer getCPTRd() {
            return CPTRd;
        }

        @JsonIgnore
        public void setCPTRd(Integer CPTRd) {
            this.CPTRd = CPTRd;
        }

        @JsonIgnore
        public Integer getCLevelRd() {
            return CLevelRd;
        }

        @JsonIgnore
        public void setCLevelRd(Integer CLevelRd) {
            this.CLevelRd = CLevelRd;
        }

        @JsonIgnore
        public Integer getAQLRuleRd() {
            return AQLRuleRd;
        }

        @JsonIgnore
        public void setAQLRuleRd(Integer AQLRuleRd) {
            this.AQLRuleRd = AQLRuleRd;
        }
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
    public Integer getCTRelRd() {
        return CTRelRd;
    }

    @JsonIgnore
    public void setCTRelRd(Integer CTRelRd) {
        this.CTRelRd = CTRelRd;
    }

    @JsonIgnore
    public String getTempRelName() {
        return TempRelName;
    }

    @JsonIgnore
    public void setTempRelName(String tempRelName) {
        TempRelName = tempRelName;
    }

    @JsonIgnore
    public String getTempRelType() {
        return TempRelType;
    }

    @JsonIgnore
    public void setTempRelType(String tempRelType) {
        TempRelType = tempRelType;
    }

    @JsonIgnore
    public String getRelType() {
        return RelType;
    }

    @JsonIgnore
    public void setRelType(String relType) {
        RelType = relType;
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
    public int getCustomerRd() {
        return CustomerRd;
    }
    @JsonIgnore
    public void setCustomerRd(int customerRd) {
        CustomerRd = customerRd;
    }

    @JsonIgnore
    public Integer getCDOCRd() {
        return CDOCRd;
    }

    @JsonIgnore
    public void setCDOCRd(Integer CDOCRd) {
        this.CDOCRd = CDOCRd;
    }


    @JsonIgnore
    public List<SaveCheckTRelBaseDTO.CTRelDtlInfo> getCTRelDtlInfo() {
        return CTRelDtlInfo;
    }

    @JsonIgnore
    public void setCTRelDtlInfo(List<SaveCheckTRelBaseDTO.CTRelDtlInfo> CTRelDtlInfo) {
        this.CTRelDtlInfo = CTRelDtlInfo;
    }
    @JsonIgnore
    public int getRelRd() {
        return RelRd;
    }
    @JsonIgnore
    public void setRelRd(int relRd) {
        RelRd = relRd;
    }
}

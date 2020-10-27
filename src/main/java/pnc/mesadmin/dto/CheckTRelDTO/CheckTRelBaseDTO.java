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
public class CheckTRelBaseDTO {
    @JsonProperty("CTRelRd")
    private Integer CTRelRd;
    @JsonProperty("TempRelName")
    private String TempRelName;
    @JsonProperty("TempRelType")
    private String TempRelType;
    @JsonProperty("RelType")
    private String RelType;
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

    @JsonProperty("Supplier")
    private Supplier Supplier;
    @JsonProperty("Customer")
    private Customer Customer;
    @JsonProperty("RelInfo")
    private RelInfo RelInfo;
    @JsonProperty("CDOC")
    private CDOC CDOC;
    @JsonProperty("CTRelDtlInfo")
    private List<CTRelDtlInfo> CTRelDtlInfo;


    public static class Supplier {
        @JsonProperty("SupplierRd")
        private Integer SupplierRd;
        @JsonProperty("SupplierName")
        private String SupplierName;

        @JsonIgnore
        public Integer getSupplierRd() {
            return SupplierRd;
        }

        @JsonIgnore
        public void setSupplierRd(Integer supplierRd) {
            SupplierRd = supplierRd;
        }

        @JsonIgnore
        public String getSupplierName() {
            return SupplierName;
        }

        @JsonIgnore
        public void setSupplierName(String supplierName) {
            SupplierName = supplierName;
        }
    }

    public static class Customer {
        @JsonProperty("CustomerRd")
        private Integer CustomerRd;
        @JsonProperty("CustomerName")
        private String CustomerName;

        @JsonIgnore
        public Integer getCustomerRd() {
            return CustomerRd;
        }

        @JsonIgnore
        public void setCustomerRd(Integer customerRd) {
            CustomerRd = customerRd;
        }

        @JsonIgnore
        public String getCustomerName() {
            return CustomerName;
        }

        @JsonIgnore
        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }
    }

    public static class RelInfo {
        @JsonProperty("RelRd")
        private Integer RelRd;
        @JsonProperty("RelName")
        private String RelName;

        @JsonIgnore
        public Integer getRelRd() {
            return RelRd;
        }

        @JsonIgnore
        public void setRelRd(Integer relRd) {
            RelRd = relRd;
        }

        @JsonIgnore
        public String getRelName() {
            return RelName;
        }

        @JsonIgnore
        public void setRelName(String relName) {
            RelName = relName;
        }
    }

    public static class CDOC {
        @JsonProperty("CDOCRd")
        private Integer CDOCRd;
        @JsonProperty("CheckDocName")
        private String CheckDocName;

        @JsonIgnore
        public Integer getCDOCRd() {
            return CDOCRd;
        }

        @JsonIgnore
        public void setCDOCRd(Integer CDOCRd) {
            this.CDOCRd = CDOCRd;
        }

        @JsonIgnore
        public String getCheckDocName() {
            return CheckDocName;
        }

        @JsonIgnore
        public void setCheckDocName(String checkDocName) {
            CheckDocName = checkDocName;
        }
    }


    public static class CTRelDtlInfo {
        @JsonProperty("CPT")
        private CPT CPT;
        @JsonProperty("CLevel")
        private CLevel CLevel;
        @JsonProperty("AQLRule")
        private AQLRule AQLRule;

        @JsonIgnore
        public CheckTRelBaseDTO.CPT getCPT() {
            return CPT;
        }

        @JsonIgnore
        public void setCPT(CheckTRelBaseDTO.CPT CPT) {
            this.CPT = CPT;
        }

        @JsonIgnore
        public CheckTRelBaseDTO.CLevel getCLevel() {
            return CLevel;
        }

        @JsonIgnore
        public void setCLevel(CheckTRelBaseDTO.CLevel CLevel) {
            this.CLevel = CLevel;
        }

        @JsonIgnore
        public CheckTRelBaseDTO.AQLRule getAQLRule() {
            return AQLRule;
        }

        @JsonIgnore
        public void setAQLRule(CheckTRelBaseDTO.AQLRule AQLRule) {
            this.AQLRule = AQLRule;
        }
    }

    public static class CPT {
        @JsonProperty("CPTRd")
        private Integer CPTRd;
        @JsonProperty("CheckTempName")
        private String CheckTempName;

        @JsonIgnore
        public Integer getCPTRd() {
            return CPTRd;
        }

        @JsonIgnore
        public void setCPTRd(Integer CPTRd) {
            this.CPTRd = CPTRd;
        }

        @JsonIgnore
        public String getCheckTempName() {
            return CheckTempName;
        }

        @JsonIgnore
        public void setCheckTempName(String checkTempName) {
            CheckTempName = checkTempName;
        }
    }

    public static class CLevel {
        @JsonProperty("CLevelRd")
        private Integer CLevelRd;
        @JsonProperty("CheckLevelName")
        private String CheckLevelName;

        @JsonIgnore
        public Integer getCLevelRd() {
            return CLevelRd;
        }

        @JsonIgnore
        public void setCLevelRd(Integer CLevelRd) {
            this.CLevelRd = CLevelRd;
        }

        @JsonIgnore
        public String getCheckLevelName() {
            return CheckLevelName;
        }

        @JsonIgnore
        public void setCheckLevelName(String checkLevelName) {
            CheckLevelName = checkLevelName;
        }
    }

    public static class AQLRule {
        @JsonProperty("AQLRuleRd")
        private Integer AQLRuleRd;
        @JsonProperty("AQLRuleName")
        private String AQLRuleName;

        @JsonIgnore
        public Integer getAQLRuleRd() {
            return AQLRuleRd;
        }

        @JsonIgnore
        public void setAQLRuleRd(Integer AQLRuleRd) {
            this.AQLRuleRd = AQLRuleRd;
        }

        @JsonIgnore
        public String getAQLRuleName() {
            return AQLRuleName;
        }

        @JsonIgnore
        public void setAQLRuleName(String AQLRuleName) {
            this.AQLRuleName = AQLRuleName;
        }
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
    public CheckTRelBaseDTO.Supplier getSupplier() {
        return Supplier;
    }

    @JsonIgnore
    public void setSupplier(CheckTRelBaseDTO.Supplier supplier) {
        Supplier = supplier;
    }

    @JsonIgnore
    public CheckTRelBaseDTO.Customer getCustomer() {
        return Customer;
    }

    @JsonIgnore
    public void setCustomer(CheckTRelBaseDTO.Customer customer) {
        Customer = customer;
    }

    @JsonIgnore
    public CheckTRelBaseDTO.RelInfo getRelInfo() {
        return RelInfo;
    }

    @JsonIgnore
    public void setRelInfo(CheckTRelBaseDTO.RelInfo relInfo) {
        RelInfo = relInfo;
    }

    @JsonIgnore
    public CheckTRelBaseDTO.CDOC getCDOC() {
        return CDOC;
    }

    @JsonIgnore
    public void setCDOC(CheckTRelBaseDTO.CDOC CDOC) {
        this.CDOC = CDOC;
    }

    @JsonIgnore
    public List<CheckTRelBaseDTO.CTRelDtlInfo> getCTRelDtlInfo() {
        return CTRelDtlInfo;
    }

    @JsonIgnore
    public void setCTRelDtlInfo(List<CheckTRelBaseDTO.CTRelDtlInfo> CTRelDtlInfo) {
        this.CTRelDtlInfo = CTRelDtlInfo;
    }
}

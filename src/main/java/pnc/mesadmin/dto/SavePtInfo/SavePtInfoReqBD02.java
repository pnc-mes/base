package pnc.mesadmin.dto.SavePtInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/31.
 */
public class SavePtInfoReqBD02 implements Serializable{

    @JsonProperty("PtRd")
    private int PtRd;

    @JsonProperty("PtName")
    private String PtName;

    @JsonProperty("FileName")
    private String FileName;

    @JsonProperty("BFInfo")
    private List<SavePtInfoReqBD02BFInfo> BFInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("Customer")
    private Customer Customer;

    public static class Customer {
        @JsonProperty("IsScript")
        private String IsScript;
        @JsonProperty("ScriptName")
        private String ScriptName;
        @JsonProperty("InParams")
        private List<InParams> InParams;
        @JsonProperty("OutParams")
        private List<OutParams> OutParams;

        @JsonIgnore
        public String getIsScript() {
            return IsScript;
        }

        @JsonIgnore
        public void setIsScript(String isScript) {
            IsScript = isScript;
        }

        @JsonIgnore
        public String getScriptName() {
            return ScriptName;
        }

        @JsonIgnore
        public void setScriptName(String scriptName) {
            ScriptName = scriptName;
        }

        @JsonIgnore
        public List<SavePtInfoReqBD02.InParams> getInParams() {
            return InParams;
        }

        @JsonIgnore
        public void setInParams(List<SavePtInfoReqBD02.InParams> inParams) {
            InParams = inParams;
        }

        @JsonIgnore
        public List<SavePtInfoReqBD02.OutParams> getOutParams() {
            return OutParams;
        }

        @JsonIgnore
        public void setOutParams(List<SavePtInfoReqBD02.OutParams> outParams) {
            OutParams = outParams;
        }
    }

    public static class InParams {
        @JsonProperty("FieldCode")
        private String FieldCode;
        @JsonProperty("FieldSource")
        private String FieldSource;
        @JsonProperty("Val")
        private String Val;

        @JsonIgnore
        public String getFieldCode() {
            return FieldCode;
        }

        @JsonIgnore
        public void setFieldCode(String fieldCode) {
            FieldCode = fieldCode;
        }

        @JsonIgnore
        public String getFieldSource() {
            return FieldSource;
        }

        @JsonIgnore
        public void setFieldSource(String fieldSource) {
            FieldSource = fieldSource;
        }

        @JsonIgnore
        public String getVal() {
            return Val;
        }

        @JsonIgnore
        public void setVal(String val) {
            Val = val;
        }
    }

    public static class OutParams {
        @JsonProperty("FieldCode")
        private String FieldCode;
        @JsonProperty("FieldName")
        private String FieldName;

        @JsonIgnore
        public String getFieldCode() {
            return FieldCode;
        }

        @JsonIgnore
        public void setFieldCode(String fieldCode) {
            FieldCode = fieldCode;
        }

        @JsonIgnore
        public String getFieldName() {
            return FieldName;
        }

        @JsonIgnore
        public void setFieldName(String fieldName) {
            FieldName = fieldName;
        }
    }


    @JsonIgnore
    public int getPtRd() {
        return PtRd;
    }

    @JsonIgnore
    public void setPtRd(int ptRd) {
        PtRd = ptRd;
    }

    @JsonIgnore
    public String getPtName() {
        return PtName;
    }

    @JsonIgnore
    public void setPtName(String ptName) {
        PtName = ptName;
    }

    @JsonIgnore
    public String getFileName() {
        return FileName;
    }

    @JsonIgnore
    public void setFileName(String fileName) {
        FileName = fileName;
    }

    @JsonIgnore
    public List<SavePtInfoReqBD02BFInfo> getBFInfo() {
        return BFInfo;
    }

    @JsonIgnore
    public void setBFInfo(List<SavePtInfoReqBD02BFInfo> BFInfo) {
        this.BFInfo = BFInfo;
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
    public SavePtInfoReqBD02.Customer getCustomer() {
        return Customer;
    }

    @JsonIgnore
    public void setCustomer(SavePtInfoReqBD02.Customer customer) {
        Customer = customer;
    }
}

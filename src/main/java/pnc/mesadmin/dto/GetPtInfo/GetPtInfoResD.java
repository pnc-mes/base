package pnc.mesadmin.dto.GetPtInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SavePtInfo.SavePtInfoReqBD00;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xfxi on 2017/5/27.
 */
public class GetPtInfoResD implements Serializable {

    @JsonProperty("PtRd")
    private int PtRd;

    @JsonProperty("PtName")
    private String PtName;

    @JsonProperty("FileName")
    private String FileName;

    @JsonProperty("BFInfo")
    private List<GetPtInfoResDBFInfo> BFInfo;

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

    @JsonProperty("Customer")
    private GetPtInfoResD.Customer Customer;

    public static class Customer {
        @JsonProperty("IsScript")
        private String IsScript;
        @JsonProperty("ScriptName")
        private String ScriptName;
        @JsonProperty("InParams")
        private List<GetPtInfoResD.InParams> InParams;
        @JsonProperty("OutParams")
        private List<GetPtInfoResD.OutParams> OutParams;

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
        public List<GetPtInfoResD.InParams> getInParams() {
            return InParams;
        }

        @JsonIgnore
        public void setInParams(List<GetPtInfoResD.InParams> inParams) {
            InParams = inParams;
        }

        @JsonIgnore
        public List<GetPtInfoResD.OutParams> getOutParams() {
            return OutParams;
        }

        @JsonIgnore
        public void setOutParams(List<GetPtInfoResD.OutParams> outParams) {
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
    public GetPtInfoResD.Customer getCustomer() {
        return Customer;
    }

    @JsonIgnore
    public void setCustomer(GetPtInfoResD.Customer customer) {
        Customer = customer;
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
    public List<GetPtInfoResDBFInfo> getBFInfo() {
        return BFInfo;
    }

    @JsonIgnore
    public void setBFInfo(List<GetPtInfoResDBFInfo> BFInfo) {
        this.BFInfo = BFInfo;
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
}

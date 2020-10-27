package pnc.mesadmin.dto.GetAllCheckTypeDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetCheckTypeInfoResponse {
    @JsonProperty("CTRd")
    private Integer CTRd;
    @JsonProperty("CheckTypeCode")
    private String CheckTypeCode;
    @JsonProperty("CheckTypeName")
    private String CheckTypeName;
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
    @JsonProperty("CTDtlInfo")
    private List<CTDtlInfo> CTDtlInfo;

    public static class CTDtlInfo {
        @JsonProperty("CheckTypeDtlRd")
        private Integer CheckTypeDtlRd;
        @JsonProperty("CheckItemCode")
        private String CheckItemCode;
        @JsonProperty("CheckItemName")
        private String CheckItemName;
        @JsonProperty("CheckItemC")
        private String CheckItemC;
        @JsonProperty("SureType")
        private String SureType;

        @JsonIgnore
        public Integer getCheckTypeDtlRd() {
            return CheckTypeDtlRd;
        }

        @JsonIgnore
        public void setCheckTypeDtlRd(Integer checkTypeDtlRd) {
            CheckTypeDtlRd = checkTypeDtlRd;
        }

        @JsonIgnore
        public String getCheckItemCode() {
            return CheckItemCode;
        }

        @JsonIgnore
        public void setCheckItemCode(String checkItemCode) {
            CheckItemCode = checkItemCode;
        }

        @JsonIgnore
        public String getCheckItemName() {
            return CheckItemName;
        }

        @JsonIgnore
        public void setCheckItemName(String checkItemName) {
            CheckItemName = checkItemName;
        }

        @JsonIgnore
        public String getCheckItemC() {
            return CheckItemC;
        }

        @JsonIgnore
        public void setCheckItemC(String checkItemC) {
            CheckItemC = checkItemC;
        }

        @JsonIgnore
        public String getSureType() {
            return SureType;
        }

        @JsonIgnore
        public void setSureType(String sureType) {
            SureType = sureType;
        }
    }

    @JsonIgnore
    public Integer getCTRd() {
        return CTRd;
    }

    @JsonIgnore
    public void setCTRd(Integer CTRd) {
        this.CTRd = CTRd;
    }

    @JsonIgnore
    public String getCheckTypeCode() {
        return CheckTypeCode;
    }

    @JsonIgnore
    public void setCheckTypeCode(String checkTypeCode) {
        CheckTypeCode = checkTypeCode;
    }

    @JsonIgnore
    public String getCheckTypeName() {
        return CheckTypeName;
    }

    @JsonIgnore
    public void setCheckTypeName(String checkTypeName) {
        CheckTypeName = checkTypeName;
    }

    @JsonIgnore
    public List<GetCheckTypeInfoResponse.CTDtlInfo> getCTDtlInfo() {
        return CTDtlInfo;
    }

    @JsonIgnore
    public void setCTDtlInfo(List<GetCheckTypeInfoResponse.CTDtlInfo> CTDtlInfo) {
        this.CTDtlInfo = CTDtlInfo;
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

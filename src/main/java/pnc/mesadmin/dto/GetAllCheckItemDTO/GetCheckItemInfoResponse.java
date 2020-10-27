package pnc.mesadmin.dto.GetAllCheckItemDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCheckItemInfoResponse {
    @JsonProperty("CIRd")
    private Integer CIRd;
    @JsonProperty("CheckItemCode")
    private String CheckItemCode;
    @JsonProperty("CheckItemName")
    private String CheckItemName;
    @JsonProperty("CheckItemC")
    private String CheckItemC;
    @JsonProperty("SureType")
    private String SureType;
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

    @JsonProperty("MethodInfo")
    private MethodInfo methodInfo;

    @JsonProperty("CMethodRd")
    private int CMethodRd;
    @JsonIgnore
    public Integer getCIRd() {
        return CIRd;
    }

    @JsonIgnore
    public void setCIRd(Integer CIRd) {
        this.CIRd = CIRd;
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
    public int getCMethodRd() {
        return CMethodRd;
    }
    @JsonIgnore
    public void setCMethodRd(int CMethodRd) {
        this.CMethodRd = CMethodRd;
    }
    @JsonIgnore
    public MethodInfo getMethodInfo() {
        return methodInfo;
    }
    @JsonIgnore
    public void setMethodInfo(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }
}

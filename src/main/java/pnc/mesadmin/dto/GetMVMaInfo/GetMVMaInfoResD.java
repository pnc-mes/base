package pnc.mesadmin.dto.GetMVMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
public class GetMVMaInfoResD {
    @JsonProperty("MVRd")
    private int MVRd;
    @JsonProperty("MVCode")
    private String MVCode;
    @JsonProperty("ExStatus")
    private String ExStatus;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("Execor")
    private String Execor;
    @JsonProperty("ExecTime")
    private String ExecTime;
    @JsonProperty("Cancelor")
    private String Cancelor;
    @JsonProperty("CancelTime")
    private String CancelTime;
    @JsonProperty("Finishor")
    private String Finishor;
    @JsonProperty("FinishTime")
    private String FinishTime;
    @JsonProperty("MVDtlInfo")
    private List<GetMVMaInfoResDMVDtl> MVDtlInfo;
    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public int getMVRd() {
        return MVRd;
    }
    @JsonIgnore
    public void setMVRd(int MVRd) {
        this.MVRd = MVRd;
    }
    @JsonIgnore
    public String getMVCode() {
        return MVCode;
    }
    @JsonIgnore
    public void setMVCode(String MVCode) {
        this.MVCode = MVCode;
    }
    @JsonIgnore
    public String getExStatus() {
        return ExStatus;
    }
    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
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
    public String getExecor() {
        return Execor;
    }
    @JsonIgnore
    public void setExecor(String execor) {
        Execor = execor;
    }
    @JsonIgnore
    public String getExecTime() {
        return ExecTime;
    }
    @JsonIgnore
    public void setExecTime(String execTime) {
        ExecTime = execTime;
    }
    @JsonIgnore
    public String getCancelor() {
        return Cancelor;
    }
    @JsonIgnore
    public void setCancelor(String cancelor) {
        Cancelor = cancelor;
    }
    @JsonIgnore
    public String getCancelTime() {
        return CancelTime;
    }
    @JsonIgnore
    public void setCancelTime(String cancelTime) {
        CancelTime = cancelTime;
    }
    @JsonIgnore
    public String getFinishor() {
        return Finishor;
    }
    @JsonIgnore
    public void setFinishor(String finishor) {
        Finishor = finishor;
    }
    @JsonIgnore
    public String getFinishTime() {
        return FinishTime;
    }
    @JsonIgnore
    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }
    @JsonIgnore
    public List<GetMVMaInfoResDMVDtl> getMVDtlInfo() {
        return MVDtlInfo;
    }
    @JsonIgnore
    public void setMVDtlInfo(List<GetMVMaInfoResDMVDtl> MVDtlInfo) {
        this.MVDtlInfo = MVDtlInfo;
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

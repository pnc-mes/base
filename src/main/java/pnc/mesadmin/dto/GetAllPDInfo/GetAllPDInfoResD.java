package pnc.mesadmin.dto.GetAllPDInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点单列表信息DTO返回业务实体类data
 * 创建人：刘福志
 * 创建时间：2017/6/10
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllPDInfoResD implements Serializable{

    @JsonProperty("PDRd")
    private int PDRd;

    @JsonProperty("PDCode")
    private String PDCode;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("ExStatus")
    private String ExStatus;

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

    @JsonIgnore
    public String getExStatus() {
        return ExStatus;
    }

    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
    }

    @JsonIgnore
    public int getPDRd() {
        return PDRd;
    }

    @JsonIgnore
    public void setPDRd(int PDRd) {
        this.PDRd = PDRd;
    }

    @JsonIgnore
    public String getPDCode() {
        return PDCode;
    }

    @JsonIgnore
    public void setPDCode(String PDCode) {
        this.PDCode = PDCode;
    }

    @JsonIgnore
    public String getStoreName() {
        return StoreName;
    }

    @JsonIgnore
    public void setStoreName(String storeName) {
        StoreName = storeName;
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
    public String getCancelTime() {
        return CancelTime;
    }

    @JsonIgnore
    public void setCancelTime(String cancelTime) {
        CancelTime = cancelTime;
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
    public String getCancelor() {
        return Cancelor;
    }

    @JsonIgnore
    public void setCancelor(String cancelor) {
        Cancelor = cancelor;
    }

    @JsonIgnore
    public String getFinishor() {
        return Finishor;
    }

    @JsonIgnore
    public void setFinishor(String finishor) {
        Finishor = finishor;
    }
}

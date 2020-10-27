package pnc.mesadmin.dto.GetAllRKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：  获取入库单列表信息DTO返回业务数据实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public class GetAllRKInfoResD implements Serializable{

    @JsonProperty("RkRd")
    private int RkRd;

    @JsonProperty("RkCode")
    private String RkCode;

    @JsonProperty("AssCode")
    private String AssCode;

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

    @JsonProperty("ExStatus")
    private String ExStatus;

    @JsonIgnore
    public String getExStatus() {
        return ExStatus;
    }

    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
    }

    @JsonIgnore
    public int getRkRd() {
        return RkRd;
    }

    @JsonIgnore
    public void setRkRd(int rkRd) {
        RkRd = rkRd;
    }

    @JsonIgnore
    public String getRkCode() {
        return RkCode;
    }

    @JsonIgnore
    public void setRkCode(String rkCode) {
        RkCode = rkCode;
    }

    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }

    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
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
}

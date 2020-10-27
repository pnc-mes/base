package pnc.mesadmin.dto.GetAllCKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetAllCKInfoResD{
    @JsonProperty("CkRd")
    private int CkRd;
    @JsonProperty("CkCode")
    private String CkCode;
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
    public int getCkRd() {
        return CkRd;
    }
    @JsonIgnore
    public void setCkRd(int ckRd) {
        CkRd = ckRd;
    }
    @JsonIgnore
    public String getCkCode() {
        return CkCode;
    }
    @JsonIgnore
    public void setCkCode(String ckCode) {
        CkCode = ckCode;
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
    @JsonIgnore
    public String getExStatus() {
        return ExStatus;
    }
    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
    }
}

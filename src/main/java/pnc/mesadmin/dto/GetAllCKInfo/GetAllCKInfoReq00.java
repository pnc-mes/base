package pnc.mesadmin.dto.GetAllCKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetAllCKInfoReq00 {
    @JsonProperty("CkTCode")
    private String CkTCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("ExecTime")
    private String ExecTime;

    @JsonProperty("ExecTime1")
    private String ExecTime1;


    @JsonIgnore
    public String getCkTCode() {
        return CkTCode;
    }

    @JsonIgnore
    public void setCkTCode(String ckTCode) {
        CkTCode = ckTCode;
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
    public String getExecTime() {
        return ExecTime;
    }

    @JsonIgnore
    public void setExecTime(String execTime) {
        ExecTime = execTime;
    }

    @JsonIgnore
    public String getExecTime1() {
        return ExecTime1;
    }

    @JsonIgnore
    public void setExecTime1(String execTime1) {
        ExecTime1 = execTime1;
    }
}

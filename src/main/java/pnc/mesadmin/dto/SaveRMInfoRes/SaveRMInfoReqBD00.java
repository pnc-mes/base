package pnc.mesadmin.dto.SaveRMInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/9/21.
 */
public class SaveRMInfoReqBD00 implements Serializable {

    @JsonProperty("RetCode")
    private String RetCode;
    @JsonProperty("AssCode")
    private String AssCode;
    @JsonProperty("AssSource")
    private String AssSource;
    @JsonProperty("RetDlInfo")
    private List<SaveRMInfoReqBD00RetDlInfo> RetDlInfo;

    @JsonIgnore
    public String getRetCode() {
        return RetCode;
    }
    @JsonIgnore
    public void setRetCode(String retCode) {
        RetCode = retCode;
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
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }
    @JsonIgnore
    public List<SaveRMInfoReqBD00RetDlInfo> getRetDlInfo() {
        return RetDlInfo;
    }
    @JsonIgnore
    public void setRetDlInfo(List<SaveRMInfoReqBD00RetDlInfo> retDlInfo) {
        RetDlInfo = retDlInfo;
    }
}

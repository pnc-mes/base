package pnc.mesadmin.dto.SaveRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public class SaveRMaNInfoReq00 {
    @JsonProperty("RetCode")
    private String RetCode;
    @JsonProperty("AssCode")
    private String AssCode;
    @JsonProperty("AssSource")
    private String AssSource;
    @JsonProperty("RMaNDlInfo")
    private List<SaveRMaNInfoReq00RMaNDl> RMaNDlInfo;
    @JsonIgnore
    public List<SaveRMaNInfoReq00RMaNDl> getRMaNDlInfo() {
        return RMaNDlInfo;
    }
    @JsonIgnore
    public void setRMaNDlInfo(List<SaveRMaNInfoReq00RMaNDl> RMaNDlInfo) {
        this.RMaNDlInfo = RMaNDlInfo;
    }

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
}

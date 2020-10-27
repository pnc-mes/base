package pnc.mesadmin.dto.SaveIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/6/12.
 */
public class SaveIQCBInfoReqBD00 {
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("CkResult")
    private String CkResult;
    @JsonProperty("BadInfo")
    private List<SaveIQCBInfoReqBD00Badinfo> BadInfo;
    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
    @JsonIgnore
    public String getCkResult() {
        return CkResult;
    }
    @JsonIgnore
    public void setCkResult(String ckResult) {
        CkResult = ckResult;
    }
    @JsonIgnore
    public List<SaveIQCBInfoReqBD00Badinfo> getBadInfo() {
        return BadInfo;
    }
    @JsonIgnore
    public void setBadInfo(List<SaveIQCBInfoReqBD00Badinfo> badInfo) {
        BadInfo = badInfo;
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

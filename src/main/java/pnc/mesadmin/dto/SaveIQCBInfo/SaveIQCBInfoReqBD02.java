package pnc.mesadmin.dto.SaveIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/6/12.
 */
public class SaveIQCBInfoReqBD02 {
    @JsonProperty("IQCRd")
    private int IQCRd;
    @JsonProperty("CkResult")
    private String CkResult;
    @JsonProperty("BadInfo")
    private List<SaveIQCBInfoReqBD02BadInfo> BadInfo;
    @JsonProperty("CentInfo")
    private List<SaveIQCBInfoReqBD02CentInfo> CentInfo;
    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public int getIQCRd() {
        return IQCRd;
    }
    @JsonIgnore
    public void setIQCRd(int IQCRd) {
        this.IQCRd = IQCRd;
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
    public List<SaveIQCBInfoReqBD02BadInfo> getBadInfo() {
        return BadInfo;
    }
    @JsonIgnore
    public void setBadInfo(List<SaveIQCBInfoReqBD02BadInfo> badInfo) {
        BadInfo = badInfo;
    }
    @JsonIgnore
    public List<SaveIQCBInfoReqBD02CentInfo> getCentInfo() {
        return CentInfo;
    }
    @JsonIgnore
    public void setCentInfo(List<SaveIQCBInfoReqBD02CentInfo> centInfo) {
        CentInfo = centInfo;
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

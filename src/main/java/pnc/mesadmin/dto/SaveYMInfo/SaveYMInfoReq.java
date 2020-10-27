package pnc.mesadmin.dto.SaveYMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by zhaochao on 11/6 0006.
 */
public class SaveYMInfoReq {
    @JsonProperty("DPType")
    private String DPType;
    @JsonProperty("MOWoCode")
    private String MOWoCode;
    @JsonProperty("MIWoCode")
    private String MIWoCode;
    @JsonProperty("BaInfo")
    private List<SaveYMInfoReqBaInfo> BaInfo;
    @JsonIgnore
    public String getDPType() {
        return DPType;
    }
    @JsonIgnore
    public void setDPType(String DPType) {
        this.DPType = DPType;
    }
    @JsonIgnore
    public String getMOWoCode() {
        return MOWoCode;
    }
    @JsonIgnore
    public void setMOWoCode(String MOWoCode) {
        this.MOWoCode = MOWoCode;
    }
    @JsonIgnore
    public String getMIWoCode() {
        return MIWoCode;
    }
    @JsonIgnore
    public void setMIWoCode(String MIWoCode) {
        this.MIWoCode = MIWoCode;
    }
    @JsonIgnore
    public List<SaveYMInfoReqBaInfo> getBaInfo() {
        return BaInfo;
    }
    @JsonIgnore
    public void setBaInfo(List<SaveYMInfoReqBaInfo> baInfo) {
        BaInfo = baInfo;
    }
}

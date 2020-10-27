package pnc.mesadmin.dto.GetAllIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllIChInfoResD {
    @JsonProperty("InCRd")
    private int InCRd;
    @JsonProperty("InCCode")
    private String InCCode;
    @JsonProperty("PurChCode")
    private String PurChCode;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public int getInCRd() {
        return InCRd;
    }
    @JsonIgnore
    public void setInCRd(int inCRd) {
        InCRd = inCRd;
    }
    @JsonIgnore
    public String getInCCode() {
        return InCCode;
    }
    @JsonIgnore
    public void setInCCode(String inCCode) {
        InCCode = inCCode;
    }
    @JsonIgnore
    public String getPurChCode() {
        return PurChCode;
    }
    @JsonIgnore
    public void setPurChCode(String purChCode) {
        PurChCode = purChCode;
    }
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
}

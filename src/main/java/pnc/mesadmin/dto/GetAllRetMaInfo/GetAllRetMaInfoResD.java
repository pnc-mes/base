package pnc.mesadmin.dto.GetAllRetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/21.
 */
public class GetAllRetMaInfoResD implements Serializable{
    @JsonProperty("RetRd")
    private int RetRd;

    @JsonProperty("RetCode")
    private String RetCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("ExStatus")
    private String ExStatus;

    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }
    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
    }

    public String getExStatus() {
        return ExStatus;
    }
    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public int getRetRd() {
        return RetRd;
    }
    @JsonIgnore
    public void setRetRd(int retRd) {
        RetRd = retRd;
    }
    @JsonIgnore
    public String getRetCode() {
        return RetCode;
    }
    @JsonIgnore
    public void setRetCode(String retCode) {
        RetCode = retCode;
    }
}

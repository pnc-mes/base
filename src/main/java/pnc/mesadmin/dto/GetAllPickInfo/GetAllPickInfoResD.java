package pnc.mesadmin.dto.GetAllPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetAllPickInfoResD {
    @JsonProperty("PickRd")
    private int PickRd;

    @JsonProperty("PickCode")
    private String PickCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("ExStatus")
    private String ExStatus;

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
    public String getAssCode() {
        return AssCode;
    }
    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
    }
    @JsonIgnore
    public String getExStatus() {
        return ExStatus;
    }
    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
    }

    @JsonIgnore
    public int getPickRd() {
        return PickRd;
    }
    @JsonIgnore
    public void setPickRd(int pickRd) {
        PickRd = pickRd;
    }
    @JsonIgnore
    public String getPickCode() {
        return PickCode;
    }
    @JsonIgnore
    public void setPickCode(String pickCode) {
        PickCode = pickCode;
    }
}

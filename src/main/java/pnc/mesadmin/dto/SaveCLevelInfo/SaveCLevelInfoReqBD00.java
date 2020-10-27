package pnc.mesadmin.dto.SaveCLevelInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class SaveCLevelInfoReqBD00 implements Serializable {

    @JsonProperty("CheckLevelCode")
    private String CheckLevelCode;

    @JsonProperty("CheckLevelName")
    private String CheckLevelName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getCheckLevelCode() {
        return CheckLevelCode;
    }
    @JsonIgnore
    public void setCheckLevelCode(String checkLevelCode) {
        CheckLevelCode = checkLevelCode;
    }
    @JsonIgnore
    public String getCheckLevelName() {
        return CheckLevelName;
    }
    @JsonIgnore
    public void setCheckLevelName(String checkLevelName) {
        CheckLevelName = checkLevelName;
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

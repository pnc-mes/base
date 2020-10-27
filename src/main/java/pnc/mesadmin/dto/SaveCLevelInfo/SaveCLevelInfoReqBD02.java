package pnc.mesadmin.dto.SaveCLevelInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class SaveCLevelInfoReqBD02 implements Serializable {

    @JsonProperty("CLevelRd")
    private int CLevelRd;

    @JsonProperty("CheckLevelCode")
    private String CheckLevelCode;

    @JsonProperty("CheckLevelName")
    private String CheckLevelName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore

    public int getCLevelRd() {
        return CLevelRd;
    }
    @JsonIgnore
    public void setCLevelRd(int CLevelRd) {
        this.CLevelRd = CLevelRd;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
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
}

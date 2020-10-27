package pnc.mesadmin.dto.SaveSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2017/8/16.
 */
public class SaveSGInfoReq02 implements Serializable {
    @JsonProperty("SGRd")
    private int SGRd;
    @JsonProperty("SGName")
    private String SGName;
    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public int getSGRd() {
        return SGRd;
    }
    @JsonIgnore
    public void setSGRd(int SGRd) {
        this.SGRd = SGRd;
    }
    @JsonIgnore
    public String getSGName() {
        return SGName;
    }
    @JsonIgnore
    public void setSGName(String SGName) {
        this.SGName = SGName;
    }
    @JsonProperty("SkillInfo")
    private List<SaveSGInfoReq02Skill> SkillInfo;

    @JsonIgnore
    public List<SaveSGInfoReq02Skill> getSkillInfo() {
        return SkillInfo;
    }
    @JsonIgnore
    public void setSkillInfo(List<SaveSGInfoReq02Skill> skillInfo) {
        SkillInfo = skillInfo;
    }
}

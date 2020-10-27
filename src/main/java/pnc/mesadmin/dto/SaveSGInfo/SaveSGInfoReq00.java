package pnc.mesadmin.dto.SaveSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2017/8/16.
 */
public class SaveSGInfoReq00  implements Serializable {
    @JsonProperty("SGName")
    private String SGName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("SkillInfo")
    private List<SaveSGInfoReq00Skill> SkillInfo;

    @JsonIgnore
    public String getSGName() {
        return SGName;
    }
    @JsonIgnore
    public void setSGName(String SGName) {
        this.SGName = SGName;
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
    public List<SaveSGInfoReq00Skill> getSkillInfo() {
        return SkillInfo;
    }
    @JsonIgnore
    public void setSkillInfo(List<SaveSGInfoReq00Skill> skillInfo) {
        SkillInfo = skillInfo;
    }
}

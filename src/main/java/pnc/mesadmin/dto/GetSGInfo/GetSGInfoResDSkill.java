package pnc.mesadmin.dto.GetSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/16.
 */
public class GetSGInfoResDSkill {
    @JsonProperty("SkillRd")
    private int SkillRd;

    @JsonProperty("SkillName")
    private String SkillName;

    @JsonIgnore
    public int getSkillRd() {
        return SkillRd;
    }

    @JsonIgnore
    public void setSkillRd(int skillRd) {
        SkillRd = skillRd;
    }

    @JsonIgnore
    public String getSkillName() {
        return SkillName;
    }

    @JsonIgnore
    public void setSkillName(String skillName) {
        SkillName = skillName;
    }
}

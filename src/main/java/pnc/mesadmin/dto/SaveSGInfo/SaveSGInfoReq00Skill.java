package pnc.mesadmin.dto.SaveSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/16.
 */
public class SaveSGInfoReq00Skill{
    @JsonProperty("SkillRd")
    private int SkillRd;
    @JsonIgnore
    public int getSkillRd() {
        return SkillRd;
    }
    @JsonIgnore
    public void setSkillRd(int skillRd) {
        SkillRd = skillRd;
    }
}

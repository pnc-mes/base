package pnc.mesadmin.dto.GetSVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/22.
 */
public class GetSVInfoResDSkillG {
    @JsonProperty("SkillGRd")
    private int SkillGRd;

    @JsonProperty("SkillGName")
    private String SkillGName;
    @JsonIgnore
    public int getSkillGRd() {
        return SkillGRd;
    }
    @JsonIgnore
    public void setSkillGRd(int skillGRd) {
        SkillGRd = skillGRd;
    }
    @JsonIgnore
    public String getSkillGName() {
        return SkillGName;
    }
    @JsonIgnore
    public void setSkillGName(String skillGName) {
        SkillGName = skillGName;
    }
}

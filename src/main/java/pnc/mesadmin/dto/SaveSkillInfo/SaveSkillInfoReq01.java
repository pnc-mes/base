package pnc.mesadmin.dto.SaveSkillInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/8/16.
 */
public class SaveSkillInfoReq01 implements Serializable {
    @JsonProperty("SkillRd")
    public int SkillRd;

    @JsonIgnore
    public int getSkillRd() {
        return SkillRd;
    }

    @JsonIgnore
    public void setSkillRd(int SkillRd) {
        this.SkillRd = SkillRd;
    }
}

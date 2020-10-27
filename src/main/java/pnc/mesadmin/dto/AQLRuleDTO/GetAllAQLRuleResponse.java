package pnc.mesadmin.dto.AQLRuleDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-22
 **/
public class GetAllAQLRuleResponse {
    @JsonProperty(value = "AQLRuleRd")
    private Integer AQLRuleRd;
    @JsonProperty(value = "AQLRuleName")
    private String AQLRuleName;

    @JsonIgnore
    public Integer getAQLRuleRd() {
        return AQLRuleRd;
    }

    @JsonIgnore
    public void setAQLRuleRd(Integer AQLRuleRd) {
        this.AQLRuleRd = AQLRuleRd;
    }

    @JsonIgnore
    public String getAQLRuleName() {
        return AQLRuleName;
    }

    @JsonIgnore
    public void setAQLRuleName(String AQLRuleName) {
        this.AQLRuleName = AQLRuleName;
    }
}

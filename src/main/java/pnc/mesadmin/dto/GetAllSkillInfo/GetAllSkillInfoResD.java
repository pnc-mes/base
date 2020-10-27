package pnc.mesadmin.dto.GetAllSkillInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：序号管理DTO返回业务数据实体类Data
 * 创建人：ZC
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
public class GetAllSkillInfoResD implements Serializable{

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

    public String getSkillName() {
        return SkillName;
    }

    public void setSkillName(String skillName) {
        SkillName = skillName;
    }
}

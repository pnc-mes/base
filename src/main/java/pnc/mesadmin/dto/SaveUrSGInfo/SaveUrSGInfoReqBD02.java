package pnc.mesadmin.dto.SaveUrSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存技能培训信息DTO请求业务数据实体类BD02：编辑
 * 创建人：刘福志
 * 创建时间：2017-8-28
 * 修改人：
 * 修改时间：
 */
public class SaveUrSGInfoReqBD02 implements Serializable {
    @JsonProperty("UserRd")
    private int UserRd;

    @JsonProperty("SkillRd")
    private int SkillRd;

    @JsonProperty("SVPDate")
    private String SVPDate;

    @JsonProperty("EVPDate")
    private String EVPDate;

    @JsonProperty("IsPass")
    private String IsPass;

    @JsonIgnore
    public int getUserRd() {
        return UserRd;
    }

    @JsonIgnore
    public void setUserRd(int userRd) {
        UserRd = userRd;
    }

    @JsonIgnore
    public int getSkillRd() {
        return SkillRd;
    }

    @JsonIgnore
    public void setSkillRd(int skillRd) {
        SkillRd = skillRd;
    }

    @JsonIgnore
    public String getSVPDate() {
        return SVPDate;
    }

    @JsonIgnore
    public void setSVPDate(String SVPDate) {
        this.SVPDate = SVPDate;
    }

    @JsonIgnore
    public String getEVPDate() {
        return EVPDate;
    }

    @JsonIgnore
    public void setEVPDate(String EVPDate) {
        this.EVPDate = EVPDate;
    }

    @JsonIgnore
    public String getIsPass() {
        return IsPass;
    }

    @JsonIgnore
    public void setIsPass(String isPass) {
        IsPass = isPass;
    }
}

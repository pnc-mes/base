package pnc.mesadmin.dto.GetSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PNC on 2017/8/16.
 */
public class GetSGInfoResD implements Serializable {
    @JsonProperty("SGRd")
    private int SGRd;

    @JsonProperty("SGName")
    private String SGName;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("SkillInfo")
    private List<GetSGInfoResDSkill> SkillInfo;

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

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }

    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
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
    public String getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    @JsonIgnore
    public List<GetSGInfoResDSkill> getSkillInfo() {
        return SkillInfo;
    }

    @JsonIgnore
    public void setSkillInfo(List<GetSGInfoResDSkill> skillInfo) {
        SkillInfo = skillInfo;
    }
}

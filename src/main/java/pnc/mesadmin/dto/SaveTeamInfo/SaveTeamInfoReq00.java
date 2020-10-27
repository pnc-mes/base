package pnc.mesadmin.dto.SaveTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 10:26
 * @Description:
 */
public class SaveTeamInfoReq00 {
    @JsonProperty("TeamName")
    private String TeamName;

    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("TeamMember")
    private List<SaveTeamInfoReq00TeamMember> TeamMember;
    @JsonProperty("LineInfo")
    private List<SaveTeamInfoReq00TeamLineInfo> LineInfo;
    @JsonIgnore
    public String getTeamName() {
        return TeamName;
    }
    @JsonIgnore
    public void setTeamName(String teamName) {
        TeamName = teamName;
    }
    @JsonIgnore
    public String getDescription() {
        return Description;
    }
    @JsonIgnore
    public void setDescription(String description) {
        Description = description;
    }
    @JsonIgnore
    public List<SaveTeamInfoReq00TeamMember> getTeamMember() {
        return TeamMember;
    }
    @JsonIgnore
    public void setTeamMember(List<SaveTeamInfoReq00TeamMember> teamMember) {
        TeamMember = teamMember;
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
    public List<SaveTeamInfoReq00TeamLineInfo> getLineInfo() {
        return LineInfo;
    }
    @JsonIgnore
    public void setLineInfo(List<SaveTeamInfoReq00TeamLineInfo> lineInfo) {
        LineInfo = lineInfo;
    }
}

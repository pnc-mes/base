package pnc.mesadmin.dto.GetTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:31
 * @Description:
 */
public class GetTeamInfoResD {
    @JsonProperty("TeamRd")
    private int TeamRd;
    @JsonProperty("TeamName")
    private String TeamName;
    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("TeamMember")
    List<GetTeamInfoResDTeamMember> TeamMember;
    @JsonProperty("LineInfo")
    List<GetTeamInfoResDTeamLineInfo> LineInfo;

    @JsonIgnore
    public int getTeamRd() {
        return TeamRd;
    }
    @JsonIgnore
    public void setTeamRd(int teamRd) {
        TeamRd = teamRd;
    }
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
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public List<GetTeamInfoResDTeamMember> getTeamMember() {
        return TeamMember;
    }
    @JsonIgnore
    public void setTeamMember(List<GetTeamInfoResDTeamMember> teamMember) {
        TeamMember = teamMember;
    }
    @JsonIgnore
    public List<GetTeamInfoResDTeamLineInfo> getLineInfo() {
        return LineInfo;
    }
    @JsonIgnore
    public void setLineInfo(List<GetTeamInfoResDTeamLineInfo> lineInfo) {
        LineInfo = lineInfo;
    }
}

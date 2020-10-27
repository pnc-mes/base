package pnc.mesadmin.dto.GetSkillInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：
 * 创建人：ZC
 * 创建时间：2017-06-07
 * 修改人：
 * 修改时间：
 */
public class GetSkillInfoResD implements Serializable{

    @JsonProperty("SkillRd")
    private int SkillRd;

    @JsonProperty("SkillName")
    private String SkillName;

    /*@JsonProperty("StartDate")
    private String StartDate;

    @JsonProperty("EndDate")
    private String EndDate;*/

    @JsonProperty("VPDate")
    private int VPDate;

    @JsonProperty("Status")
    private String Status;

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
    @JsonIgnore
    public int getVPDate() {
        return VPDate;
    }
    @JsonIgnore
    public void setVPDate(int VPDate) {
        this.VPDate = VPDate;
    }
    /*@JsonIgnore
    public String getStartDate() {
        return StartDate;
    }
    @JsonIgnore
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }
    @JsonIgnore
    public String getEndDate() {
        return EndDate;
    }
    @JsonIgnore
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }*/
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
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
}

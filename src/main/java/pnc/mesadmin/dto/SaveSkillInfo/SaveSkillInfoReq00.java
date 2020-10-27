package pnc.mesadmin.dto.SaveSkillInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/8/16.
 */
public class SaveSkillInfoReq00 implements Serializable{
    @JsonProperty("SkillName")
    public String SkillName;

    @JsonProperty("VPDate")
    public int VPDate;

    @JsonProperty("Status")
    public String Status;

    @JsonProperty("Remark")
    public String Remark;
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
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
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

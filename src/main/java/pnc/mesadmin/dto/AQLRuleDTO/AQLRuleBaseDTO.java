package pnc.mesadmin.dto.AQLRuleDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-22
 **/
public class AQLRuleBaseDTO {
    @JsonProperty(value = "AQLRuleRd")
    private Integer AQLRuleRd;
    @JsonProperty(value = "AQLRuleName")
    private String AQLRuleName;
    @JsonProperty("CLevelRd")
    private Integer CLevelRd;
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
    @JsonProperty("CheckLevel")
    private CheckLevel CheckLevel;
    @JsonProperty("AQLDtlInfo")
    private List<AQLDtlInfo> AQLDtlInfo;

    public static class CheckLevel {
        @JsonProperty("CLevelRd")
        private Integer CLevelRd;
        @JsonProperty("CheckLevelName")
        private String CheckLevelName;

        @JsonIgnore
        public Integer getCLevelRd() {
            return CLevelRd;
        }

        @JsonIgnore
        public void setCLevelRd(Integer CLevelRd) {
            this.CLevelRd = CLevelRd;
        }

        @JsonIgnore
        public String getCheckLevelName() {
            return CheckLevelName;
        }

        @JsonIgnore
        public void setCheckLevelName(String checkLevelName) {
            CheckLevelName = checkLevelName;
        }
    }

    public static class AQLDtlInfo {
        @JsonProperty("MinNum")
        private Float MinNum;
        @JsonProperty("MaxNum")
        private Float MaxNum;
        @JsonProperty("SamplingNum")
        private Float SamplingNum;
        @JsonProperty("AcceptNum")
        private Float AcceptNum;
        @JsonProperty("RejectNum")
        private Float RejectNum;

        @JsonIgnore
        public Float getMinNum() {
            return MinNum;
        }

        @JsonIgnore
        public void setMinNum(Float minNum) {
            MinNum = minNum;
        }

        @JsonIgnore
        public Float getMaxNum() {
            return MaxNum;
        }

        @JsonIgnore
        public void setMaxNum(Float maxNum) {
            MaxNum = maxNum;
        }

        @JsonIgnore
        public Float getSamplingNum() {
            return SamplingNum;
        }

        @JsonIgnore
        public void setSamplingNum(Float samplingNum) {
            SamplingNum = samplingNum;
        }

        @JsonIgnore
        public Float getAcceptNum() {
            return AcceptNum;
        }

        @JsonIgnore
        public void setAcceptNum(Float acceptNum) {
            AcceptNum = acceptNum;
        }

        @JsonIgnore
        public Float getRejectNum() {
            return RejectNum;
        }

        @JsonIgnore
        public void setRejectNum(Float rejectNum) {
            RejectNum = rejectNum;
        }
    }


    @JsonIgnore
    public Integer getCLevelRd() {
        return CLevelRd;
    }

    @JsonIgnore
    public void setCLevelRd(Integer CLevelRd) {
        this.CLevelRd = CLevelRd;
    }

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
    public AQLRuleBaseDTO.CheckLevel getCheckLevel() {
        return CheckLevel;
    }

    @JsonIgnore
    public void setCheckLevel(AQLRuleBaseDTO.CheckLevel checkLevel) {
        CheckLevel = checkLevel;
    }

    @JsonIgnore
    public List<AQLRuleBaseDTO.AQLDtlInfo> getAQLDtlInfo() {
        return AQLDtlInfo;
    }

    @JsonIgnore
    public void setAQLDtlInfo(List<AQLRuleBaseDTO.AQLDtlInfo> AQLDtlInfo) {
        this.AQLDtlInfo = AQLDtlInfo;
    }
}

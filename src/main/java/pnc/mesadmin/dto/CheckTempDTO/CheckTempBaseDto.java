package pnc.mesadmin.dto.CheckTempDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public class CheckTempBaseDto {
    @JsonProperty(value = "CPTRd")
    private Integer CPTRd;
    @JsonProperty(value = "CheckTempName")
    private String CheckTempName;
    @JsonProperty(value = "Status")
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
    @JsonProperty("CPTInfo")
    private List<CPTInfo> CPTInfo;

    public static class CPTInfo {
        @JsonProperty(value = "RelRd")
        private Integer RelRd;
        @JsonProperty(value = "RelType")
        private String RelType;
        @JsonProperty(value = "RelName")
        private String RelName;

        @JsonIgnore
        public Integer getRelRd() {
            return RelRd;
        }

        @JsonIgnore
        public void setRelRd(Integer relRd) {
            RelRd = relRd;
        }

        @JsonIgnore
        public String getRelType() {
            return RelType;
        }

        @JsonIgnore
        public void setRelType(String relType) {
            RelType = relType;
        }

        @JsonIgnore
        public String getRelName() {
            return RelName;
        }

        @JsonIgnore
        public void setRelName(String relName) {
            RelName = relName;
        }
    }

    @JsonIgnore
    public Integer getCPTRd() {
        return CPTRd;
    }

    @JsonIgnore
    public void setCPTRd(Integer CPTRd) {
        this.CPTRd = CPTRd;
    }

    @JsonIgnore
    public String getCheckTempName() {
        return CheckTempName;
    }

    @JsonIgnore
    public void setCheckTempName(String checkTempName) {
        CheckTempName = checkTempName;
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
    public List<CheckTempBaseDto.CPTInfo> getCPTInfo() {
        return CPTInfo;
    }

    @JsonIgnore
    public void setCPTInfo(List<CheckTempBaseDto.CPTInfo> CPTInfo) {
        this.CPTInfo = CPTInfo;
    }
}

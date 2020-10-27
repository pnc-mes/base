package pnc.mesadmin.dto.GetAllBadInfoDto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class GetBadInfoResB implements Serializable {

    @JsonProperty("BadRd")
    private Integer BadRd;
    @JsonProperty("BadTypeName")
    private String BadTypeName;
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
    @JsonProperty("BadDtlInfo")
    private List<BadDtlInfo> BadDtlInfo;

    public static class BadDtlInfo {
        @JsonProperty("BadDtlRd")
        private Integer BadDtlRd;
        @JsonProperty("BadCode")
        private String BadCode;
        @JsonProperty("BadName")
        private String BadName;

        @JsonIgnore
        public Integer getBadDtlRd() {
            return BadDtlRd;
        }

        @JsonIgnore
        public void setBadDtlRd(Integer badDtlRd) {
            BadDtlRd = badDtlRd;
        }

        @JsonIgnore
        public String getBadCode() {
            return BadCode;
        }

        @JsonIgnore
        public void setBadCode(String badCode) {
            BadCode = badCode;
        }

        @JsonIgnore
        public String getBadName() {
            return BadName;
        }

        @JsonIgnore
        public void setBadName(String badName) {
            BadName = badName;
        }
    }

    @JsonIgnore
    public Integer getBadRd() {
        return BadRd;
    }

    @JsonIgnore
    public void setBadRd(Integer badRd) {
        BadRd = badRd;
    }

    @JsonIgnore
    public String getBadTypeName() {
        return BadTypeName;
    }

    @JsonIgnore
    public void setBadTypeName(String badTypeName) {
        BadTypeName = badTypeName;
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
    public List<GetBadInfoResB.BadDtlInfo> getBadDtlInfo() {
        return BadDtlInfo;
    }

    @JsonIgnore
    public void setBadDtlInfo(List<GetBadInfoResB.BadDtlInfo> badDtlInfo) {
        BadDtlInfo = badDtlInfo;
    }
}

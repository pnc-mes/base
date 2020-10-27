package pnc.mesadmin.dto.GetAllBadInfoDto;

import java.io.Serializable;
import java.util.List;

public class SaveBadInfoRes implements Serializable {
    @com.fasterxml.jackson.annotation.JsonProperty("BadRd")
    private String BadRd;


    @com.fasterxml.jackson.annotation.JsonProperty("BadTypeName")
    private String BadTypeName;
    @com.fasterxml.jackson.annotation.JsonProperty("Remark")
    private String Remark;
    @com.fasterxml.jackson.annotation.JsonProperty("BadDtlInfo")
    private List<BadDtlInfo> BadDtlInfo;

    public static class BadDtlInfo {
        @com.fasterxml.jackson.annotation.JsonProperty("BadCode")
        private String BadCode;
        @com.fasterxml.jackson.annotation.JsonProperty("BadName")
        private String BadName;
        @com.fasterxml.jackson.annotation.JsonProperty("BadDtlRd")
        private String BadDtlRd;

        @com.fasterxml.jackson.annotation.JsonIgnore
        public String getBadCode() {
            return BadCode;
        }
        @com.fasterxml.jackson.annotation.JsonIgnore
        public void setBadCode(String badCode) {
            BadCode = badCode;
        }
        @com.fasterxml.jackson.annotation.JsonIgnore
        public String getBadName() {
            return BadName;
        }
        @com.fasterxml.jackson.annotation.JsonIgnore
        public void setBadName(String badName) {
            BadName = badName;
        }
        @com.fasterxml.jackson.annotation.JsonIgnore
        public String getBadDtlRd() {
            return BadDtlRd;
        }
        @com.fasterxml.jackson.annotation.JsonIgnore
        public void setBadDtlRd(String badDtlRd) {
            BadDtlRd = badDtlRd;
        }
    }
    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getBadTypeName() {
        return BadTypeName;
    }
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setBadTypeName(String badTypeName) {
        BadTypeName = badTypeName;
    }
    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @com.fasterxml.jackson.annotation.JsonIgnore
    public List<SaveBadInfoRes.BadDtlInfo> getBadDtlInfo() {
        return BadDtlInfo;
    }
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setBadDtlInfo(List<SaveBadInfoRes.BadDtlInfo> badDtlInfo) {
        BadDtlInfo = badDtlInfo;
    }
    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getBadRd() {
        return BadRd;
    }
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setBadRd(String badRd) {
        BadRd = badRd;
    }
}

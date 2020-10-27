package pnc.mesadmin.dto.GetAllDotaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveDoCheckRes implements Serializable {
    @JsonProperty("DoCheckRd")
    private Integer DoCheckRd;
    @JsonProperty("CheckDtl")
    private List<CheckDtl> CheckDtl;

    public static class CheckDtl {
        @JsonProperty("DoCheckDtlRd")
        private Integer DoCheckDtlRd;
        @JsonProperty("DoCheckResult")
        private String DoCheckResult;

        @JsonIgnore
        public Integer getDoCheckDtlRd() {
            return DoCheckDtlRd;
        }

        @JsonIgnore
        public void setDoCheckDtlRd(Integer doCheckDtlRd) {
            DoCheckDtlRd = doCheckDtlRd;
        }

        @JsonIgnore
        public String getDoCheckResult() {
            return DoCheckResult;
        }

        @JsonIgnore
        public void setDoCheckResult(String doCheckResult) {
            DoCheckResult = doCheckResult;
        }
    }

    @JsonIgnore
    public Integer getDoCheckRd() {
        return DoCheckRd;
    }

    @JsonIgnore
    public void setDoCheckRd(Integer doCheckRd) {
        DoCheckRd = doCheckRd;
    }

    @JsonIgnore
    public List<SaveDoCheckRes.CheckDtl> getCheckDtl() {
        return CheckDtl;
    }

    @JsonIgnore
    public void setCheckDtl(List<SaveDoCheckRes.CheckDtl> checkDtl) {
        CheckDtl = checkDtl;
    }
}

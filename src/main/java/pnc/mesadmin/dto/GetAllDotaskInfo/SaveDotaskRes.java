package pnc.mesadmin.dto.GetAllDotaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class SaveDotaskRes implements Serializable {
    @JsonProperty("DoPMRd")
    private Integer DoPMRd;
    @JsonProperty("PMDtl")
    private List<PMDtl> PMDtl;

    public static class PMDtl {
        @JsonProperty("DoPMDtlRd")
        private Integer DoPMDtlRd;
        @JsonProperty("DoPMResult")
        private String DoPMResult;

        @JsonIgnore

        public Integer getDoPMDtlRd() {
            return DoPMDtlRd;
        }

        @JsonIgnore
        public void setDoPMDtlRd(Integer doPMDtlRd) {
            DoPMDtlRd = doPMDtlRd;
        }

        @JsonIgnore
        public String getDoPMResult() {
            return DoPMResult;
        }

        @JsonIgnore
        public void setDoPMResult(String doPMResult) {
            DoPMResult = doPMResult;
        }
    }

    @JsonIgnore
    public Integer getDoPMRd() {
        return DoPMRd;
    }

    @JsonIgnore
    public void setDoPMRd(Integer doPMRd) {
        DoPMRd = doPMRd;
    }

    @JsonIgnore
    public List<SaveDotaskRes.PMDtl> getPMDtl() {
        return PMDtl;
    }

    @JsonIgnore
    public void setPMDtl(List<SaveDotaskRes.PMDtl> PMDtl) {
        this.PMDtl = PMDtl;
    }
}

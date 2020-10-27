package pnc.mesadmin.dto.GetCKBarInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKBarInfoReq00 {
    @JsonProperty("CkDtlRd")
    private int CkDtlRd;
    @JsonProperty("AssSource")
    private String AssSource;
    @JsonIgnore
    public int getCkDtlRd() {
        return CkDtlRd;
    }
    @JsonIgnore
    public void setCkDtlRd(int ckDtlRd) {
        CkDtlRd = ckDtlRd;
    }
    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }
}

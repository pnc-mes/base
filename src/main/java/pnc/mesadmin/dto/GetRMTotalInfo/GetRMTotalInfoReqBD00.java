package pnc.mesadmin.dto.GetRMTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/21.
 */
public class GetRMTotalInfoReqBD00 implements Serializable{
    @JsonProperty("AssCode")
    private String AssCode;
    @JsonProperty("AssSource")
    private String AssSource;

    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }
    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
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

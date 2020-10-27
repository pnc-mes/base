package pnc.mesadmin.dto.CheckTempDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public class GetAllCheckTempResponse {
    @JsonProperty(value = "CPTRd")
    private Integer CPTRd;
    @JsonProperty(value = "CheckTempName")
    private String CheckTempName;

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
}

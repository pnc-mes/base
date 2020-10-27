package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/16.
 */
public class SaveIOSInfoReqBD03DoRCInfo {

    @JsonProperty("ReaCode")
    private String ReaCode;

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonProperty("Num")
    private Integer Num;

    @JsonIgnore
    public String getReaCode() {
        return ReaCode;
    }

    @JsonIgnore
    public void setReaCode(String reaCode) {
        ReaCode = reaCode;
    }

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
    }

    @JsonIgnore
    public Integer getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(Integer num) {
        Num = num;
    }
}

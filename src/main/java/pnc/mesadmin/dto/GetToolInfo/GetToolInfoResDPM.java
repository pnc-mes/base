package pnc.mesadmin.dto.GetToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 11:15
 * @Description:
 */
public class GetToolInfoResDPM {
    @JsonProperty("PMRd")
    private int PMRd;

    @JsonProperty("PMName")
    private String PMName;

    @JsonProperty("PMType")
    private String PMType;
    @JsonIgnore
    public int getPMRd() {
        return PMRd;
    }
    @JsonIgnore
    public void setPMRd(int PMRd) {
        this.PMRd = PMRd;
    }
    @JsonIgnore
    public String getPMName() {
        return PMName;
    }
    @JsonIgnore
    public void setPMName(String PMName) {
        this.PMName = PMName;
    }
    @JsonIgnore
    public String getPMType() {
        return PMType;
    }
    @JsonIgnore
    public void setPMType(String PMType) {
        this.PMType = PMType;
    }
}

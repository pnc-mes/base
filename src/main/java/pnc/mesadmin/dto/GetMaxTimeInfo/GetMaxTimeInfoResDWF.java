package pnc.mesadmin.dto.GetMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 11:31
 * @Description:
 */
public class GetMaxTimeInfoResDWF {

    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonProperty("WFName")
    private String  WFName;
    @JsonIgnore
    public int getWfVerRd() {
        return WfVerRd;
    }
    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }
    @JsonIgnore
    public String getWFName() {
        return WFName;
    }
    @JsonIgnore
    public void setWFName(String WFName) {
        this.WFName = WFName;
    }
}

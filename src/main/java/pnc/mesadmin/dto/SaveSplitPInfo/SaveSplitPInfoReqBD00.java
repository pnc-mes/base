package pnc.mesadmin.dto.SaveSplitPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/9/27.
 */
public class SaveSplitPInfoReqBD00 implements Serializable {
    @JsonProperty("BarType")
    private String BarType;
    @JsonProperty("BarInfo")
    private List<SaveSplitPInfoReqBD00BarInfo> BarInfo;
    @JsonIgnore
    public String getBarType() {
        return BarType;
    }
    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }
    @JsonIgnore
    public List<SaveSplitPInfoReqBD00BarInfo> getBarInfo() {
        return BarInfo;
    }
    @JsonIgnore
    public void setBarInfo(List<SaveSplitPInfoReqBD00BarInfo> barInfo) {
        BarInfo = barInfo;
    }
}
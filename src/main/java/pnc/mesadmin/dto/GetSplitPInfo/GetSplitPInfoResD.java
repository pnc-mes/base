package pnc.mesadmin.dto.GetSplitPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by test on 2017/9/26.
 */
public class GetSplitPInfoResD implements Serializable{
    @JsonProperty("BarCode")
    private String BarCode;
    @JsonProperty("BarType")
    private String BarType;
    @JsonProperty("BarInfo")
    private List<GetSplitPInfoResDBarInfo> BarInfo;
    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }
    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
    @JsonIgnore
    public String getBarType() {
        return BarType;
    }
    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }
    @JsonIgnore
    public List<GetSplitPInfoResDBarInfo> getBarInfo() {
        return BarInfo;
    }
    @JsonIgnore
    public void setBarInfo(List<GetSplitPInfoResDBarInfo> barInfo) {
        BarInfo = barInfo;
    }
}

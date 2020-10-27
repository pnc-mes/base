package pnc.mesadmin.dto.GetMTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/12 11:14
 * @Description:
 */
public class GetMTInfoResDExpand implements Serializable {
    @JsonProperty("ExpandRd")
    private int ExpandRd;

    @JsonProperty("ExpandName")
    private String ExpandName;
    @JsonIgnore
    public int getExpandRd() {
        return ExpandRd;
    }
    @JsonIgnore
    public void setExpandRd(int expandRd) {
        ExpandRd = expandRd;
    }
    @JsonIgnore
    public String getExpandName() {
        return ExpandName;
    }
    @JsonIgnore
    public void setExpandName(String expandName) {
        ExpandName = expandName;
    }
}

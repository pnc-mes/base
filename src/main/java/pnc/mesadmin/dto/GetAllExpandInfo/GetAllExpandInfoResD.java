package pnc.mesadmin.dto.GetAllExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 15:40
 * @Description:
 */
public class GetAllExpandInfoResD  implements Serializable {
    @JsonProperty("ExpandRd")
    private int ExpandRd;

    @JsonProperty("ExpandType")
    private String ExpandType;

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
    public String getExpandType() {
        return ExpandType;
    }
    @JsonIgnore
    public void setExpandType(String expandType) {
        ExpandType = expandType;
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

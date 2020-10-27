package pnc.mesadmin.dto.GetDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/13 11:15
 * @Description:
 */
public class GetDevInfoResDToolF {
    @JsonProperty("ToolFRd")
    private int ToolFRd;

    @JsonProperty("ToolFName")
    private String ToolFName;

    @JsonIgnore
    public int getToolFRd() {
        return ToolFRd;
    }
    @JsonIgnore
    public void setToolFRd(int toolFRd) {
        ToolFRd = toolFRd;
    }
    @JsonIgnore
    public String getToolFName() {
        return ToolFName;
    }
    @JsonIgnore
    public void setToolFName(String toolFName) {
        ToolFName = toolFName;
    }
}

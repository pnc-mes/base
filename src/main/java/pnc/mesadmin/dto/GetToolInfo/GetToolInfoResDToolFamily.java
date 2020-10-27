package pnc.mesadmin.dto.GetToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/18 11:21
 * @Description:
 */
public class GetToolInfoResDToolFamily implements Serializable {
    @JsonProperty("ToolFamilyRd")
    private int ToolFamilyRd;

    @JsonProperty("ToolFamilyName")
    private String ToolFamilyName;

    @JsonIgnore
    public int getToolFamilyRd() {
        return ToolFamilyRd;
    }
    @JsonIgnore
    public void setToolFamilyRd(int toolFamilyRd) {
        ToolFamilyRd = toolFamilyRd;
    }
    @JsonIgnore
    public String getToolFamilyName() {
        return ToolFamilyName;
    }
    @JsonIgnore
    public void setToolFamilyName(String toolFamilyName) {
        ToolFamilyName = toolFamilyName;
    }
}

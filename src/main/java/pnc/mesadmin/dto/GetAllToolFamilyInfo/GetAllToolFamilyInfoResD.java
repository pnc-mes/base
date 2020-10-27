package pnc.mesadmin.dto.GetAllToolFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取工具家族信息DTO返回实体类Res
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public class GetAllToolFamilyInfoResD implements Serializable{

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

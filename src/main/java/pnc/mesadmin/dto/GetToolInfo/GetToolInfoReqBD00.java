package pnc.mesadmin.dto.GetToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取工具信息DTO返回实体类Body
 * 创建人：郝赞
 * 创建时间：2018-6-12
 * 修改人：
 * 修改时间：
 */
public class GetToolInfoReqBD00 implements Serializable{

    @JsonProperty("ToolRd")
    private  int ToolRd;

    @JsonIgnore
    public int getToolRd() {
        return ToolRd;
    }

    @JsonIgnore
    public void setToolRd(int toolRd) {
        ToolRd = toolRd;
    }
}

package pnc.mesadmin.dto.SaveLineInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：删除工厂信息dto
 * 创建人：郝赞
 * 创建时间：2018-6-8
 * 修改人：
 * 修改时间：
 */
public class SaveLineInfoReqBD01 implements Serializable{

    @JsonProperty("LineRd")
    private Integer LineRd;

    @JsonIgnore
    public Integer getLineRd() {
        return LineRd;
    }

    @JsonIgnore
    public void setLineRd(Integer lineRd) {
        LineRd = lineRd;
    }
}

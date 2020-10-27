package pnc.mesadmin.dto.GetReaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：根据传过来的rReaRd,获取资源信息DTO返回业务数据实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-5-31
 * 修改人：
 * 修改时间：
 */
public class GetReaInfoReqBD00 implements Serializable{

    @JsonProperty("ReaRd")
    private int ReaRd;

    @JsonIgnore
    public int getReaRd() {
        return ReaRd;
    }

    @JsonIgnore
    public void setReaRd(int reaRd) {
        ReaRd = reaRd;
    }
}

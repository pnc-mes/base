package pnc.mesadmin.dto.GetRKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取入库单信息DTO返回实体类Body
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public class GetRKInfoReqBD00 implements Serializable{

    @JsonProperty("RkRd")
    private int RkRd;

    @JsonIgnore
    public int getRkRd() {
        return RkRd;
    }

    @JsonIgnore
    public void setRkRd(int rkRd) {
        RkRd = rkRd;
    }
}

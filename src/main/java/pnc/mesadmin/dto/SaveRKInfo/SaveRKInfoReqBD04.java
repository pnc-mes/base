package pnc.mesadmin.dto.SaveRKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：  成功入库单信息DTO返回业务数据实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public class SaveRKInfoReqBD04 implements Serializable{

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

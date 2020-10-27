package pnc.mesadmin.dto.GetPrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印机信息DTO请求业务数据实体类Req
 * 创建人：刘福志
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class GetPrInfoReqBD00 implements Serializable{

    @JsonProperty("PrRd")
    private int PrRd;

    @JsonIgnore
    public int getPrRd() {
        return PrRd;
    }

    @JsonIgnore
    public void setPrRd(int prRd) {
        PrRd = prRd;
    }
}

package pnc.mesadmin.dto.GetCarrierInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取载具信息DTO返回实体类Body
 * 创建人：郝赞
 * 创建时间：2018-6-13
 * 修改人：
 * 修改时间：
 */
public class GetCarrierInfoReqBD00 implements Serializable{

    @JsonProperty("CarrierRd")
    private  int CarrierRd;

    @JsonIgnore
    public int getCarrierRd() {
        return CarrierRd;
    }

    @JsonIgnore
    public void setCarrierRd(int carrierRd) {
        CarrierRd = carrierRd;
    }
}

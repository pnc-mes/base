package pnc.mesadmin.dto.SaveCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:50
 * @Description:
 */
public class SaveCarrierRelationInfoReq00Carrier {
    @JsonProperty("CarrierRd")
    private int CarrierRd;
    @JsonIgnore
    public int getCarrierRd() {
        return CarrierRd;
    }
    @JsonIgnore
    public void setCarrierRd(int carrierRd) {
        CarrierRd = carrierRd;
    }
}

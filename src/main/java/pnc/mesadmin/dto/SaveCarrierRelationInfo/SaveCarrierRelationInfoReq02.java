package pnc.mesadmin.dto.SaveCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/20 10:35
 * @Description:
 */
public class SaveCarrierRelationInfoReq02 {
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

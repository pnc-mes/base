package pnc.mesadmin.dto.GetCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/27 09:11
 * @Description:
 */
public class GetCarrierRelationInfoReq00 {
    @JsonProperty("VenderSN")
    private String VenderSN;
    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }
    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }
}

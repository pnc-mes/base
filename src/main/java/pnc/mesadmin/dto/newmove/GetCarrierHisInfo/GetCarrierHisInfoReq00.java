package pnc.mesadmin.dto.newmove.GetCarrierHisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: haozan
 * @Date: 2018-10-24 10:02:21
 * @Description:
 */
public class GetCarrierHisInfoReq00 {
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

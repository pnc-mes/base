package pnc.mesadmin.dto.SaveCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:50
 * @Description:
 */
public class SaveCarrierRelationInfoReq00 {
    @JsonProperty("VenderSN")
    private String VenderSN;

    @JsonProperty("Batch")
    private String Batch;

    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }
    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }
    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
}

package pnc.mesadmin.dto.SaveCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:50
 * @Description:
 */
public class SaveCarrierRelationInfoReq00B {
    @JsonProperty("Batch")
    private String Batch;
    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
}

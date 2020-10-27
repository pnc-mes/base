package pnc.mesadmin.dto.SaveCarrierRelationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:58
 * @Description:
 */
public class SaveCarrierRelationInfoReq01 {
    @JsonProperty("VenderSN")
    private String VenderSN;

    @JsonProperty("BInfo")
    private List<BInfo> BInfo;

    public static class BInfo{
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
    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }
    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }
    @JsonIgnore
    public List<SaveCarrierRelationInfoReq01.BInfo> getBInfo() {
        return BInfo;
    }
    @JsonIgnore
    public void setBInfo(List<SaveCarrierRelationInfoReq01.BInfo> BInfo) {
        this.BInfo = BInfo;
    }
}

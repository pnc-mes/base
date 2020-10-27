package pnc.mesadmin.dto.GetNRMTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 2017/10/25.
 */
public class GetNRMTotalInfoReqBD00 implements Serializable {

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("IsStore")
    private String IsStore;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }
    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
    @JsonIgnore
    public String getIsStore() {
        return IsStore;
    }
    @JsonIgnore
    public void setIsStore(String isStore) {
        IsStore = isStore;
    }
}

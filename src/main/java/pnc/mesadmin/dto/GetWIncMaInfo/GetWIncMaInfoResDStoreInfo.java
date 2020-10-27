package pnc.mesadmin.dto.GetWIncMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetWIncMaInfoResDStoreInfo {

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }

    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }

    @JsonIgnore
    public String getStoreName() {
        return StoreName;
    }

    @JsonIgnore
    public void setStoreName(String storeName) {
        StoreName = storeName;
    }
}

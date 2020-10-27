package pnc.mesadmin.dto.GetPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetPickInfoResDStore {
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

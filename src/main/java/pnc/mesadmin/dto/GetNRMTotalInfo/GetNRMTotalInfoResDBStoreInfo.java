package pnc.mesadmin.dto.GetNRMTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 2017/10/25.
 */
public class GetNRMTotalInfoResDBStoreInfo implements Serializable{

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

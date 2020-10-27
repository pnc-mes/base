package pnc.mesadmin.dto.SaveStoreInfo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @ClassName: SaveStoreInfoResD 
 * @Description: TODO 保存
 * @author: linyichun
 * @date: 2017-6-9 上午12:26:56
 */
public class SaveStoreInfoResD {
    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("StoreCode")
    private String StoreCode;

    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }

    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }

    @JsonIgnore
    public String getStoreCode() {
        return StoreCode;
    }

    @JsonIgnore
    public void setStoreCode(String storeCode) {
        StoreCode = storeCode;
    }
}

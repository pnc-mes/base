package pnc.mesadmin.dto.GetOutMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 14:41
 * @Description:
 */
public class GetOutMaInfoResDOutDlStoreInfo {
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

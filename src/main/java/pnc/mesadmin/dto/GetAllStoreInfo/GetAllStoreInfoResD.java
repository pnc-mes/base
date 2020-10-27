package pnc.mesadmin.dto.GetAllStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 
 * @ClassName: GetAllStoreInfoResD 
 * @Description: TODO 仓库的返回的Data里面的内容
 * @author: linyichun
 * @date: 2017-6-8 下午6:11:23
 */
public class GetAllStoreInfoResD implements Serializable{

	@JsonProperty("StoreRd")
    private Integer StoreRd;

	@JsonProperty("StoreName")
    private String StoreName;

	@JsonIgnore
	public Integer getStoreRd() {
		return StoreRd;
	}

	@JsonIgnore
	public void setStoreRd(Integer storeRd) {
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

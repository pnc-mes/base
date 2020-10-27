package pnc.mesadmin.dto.GetStoreInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：仓库信息DTO返回业务数据实体类BD00
 * 创建人：刘福志
 * 创建时间：2017-8-15
 * 修改人：
 * 修改时间：
 */
public class GetStoreInfoReqBD00 implements Serializable {
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
}

package pnc.mesadmin.dto.GetNMaTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/10/24.
 */
public class GetNMaTotalInfoReq00 {
    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("MaVerDesc")
    private String MaVerDesc;

    //物料描述提供筛选
    @JsonIgnore
    public String getMaVerDesc() {
        return MaVerDesc;
    }

    @JsonIgnore
    public void setMaVerDesc(String maVerDesc) {
        MaVerDesc = maVerDesc;
    }

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
}

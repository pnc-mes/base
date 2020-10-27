package pnc.mesadmin.dto.GetRKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取入库单信息DTO返回实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public class GetRKInfoResD implements Serializable{

    @JsonProperty("RkRd")
    private int RkRd;

    @JsonProperty("RkCode")
    private String RkCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("RkType")
    private String RkType;

    @JsonProperty("AssSource")
    private String AssSource;

    @JsonProperty("StoreInfo")
    private GetRKInfoResBDStore StoreInfo;

    @JsonProperty("RKMaInfo")
    private List<GetRKInfoResBDPKMa> RKMaInfo;

    @JsonIgnore
    public int getRkRd() {
        return RkRd;
    }

    @JsonIgnore
    public void setRkRd(int rkRd) {
        RkRd = rkRd;
    }

    @JsonIgnore
    public String getRkCode() {
        return RkCode;
    }

    @JsonIgnore
    public void setRkCode(String rkCode) {
        RkCode = rkCode;
    }

    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }

    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
    }

    @JsonIgnore
    public GetRKInfoResBDStore getStoreInfo() {
        return StoreInfo;
    }

    @JsonIgnore
    public void setStoreInfo(GetRKInfoResBDStore storeInfo) {
        StoreInfo = storeInfo;
    }

    @JsonIgnore
    public List<GetRKInfoResBDPKMa> getRKMaInfo() {
        return RKMaInfo;
    }

    @JsonIgnore
    public void setRKMaInfo(List<GetRKInfoResBDPKMa> RKMaInfo) {
        this.RKMaInfo = RKMaInfo;
    }

    @JsonIgnore
    public String getRkType() {
        return RkType;
    }
    @JsonIgnore
    public void setRkType(String rkType) {
        RkType = rkType;
    }
    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }
}

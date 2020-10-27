package pnc.mesadmin.dto.GetAllSWareInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：库存预警报表DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-9-22
 * 修改人：
 * 修改时间：
 */
public class GetAllSWareInfoResD implements Serializable {
    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("StoreNum")
    private Float StoreNum;

    @JsonProperty("MinSNum")
    private Float MinSNum;

    @JsonProperty("MaxSNum")
    private Float MaxSNum;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public Float getStoreNum() {
        return StoreNum;
    }

    @JsonIgnore
    public void setStoreNum(Float storeNum) {
        StoreNum = storeNum;
    }

    @JsonIgnore
    public Float getMinSNum() {
        return MinSNum;
    }

    @JsonIgnore
    public void setMinSNum(Float minSNum) {
        MinSNum = minSNum;
    }

    @JsonIgnore
    public Float getMaxSNum() {
        return MaxSNum;
    }

    @JsonIgnore
    public void setMaxSNum(Float maxSNum) {
        MaxSNum = maxSNum;
    }

    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }
}

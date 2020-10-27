package pnc.mesadmin.dto.GetAllMaSTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料库存信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-6-16
 * 修改人：
 * 修改时间：
 */
public class GetAllMaSTInfoResD implements Serializable{

    @JsonProperty("InsRd")
    private int InsRd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Num")
    private Float Num;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("MinSNum")
    private Float MinSNum;

    @JsonProperty("MaxSNum")
    private Float MaxSNum;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
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
    public String getStoreName() {
        return StoreName;
    }

    @JsonIgnore
    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    @JsonIgnore
    public String getMaDes() {
        return MaDes;
    }

    @JsonIgnore
    public void setMaDes(String maDes) {
        MaDes = maDes;
    }

    @JsonIgnore
    public int getInsRd() {
        return InsRd;
    }

    @JsonIgnore
    public void setInsRd(int insRd) {
        InsRd = insRd;
    }

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
    public Float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(Float num) {
        Num = num;
    }
}

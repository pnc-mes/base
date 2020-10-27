package pnc.mesadmin.dto.GetAllMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/2.
 */
public class GetAllMaInfoResD implements Serializable{

    @JsonProperty("MaRd")
    private int MaRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaVerGd")
    private String MaVerGd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("MaDes")
    private String MaDes;

    @JsonProperty("MaType")
    private String MaType;

    @JsonProperty("OrderNum")
    private String OrderNum;

    @JsonProperty("Brand")
    private String Brand;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public String getBrand() {
        return Brand;
    }

    @JsonIgnore
    public void setBrand(String brand) {
        Brand = brand;
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
    public String getMaType() {
        return MaType;
    }

    @JsonIgnore
    public void setMaType(String maType) {
        MaType = maType;
    }

    @JsonIgnore
    public String getOrderNum() {
        return OrderNum;
    }

    @JsonIgnore
    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
    }

    @JsonIgnore
    public int getMaRd() {
        return MaRd;
    }

    @JsonIgnore
    public void setMaRd(int maRd) {
        MaRd = maRd;
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
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
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
    public String getMaVerGd() {
        return MaVerGd;
    }

    @JsonIgnore
    public void setMaVerGd(String maVerGd) {
        MaVerGd = maVerGd;
    }
}

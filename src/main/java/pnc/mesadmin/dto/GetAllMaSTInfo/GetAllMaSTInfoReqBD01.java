package pnc.mesadmin.dto.GetAllMaSTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取物料库存信息DTO业务请求数据实体类ReqBD01
 * 创建人：刘福志
 * 创建时间：2017-09-2
 * 修改人：
 * 修改时间：
 */
public class GetAllMaSTInfoReqBD01 implements Serializable {
    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("StoreGd")
    private String StoreGd;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("Brand")
    private String Brand;

    @JsonProperty("OrderNum")
    private String OrderNum;

    @JsonProperty("MaterialDes")
    private String MaterialDes;

    @JsonProperty("Size")
    private String Size;

    @JsonProperty("Material")
    private String Material;

    @JsonProperty("Norm")
    private String Norm;

    @JsonProperty("Model")
    private String Model;

    @JsonIgnore
    public String getSize() {
        return Size;
    }

    @JsonIgnore
    public void setSize(String size) {
        Size = size;
    }

    @JsonIgnore
    public String getMaterial() {
        return Material;
    }

    @JsonIgnore
    public void setMaterial(String material) {
        Material = material;
    }

    @JsonIgnore
    public String getNorm() {
        return Norm;
    }

    @JsonIgnore
    public void setNorm(String norm) {
        Norm = norm;
    }

    @JsonIgnore
    public String getModel() {
        return Model;
    }

    @JsonIgnore
    public void setModel(String model) {
        Model = model;
    }

    @JsonIgnore
    public String getBrand() {
        return Brand;
    }

    @JsonIgnore
    public void setBrand(String brand) {
        Brand = brand;
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
    public String getMaterialDes() {
        return MaterialDes;
    }

    @JsonIgnore
    public void setMaterialDes(String materialDes) {
        MaterialDes = materialDes;
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
    public String getStoreGd() {
        return StoreGd;
    }

    @JsonIgnore
    public void setStoreGd(String storeGd) {
        StoreGd = storeGd;
    }

    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }

    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }
}

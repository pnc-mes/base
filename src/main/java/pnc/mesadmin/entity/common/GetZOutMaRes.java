package pnc.mesadmin.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/2 18:34
 * @Description: 汇总的信息
 */
public class GetZOutMaRes {
    @JsonProperty("MaVerRd")
    private String MaVerRd;

    @JsonProperty("MaterialCode")
    private String MaterialCode;

    @JsonProperty("MaterialName")
    private String MaterialName;

    @JsonProperty("MaterialDes")
    private String MaterialDes;

    @JsonProperty("PowerGear")
    private String PowerGear;

    @JsonProperty("ELGear")
    private String ELGear;

    @JsonProperty("ColorGear")
    private String ColorGear;

    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Num")
    private String Num;

    @JsonProperty("NNum")
    private String NNum;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonIgnore
    public String getMaterialCode() {
        return MaterialCode;
    }

    @JsonIgnore
    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
    }


    @JsonIgnore
    public String getMaterialName() {
        return MaterialName;
    }


    @JsonIgnore
    public void setMaterialName(String materialName) {
        MaterialName = materialName;
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
    public String getPowerGear() {
        return PowerGear;
    }
    @JsonIgnore
    public void setPowerGear(String powerGear) {
        PowerGear = powerGear;
    }
    @JsonIgnore
    public String getELGear() {
        return ELGear;
    }
    @JsonIgnore
    public void setELGear(String ELGear) {
        this.ELGear = ELGear;
    }
    @JsonIgnore
    public String getColorGear() {
        return ColorGear;
    }
    @JsonIgnore
    public void setColorGear(String colorGear) {
        ColorGear = colorGear;
    }

    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }


    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
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
    public String getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(String num) {
        Num = num;
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
    public int getStoreRd() {
        return StoreRd;
    }
    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }
    @JsonIgnore
    public String getNNum() {
        return NNum;
    }
    @JsonIgnore
    public void setNNum(String NNum) {
        this.NNum = NNum;
    }
    @JsonIgnore
    public String getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(String maVerRd) {
        MaVerRd = maVerRd;
    }
}

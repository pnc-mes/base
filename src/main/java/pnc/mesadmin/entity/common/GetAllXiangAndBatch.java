package pnc.mesadmin.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/24 09:46
 * @Description: 组件出库这边查询所有仓库的箱或组件
 */
public class GetAllXiangAndBatch {
    @JsonProperty("BarType")
    private String BarType;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("LName")
    private String LName;

    @JsonProperty("MaterialName")
    private String MaterialName;

    @JsonProperty("MaterialCode")
    private String MaterialCode;

    @JsonProperty("MaterialDes")
    private String MaterialDes;

    @JsonProperty("PowerGear")
    private String PowerGear;

    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("ELGear")
    private String ELGear;

    @JsonProperty("ColorGear")
    private String ColorGear;

    @JsonProperty("Num")
    private int Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("LRd")
    private int LRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("UniqueText")
    private String UniqueText;//识别码
    @JsonIgnore
    public String getUniqueText() {
        return UniqueText;
    }
    @JsonIgnore
    public void setUniqueText(String uniqueText) {
        UniqueText = uniqueText;
    }

    @JsonIgnore
    public String getBarType() {
        return BarType;
    }
    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
    public String getLName() {
        return LName;
    }
    @JsonIgnore
    public void setLName(String LName) {
        this.LName = LName;
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
    public String getMaterialCode() {
        return MaterialCode;
    }
    @JsonIgnore
    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
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
    public String getGradeName() {
        return GradeName;
    }
    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
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
    public int getNum() {
        return Num;
    }
    @JsonIgnore
    public void setNum(int num) {
        Num = num;
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
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
    @JsonIgnore
    public int getLRd() {
        return LRd;
    }
    @JsonIgnore
    public void setLRd(int LRd) {
        this.LRd = LRd;
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
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

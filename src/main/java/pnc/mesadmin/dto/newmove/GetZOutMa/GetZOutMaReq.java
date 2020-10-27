package pnc.mesadmin.dto.newmove.GetZOutMa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/3 13:40
 * @Description:
 */
public class GetZOutMaReq {
    @JsonProperty("MaterialCode")
    private String MaterialCode;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("PowerGear")
    private String PowerGear;

    @JsonProperty("ELGear")
    private String ELGear;

    @JsonProperty("ColorGear")
    private String ColorGear;

    @JsonProperty("GradeName")
    private String GradeName;
    @JsonIgnore
    public String getMaterialCode() {
        return MaterialCode;
    }
    @JsonIgnore
    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
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
}

package pnc.mesadmin.dto.newmove.MStoreDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-26
 **/
public class CheckZJXInfoResponse {
    @JsonProperty("BarType")
    private String BarType;
    @JsonProperty("BarCode")
    private String BarCode;
    @JsonProperty("Num")
    private Float Num;
    @JsonProperty("PowerGear")
    private String PowerGear;
    @JsonProperty("ELGear")
    private String ELGear;
    @JsonProperty("ColorGear")
    private String ColorGear;
    @JsonProperty("GradeName")
    private String GradeName;

    @JsonIgnore
    public String getBarType() {
        return BarType;
    }

    @JsonIgnore
    public void setBarType(String barType) {
        BarType = barType;
    }

    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }

    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    @JsonIgnore
    public Float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(Float num) {
        Num = num;
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

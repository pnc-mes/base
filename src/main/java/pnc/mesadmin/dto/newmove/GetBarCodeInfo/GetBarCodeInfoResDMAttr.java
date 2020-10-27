package pnc.mesadmin.dto.newmove.GetBarCodeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBarCodeInfoResDMAttr {
    @JsonProperty("Impp")
    private String Impp;

    @JsonProperty("Pmpp")
    private String Pmpp;

    @JsonProperty("Color")
    private String Color;

    @JsonProperty("PowerGear")
    private String PowerGear;

    @JsonProperty("ColorGear")
    private String ColorGear;

    @JsonProperty("ELGear")
    private String ELGear;

    @JsonProperty("GradeName")
    private String GradeName;

    @JsonIgnore
    public String getImpp() {
        return Impp;
    }

    @JsonIgnore
    public void setImpp(String impp) {
        Impp = impp;
    }

    @JsonIgnore
    public String getPmpp() {
        return Pmpp;
    }

    @JsonIgnore
    public void setPmpp(String pmpp) {
        Pmpp = pmpp;
    }

    @JsonIgnore
    public String getColor() {
        return Color;
    }

    @JsonIgnore
    public void setColor(String color) {
        Color = color;
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
    public String getColorGear() {
        return ColorGear;
    }

    @JsonIgnore
    public void setColorGear(String colorGear) {
        ColorGear = colorGear;
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
    public String getGradeName() {
        return GradeName;
    }

    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }
}

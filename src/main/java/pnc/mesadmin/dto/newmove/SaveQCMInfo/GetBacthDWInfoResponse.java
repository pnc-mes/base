package pnc.mesadmin.dto.newmove.SaveQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: 箱/组件挡位等级返回信息model
 * @author: yin.yang
 * @create: 2019-04-26
 **/
public class GetBacthDWInfoResponse {
    @JsonProperty("PowerGear")
    private String PowerGear;
    @JsonProperty("ColorGear")
    private String ColorGear;
    @JsonProperty("ELGear")
    private String ELGear;
    @JsonProperty("GradeName")
    private String GradeName;
    @JsonProperty("MixedBag")
    private String MixedBag;

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

    @JsonIgnore
    public String getMixedBag() {
        return MixedBag;
    }

    @JsonIgnore
    public void setMixedBag(String mixedBag) {
        MixedBag = mixedBag;
    }
}

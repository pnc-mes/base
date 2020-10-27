package pnc.mesadmin.dto.WmsMaterialsBDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：虚拟箱号DTO
 * 创建人：潘俊峰
 * 创建时间：2019-10-31
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBPackReq {
    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("PackCode")
    private String PackCode;

    @JsonProperty("PowerGear")
    private String PowerGear;

    @JsonProperty("ELGear")
    private String ELGear;

    @JsonProperty("ColorGear")
    private String ColorGear;

    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("Num")
    private float Num;

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getPackCode() {
        return PackCode;
    }

    @JsonIgnore
    public void setPackCode(String packCode) {
        PackCode = packCode;
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
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }
}

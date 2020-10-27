package pnc.mesadmin.dto.newmove.MStoreDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: 查询箱子检验合格待入库
 * @author: yin.yang
 * @create: 2019-07-11
 **/
public class GetAllNotInStarage {
    @JsonProperty("BarCode")
    private String barCode; //箱号
    @JsonProperty("Num")
    private String num; //数量
    @JsonProperty("Remark")
    private String remark; //备注
    @JsonProperty("PowerGear")
    private String powerGear; //功率
    @JsonProperty("ElGear")
    private String elGear; //电流
    @JsonProperty("ColorGear")
    private String colorGear; //颜色
    @JsonProperty("GradeName")
    private String gradeName; //等级
    @JsonProperty("WoAttr")
    private String woAttr; //识别码

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPowerGear() {
        return powerGear;
    }

    public void setPowerGear(String powerGear) {
        this.powerGear = powerGear;
    }

    public String getElGear() {
        return elGear;
    }

    public void setElGear(String elGear) {
        this.elGear = elGear;
    }

    public String getColorGear() {
        return colorGear;
    }

    public void setColorGear(String colorGear) {
        this.colorGear = colorGear;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getWoAttr() {
        return woAttr;
    }

    public void setWoAttr(String woAttr) {
        this.woAttr = woAttr;
    }
}

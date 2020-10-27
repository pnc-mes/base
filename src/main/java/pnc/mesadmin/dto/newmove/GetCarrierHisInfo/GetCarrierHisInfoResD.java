package pnc.mesadmin.dto.newmove.GetCarrierHisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/27 17:35
 * @Description:
 */
public class GetCarrierHisInfoResD {
    @JsonProperty("BoxCode")
    private String BoxCode;
    @JsonProperty("Eff")
    private String Eff;
    @JsonProperty("Color")
    private String Color;
    @JsonProperty("Grade")
    private String Grade;
    @JsonProperty("CellType")
    private String CellType;
    @JsonProperty("TechInfo")
    private String TechInfo;
    @JsonProperty("Appear")
    private String Appear;
    @JsonProperty("WaferSize")
    private String WaferSize;
    @JsonProperty("Factory")
    private String Factory;

    @JsonIgnore
    public String getBoxCode() {
        return BoxCode;
    }
    @JsonIgnore
    public void setBoxCode(String boxCode) {
        BoxCode = boxCode;
    }
    @JsonIgnore
    public String getEff() {
        return Eff;
    }
    @JsonIgnore
    public void setEff(String eff) {
        Eff = eff;
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
    public String getGrade() {
        return Grade;
    }
    @JsonIgnore
    public void setGrade(String grade) {
        Grade = grade;
    }
    @JsonIgnore
    public String getCellType() {
        return CellType;
    }
    @JsonIgnore
    public void setCellType(String cellType) {
        CellType = cellType;
    }
    @JsonIgnore
    public String getTechInfo() {
        return TechInfo;
    }
    @JsonIgnore
    public void setTechInfo(String techInfo) {
        TechInfo = techInfo;
    }
    @JsonIgnore
    public String getAppear() {
        return Appear;
    }
    @JsonIgnore
    public void setAppear(String appear) {
        Appear = appear;
    }
    @JsonIgnore
    public String getWaferSize() {
        return WaferSize;
    }
    @JsonIgnore
    public void setWaferSize(String waferSize) {
        WaferSize = waferSize;
    }
    @JsonIgnore
    public String getFactory() {
        return Factory;
    }
    @JsonIgnore
    public void setFactory(String factory) {
        Factory = factory;
    }
}

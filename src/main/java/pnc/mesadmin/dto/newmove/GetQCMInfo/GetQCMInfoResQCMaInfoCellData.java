package pnc.mesadmin.dto.newmove.GetQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * Created by PNC on 2019/4/4.
 */
public class GetQCMInfoResQCMaInfoCellData implements Serializable {
    @JsonProperty("BarCode")
    private String BarCode;
    @JsonProperty("Eff")
    private String Eff;
    @JsonProperty("Color")
    private String Color;
    @JsonProperty("Grade")
    private String Grade;
    @JsonProperty("GongChangName")
    private String GongChangName;
    @JsonProperty("DanDuoJingName")
    private String DanDuoJingName;
    @JsonProperty("SizeName")
    private String SizeName;
    @JsonProperty("TuXingName")
    private String TuXingName;
    @JsonProperty("InforMationName")
    private String InforMationName;

    @JsonProperty("GradeName")
    private String GradeName;
    @JsonProperty("ZJBlBadName")
    private String ZJBlBadName;
    @JsonProperty("ELBlBadName")
    private String ELBlBadName;

    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }

    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    @JsonIgnore
    public String getZJBlBadName() {
        return ZJBlBadName;
    }

    @JsonIgnore
    public void setZJBlBadName(String ZJBlBadName) {
        this.ZJBlBadName = ZJBlBadName;
    }

    @JsonIgnore
    public String getELBlBadName() {
        return ELBlBadName;
    }

    @JsonIgnore
    public void setELBlBadName(String ELBlBadName) {
        this.ELBlBadName = ELBlBadName;
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
    public String getGongChangName() {
        return GongChangName;
    }
    @JsonIgnore
    public void setGongChangName(String gongChangName) {
        GongChangName = gongChangName;
    }
    @JsonIgnore
    public String getDanDuoJingName() {
        return DanDuoJingName;
    }
    @JsonIgnore
    public void setDanDuoJingName(String danDuoJingName) {
        DanDuoJingName = danDuoJingName;
    }
    @JsonIgnore
    public String getSizeName() {
        return SizeName;
    }
    @JsonIgnore
    public void setSizeName(String sizeName) {
        SizeName = sizeName;
    }
    @JsonIgnore
    public String getTuXingName() {
        return TuXingName;
    }
    @JsonIgnore
    public void setTuXingName(String tuXingName) {
        TuXingName = tuXingName;
    }
    @JsonIgnore
    public String getInforMationName() {
        return InforMationName;
    }
    @JsonIgnore
    public void setInforMationName(String inforMationName) {
        InforMationName = inforMationName;
    }
    @JsonIgnore
    public String getBarCode() {
        return BarCode;
    }
    @JsonIgnore
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
}

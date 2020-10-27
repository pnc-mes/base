package pnc.mesadmin.dto.SavePackSpecificationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/26.
 */
public class SavePackSpecificationInfoReq02 implements Serializable{

    @JsonProperty("MPRd")
    private int MPRd;

    @JsonProperty("MPName")
    private String MPName;

    @JsonProperty("PackType")
    private String PackType;

    @JsonProperty("PTRd")
    private int PTRd;

    @JsonProperty("SNRd")
    private int SNRd;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("Weight")
    private float Weight;

    @JsonProperty("UpLimit")
    private float UpLimit;

    @JsonProperty("DownLimit")
    private float DownLimit;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("IsProperty")
    private String IsProperty;

    @JsonProperty("MaRd")
    private int MaRd;

    @JsonProperty("PowerRd")
    private int PowerRd;

    @JsonProperty("CurrentRd")
    private int CurrentRd;

    @JsonProperty("ColorRd")
    private int ColorRd;

    @JsonProperty("GradeRd")
    private int GradeRd;

    @JsonProperty("StartPower")
    private float StartPower;

    @JsonProperty("EndPower")
    private float EndPower;

    @JsonProperty("Property")
    private List<SavePackSpecificationInfoReq02.Property> Property;

    @JsonIgnore
    public int getMPRd() {
        return MPRd;
    }
    @JsonIgnore
    public void setMPRd(int MPRd) {
        this.MPRd = MPRd;
    }

    @JsonIgnore
    public String getMPName() {
        return MPName;
    }
    @JsonIgnore
    public void setMPName(String MPName) {
        this.MPName = MPName;
    }
    @JsonIgnore
    public String getPackType() {
        return PackType;
    }
    @JsonIgnore
    public void setPackType(String packType) {
        PackType = packType;
    }
    @JsonIgnore
    public int getPTRd() {
        return PTRd;
    }
    @JsonIgnore
    public void setPTRd(int PTRd) {
        this.PTRd = PTRd;
    }
    @JsonIgnore
    public int getSNRd() {
        return SNRd;
    }
    @JsonIgnore
    public void setSNRd(int SNRd) {
        this.SNRd = SNRd;
    }
    @JsonIgnore
    public float getNum() {
        return Num;
    }
    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }
    @JsonIgnore
    public float getWeight() {
        return Weight;
    }
    @JsonIgnore
    public void setWeight(float weight) {
        Weight = weight;
    }
    @JsonIgnore
    public float getUpLimit() {
        return UpLimit;
    }
    @JsonIgnore
    public void setUpLimit(float upLimit) {
        UpLimit = upLimit;
    }
    @JsonIgnore
    public float getDownLimit() {
        return DownLimit;
    }
    @JsonIgnore
    public void setDownLimit(float downLimit) {
        DownLimit = downLimit;
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
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public String getIsProperty() {
        return IsProperty;
    }
    @JsonIgnore
    public void setIsProperty(String isProperty) {
        IsProperty = isProperty;
    }
    @JsonIgnore
    public int getPowerRd() {
        return PowerRd;
    }
    @JsonIgnore
    public void setPowerRd(int powerRd) {
        PowerRd = powerRd;
    }
    @JsonIgnore
    public int getCurrentRd() {
        return CurrentRd;
    }
    @JsonIgnore
    public void setCurrentRd(int currentRd) {
        CurrentRd = currentRd;
    }
    @JsonIgnore
    public int getColorRd() {
        return ColorRd;
    }
    @JsonIgnore
    public void setColorRd(int colorRd) {
        ColorRd = colorRd;
    }
    @JsonIgnore
    public int getGradeRd() {
        return GradeRd;
    }
    @JsonIgnore
    public void setGradeRd(int gradeRd) {
        GradeRd = gradeRd;
    }
    @JsonIgnore
    public List<SavePackSpecificationInfoReq02.Property> getProperty() {
        return Property;
    }
    @JsonIgnore
    public void setProperty(List<SavePackSpecificationInfoReq02.Property> property) {
        Property = property;
    }
    @JsonIgnore
    public float getStartPower() {
        return StartPower;
    }
    @JsonIgnore
    public void setStartPower(float startPower) {
        StartPower = startPower;
    }
    @JsonIgnore
    public float getEndPower() {
        return EndPower;
    }
    @JsonIgnore
    public void setEndPower(float endPower) {
        EndPower = endPower;
    }
    @JsonIgnore
    public int getMaRd() {
        return MaRd;
    }
    @JsonIgnore
    public void setMaRd(int maRd) {
        MaRd = maRd;
    }

    public static class Property{
        @JsonProperty("id")
        private int id;

        @JsonProperty("type")
        private String type;

        @JsonIgnore
        public int getId() {
            return id;
        }

        @JsonIgnore
        public void setId(int id) {
            this.id = id;
        }

        @JsonIgnore
        public String getType() {
            return type;
        }

        @JsonIgnore
        public void setType(String type) {
            this.type = type;
        }
    }
}

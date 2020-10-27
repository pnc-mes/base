package pnc.mesadmin.dto.GetPackSpecificationInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetPackSpecificationInfoResD implements Serializable{

    @JsonProperty("MPRd")
    private int MPRd;

    @JsonProperty("MPName")
    private String MPName;

    @JsonProperty("PackType")
    private String PackType;

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

    @JsonProperty("PTInfo")
    private GetPackSpecificationInfoResDPTInfo PTInfo;

    @JsonProperty("SNInfo")
    private GetPackSpecificationInfoResDSNInfo SNInfo;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("IsProperty")
    private String IsProperty;

    @JsonProperty("MaRd")
    private int MaRd;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("PowerRd")
    private int PowerRd;

    @JsonProperty("PowerName")
    private String PowerName;

    @JsonProperty("CurrentRd")
    private int CurrentRd;

    @JsonProperty("CurrentName")
    private String CurrentName;

    @JsonProperty("ColorRd")
    private int ColorRd;

    @JsonProperty("ColorName")
    private String ColorName;

    @JsonProperty("GradeRd")
    private int GradeRd;

    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("StartPower")
    private float StartPower;

    @JsonProperty("EndPower")
    private float EndPower;

    @JsonProperty("MaProperty")
    private List<GetPackSpecificationInfoResD.Property> MaProperty;

    @JsonProperty("PowerProperty")
    private List<GetPackSpecificationInfoResD.Property> PowerProperty;

    @JsonProperty("CurrentProperty")
    private List<GetPackSpecificationInfoResD.Property> CurrentProperty;

    @JsonProperty("ColorProperty")
    private List<GetPackSpecificationInfoResD.Property> ColorProperty;

    @JsonProperty("GradeProperty")
    private List<GetPackSpecificationInfoResD.Property> GradeProperty;

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
    public GetPackSpecificationInfoResDPTInfo getPTInfo() {
        return PTInfo;
    }
    @JsonIgnore
    public void setPTInfo(GetPackSpecificationInfoResDPTInfo PTInfo) {
        this.PTInfo = PTInfo;
    }
    @JsonIgnore
    public GetPackSpecificationInfoResDSNInfo getSNInfo() {
        return SNInfo;
    }
    @JsonIgnore
    public void setSNInfo(GetPackSpecificationInfoResDSNInfo SNInfo) {
        this.SNInfo = SNInfo;
    }

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
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
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }
    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public List<Property> getPowerProperty() {
        return PowerProperty;
    }
    @JsonIgnore
    public void setPowerProperty(List<Property> powerProperty) {
        PowerProperty = powerProperty;
    }
    @JsonIgnore
    public List<Property> getCurrentProperty() {
        return CurrentProperty;
    }
    @JsonIgnore
    public void setCurrentProperty(List<Property> currentProperty) {
        CurrentProperty = currentProperty;
    }
    @JsonIgnore
    public List<Property> getColorProperty() {
        return ColorProperty;
    }
    @JsonIgnore
    public void setColorProperty(List<Property> colorProperty) {
        ColorProperty = colorProperty;
    }
    @JsonIgnore
    public List<Property> getGradeProperty() {
        return GradeProperty;
    }
    @JsonIgnore
    public void setGradeProperty(List<Property> gradeProperty) {
        GradeProperty = gradeProperty;
    }
    @JsonIgnore
    public String getPowerName() {
        return PowerName;
    }
    @JsonIgnore
    public void setPowerName(String powerName) {
        PowerName = powerName;
    }
    @JsonIgnore
    public String getCurrentName() {
        return CurrentName;
    }
    @JsonIgnore
    public void setCurrentName(String currentName) {
        CurrentName = currentName;
    }
    @JsonIgnore
    public String getColorName() {
        return ColorName;
    }
    @JsonIgnore
    public void setColorName(String colorName) {
        ColorName = colorName;
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
    @JsonIgnore
    public String getMaName() {
        return MaName;
    }
    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }
    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }
    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }
    @JsonIgnore
    public List<Property> getMaProperty() {
        return MaProperty;
    }
    @JsonIgnore
    public void setMaProperty(List<Property> maProperty) {
        MaProperty = maProperty;
    }

    public static class Property{
        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;

        @JsonIgnore
        public int getId() {
            return id;
        }

        @JsonIgnore
        public void setId(int id) {
            this.id = id;
        }

        @JsonIgnore
        public String getName() {
            return name;
        }

        @JsonIgnore
        public void setName(String name) {
            this.name = name;
        }
    }
}

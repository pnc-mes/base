package pnc.mesadmin.dto.CommonPageInfo;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/15 13:12
 * @Description:
 */
public class CommonPageData implements Serializable {
    private String WoCode;
    private String Batch;
    private String CZPCode;
    private String MaCode;//产品代码
    private String MaName;//产品名称
    private String Eff;//电池片效率
    private String Grade;
    private String Color;
    private String GradeName;
    private String BadName;
    private String LocationName;
    private String MaCode1;//物料的物料代码
    private String MaName1;//物料的物料名称
    private String BomName;
    private String CreateTime;
    private String Creator;

    public String getWoCode() {
        return WoCode;
    }

    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getCZPCode() {
        return CZPCode;
    }

    public void setCZPCode(String CZPCode) {
        this.CZPCode = CZPCode;
    }

    public String getMaCode() {
        return MaCode;
    }

    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    public String getMaName() {
        return MaName;
    }

    public void setMaName(String maName) {
        MaName = maName;
    }

    public String getEff() {
        return Eff;
    }

    public void setEff(String eff) {
        Eff = eff;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    public String getBadName() {
        return BadName;
    }

    public void setBadName(String badName) {
        BadName = badName;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getMaCode1() {
        return MaCode1;
    }

    public void setMaCode1(String maCode1) {
        MaCode1 = maCode1;
    }

    public String getMaName1() {
        return MaName1;
    }

    public void setMaName1(String maName1) {
        MaName1 = maName1;
    }

    public String getBomName() {
        return BomName;
    }

    public void setBomName(String bomName) {
        BomName = bomName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }
}

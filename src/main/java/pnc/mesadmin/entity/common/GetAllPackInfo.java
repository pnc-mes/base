package pnc.mesadmin.entity.common;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/26 16:05
 * @Description:
 */
public class GetAllPackInfo {
    private String PackGd;
    private String  PackCode;
    private String MaCode;
    private String MaName;
    private String CreateTime;
    private String Creator;
    private String PowerGear;
    private String ELGear;
    private String ColorGear;
    private String GradeName;
    private int Num;
    private String FileName;
    private String IsFull;
    private String PrintTGd;
    private String Remark;
    private String PackName;

    private String GradeName1;
    private String BeforeCheckQC;


    private String MixedBag;//shi否混包
    private String TorsionStatus;//箱子的状态

    public String getMixedBag() {
        return MixedBag;
    }

    public void setMixedBag(String mixedBag) {
        MixedBag = mixedBag;
    }

    public String getTorsionStatus() {
        return TorsionStatus;
    }

    public void setTorsionStatus(String torsionStatus) {
        TorsionStatus = torsionStatus;
    }

    public String getPackName() {
        return PackName;
    }

    public void setPackName(String packName) {
        PackName = packName;
    }

    public String getPackGd() {
        return PackGd;
    }

    public void setPackGd(String packGd) {
        PackGd = packGd;
    }

    public String getPackCode() {
        return PackCode;
    }

    public void setPackCode(String packCode) {
        PackCode = packCode;
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

    public String getPowerGear() {
        return PowerGear;
    }

    public void setPowerGear(String powerGear) {
        PowerGear = powerGear;
    }

    public String getELGear() {
        return ELGear;
    }

    public void setELGear(String ELGear) {
        this.ELGear = ELGear;
    }

    public String getColorGear() {
        return ColorGear;
    }

    public void setColorGear(String colorGear) {
        ColorGear = colorGear;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getIsFull() {
        return IsFull;
    }

    public void setIsFull(String isFull) {
        IsFull = isFull;
    }

    public String getGradeName1() {
        return GradeName1;
    }

    public void setGradeName1(String gradeName1) {
        GradeName1 = gradeName1;
    }

    public String getPrintTGd() {
        return PrintTGd;
    }

    public void setPrintTGd(String printTGd) {
        PrintTGd = printTGd;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getBeforeCheckQC() {
        return BeforeCheckQC;
    }

    public void setBeforeCheckQC(String beforeCheckQC) {
        BeforeCheckQC = beforeCheckQC;
    }
}

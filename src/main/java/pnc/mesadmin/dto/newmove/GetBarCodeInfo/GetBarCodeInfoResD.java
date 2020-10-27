package pnc.mesadmin.dto.newmove.GetBarCodeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBarCodeInfoResD {
    @JsonProperty("WoRd")
    private int WoRd;

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("WoType")
    private String WoType;

    @JsonProperty("WoPropertyCode")
    private String WoPropertyCode;

    @JsonProperty("CustomerName")
    private String CustomerName;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("MaterialAttr")
    private String MaterialAttr;

    @JsonProperty("BinRuleRd")
    private int BinRuleRd;

    @JsonProperty("BadLCRd")
    private int BadLCRd;

    @JsonProperty("LineInfo")
    private GetBarCodeInfoResDLine LineInfo;

    @JsonProperty("MAttrInfo")
    private GetBarCodeInfoResDMAttr MAttrInfo;

    @JsonProperty("YHCheckInfo")
    private GetBarCodeInfoResDYHCheck YHCheckInfo;

    @JsonProperty("PELCheckInfo")
    private GetBarCodeInfoResDPELCheck PELCheckInfo;

    @JsonProperty("TELCheckInfo")
    private GetBarCodeInfoResDTELCheck TELCheckInfo;

    @JsonProperty("MCInfo")
    private MCInfo MCInfo;

    @JsonProperty("RuleName")
    private String RuleName;

    @JsonProperty("SysGradeName")
    private String SysGradeName;

    @JsonProperty("PackStatus")
    private String PackStatus;

    @JsonProperty("Bad")
    private String Bad;

    @JsonProperty("Status")
    private String Status;

    public static class MCInfo {
        @JsonProperty("Eff")
        private String Eff;
        @JsonProperty("Color")
        private String Color;
        @JsonProperty("Grade")
        private String Grade;
        @JsonProperty("EffMsg")
        private String EffMsg;
        @JsonProperty("ColorMsg")
        private String ColorMsg;
        @JsonProperty("GradeMsg")
        private String GradeMsg;

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
        public String getEffMsg() {
            return EffMsg;
        }

        @JsonIgnore
        public void setEffMsg(String effMsg) {
            EffMsg = effMsg;
        }

        @JsonIgnore
        public String getColorMsg() {
            return ColorMsg;
        }

        @JsonIgnore
        public void setColorMsg(String colorMsg) {
            ColorMsg = colorMsg;
        }

        @JsonIgnore
        public String getGradeMsg() {
            return GradeMsg;
        }

        @JsonIgnore
        public void setGradeMsg(String gradeMsg) {
            GradeMsg = gradeMsg;
        }
    }

    @JsonIgnore
    public int getWoRd() {
        return WoRd;
    }

    @JsonIgnore
    public void setWoRd(int woRd) {
        WoRd = woRd;
    }

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    @JsonIgnore
    public String getWoType() {
        return WoType;
    }

    @JsonIgnore
    public void setWoType(String woType) {
        WoType = woType;
    }

    @JsonIgnore
    public String getCustomerName() {
        return CustomerName;
    }

    @JsonIgnore
    public void setCustomerName(String customerName) {
        CustomerName = customerName;
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
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
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
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public String getMaterialAttr() {
        return MaterialAttr;
    }

    @JsonIgnore
    public void setMaterialAttr(String materialAttr) {
        MaterialAttr = materialAttr;
    }

    @JsonIgnore
    public int getBinRuleRd() {
        return BinRuleRd;
    }

    @JsonIgnore
    public void setBinRuleRd(int binRuleRd) {
        BinRuleRd = binRuleRd;
    }

    @JsonIgnore
    public int getBadLCRd() {
        return BadLCRd;
    }

    @JsonIgnore
    public void setBadLCRd(int badLCRd) {
        BadLCRd = badLCRd;
    }

    @JsonIgnore
    public GetBarCodeInfoResDLine getLineInfo() {
        return LineInfo;
    }

    @JsonIgnore
    public void setLineInfo(GetBarCodeInfoResDLine lineInfo) {
        LineInfo = lineInfo;
    }

    @JsonIgnore
    public GetBarCodeInfoResDMAttr getMAttrInfo() {
        return MAttrInfo;
    }

    @JsonIgnore
    public void setMAttrInfo(GetBarCodeInfoResDMAttr MAttrInfo) {
        this.MAttrInfo = MAttrInfo;
    }

    @JsonIgnore
    public GetBarCodeInfoResDYHCheck getYHCheckInfo() {
        return YHCheckInfo;
    }

    @JsonIgnore
    public void setYHCheckInfo(GetBarCodeInfoResDYHCheck YHCheckInfo) {
        this.YHCheckInfo = YHCheckInfo;
    }

    @JsonIgnore
    public GetBarCodeInfoResDPELCheck getPELCheckInfo() {
        return PELCheckInfo;
    }

    @JsonIgnore
    public void setPELCheckInfo(GetBarCodeInfoResDPELCheck PELCheckInfo) {
        this.PELCheckInfo = PELCheckInfo;
    }

    @JsonIgnore
    public GetBarCodeInfoResDTELCheck getTELCheckInfo() {
        return TELCheckInfo;
    }

    @JsonIgnore
    public void setTELCheckInfo(GetBarCodeInfoResDTELCheck TELCheckInfo) {
        this.TELCheckInfo = TELCheckInfo;
    }

    @JsonIgnore
    public String getRuleName() {
        return RuleName;
    }

    @JsonIgnore
    public void setRuleName(String ruleName) {
        RuleName = ruleName;
    }

    @JsonIgnore
    public String getSysGradeName() {
        return SysGradeName;
    }

    @JsonIgnore
    public void setSysGradeName(String sysGradeName) {
        SysGradeName = sysGradeName;
    }

    @JsonIgnore
    public GetBarCodeInfoResD.MCInfo getMCInfo() {
        return MCInfo;
    }

    @JsonIgnore
    public void setMCInfo(GetBarCodeInfoResD.MCInfo MCInfo) {
        this.MCInfo = MCInfo;
    }

    @JsonIgnore
    public String getPackStatus() {
        return PackStatus;
    }

    @JsonIgnore
    public void setPackStatus(String packStatus) {
        PackStatus = packStatus;
    }

    @JsonIgnore
    public String getBad() {
        return Bad;
    }

    @JsonIgnore
    public void setBad(String bad) {
        Bad = bad;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }


    @JsonIgnore
    public String getWoPropertyCode() {
        return WoPropertyCode;
    }

    @JsonIgnore
    public void setWoPropertyCode(String woPropertyCode) {
        WoPropertyCode = woPropertyCode;
    }
}

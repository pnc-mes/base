package pnc.mesadmin.dto.newmove.STCKMDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-04-14
 **/
public class STCKMInfoGetStckmResponse {
    @JsonProperty("StockMaRd") //备货单
    private Integer StockMaRd;
    @JsonProperty("StockMaCode")
    private String StockMaCode;
    @JsonProperty("OutCode")
    private String OutCode;
    @JsonProperty("QCStatus")
    private String QCStatus;
    @JsonProperty("CKStatus")
    private String CKStatus;
    @JsonProperty("Creator")
    private String Creator;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("STCMaInfo")
    private List<STCMaInfo> STCMaInfo;

    public static class STCMaInfo {
        @JsonProperty("BarType")
        private String BarType;
        @JsonProperty("BarCode")
        private String BarCode;
        @JsonProperty("PowerGear")
        private String PowerGear;
        @JsonProperty("ColorGear")
        private String ColorGear;
        @JsonProperty("ELGear")
        private String ELGear;
        @JsonProperty("GradeName")
        private String GradeName;
        @JsonProperty("ShbCode")
        private String ShbCode;
        @JsonProperty("MixedBag")
        private String MixedBag;
        @JsonProperty("IVData")
        private List<IVData> IVData;
        @JsonProperty("CellData")
        private List<CellData> CellData;

        @JsonIgnore
        public String getMixedBag() {
            return MixedBag;
        }

        @JsonIgnore
        public void setMixedBag(String mixedBag) {
            MixedBag = mixedBag;
        }

        @JsonIgnore
        public String getShbCode() {
            return ShbCode;
        }

        @JsonIgnore
        public void setShbCode(String shbCode) {
            ShbCode = shbCode;
        }

        @JsonIgnore
        public String getBarType() {
            return BarType;
        }

        @JsonIgnore
        public void setBarType(String barType) {
            BarType = barType;
        }

        @JsonIgnore
        public String getBarCode() {
            return BarCode;
        }

        @JsonIgnore
        public void setBarCode(String barCode) {
            BarCode = barCode;
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
        public List<STCKMInfoGetStckmResponse.IVData> getIVData() {
            return IVData;
        }

        @JsonIgnore
        public void setIVData(List<STCKMInfoGetStckmResponse.IVData> IVData) {
            this.IVData = IVData;
        }

        @JsonIgnore
        public List<STCKMInfoGetStckmResponse.CellData> getCellData() {
            return CellData;
        }

        @JsonIgnore
        public void setCellData(List<STCKMInfoGetStckmResponse.CellData> cellData) {
            CellData = cellData;
        }
    }

    public static class IVData {
        @JsonProperty("BarCode")
        private String BarCode;
        @JsonProperty("Eta")
        private String Eta;
        @JsonProperty("Isc")
        private String Isc;
        @JsonProperty("Uoc")
        private String Uoc;
        @JsonProperty("Impp")
        private String Impp;
        @JsonProperty("Umpp")
        private String Umpp;
        @JsonProperty("Pmpp")
        private String Pmpp;
        @JsonProperty("FF")
        private String FF;
        @JsonProperty("Tcell")
        private String Tcell;
        @JsonProperty("Tmonicell")
        private String Tmonicell;
        @JsonProperty("Rser")
        private String Rser;
        @JsonProperty("Rshunt")
        private String Rshunt;
        @JsonProperty("Insol")
        private String Insol;

        @JsonIgnore
        public String getBarCode() {
            return BarCode;
        }

        @JsonIgnore
        public void setBarCode(String barCode) {
            BarCode = barCode;
        }

        @JsonIgnore
        public String getEta() {
            return Eta;
        }

        @JsonIgnore
        public void setEta(String eta) {
            Eta = eta;
        }

        @JsonIgnore
        public String getIsc() {
            return Isc;
        }

        @JsonIgnore
        public void setIsc(String isc) {
            Isc = isc;
        }

        @JsonIgnore
        public String getUoc() {
            return Uoc;
        }

        @JsonIgnore
        public void setUoc(String uoc) {
            Uoc = uoc;
        }

        @JsonIgnore
        public String getImpp() {
            return Impp;
        }

        @JsonIgnore
        public void setImpp(String impp) {
            Impp = impp;
        }

        @JsonIgnore
        public String getUmpp() {
            return Umpp;
        }

        @JsonIgnore
        public void setUmpp(String umpp) {
            Umpp = umpp;
        }

        @JsonIgnore
        public String getPmpp() {
            return Pmpp;
        }

        @JsonIgnore
        public void setPmpp(String pmpp) {
            Pmpp = pmpp;
        }

        @JsonIgnore
        public String getFF() {
            return FF;
        }

        @JsonIgnore
        public void setFF(String FF) {
            this.FF = FF;
        }

        @JsonIgnore
        public String getTcell() {
            return Tcell;
        }

        @JsonIgnore
        public void setTcell(String tcell) {
            Tcell = tcell;
        }

        @JsonIgnore
        public String getTmonicell() {
            return Tmonicell;
        }

        @JsonIgnore
        public void setTmonicell(String tmonicell) {
            Tmonicell = tmonicell;
        }

        @JsonIgnore
        public String getRser() {
            return Rser;
        }

        @JsonIgnore
        public void setRser(String rser) {
            Rser = rser;
        }

        @JsonIgnore
        public String getRshunt() {
            return Rshunt;
        }

        @JsonIgnore
        public void setRshunt(String rshunt) {
            Rshunt = rshunt;
        }

        @JsonIgnore
        public String getInsol() {
            return Insol;
        }

        @JsonIgnore
        public void setInsol(String insol) {
            Insol = insol;
        }
    }

    public static class CellData {
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
        public String getBarCode() {
            return BarCode;
        }

        @JsonIgnore
        public void setBarCode(String barCode) {
            BarCode = barCode;
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
    }

    @JsonIgnore
    public Integer getStockMaRd() {
        return StockMaRd;
    }

    @JsonIgnore
    public void setStockMaRd(Integer stockMaRd) {
        StockMaRd = stockMaRd;
    }

    @JsonIgnore
    public String getStockMaCode() {
        return StockMaCode;
    }

    @JsonIgnore
    public void setStockMaCode(String stockMaCode) {
        StockMaCode = stockMaCode;
    }

    @JsonIgnore
    public String getOutCode() {
        return OutCode;
    }

    @JsonIgnore
    public void setOutCode(String outCode) {
        OutCode = outCode;
    }

    @JsonIgnore
    public String getQCStatus() {
        return QCStatus;
    }

    @JsonIgnore
    public void setQCStatus(String QCStatus) {
        this.QCStatus = QCStatus;
    }

    @JsonIgnore
    public String getCKStatus() {
        return CKStatus;
    }

    @JsonIgnore
    public void setCKStatus(String CKStatus) {
        this.CKStatus = CKStatus;
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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public List<STCKMInfoGetStckmResponse.STCMaInfo> getSTCMaInfo() {
        return STCMaInfo;
    }

    @JsonIgnore
    public void setSTCMaInfo(List<STCKMInfoGetStckmResponse.STCMaInfo> STCMaInfo) {
        this.STCMaInfo = STCMaInfo;
    }
}

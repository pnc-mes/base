package pnc.mesadmin.dto.WmsMaterialsBDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料批次创建DTO
 * 创建人：潘俊峰
 * 创建时间：2019-10-31
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBAddReq {
    @JsonProperty("WoSource")
    private String WoSource;

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonProperty("BInfo")
    private List<BInfo> BInfo;

    @JsonProperty("PrintTRd")
    private int PrintTRd;

    @JsonProperty("PrintRd")
    private int PrintRd;

    @JsonProperty("IsPrint")
    private String IsPrint;

    @JsonProperty("PrintCopy")
    private int PrintCopy;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getWoSource() {
        return WoSource;
    }

    @JsonIgnore
    public void setWoSource(String woSource) {
        WoSource = woSource;
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
    public List<WmsMaterialsBAddReq.BInfo> getBInfo() {
        return BInfo;
    }

    @JsonIgnore
    public void setBInfo(List<WmsMaterialsBAddReq.BInfo> BInfo) {
        this.BInfo = BInfo;
    }

    @JsonIgnore
    public int getPrintTRd() {
        return PrintTRd;
    }

    @JsonIgnore
    public void setPrintTRd(int printTRd) {
        PrintTRd = printTRd;
    }

    @JsonIgnore
    public int getPrintRd() {
        return PrintRd;
    }

    @JsonIgnore
    public void setPrintRd(int printRd) {
        PrintRd = printRd;
    }

    @JsonIgnore
    public String getIsPrint() {
        return IsPrint;
    }

    @JsonIgnore
    public void setIsPrint(String isPrint) {
        IsPrint = isPrint;
    }

    @JsonIgnore
    public int getPrintCopy() {
        return PrintCopy;
    }

    @JsonIgnore
    public void setPrintCopy(int printCopy) {
        PrintCopy = printCopy;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    public static class BInfo {
        @JsonProperty("WoDlRd")
        private int WoDlRd;

        @JsonProperty("MaVerRd")
        private int MaVerRd;

        @JsonProperty("SplitBCount")
        private int SplitBCount;

        @JsonProperty("BCount")
        private BigDecimal BCount;

        @JsonProperty("IsPrintPack")
        private String IsPrintPack;

        @JsonProperty("IsPrintWP")
        private String IsPrintWP;

        @JsonProperty("GradeName")
        private String GradeName;

        @JsonProperty("BatchNo")
        private String BatchNo;

        @JsonProperty("F1")
        private String F1;

        @JsonProperty("F2")
        private String F2;

        @JsonProperty("F3")
        private String F3;

        @JsonProperty("IsQualified")
        private String IsQualified;

        @JsonIgnore
        public int getMaVerRd() {
            return MaVerRd;
        }

        @JsonIgnore
        public void setMaVerRd(int maVerRd) {
            MaVerRd = maVerRd;
        }

        @JsonIgnore
        public int getWoDlRd() {
            return WoDlRd;
        }

        @JsonIgnore
        public void setWoDlRd(int woDlRd) {
            WoDlRd = woDlRd;
        }

        @JsonIgnore
        public int getSplitBCount() {
            return SplitBCount;
        }

        @JsonIgnore
        public void setSplitBCount(int splitBCount) {
            SplitBCount = splitBCount;
        }

        @JsonIgnore
        public BigDecimal getBCount() {
            return BCount;
        }

        @JsonIgnore
        public void setBCount(BigDecimal BCount) {
            this.BCount = BCount;
        }

        @JsonIgnore
        public String getIsPrintPack() {
            return IsPrintPack;
        }

        @JsonIgnore
        public void setIsPrintPack(String isPrintPack) {
            IsPrintPack = isPrintPack;
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
        public String getBatchNo() {
            return BatchNo;
        }

        @JsonIgnore
        public void setBatchNo(String batchNo) {
            BatchNo = batchNo;
        }

        @JsonIgnore
        public String getF1() {
            return F1;
        }

        @JsonIgnore
        public void setF1(String f1) {
            F1 = f1;
        }

        @JsonIgnore
        public String getF2() {
            return F2;
        }

        @JsonIgnore
        public void setF2(String f2) {
            F2 = f2;
        }

        @JsonIgnore
        public String getF3() {
            return F3;
        }

        @JsonIgnore
        public void setF3(String f3) {
            F3 = f3;
        }

        @JsonIgnore
        public String getIsPrintWP() {
            return IsPrintWP;
        }

        @JsonIgnore
        public void setIsPrintWP(String isPrintWP) {
            IsPrintWP = isPrintWP;
        }

        @JsonIgnore
        public String getIsQualified() {
            return IsQualified;
        }

        @JsonIgnore
        public void setIsQualified(String isQualified) {
            IsQualified = isQualified;
        }
    }
}

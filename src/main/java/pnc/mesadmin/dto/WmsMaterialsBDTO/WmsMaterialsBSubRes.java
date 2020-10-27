package pnc.mesadmin.dto.WmsMaterialsBDTO;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料批次拆分查询DTO
 * 创建人：潘俊峰
 * 创建时间：2019-10-31
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBSubRes {
    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("Num")
    private BigDecimal Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("RefBatch")
    private List<RefBatch> RefBatch;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
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
    public BigDecimal getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(BigDecimal num) {
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
    public List<WmsMaterialsBSubRes.RefBatch> getRefBatch() {
        return RefBatch;
    }

    @JsonIgnore
    public void setRefBatch(List<WmsMaterialsBSubRes.RefBatch> refBatch) {
        RefBatch = refBatch;
    }

    public static class RefBatch {
        @JsonProperty("Batch")
        private String Batch;

        @JsonProperty("Num")
        private BigDecimal Num;

        @JsonProperty("UnitName")
        private String UnitName;

        @JsonProperty("Splitor")
        private String Splitor;

        @JsonProperty("SplitTime")
        private String SplitTime;

        @JsonIgnore
        public String getBatch() {
            return Batch;
        }

        @JsonIgnore
        public void setBatch(String batch) {
            Batch = batch;
        }

        @JsonIgnore
        public BigDecimal getNum() {
            return Num;
        }

        @JsonIgnore
        public void setNum(BigDecimal num) {
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
        public String getSplitor() {
            return Splitor;
        }

        @JsonIgnore
        public void setSplitor(String splitor) {
            Splitor = splitor;
        }

        @JsonIgnore
        public String getSplitTime() {
            return SplitTime;
        }

        @JsonIgnore
        public void setSplitTime(String splitTime) {
            SplitTime = splitTime;
        }
    }
}

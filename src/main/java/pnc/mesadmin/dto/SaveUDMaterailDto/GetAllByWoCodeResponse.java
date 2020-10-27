package pnc.mesadmin.dto.SaveUDMaterailDto;


import pnc.mesadmin.entity.UDMaterialLogInfo;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by yin.yang on 2019/06/15
 */
public class GetAllByWoCodeResponse {
    private Float woNum;//工单总量
    private BigDecimal specSumNum;//当前工序需要上料总量
    private List<StockBomDetail> stockBomDetails;//用料明细
    private List<UDMaterialLogInfo> details; //上下料明细信息

    //用料明细
    public static class StockBomDetail {
        private Integer bomDetailRd; //
        private String maCode; //物料编码
        private String maName;  //物料名称
        private BigDecimal stockSumNum; //备料总量
        private BigDecimal upSumNum; //累计上料总量
        private BigDecimal updSumNum; //累计下料总量
        private String unitName; //单位
        private List<StockBomDetail> StockReBomDetails; //替代料

        public Integer getBomDetailRd() {
            return bomDetailRd;
        }

        public void setBomDetailRd(Integer bomDetailRd) {
            this.bomDetailRd = bomDetailRd;
        }

        public String getMaCode() {
            return maCode;
        }

        public void setMaCode(String maCode) {
            this.maCode = maCode;
        }

        public String getMaName() {
            return maName;
        }

        public void setMaName(String maName) {
            this.maName = maName;
        }

        public BigDecimal getStockSumNum() {
            return stockSumNum;
        }

        public void setStockSumNum(BigDecimal stockSumNum) {
            this.stockSumNum = stockSumNum;
        }

        public BigDecimal getUpSumNum() {
            return upSumNum;
        }

        public void setUpSumNum(BigDecimal upSumNum) {
            this.upSumNum = upSumNum;
        }

        public BigDecimal getUpdSumNum() {
            return updSumNum;
        }

        public void setUpdSumNum(BigDecimal updSumNum) {
            this.updSumNum = updSumNum;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public List<StockBomDetail> getStockReBomDetails() {
            return StockReBomDetails;
        }

        public void setStockReBomDetails(List<StockBomDetail> stockReBomDetails) {
            StockReBomDetails = stockReBomDetails;
        }
    }

    public Float getWoNum() {
        return woNum;
    }

    public void setWoNum(Float woNum) {
        this.woNum = woNum;
    }

    public BigDecimal getSpecSumNum() {
        return specSumNum;
    }

    public void setSpecSumNum(BigDecimal specSumNum) {
        this.specSumNum = specSumNum;
    }

    public List<StockBomDetail> getStockBomDetails() {
        return stockBomDetails;
    }

    public void setStockBomDetails(List<StockBomDetail> stockBomDetails) {
        this.stockBomDetails = stockBomDetails;
    }

    public List<UDMaterialLogInfo> getDetails() {
        return details;
    }

    public void setDetails(List<UDMaterialLogInfo> details) {
        this.details = details;
    }
}

package pnc.mesadmin.dto.newmove.QCheckMaDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-26
 **/
public class QCheckMaInfoRequest {
    //请检单类型 00#生产入库请检 01#出库请检
    @JsonProperty("QCheckMaType")
    private String QCheckMaType;
    //备货单号
    @JsonProperty("StockMaCode")
    private String StockMaCode;
    //备注
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("BarInfo")
    private List<BarInfo> BarInfo;

    public static class BarInfo {
        //条码类型 00#箱 01#组件
        @JsonProperty("BarType")
        private String BarType;
        //条码
        @JsonProperty("BarCode")
        private String BarCode;
        //请检原因
        @JsonProperty("QCheckReason")
        private String QCheckReason;

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
        public String getQCheckReason() {
            return QCheckReason;
        }

        @JsonIgnore
        public void setQCheckReason(String QCheckReason) {
            this.QCheckReason = QCheckReason;
        }
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
    public String getQCheckMaType() {
        return QCheckMaType;
    }

    @JsonIgnore
    public void setQCheckMaType(String QCheckMaType) {
        this.QCheckMaType = QCheckMaType;
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
    public List<QCheckMaInfoRequest.BarInfo> getBarInfo() {
        return BarInfo;
    }

    @JsonIgnore
    public void setBarInfo(List<QCheckMaInfoRequest.BarInfo> barInfo) {
        BarInfo = barInfo;
    }
}

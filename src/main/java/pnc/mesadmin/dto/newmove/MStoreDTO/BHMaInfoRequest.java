package pnc.mesadmin.dto.newmove.MStoreDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-04-10
 **/
public class BHMaInfoRequest {
    //出库单号
    @JsonProperty(value = "OutCode")
    private String OutCode;
    //备货单号
    @JsonProperty(value = "StockMaCode")
    private String StockMaCode;
    //备注
    @JsonProperty(value = "Remark")
    private String Remark;

    @JsonProperty(value = "BarInfo")
    private List<BarInfo> BarInfo;

    public static class BarInfo {
        //任务单明细
        @JsonProperty("TkDtlRd")
        private Integer TkDtlRd;
        //条码类型 00#箱 01#组件
        @JsonProperty("BarType")
        private String BarType;
        //条码
        @JsonProperty("BarCode")
        private String BarCode;


        @JsonIgnore
        public Integer getTkDtlRd() {
            return TkDtlRd;
        }

        @JsonIgnore
        public void setTkDtlRd(Integer tkDtlRd) {
            TkDtlRd = tkDtlRd;
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
    public String getStockMaCode() {
        return StockMaCode;
    }

    @JsonIgnore
    public void setStockMaCode(String stockMaCode) {
        StockMaCode = stockMaCode;
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
    public List<BHMaInfoRequest.BarInfo> getBarInfo() {
        return BarInfo;
    }

    @JsonIgnore
    public void setBarInfo(List<BHMaInfoRequest.BarInfo> barInfo) {
        BarInfo = barInfo;
    }
}

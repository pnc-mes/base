package pnc.mesadmin.dto.newmove.STCKMDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-04-14
 **/
public class STCKMInfoGetStckmRequest {
    @JsonProperty("StockMaRd") //备货单
    private Integer StockMaRd;
    @JsonProperty("StoreRd") //仓库
    private Integer StoreRd;
    @JsonProperty("StockMaCode")
    private String StockMaCode;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("QCheckMaRd")
    private Integer QCheckMaRd;

    @JsonIgnore
    public Integer getQCheckMaRd() {
        return QCheckMaRd;
    }

    @JsonIgnore
    public void setQCheckMaRd(Integer QCheckMaRd) {
        this.QCheckMaRd = QCheckMaRd;
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
    public Integer getStoreRd() {
        return StoreRd;
    }

    @JsonIgnore
    public void setStoreRd(Integer storeRd) {
        StoreRd = storeRd;
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
}

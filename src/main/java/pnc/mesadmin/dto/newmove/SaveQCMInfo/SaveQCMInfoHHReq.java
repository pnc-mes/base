package pnc.mesadmin.dto.newmove.SaveQCMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/4/7.
 */
public class SaveQCMInfoHHReq implements Serializable {

    //备货单Rd
    @JsonProperty("StockMaRd")
    private Integer StockMaRd;
    //出货单RD
    @JsonProperty("QCheckMaRd")
    private Integer QCheckMaRd;
    //00-备货单换货，01-出库确认单换货
    @JsonProperty("ReplaceType")
    private String ReplaceType;
    //00箱，01组件
    @JsonProperty("BarType")
    private String BarType;
    //条码或者组件
    @JsonProperty("BarCode")
    private String BarCode;
    //替换条码或者组件
    @JsonProperty("ReplaceBarCode")
    private String ReplaceBarCode;
    //原因
    @JsonProperty("Remark")
    private String Remark;


    @JsonIgnore
    public Integer getStockMaRd() {
        return StockMaRd;
    }

    @JsonIgnore
    public void setStockMaRd(Integer stockMaRd) {
        StockMaRd = stockMaRd;
    }

    @JsonIgnore
    public Integer getQCheckMaRd() {
        return QCheckMaRd;
    }

    @JsonIgnore
    public void setQCheckMaRd(Integer QCheckMaRd) {
        this.QCheckMaRd = QCheckMaRd;
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
    public String getReplaceBarCode() {
        return ReplaceBarCode;
    }

    @JsonIgnore
    public void setReplaceBarCode(String replaceBarCode) {
        ReplaceBarCode = replaceBarCode;
    }

    @JsonIgnore
    public String getReplaceType() {
        return ReplaceType;
    }

    @JsonIgnore
    public void setReplaceType(String replaceType) {
        ReplaceType = replaceType;
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

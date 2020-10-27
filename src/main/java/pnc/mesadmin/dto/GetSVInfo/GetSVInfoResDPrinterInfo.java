package pnc.mesadmin.dto.GetSVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取工序DTO返回业务数据实体类Opert
 * 创建人：张亮亮
 * 创建时间：2017-12-17
 * 修改人：
 * 修改时间：
 */
public class GetSVInfoResDPrinterInfo implements Serializable{

    @JsonProperty("PrRd")
    private int PrRd;

    @JsonProperty("PrName")
    private String PrName;

    @JsonIgnore
    public int getPrRd() {
        return PrRd;
    }
    @JsonIgnore
    public void setPrRd(int prRd) {
        PrRd = prRd;
    }
    @JsonIgnore
    public String getPrName() {
        return PrName;
    }
    @JsonIgnore
    public void setPrName(String prName) {
        PrName = prName;
    }
}

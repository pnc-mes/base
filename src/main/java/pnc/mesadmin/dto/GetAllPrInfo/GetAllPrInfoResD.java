package pnc.mesadmin.dto.GetAllPrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印机信息列表DTO返回业务实体类data
 * 创建人：刘福志
 * 创建时间：2017/5/27
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllPrInfoResD implements Serializable{

    @JsonProperty("PrRd")
    private int PrRd;

    @JsonProperty("PrName")
    private  String PrName;

    @JsonProperty("PrSer")
    private  String PrSer;

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

    @JsonIgnore
    public String getPrSer() {
        return PrSer;
    }

    @JsonIgnore
    public void setPrSer(String prSer) {
        PrSer = prSer;
    }
}

package pnc.mesadmin.dto.WmsMaterialsBDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料批次库存DTO
 * 创建人：潘俊峰
 * 创建时间：2019-10-31
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBStoreReq {
    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("StoreCode")
    private String StoreCode;

    @JsonProperty("StartDate")
    private String StartDate;

    @JsonProperty("EndDate")
    private String EndDate;

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getStoreCode() {
        return StoreCode;
    }

    @JsonIgnore
    public void setStoreCode(String storeCode) {
        StoreCode = storeCode;
    }

    @JsonIgnore
    public String getStartDate() {
        return StartDate;
    }

    @JsonIgnore
    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    @JsonIgnore
    public String getEndDate() {
        return EndDate;
    }

    @JsonIgnore
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}

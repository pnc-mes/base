package pnc.mesadmin.dto.GetMaTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：根据工单ID获取用料汇总信息
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public class GetMaTotalInfoReq00 {
    @JsonProperty("AssSource")
    private String AssSource;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }
    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }
    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
    }
    @JsonIgnore
    public int getStoreRd() {
        return StoreRd;
    }
    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
    }
}

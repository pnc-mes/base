package pnc.mesadmin.dto.GetPDInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：盘点单信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-6-10
 * 修改人：
 * 修改时间：
 */
public class GetPDInfoResD implements Serializable{

    @JsonProperty("PDRd")
    private int PDRd;

    @JsonProperty("PDCode")
    private String PDCode;

    @JsonProperty("StoreInfo")
    private List<GetPDInfoResDStoreInfo> StoreInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getPDRd() {
        return PDRd;
    }

    @JsonIgnore
    public void setPDRd(int PDRd) {
        this.PDRd = PDRd;
    }

    @JsonIgnore
    public String getPDCode() {
        return PDCode;
    }

    @JsonIgnore
    public void setPDCode(String PDCode) {
        this.PDCode = PDCode;
    }

    @JsonIgnore
    public List<GetPDInfoResDStoreInfo> getStoreInfo() {
        return StoreInfo;
    }

    @JsonIgnore
    public void setStoreInfo(List<GetPDInfoResDStoreInfo> storeInfo) {
        StoreInfo = storeInfo;
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

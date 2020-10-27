package pnc.mesadmin.dto.SaveRKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：  新增入库单信息DTO返回业务数据实体类Res
 * 创建人：张亮亮
 * 创建时间：2017-06-12
 * 修改人：
 * 修改时间：
 */
public class SaveRKInfoReqBD00 implements Serializable{

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("RkType")
    private String RkType;

    @JsonProperty("RKMaInfo")
    private List<SaveRKInfoReqBD00RkMa> RKMaInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getRkType() {
        return RkType;
    }

    @JsonIgnore
    public void setRkType(String rkType) {
        RkType = rkType;
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
    public int getStoreRd() {
        return StoreRd;
    }

    @JsonIgnore
    public void setStoreRd(int storeRd) {
        StoreRd = storeRd;
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
    public List<SaveRKInfoReqBD00RkMa> getRKMaInfo() {
        return RKMaInfo;
    }

    @JsonIgnore
    public void setRKMaInfo(List<SaveRKInfoReqBD00RkMa> RKMaInfo) {
        this.RKMaInfo = RKMaInfo;
    }
}

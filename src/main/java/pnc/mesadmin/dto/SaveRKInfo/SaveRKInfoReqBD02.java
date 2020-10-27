package pnc.mesadmin.dto.SaveRKInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：  编辑入库单信息DTO返回业务数据实体类
 * 创建人：张亮亮
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public class SaveRKInfoReqBD02 implements Serializable{

    @JsonProperty("RkRd")
    private int RkRd;

    @JsonProperty("StoreRd")
    private int StoreRd;

    @JsonProperty("AssCode")
    private String AssCode;


    @JsonProperty("AssSource")
    private String AssSource;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("RKMaInfo")
    private List<SaveRKInfoReqBD02RkMa> RKMaInfo;

    @JsonIgnore
    public int getRkRd() {
        return RkRd;
    }

    @JsonIgnore
    public void setRkRd(int rkRd) {
        RkRd = rkRd;
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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public List<SaveRKInfoReqBD02RkMa> getRKMaInfo() {
        return RKMaInfo;
    }

    @JsonIgnore
    public void setRKMaInfo(List<SaveRKInfoReqBD02RkMa> RKMaInfo) {
        this.RKMaInfo = RKMaInfo;
    }
    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }
}

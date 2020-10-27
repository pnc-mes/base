package pnc.mesadmin.dto.SaveCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 11:16
 * @Description:
 */
public class SaveCDataInfoReq02 implements Serializable {
    @JsonProperty("CusDataRd")
    private int CusDataRd;

    @JsonProperty("CusDataName")
    private String CusDataName;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("CusDataDtl")
    private List<SaveCDataInfoReq02CusDataDtl> CusDataDtl;

    @JsonIgnore
    public int getCusDataRd() {
        return CusDataRd;
    }
    @JsonIgnore
    public void setCusDataRd(int cusDataRd) {
        CusDataRd = cusDataRd;
    }

    @JsonIgnore
    public String getCusDataName() {
        return CusDataName;
    }
    @JsonIgnore
    public void setCusDataName(String cusDataName) {
        CusDataName = cusDataName;
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
    public List<SaveCDataInfoReq02CusDataDtl> getCusDataDtl() {
        return CusDataDtl;
    }
    @JsonIgnore
    public void setCusDataDtl(List<SaveCDataInfoReq02CusDataDtl> cusDataDtl) {
        CusDataDtl = cusDataDtl;
    }
}

package pnc.mesadmin.dto.SaveRCGpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：
 * 创建人：
 * 创建时间：
 * 修改人：
 * 修改时间：
 */
public class SaveRCGpInfoReq00 implements Serializable{
    @JsonProperty("RCGpName")
    private String RCGpName;

    @JsonProperty("RCInfo")
    private List<SaveRCGpInfoReq00RCInfo> RCInfo = new ArrayList<SaveRCGpInfoReq00RCInfo>();

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getRCGpName() {
        return RCGpName;
    }
    @JsonIgnore
    public void setRCGpName(String RCGpName) {
        this.RCGpName = RCGpName;
    }
    @JsonIgnore
    public List<SaveRCGpInfoReq00RCInfo> getRCInfo() {
        return RCInfo;
    }
    @JsonIgnore
    public void setRCInfo(List<SaveRCGpInfoReq00RCInfo> RCInfo) {
        this.RCInfo = RCInfo;
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

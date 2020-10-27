package pnc.mesadmin.dto.SaveDevGPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存设备组信息DTO请求业务数据实体类BD00：新增
 * 创建人：刘福志
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class SaveDevGpInfoReqBD00 implements Serializable{

    @JsonProperty("DevGpName")
    private String DevGpName;

    @JsonProperty("DevInfo")
    private List<SaveDevGpInfoReqBD00DevInfo> DevInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public List<SaveDevGpInfoReqBD00DevInfo> getDevInfo() {
        return DevInfo;
    }

    @JsonIgnore
    public void setDevInfo(List<SaveDevGpInfoReqBD00DevInfo> devInfo) {
        DevInfo = devInfo;
    }

    @JsonIgnore
    public String getDevGpName() {
        return DevGpName;
    }

    @JsonIgnore
    public void setDevGpName(String devGpName) {
        DevGpName = devGpName;
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

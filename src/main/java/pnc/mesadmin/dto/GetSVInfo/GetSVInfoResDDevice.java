package pnc.mesadmin.dto.GetSVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取工序DTO返回业务数据实体类Device
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public class GetSVInfoResDDevice implements Serializable{

    @JsonProperty("DevGpRd")
    private int DevGpRd;

    @JsonProperty("DevGpName")
    private String DevGpName;

    @JsonIgnore
    public int getDevGpRd() {
        return DevGpRd;
    }

    @JsonIgnore
    public void setDevGpRd(int devGpRd) {
        DevGpRd = devGpRd;
    }

    @JsonIgnore
    public String getDevGpName() {
        return DevGpName;
    }

    @JsonIgnore
    public void setDevGpName(String devGpName) {
        DevGpName = devGpName;
    }
}

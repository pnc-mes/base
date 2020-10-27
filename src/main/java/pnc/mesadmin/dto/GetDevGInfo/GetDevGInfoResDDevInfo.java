package pnc.mesadmin.dto.GetDevGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备组信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public class GetDevGInfoResDDevInfo implements Serializable {
    @JsonProperty("DevRd")
    private int DevRd;

    @JsonProperty("DevName")
    private String DevName;

    @JsonIgnore
    public int getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(int devRd) {
        DevRd = devRd;
    }

    @JsonIgnore
    public String getDevName() {
        return DevName;
    }

    @JsonIgnore
    public void setDevName(String devName) {
        DevName = devName;
    }

}

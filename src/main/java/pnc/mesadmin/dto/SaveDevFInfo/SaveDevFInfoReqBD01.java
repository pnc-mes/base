package pnc.mesadmin.dto.SaveDevFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：删除设备家族信息DTO请求业务数据实体类BD01
 * 创建人：刘福志
 * 创建时间：2017-8-16
 * 修改人：
 * 修改时间：
 */
public class SaveDevFInfoReqBD01 implements Serializable {
    @JsonProperty("DevFRd")
    private int DevFRd;

    @JsonIgnore
    public int getDevFRd() {
        return DevFRd;
    }

    @JsonIgnore
    public void setDevFRd(int devFRd) {
        DevFRd = devFRd;
    }
}

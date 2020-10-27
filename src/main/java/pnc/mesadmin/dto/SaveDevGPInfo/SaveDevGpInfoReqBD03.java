package pnc.mesadmin.dto.SaveDevGPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存设备组信息DTO请求业务数据实体类BD03：复制
 * 创建人：刘福志
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public class SaveDevGpInfoReqBD03 implements Serializable {
    @JsonProperty("DevGRd")
    private int DevGRd;

    @JsonIgnore
    public int getDevGRd() {
        return DevGRd;
    }

    @JsonIgnore
    public void setDevGRd(int devGRd) {
        DevGRd = devGRd;
    }
}

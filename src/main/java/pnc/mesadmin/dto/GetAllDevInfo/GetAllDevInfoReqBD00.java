package pnc.mesadmin.dto.GetAllDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备信息列表DTO业务请求数据实体类ReqBD00
 * 创建人：刘福志
 * 创建时间：2017-6-13
 * 修改人：
 * 修改时间：
 */
public class GetAllDevInfoReqBD00 implements Serializable{

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

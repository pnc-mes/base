package pnc.mesadmin.dto.GetAllDevTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备类型信息列表DTO返回业务实体类data
 * 创建人：蒋顾毅
 * 创建时间：2020/9/17
 * 修改人：
 * 修改时间：
 *
 */
public class GetAllDevTypeInfoResD implements Serializable {

    @JsonProperty("DevTypeRd")
    private int DevTypeRd;

    @JsonProperty("DevType")
    private String DevType;

    @JsonIgnore
    public int getDevTypeRd() {
        return DevTypeRd;
    }

    @JsonIgnore
    public void setDevTypeRd(int devTypeRd) {
        DevTypeRd = devTypeRd;
    }

    @JsonIgnore
    public String getDevType() {
        return DevType;
    }

    @JsonIgnore
    public void setDevType(String devType) {
        DevType= devType;
    }
}

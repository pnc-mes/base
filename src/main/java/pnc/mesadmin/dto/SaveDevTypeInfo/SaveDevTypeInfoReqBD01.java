package pnc.mesadmin.dto.SaveDevTypeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存设备类型信息DTO请求业务数据实体类BD01：删除
 * 创建人：蒋顾毅
 * 创建时间：2020-9-17
 * 修改人：
 * 修改时间：
 */
public class SaveDevTypeInfoReqBD01 implements Serializable {

    @JsonProperty("DevTypeRd")
    private int DevTypeRd;

    @JsonIgnore
    public int getDevTypeRd() {
        return DevTypeRd;
    }

    @JsonIgnore
    public void setDevTypeRd(int devTypeRd) {
        DevTypeRd = devTypeRd;
    }
}

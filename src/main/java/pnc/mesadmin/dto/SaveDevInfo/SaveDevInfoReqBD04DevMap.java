package pnc.mesadmin.dto.SaveDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存设备信息DTO请求业务数据实体类BD04,设备映射
 * 创建人：郝赞
 * 创建时间：2018-10-9
 * 修改人：
 * 修改时间：
 */
public class SaveDevInfoReqBD04DevMap implements Serializable {
    @JsonProperty("DevMapName")
    private String DevMapName;
    @JsonProperty("DevMapCode")
    private String DevMapCode;


    @JsonIgnore
    public String getDevMapCode() {
        return DevMapCode;
    }
    @JsonIgnore
    public void setDevMapCode(String devMapCode) {
        DevMapCode = devMapCode;
    }
    @JsonIgnore
    public String getDevMapName() {
        return DevMapName;
    }
    @JsonIgnore
    public void setDevMapName(String devMapName) {
        DevMapName = devMapName;
    }
}

package pnc.mesadmin.dto.SaveDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存设备信息DTO请求业务数据实体类BD04,设备映射
 * 创建人：郝赞
 * 创建时间：2018-10-9
 * 修改人：
 * 修改时间：
 */
public class SaveDevInfoReqBD04 implements Serializable {
    @JsonProperty("DevRd")
    private Integer DevRd;
    @JsonProperty("DevMapCode")
    private List<SaveDevInfoReqBD04DevMap> DevMapInfo;

    @JsonIgnore
    public Integer getDevRd() {
        return DevRd;
    }
    @JsonIgnore
    public void setDevRd(Integer devRd) {
        DevRd = devRd;
    }
    @JsonIgnore
    public List<SaveDevInfoReqBD04DevMap> getDevMapInfo() {
        return DevMapInfo;
    }
    @JsonIgnore
    public void setDevMapInfo(List<SaveDevInfoReqBD04DevMap> devMapInfo) {
        DevMapInfo = devMapInfo;
    }
}

package pnc.mesadmin.dto.GetWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单列表返回的UrcyInfo
 * 创建人：张亮亮
 * 创建时间：2017-08-28
 * 修改人：
 * 修改时间：
 */
public class GetWOInfoResBDUrcy implements Serializable{

    @JsonProperty("UrcyRd")
    private int UrcyRd;

    @JsonProperty("UrcyDes")
    private String UrcyDes;

    @JsonIgnore
    public int getUrcyRd() {
        return UrcyRd;
    }
    @JsonIgnore
    public void setUrcyRd(int urcyRd) {
        UrcyRd = urcyRd;
    }
    @JsonIgnore
    public String getUrcyDes() {
        return UrcyDes;
    }
    @JsonIgnore
    public void setUrcyDes(String urcyDes) {
        UrcyDes = urcyDes;
    }
}

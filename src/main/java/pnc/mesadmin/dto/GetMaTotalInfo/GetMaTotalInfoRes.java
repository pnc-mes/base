package pnc.mesadmin.dto.GetMaTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取用料汇总信息Res
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public class GetMaTotalInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetMaTotalInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetMaTotalInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetMaTotalInfoResB body) {
        Body = body;
    }
}

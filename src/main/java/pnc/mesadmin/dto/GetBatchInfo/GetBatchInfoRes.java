package pnc.mesadmin.dto.GetBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取批次信息返回实体
 * 创建人：王怀龙
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class GetBatchInfoRes {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetBatchInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetBatchInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetBatchInfoResB body) {
        Body = body;
    }


}

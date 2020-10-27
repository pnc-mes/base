package pnc.mesadmin.dto.GetAllBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResB;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取批次列表信息返回报文实体
 * 创建人：王怀龙
 * 创建时间：2017-06-09
 * 修改人：
 * 修改时间：
 */
public class GetAllBatchInfoRes {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetAllBatchInfoResB Body;
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetAllBatchInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetAllBatchInfoResB body) {
        Body = body;
    }


}

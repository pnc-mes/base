package pnc.mesadmin.dto.GetAllRejectInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码DTO返回业务数据实体类res
 * 创建人：郝赞
 * 创建时间：2018-8-6
 * 修改人：
 * 修改时间：
 */
public class GetAllRejectInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllRejectInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllRejectInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllRejectInfoResB body) {
        Body = body;
    }
}

package pnc.mesadmin.dto.GetAllSWareInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：库存预警报表DTO返回业务数据实体类res
 * 创建人：刘福志
 * 创建时间：2017-9-22
 * 修改人：
 * 修改时间：
 */
public class GetAllSWareInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllSWareInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllSWareInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllSWareInfoResB body) {
        Body = body;
    }
}

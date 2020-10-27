package pnc.mesadmin.dto.GetAllIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取质检批次列表信息返回报文实体
 * 创建人：王怀龙
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public class GetAllIQCBInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllIQCBInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllIQCBInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllIQCBInfoResB body) {
        Body = body;
    }
}

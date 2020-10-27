package pnc.mesadmin.dto.GetAllWOBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单批次列表返回的Res
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class GetAllWOBInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllWOBInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllWOBInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllWOBInfoResB body) {
        Body = body;
    }
}

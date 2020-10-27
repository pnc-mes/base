package pnc.mesadmin.dto.GetNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/10/24.
 */
public class GetNPickInfoRes {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Body")
    private GetNPickInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetNPickInfoResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetNPickInfoResB body) {
        Body = body;
    }
}

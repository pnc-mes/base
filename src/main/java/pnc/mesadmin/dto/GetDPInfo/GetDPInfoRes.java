package pnc.mesadmin.dto.GetDPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * Created by panjunfeng on 2017/11/3.
 */
public class GetDPInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetDPInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetDPInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetDPInfoResB body) {
        Body = body;
    }
}

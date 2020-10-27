package pnc.mesadmin.dto.GetAllKCRecInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-WES管理系统
 * Created by liufuzhi on 2017/10/25.
 */
public class GetAllKCRecInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetAllKCRecInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public GetAllKCRecInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(GetAllKCRecInfoResB body) {
        Body = body;
    }
}

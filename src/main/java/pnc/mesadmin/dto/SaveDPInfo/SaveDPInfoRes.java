package pnc.mesadmin.dto.SaveDPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * Created by panjunfeng on 2017/11/3.
 */
public class SaveDPInfoRes {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveDPInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveDPInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveDPInfoResB body) {
        Body = body;
    }
}

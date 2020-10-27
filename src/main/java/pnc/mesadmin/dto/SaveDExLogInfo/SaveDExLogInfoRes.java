package pnc.mesadmin.dto.SaveDExLogInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveDicInfo.SaveDicInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/10/22 17:51
 * @Description:
 */
public class SaveDExLogInfoRes implements Serializable {

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveDExLogInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveDExLogInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveDExLogInfoResB body) {
        Body = body;
    }
}

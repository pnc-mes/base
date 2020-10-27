package pnc.mesadmin.dto.SaveEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveFaInfo.SaveFaInfoResB;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 20:13
 * @Description:
 */
public class SaveEmailDisInfoRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveEmailDisInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveEmailDisInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveEmailDisInfoResB body) {
        Body = body;
    }
}

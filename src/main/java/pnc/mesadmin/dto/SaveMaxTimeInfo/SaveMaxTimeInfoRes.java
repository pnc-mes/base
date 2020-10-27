package pnc.mesadmin.dto.SaveMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveMaInfo.SaveMaInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 16:12
 * @Description:
 */
public class SaveMaxTimeInfoRes  implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveMaxTimeInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveMaxTimeInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveMaxTimeInfoResB body) {
        Body = body;
    }
}

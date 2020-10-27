package pnc.mesadmin.dto.SaveCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoResB;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 11:15
 * @Description:
 */
public class SaveCDataInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveCDataInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveCDataInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveCDataInfoResB body) {
        Body = body;
    }
}

package pnc.mesadmin.dto.ExportSWareInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/25.
 */
public class ExportSWareInfoRes implements Serializable {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private ExportSWareInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public ExportSWareInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(ExportSWareInfoResB body) {
        Body = body;
    }
}

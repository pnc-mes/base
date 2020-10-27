package pnc.mesadmin.dto.GetPickInfoResDExcel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liufuzhi on 2017/12/18.
 */
public class GetPickInfoResDExcelRes {
    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private GetPickInfoResDExcelResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }
    @JsonIgnore
    public GetPickInfoResDExcelResB getBody() {
        return Body;
    }
    @JsonIgnore
    public void setBody(GetPickInfoResDExcelResB body) {
        Body = body;
    }
}

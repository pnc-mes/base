package pnc.mesadmin.dto.GYMonitor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description: mesadmin
 * Created By panjunfeng on 2018/11/9
 */
public class SaveGYYJReqBD00CollectParam {
    @JsonProperty("DataAlias")
    private String DataAlias;

    @JsonProperty("DataVal")
    private String DataVal;

    @JsonIgnore
    public String getDataAlias() {
        return DataAlias;
    }

    @JsonIgnore
    public void setDataAlias(String dataAlias) {
        DataAlias = dataAlias;
    }

    @JsonIgnore
    public String getDataVal() {
        return DataVal;
    }

    @JsonIgnore
    public void setDataVal(String dataVal) {
        DataVal = dataVal;
    }
}

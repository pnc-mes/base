package pnc.mesadmin.dto.SaveOpertInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/3.
 */
public class SaveOpertInfoResD {
    @JsonProperty("Data")
    private String Data;

    @JsonIgnore
    public String getData() {
        return Data;
    }
    @JsonIgnore
    public void setData(String data) {
        Data = data;
    }
}

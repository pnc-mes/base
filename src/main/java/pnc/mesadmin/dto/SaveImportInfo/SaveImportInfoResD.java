package pnc.mesadmin.dto.SaveImportInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/2.
 */
public class SaveImportInfoResD implements Serializable {
    @JsonProperty("PDCode")
    private String PDCode;

    @JsonIgnore
    public String getPDCode() {
        return PDCode;
    }

    @JsonIgnore
    public void setPDCode(String PDCode) {
        this.PDCode = PDCode;
    }
}

package pnc.mesadmin.dto.SaveIQCBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/12.
 */
public class SaveIQCBInfoReqBD01 {
    @JsonProperty("IQCRd")
    private int IQCRd;
    @JsonIgnore
    public int getIQCRd() {
        return IQCRd;
    }
@JsonIgnore
    public void setIQCRd(int IQCRd) {
        this.IQCRd = IQCRd;
    }


}

package pnc.mesadmin.dto.GetAllPtInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/27.
 */
public class GetAllPtInfoResD implements Serializable{

    @JsonProperty("PtRd")
    private int PtRd;

    @JsonProperty("PtName")
    private String PtName;

    @JsonIgnore
    public int getPtRd() {
        return PtRd;
    }

    @JsonIgnore
    public void setPtRd(int ptRd) {
        PtRd = ptRd;
    }

    @JsonIgnore
    public String getPtName() {
        return PtName;
    }

    @JsonIgnore
    public void setPtName(String ptName) {
        PtName = ptName;
    }
}

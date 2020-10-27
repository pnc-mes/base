package pnc.mesadmin.dto.GetSVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/7/14.
 */
public class GetSVTreeInfoReq00 implements Serializable {
    @JsonProperty("SpecRd")
    private int SpecRd;

    @JsonIgnore
    public int getSpecRd() {
        return SpecRd;
    }

    @JsonIgnore
    public void setSpecRd(int specRd) {
        SpecRd = specRd;
    }
}

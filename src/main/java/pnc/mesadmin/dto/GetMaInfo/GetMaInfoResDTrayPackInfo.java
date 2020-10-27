package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMaInfoResDTrayPackInfo {

    @JsonProperty("PackSpRd")
    private int PackSpRd;

    @JsonProperty("PackName")
    private String PackName;

    @JsonIgnore
    public int getPackSpRd() {
        return PackSpRd;
    }

    @JsonIgnore
    public void setPackSpRd(int packSpRd) {
        PackSpRd = packSpRd;
    }

    @JsonIgnore
    public String getPackName() {
        return PackName;
    }

    @JsonIgnore
    public void setPackName(String packName) {
        PackName = packName;
    }
}

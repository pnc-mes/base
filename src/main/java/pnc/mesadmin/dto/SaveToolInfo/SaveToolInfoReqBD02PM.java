package pnc.mesadmin.dto.SaveToolInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang2
 * @Date: 2018/9/13 13:52
 * @Description:
 */
public class SaveToolInfoReqBD02PM {
    @JsonProperty("PMRd")
    private int PMRd;

    @JsonProperty("PMType")
    private String  PMType;
    @JsonIgnore
    public int getPMRd() {
        return PMRd;
    }
    @JsonIgnore
    public void setPMRd(int PMRd) {
        this.PMRd = PMRd;
    }
    @JsonIgnore
    public String getPMType() {
        return PMType;
    }
    @JsonIgnore
    public void setPMType(String PMType) {
        this.PMType = PMType;
    }
}

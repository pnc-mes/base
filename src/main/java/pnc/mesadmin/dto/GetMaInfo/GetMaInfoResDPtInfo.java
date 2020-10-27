package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/28 10:16
 * @Description:
 */
public class GetMaInfoResDPtInfo implements Serializable {
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

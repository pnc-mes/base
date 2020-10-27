package pnc.mesadmin.dto.SaveSPartInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 16:54
 * @Description:
 */
public class SaveSPartInfoReq01 implements Serializable {
    @JsonProperty("SPartRd")
    private int SPartRd;

    @JsonIgnore
    public int getSPartRd() {
        return SPartRd;
    }
    @JsonIgnore
    public void setSPartRd(int SPartRd) {
        this.SPartRd = SPartRd;
    }
}

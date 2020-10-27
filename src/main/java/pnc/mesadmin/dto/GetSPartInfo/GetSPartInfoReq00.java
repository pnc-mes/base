package pnc.mesadmin.dto.GetSPartInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 16:29
 * @Description:
 */
public class GetSPartInfoReq00 implements Serializable {
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

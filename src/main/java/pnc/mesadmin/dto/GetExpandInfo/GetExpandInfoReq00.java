package pnc.mesadmin.dto.GetExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 17:31
 * @Description:
 */
public class GetExpandInfoReq00 implements Serializable {
    @JsonProperty("ExpandRd")
    private int ExpandRd;

    @JsonIgnore
    public int getExpandRd() {
        return ExpandRd;
    }
    @JsonIgnore
    public void setExpandRd(int expandRd) {
        ExpandRd = expandRd;
    }
}

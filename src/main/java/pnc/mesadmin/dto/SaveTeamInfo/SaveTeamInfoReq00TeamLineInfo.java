package pnc.mesadmin.dto.SaveTeamInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/14 09:41
 * @Description:
 */
public class SaveTeamInfoReq00TeamLineInfo {
    @JsonProperty("LineRd")
    private int LineRd;
    @JsonIgnore
    public int getLineRd() {
        return LineRd;
    }
    @JsonIgnore
    public void setLineRd(int lineRd) {
        LineRd = lineRd;
    }
}

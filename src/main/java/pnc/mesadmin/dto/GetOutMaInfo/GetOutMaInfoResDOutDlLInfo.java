package pnc.mesadmin.dto.GetOutMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 14:41
 * @Description:
 */
public class GetOutMaInfoResDOutDlLInfo {
    @JsonProperty("LRd")
    private int LRd;

    @JsonProperty("LName")
    private String LName;
    @JsonIgnore
    public int getLRd() {
        return LRd;
    }
    @JsonIgnore
    public void setLRd(int LRd) {
        this.LRd = LRd;
    }
    @JsonIgnore
    public String getLName() {
        return LName;
    }
    @JsonIgnore
    public void setLName(String LName) {
        this.LName = LName;
    }
}

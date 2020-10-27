package pnc.mesadmin.dto.SaveLInfo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @ClassName: SaveLInfoResD 
 * @Description: TODO 保存
 * @author: linyichun
 * @date: 2017-6-9 上午12:26:56
 */
public class SaveLInfoResD {
    @JsonProperty("LRd")
    private int LRd;

    @JsonProperty("LCode")
    private String LCode;

    @JsonIgnore
    public int getLRd() {
        return LRd;
    }

    @JsonIgnore
    public void setLRd(int LRd) {
        this.LRd = LRd;
    }

    @JsonIgnore
    public String getLCode() {
        return LCode;
    }

    @JsonIgnore
    public void setLCode(String LCode) {
        this.LCode = LCode;
    }
}

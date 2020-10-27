package pnc.mesadmin.dto.SaveEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 20:14
 * @Description:
 */
public class SaveEmailDisInfoReq01 {
    @JsonProperty("EmailDisRd")
    private int EmailDisRd;
    @JsonIgnore
    public int getEmailDisRd() {
        return EmailDisRd;
    }
    @JsonIgnore
    public void setEmailDisRd(int emailDisRd) {
        EmailDisRd = emailDisRd;
    }
}

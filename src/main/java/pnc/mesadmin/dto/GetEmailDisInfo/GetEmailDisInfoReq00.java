package pnc.mesadmin.dto.GetEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:47
 * @Description:
 */
public class GetEmailDisInfoReq00 {
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

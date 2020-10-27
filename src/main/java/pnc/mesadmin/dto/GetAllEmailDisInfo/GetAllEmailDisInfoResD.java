package pnc.mesadmin.dto.GetAllEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:17
 * @Description:
 */
public class GetAllEmailDisInfoResD {
    @JsonProperty("EmailDisRd")
    private int EmailDisRd;

    @JsonProperty("EmailDisName")
    private String EmailDisName;
    @JsonIgnore
    public int getEmailDisRd() {
        return EmailDisRd;
    }
    @JsonIgnore
    public void setEmailDisRd(int emailDisRd) {
        EmailDisRd = emailDisRd;
    }
    @JsonIgnore
    public String getEmailDisName() {
        return EmailDisName;
    }
    @JsonIgnore
    public void setEmailDisName(String emailDisName) {
        EmailDisName = emailDisName;
    }
}

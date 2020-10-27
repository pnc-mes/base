package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDStartEmailDistribution implements Serializable {
    @JsonProperty("EmailDistributionRd")
    private int EmailDistributionRd;

    @JsonProperty("EmailDistributionName")
    private String EmailDistributionName;


    @JsonIgnore
    public int getEmailDistributionRd() {
        return EmailDistributionRd;
    }
    @JsonIgnore
    public void setEmailDistributionRd(int emailDistributionRd) {
        EmailDistributionRd = emailDistributionRd;
    }
    @JsonIgnore
    public String getEmailDistributionName() {
        return EmailDistributionName;
    }
    @JsonIgnore
    public void setEmailDistributionName(String emailDistributionName) {
        EmailDistributionName = emailDistributionName;
    }
}

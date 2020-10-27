package pnc.mesadmin.dto.GetMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 11:37
 * @Description:
 */
public class GetMaxTimeInfoResDOverdueEmailDistribution {
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

package pnc.mesadmin.dto.GetFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by haozan on 2018/9/7.
 */
public class StartEmailDistributionInfo {
    @JsonProperty("EmailDistributionRd")
    private Integer EmailDistributionRd;
    @JsonProperty("EmailDistributionName")
    private String EmailDistributionName;
    @JsonIgnore
    public Integer getEmailDistributionRd() {
        return EmailDistributionRd;
    }
    @JsonIgnore
    public void setEmailDistributionRd(Integer emailDistributionRd) {
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

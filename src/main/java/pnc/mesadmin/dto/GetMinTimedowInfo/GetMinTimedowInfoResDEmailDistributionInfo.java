package pnc.mesadmin.dto.GetMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetMinTimedowInfoResDEmailDistributionInfo implements Serializable{

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

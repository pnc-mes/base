package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 17:58
 * @Description:
 */
public class EmailAddressInfo {
    private int ruid;
    private String distributionGd;
    private String emailAddress;
    private String recipient;
    private int sequence;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getDistributionGd() {
        return distributionGd;
    }

    public void setDistributionGd(String distributionGd) {
        this.distributionGd = distributionGd;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}

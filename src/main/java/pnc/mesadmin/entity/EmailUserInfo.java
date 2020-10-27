package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 17:57
 * @Description:
 */
public class EmailUserInfo {
    private int ruid;
    private String distributionGd;
    private String userGd;
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

    public String getUserGd() {
        return userGd;
    }

    public void setUserGd(String userGd) {
        this.userGd = userGd;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}

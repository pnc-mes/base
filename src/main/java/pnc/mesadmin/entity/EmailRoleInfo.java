package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 17:58
 * @Description:
 */
public class EmailRoleInfo {
    private int ruid;
    private String distributionGd;
    private String roleGd;
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

    public String getRoleGd() {
        return roleGd;
    }

    public void setRoleGd(String roleGd) {
        this.roleGd = roleGd;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}

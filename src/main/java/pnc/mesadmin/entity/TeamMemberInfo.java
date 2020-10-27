package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/3 09:37
 * @Description:
 */
public class TeamMemberInfo {
    private int ruid;
    private String userGd;
    private String teamGd;
    private int sequence;


    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getUserGd() {
        return userGd;
    }

    public void setUserGd(String userGd) {
        this.userGd = userGd;
    }

    public String getTeamGd() {
        return teamGd;
    }

    public void setTeamGd(String teamGd) {
        this.teamGd = teamGd;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}

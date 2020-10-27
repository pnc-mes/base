package pnc.mesadmin.entity;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/14 09:23
 * @Description:
 */
public class TeamLineInfo {
    private int ruid;
    private String teamGd;
    private String lineGd;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getTeamGd() {
        return teamGd;
    }

    public void setTeamGd(String teamGd) {
        this.teamGd = teamGd;
    }

    public String getLineGd() {
        return lineGd;
    }

    public void setLineGd(String lineGd) {
        this.lineGd = lineGd;
    }
}

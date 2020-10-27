package pnc.mesadmin.entity.vo;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/1/18 08:53
 * @Description: 在线追踪的结果集里返回的信息，前el，后el，终检等信息
 */
public class OnlineAfterVo {
    private Date CreateTime;
    private String Creator;
    private String GradeName;
    private String BadCode;
    private String BadName;
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    public String getBadCode() {
        return BadCode;
    }

    public void setBadCode(String badCode) {
        BadCode = badCode;
    }

    public String getBadName() {
        return BadName;
    }

    public void setBadName(String badName) {
        BadName = badName;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

}

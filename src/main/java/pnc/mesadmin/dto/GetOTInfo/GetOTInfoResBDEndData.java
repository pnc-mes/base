package pnc.mesadmin.dto.GetOTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/1/17 20:24
 * @Description:
 */
public class GetOTInfoResBDEndData implements Serializable {
    @JsonProperty("CreateTime")
    private String CreateTime;  //操作时间

    @JsonProperty("StationName")
    private String StationName; //工位名称

    @JsonProperty("GradeName")
    private String GradeName; //等级名称

    @JsonProperty("BadCode")
    private String BadCode; //不良代码

    @JsonProperty("BadName")
    private String BadName; //不良名称

    @JsonProperty("Creator")
    private String Creator; //操作人
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public String getStationName() {
        return StationName;
    }
    @JsonIgnore
    public void setStationName(String stationName) {
        StationName = stationName;
    }
    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }
    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }
    @JsonIgnore
    public String getBadCode() {
        return BadCode;
    }
    @JsonIgnore
    public void setBadCode(String badCode) {
        BadCode = badCode;
    }
    @JsonIgnore
    public String getBadName() {
        return BadName;
    }
    @JsonIgnore
    public void setBadName(String badName) {
        BadName = badName;
    }
    @JsonIgnore
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }
}

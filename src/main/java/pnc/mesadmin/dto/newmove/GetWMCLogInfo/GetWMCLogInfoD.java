package pnc.mesadmin.dto.newmove.GetWMCLogInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by 郝赞 on 2018/12/19.
 */
public class GetWMCLogInfoD implements Serializable {

    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("Eff")
    private String Eff;

    @JsonProperty("Color")
    private String Color;
    @JsonProperty("Grade")
    private String Grade;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }
    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }
    @JsonIgnore
    public String getEff() {
        return Eff;
    }
    @JsonIgnore
    public void setEff(String eff) {
        Eff = eff;
    }
    @JsonIgnore
    public String getColor() {
        return Color;
    }
    @JsonIgnore
    public void setColor(String color) {
        Color = color;
    }
    @JsonIgnore
    public String getGrade() {
        return Grade;
    }
    @JsonIgnore
    public void setGrade(String grade) {
        Grade = grade;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}

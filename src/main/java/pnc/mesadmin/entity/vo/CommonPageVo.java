package pnc.mesadmin.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/2/15 13:08
 * @Description: 站点记录查询页面前端参数
 */
public class CommonPageVo {
    @JsonProperty("id")
    private int id;//表的标志
    @JsonProperty("lineGd")
    private String lineGd;//线体的标志
    @JsonProperty("woGd")
    private String[] woGd;//工单的标志
    @JsonProperty("createTimeStart")
    private String createTimeStart;//开始时间
    @JsonProperty("createTimeEnd")
    private String createTimeEnd;//结束时间
    @JsonProperty("isChecked") //是否去重
    private String isChecked;

    public static class WoGd{
        @JsonProperty("woGd")
        private String woGd;
        @JsonIgnore
        public String getWoGd() {
            return woGd;
        }
        @JsonIgnore
        public void setWoGd(String woGd) {
            this.woGd = woGd;
        }
    }

    @JsonIgnore
    public int getId() {
        return id;
    }
    @JsonIgnore
    public void setId(int id) {
        this.id = id;
    }
    @JsonIgnore
    public String getLineGd() {
        return lineGd;
    }
    @JsonIgnore
    public void setLineGd(String lineGd) {
        this.lineGd = lineGd;
    }
    @JsonIgnore
    public String[] getWoGd() {
        return woGd;
    }
    @JsonIgnore
    public void setWoGd(String[] woGd) {
        this.woGd = woGd;
    }

    @JsonIgnore
    public String getCreateTimeStart() {
        return createTimeStart;
    }
    @JsonIgnore
    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }
    @JsonIgnore
    public String getCreateTimeEnd() {
        return createTimeEnd;
    }
    @JsonIgnore
    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
    @JsonIgnore
    public String getIsChecked() {
        return isChecked;
    }
    @JsonIgnore
    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}

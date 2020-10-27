package pnc.mesadmin.dto.GetAllNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/10/24.
 */
public class GetAllNPickInfoResD {
    @JsonProperty("PickRd")
    private int PickRd;

    @JsonProperty("PickCode")
    private String PickCode;

    @JsonProperty("ExStatus")
    private String ExStatus;

    @JsonProperty("CreateDate")
    private String CreateDate;

    @JsonProperty("CreateDate1")
    private String CreateDate1;

    @JsonProperty("UserRd")
    private int UserRd;

    @JsonIgnore
    public String getExStatus() {
        return ExStatus;
    }
    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
    }
    @JsonIgnore
    public String getCreateDate() {
        return CreateDate;
    }
    @JsonIgnore
    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }
    @JsonIgnore
    public String getCreateDate1() {
        return CreateDate1;
    }
    @JsonIgnore
    public void setCreateDate1(String createDate1) {
        CreateDate1 = createDate1;
    }
    @JsonIgnore
    public int getUserRd() {
        return UserRd;
    }
    @JsonIgnore
    public void setUserRd(int userRd) {
        UserRd = userRd;
    }

    @JsonIgnore
    public int getPickRd() {
        return PickRd;
    }

    @JsonIgnore
    public void setPickRd(int pickRd) {
        PickRd = pickRd;
    }

    @JsonIgnore
    public String getPickCode() {
        return PickCode;
    }

    @JsonIgnore
    public void setPickCode(String pickCode) {
        PickCode = pickCode;
    }
}

package pnc.mesadmin.dto.GetAllKCRecInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-WES管理系统
 * Created by liufuzhi on 2017/10/25.
 */
public class GetAllKCRecInfoResD implements Serializable {
    @JsonProperty("OptType")
    private String OptType;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("OrderCode")
    private String OrderCode;

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("Num")
    private Float Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Createor")
    private String Createor;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("Dealor")
    private String Dealor;

    @JsonProperty("DealTime")
    private String DealTime;

    @JsonIgnore
    public String getOptType() {
        return OptType;
    }

    @JsonIgnore
    public void setOptType(String optType) {
        OptType = optType;
    }

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public String getOrderCode() {
        return OrderCode;
    }

    @JsonIgnore
    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public Float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(Float num) {
        Num = num;
    }

    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    @JsonIgnore
    public String getCreateor() {
        return Createor;
    }

    @JsonIgnore
    public void setCreateor(String createor) {
        Createor = createor;
    }

    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getDealor() {
        return Dealor;
    }

    @JsonIgnore
    public void setDealor(String dealor) {
        Dealor = dealor;
    }

    @JsonIgnore
    public String getDealTime() {
        return DealTime;
    }

    @JsonIgnore
    public void setDealTime(String dealTime) {
        DealTime = dealTime;
    }
}

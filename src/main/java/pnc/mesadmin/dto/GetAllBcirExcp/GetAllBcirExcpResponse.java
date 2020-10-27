package pnc.mesadmin.dto.GetAllBcirExcp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2018-12-12
 **/
public class GetAllBcirExcpResponse {
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("CreateTime")
    private String CreateTime;
    @JsonProperty("LineName")
    private String LineName;
    @JsonProperty("SpecName")
    private String SpecName;
    @JsonProperty("Msg")
    private String Msg;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
    public String getLineName() {
        return LineName;
    }

    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }

    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }

    @JsonIgnore
    public String getMsg() {
        return Msg;
    }

    @JsonIgnore
    public void setMsg(String msg) {
        Msg = msg;
    }

}

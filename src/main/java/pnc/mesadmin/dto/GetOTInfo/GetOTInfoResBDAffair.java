package pnc.mesadmin.dto.GetOTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线追踪DTO返回业务数据实体类BAffairInfo
 * 创建人：张亮亮
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public class GetOTInfoResBDAffair implements Serializable{

    @JsonProperty("LineName")
    private String LineName;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("OptType")
    private  String OptType;

    @JsonProperty("Optor")
    private  String Optor;

    @JsonProperty("OptTime")
    private  String OptTime;

    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }

    @JsonIgnore
    public String getOptType() {
        return OptType;
    }

    @JsonIgnore
    public void setOptType(String optType) {
        OptType = optType;
    }

    @JsonIgnore
    public String getOptor() {
        return Optor;
    }

    @JsonIgnore
    public void setOptor(String optor) {
        Optor = optor;
    }

    @JsonIgnore
    public String getOptTime() {
        return OptTime;
    }

    @JsonIgnore
    public void setOptTime(String optTime) {
        OptTime = optTime;
    }

    @JsonIgnore
    public String getLineName() {
        return LineName;
    }
    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }
}

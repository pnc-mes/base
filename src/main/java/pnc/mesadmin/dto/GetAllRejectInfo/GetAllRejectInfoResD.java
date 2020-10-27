package pnc.mesadmin.dto.GetAllRejectInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码DTO返回业务数据实体类resD
 * 创建人：郝赞
 * 创建时间：2018-8-6
 * 修改人：
 * 修改时间：
 */
public class GetAllRejectInfoResD {

    @JsonProperty("Ruid")
    private Integer Ruid;

    @JsonProperty("Guid")
    private String Guid;

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("ReaDes")
    private String ReaDes;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("Num")
    private Float Num;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("BadNum")
    private Float BadNum;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonIgnore
    public String getUnitName() {
        return UnitName;
    }

    @JsonIgnore
    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    @JsonIgnore
    public Integer getRuid() {
        return Ruid;
    }

    @JsonIgnore
    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    @JsonIgnore
    public String getGuid() {
        return Guid;
    }

    @JsonIgnore
    public void setGuid(String guid) {
        Guid = guid;
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
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
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
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }

    @JsonIgnore
    public String getReaDes() {
        return ReaDes;
    }

    @JsonIgnore
    public void setReaDes(String reaDes) {
        ReaDes = reaDes;
    }

    @JsonIgnore
    public Float getBadNum() {
        return BadNum;
    }

    @JsonIgnore
    public void setBadNum(Float badNum) {
        BadNum = badNum;
    }
}

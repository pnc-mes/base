package pnc.mesadmin.dto.GetOTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：在线追踪DTO返回业务数据实体类DoMaInfo
 * 创建人：张亮亮
 * 创建时间：2017-06-13
 * 修改人：
 * 修改时间：
 */
public class GetOTInfoResBDDoMa implements Serializable{

    @JsonProperty("SpecName")
    private String SpecName;
    @JsonProperty("LineName")
    private String LineName;
    @JsonProperty("DevName")
    private String DevName;
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("ConSMode")
    private String ConSMode;
    @JsonProperty("SuppBatch")
    private String SuppBatch;
    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("DoNum")
    private float DoNum;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("Optor")
    private String Optor;

    @JsonIgnore
    public String getSpecName() {
        return SpecName;
    }

    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
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
    public String getDevName() {
        return DevName;
    }

    @JsonIgnore
    public void setDevName(String devName) {
        DevName = devName;
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
    public float getDoNum() {
        return DoNum;
    }

    @JsonIgnore
    public void setDoNum(float doNum) {
        DoNum = doNum;
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
    public String getOptor() {
        return Optor;
    }

    @JsonIgnore
    public void setOptor(String optor) {
        Optor = optor;
    }
    @JsonIgnore
    public String getConSMode() {
        return ConSMode;
    }
    @JsonIgnore
    public void setConSMode(String conSMode) {
        ConSMode = conSMode;
    }
    @JsonIgnore
    public String getSuppBatch() {
        return SuppBatch;
    }
    @JsonIgnore
    public void setSuppBatch(String suppBatch) {
        SuppBatch = suppBatch;
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

package pnc.mesadmin.dto.GetAllWOBInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称:获取工单批次列表返回的Data
 * 创建人：张亮亮
 * 创建时间：2017-06-08
 * 修改人：
 * 修改时间：
 */
public class GetAllWOBInfoResD implements Serializable{

    @JsonProperty("WoBRd")
    private int WoBRd;

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("MaterialDes")
    private String MaterialDes;

    @JsonProperty("CanNum")
    private float CanNum;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("JFDate")
    private String JFDate;

    @JsonProperty("SFDate")
    private String SFDate;

    @JsonProperty("Status")
    private String Status;

    @JsonIgnore
    public int getWoBRd() {
        return WoBRd;
    }

    @JsonIgnore
    public void setWoBRd(int woBRd) {
        WoBRd = woBRd;
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
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
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
    public String getJFDate() {
        return JFDate;
    }

    @JsonIgnore
    public void setJFDate(String JFDate) {
        this.JFDate = JFDate;
    }

    @JsonIgnore
    public String getSFDate() {
        return SFDate;
    }

    @JsonIgnore
    public void setSFDate(String SFDate) {
        this.SFDate = SFDate;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
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
    public String getSpecName() {
        return SpecName;
    }
    @JsonIgnore
    public void setSpecName(String specName) {
        SpecName = specName;
    }
    @JsonIgnore
    public float getCanNum() {
        return CanNum;
    }
    @JsonIgnore
    public void setCanNum(float canNum) {
        CanNum = canNum;
    }
    @JsonIgnore
    public String getMaterialDes() {
        return MaterialDes;
    }
    @JsonIgnore
    public void setMaterialDes(String materialDes) {
        MaterialDes = materialDes;
    }
}

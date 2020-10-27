package pnc.mesadmin.dto.SaveUDMaterailDto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * Created by yin.yang on 2019/06/15
 */
public class SaveUDMaterailRequest {

    @JsonProperty("WoRd")
    private Integer WoRd;
    @JsonProperty("LuType")//00工序，01设备
    private String LuType;
    @JsonProperty("SpecRd")
    private Integer SpecRd;
    @JsonProperty("Type")//00上，01下
    private String Type;
    @JsonProperty("DevRd")
    private Integer DevRd;
    @JsonProperty("Batch")
    private String Batch;
    @JsonProperty("WarningVaule") //物料预警值
    private Float WarningVaule;
    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("Number") //数量
    private Float Number;

    //下料参数
    //下料时是否存在数量差异 00-无差异 01-有差异
    @JsonProperty("isDifference")
    private String isDifference;

    //数量
    @JsonProperty("DifferenceNumber")
    private Float DifferenceNumber;
    //原损数量
    @JsonProperty("DifferenceNumber1")
    private Float DifferenceNumber1;
    //制损数量
    @JsonProperty("DifferenceNumber2")
    private Float DifferenceNumber2;

    @JsonIgnore
    public Integer getWoRd() {
        return WoRd;
    }

    @JsonIgnore
    public void setWoRd(Integer woRd) {
        WoRd = woRd;
    }

    @JsonIgnore
    public String getLuType() {
        return LuType;
    }

    @JsonIgnore
    public void setLuType(String luType) {
        LuType = luType;
    }

    @JsonIgnore
    public String getType() {
        return Type;
    }

    @JsonIgnore
    public void setType(String type) {
        Type = type;
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
    public Float getWarningVaule() {
        return WarningVaule;
    }

    @JsonIgnore
    public void setWarningVaule(Float warningVaule) {
        WarningVaule = warningVaule;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public Integer getSpecRd() {
        return SpecRd;
    }

    @JsonIgnore
    public void setSpecRd(Integer specRd) {
        SpecRd = specRd;
    }

    @JsonIgnore
    public Integer getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(Integer devRd) {
        DevRd = devRd;
    }

    @JsonIgnore
    public Float getNumber() {
        return Number;
    }

    @JsonIgnore
    public void setNumber(Float number) {
        Number = number;
    }

    @JsonIgnore
    public Float getDifferenceNumber() {
        return DifferenceNumber;
    }

    @JsonIgnore
    public void setDifferenceNumber(Float differenceNumber) {
        DifferenceNumber = differenceNumber;
    }

    @JsonIgnore
    public Float getDifferenceNumber1() {
        return DifferenceNumber1;
    }

    @JsonIgnore
    public void setDifferenceNumber1(Float differenceNumber1) {
        DifferenceNumber1 = differenceNumber1;
    }

    @JsonIgnore
    public Float getDifferenceNumber2() {
        return DifferenceNumber2;
    }

    @JsonIgnore
    public void setDifferenceNumber2(Float differenceNumber2) {
        DifferenceNumber2 = differenceNumber2;
    }

    @JsonIgnore
    public String getIsDifference() {
        return isDifference;
    }

    @JsonIgnore
    public void setIsDifference(String isDifference) {
        this.isDifference = isDifference;
    }
}

package pnc.mesadmin.dto.GetPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/13.
 */
public class GetPickInfoResD {
    @JsonProperty("PickRd")
    private int PickRd;

    @JsonProperty("PickCode")
    private String PickCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("AssSource")
    private String AssSource;

    @JsonProperty("MaInfo")
    private GetPickInfoResDMaInfo MaInfo;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("UnitName")
    private String UnitName;

    @JsonProperty("PrePickDate")
    private String PrePickDate;

    @JsonProperty("DSource")
    private String DSource;

    @JsonProperty("ExStatus")
    private String ExStatus;

    @JsonProperty("SStatus")
    private String SStatus;

    @JsonProperty("PickDlInfo")
    private List<GetPickInfoResDPickDl> PickDlInfo;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

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

    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }

    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
    }

    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }

    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
    }

    @JsonIgnore
    public GetPickInfoResDMaInfo getMaInfo() {
        return MaInfo;
    }

    @JsonIgnore
    public void setMaInfo(GetPickInfoResDMaInfo maInfo) {
        MaInfo = maInfo;
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
    public String getPrePickDate() {
        return PrePickDate;
    }

    @JsonIgnore
    public void setPrePickDate(String prePickDate) {
        PrePickDate = prePickDate;
    }

    @JsonIgnore
    public String getDSource() {
        return DSource;
    }

    @JsonIgnore
    public void setDSource(String DSource) {
        this.DSource = DSource;
    }

    @JsonIgnore
    public String getExStatus() {
        return ExStatus;
    }

    @JsonIgnore
    public void setExStatus(String exStatus) {
        ExStatus = exStatus;
    }

    @JsonIgnore
    public String getSStatus() {
        return SStatus;
    }

    @JsonIgnore
    public void setSStatus(String SStatus) {
        this.SStatus = SStatus;
    }

    @JsonIgnore
    public List<GetPickInfoResDPickDl> getPickDlInfo() {
        return PickDlInfo;
    }

    @JsonIgnore
    public void setPickDlInfo(List<GetPickInfoResDPickDl> pickDlInfo) {
        PickDlInfo = pickDlInfo;
    }

    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
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
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

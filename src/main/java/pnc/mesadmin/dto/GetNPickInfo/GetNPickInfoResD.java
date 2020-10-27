package pnc.mesadmin.dto.GetNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/10/24.
 */
public class GetNPickInfoResD {
    @JsonProperty("PickRd")
    private int PickRd;
    @JsonProperty("PickCode")
    private String PickCode;
    @JsonProperty("PrePickDate")
    private String PrePickDate;
    @JsonProperty("Use")
    private String Use;
    @JsonProperty("ExStatus")
    private String ExStatus;
    @JsonProperty("SStatus")
    private String SStatus;
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
    @JsonProperty("DSource")
    private String DSource;
    @JsonProperty("PickerInfo")
    private GetNPickInfoResDPicker PickerInfo;

    @JsonProperty("PickDlInfo")
    private List<GetNPickInfoResDPickDl> PickDlInfo;
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
    public String getPrePickDate() {
        return PrePickDate;
    }
    @JsonIgnore
    public void setPrePickDate(String prePickDate) {
        PrePickDate = prePickDate;
    }
    @JsonIgnore
    public String getUse() {
        return Use;
    }
    @JsonIgnore
    public void setUse(String use) {
        Use = use;
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
    @JsonIgnore
    public GetNPickInfoResDPicker getPickerInfo() {
        return PickerInfo;
    }
    @JsonIgnore
    public void setPickerInfo(GetNPickInfoResDPicker pickerInfo) {
        PickerInfo = pickerInfo;
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
    public List<GetNPickInfoResDPickDl> getPickDlInfo() {
        return PickDlInfo;
    }
    @JsonIgnore
    public void setPickDlInfo(List<GetNPickInfoResDPickDl> pickDlInfo) {
        PickDlInfo = pickDlInfo;
    }
    @JsonIgnore
    public String getDSource() {
        return DSource;
    }
    @JsonIgnore
    public void setDSource(String DSource) {
        this.DSource = DSource;
    }
}

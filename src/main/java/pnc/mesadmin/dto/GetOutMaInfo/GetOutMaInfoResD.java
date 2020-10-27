package pnc.mesadmin.dto.GetOutMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/3/9 14:35
 * @Description:
 */
public class GetOutMaInfoResD {
    @JsonProperty("OutMaRd")
    private int OutMaRd;

    @JsonProperty("OutCode")
    private String OutCode;

    @JsonProperty("PreOutDate")
    private String PreOutDate;

    @JsonProperty("Use")
    private String Use;

    @JsonProperty("DSource")
    private String DSource;

    @JsonProperty("ExStatus")
    private String ExStatus;

    @JsonProperty("SStatus")
    private String SStatus;

    @JsonProperty("PickerInfo")
    private GetOutMaInfoResDPicker PickerInfo;

    @JsonProperty("OutDlInfo")
    private List<GetOutMaInfoResDOutDlInfo> OutDlInfo;


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
    public int getOutMaRd() {
        return OutMaRd;
    }
    @JsonIgnore
    public void setOutMaRd(int outMaRd) {
        OutMaRd = outMaRd;
    }
    @JsonIgnore
    public String getOutCode() {
        return OutCode;
    }
    @JsonIgnore
    public void setOutCode(String outCode) {
        OutCode = outCode;
    }
    @JsonIgnore
    public String getPreOutDate() {
        return PreOutDate;
    }
    @JsonIgnore
    public void setPreOutDate(String preOutDate) {
        PreOutDate = preOutDate;
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
    public GetOutMaInfoResDPicker getPickerInfo() {
        return PickerInfo;
    }
    @JsonIgnore
    public void setPickerInfo(GetOutMaInfoResDPicker pickerInfo) {
        PickerInfo = pickerInfo;
    }
    @JsonIgnore
    public List<GetOutMaInfoResDOutDlInfo> getOutDlInfo() {
        return OutDlInfo;
    }
    @JsonIgnore
    public void setOutDlInfo(List<GetOutMaInfoResDOutDlInfo> outDlInfo) {
        OutDlInfo = outDlInfo;
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

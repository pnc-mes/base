package pnc.mesadmin.dto.GetSVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取工序DTO返回业务数据实体类Data
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public class GetSVInfoResD implements Serializable{

    @JsonProperty("SpecRd")
    private int SpecRd;

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("SpecName")
    private String SpecName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("Status")
    private String Status;

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

    @JsonProperty("Opert")
    private GetSVInfoResDOpert  Opert;

    @JsonProperty("Device")
    private  GetSVInfoResDDevice Device;

    @JsonProperty("DC")
    private GetSVInfoResDDC DC;

    @JsonProperty("AfterDC")
    private GetSVInfoResDDC AfterDC;

    @JsonProperty("SkillG")
    private GetSVInfoResDSkillG SkillG;

    @JsonProperty("FileGrInfo")
    private GetSVInfoResDFileGrInfo FileGrInfo;
    /*@JsonProperty("PTInfo")
    private GetSVInfoResDPTInfo PTInfo;
    @JsonProperty("PrinterInfo")
    private GetSVInfoResDPrinterInfo PrinterInfo;*/
    @JsonIgnore
    public GetSVInfoResDFileGrInfo getFileGrInfo() {
        return FileGrInfo;
    }
    /*@JsonIgnore
    public GetSVInfoResDPTInfo getPTInfo() {
        return PTInfo;
    }
    @JsonIgnore
    public void setPTInfo(GetSVInfoResDPTInfo PTInfo) {
        this.PTInfo = PTInfo;
    }
    @JsonIgnore
    public GetSVInfoResDPrinterInfo getPrinterInfo() {
        return PrinterInfo;
    }
    @JsonIgnore
    public void setPrinterInfo(GetSVInfoResDPrinterInfo printerInfo) {
        PrinterInfo = printerInfo;
    }*/

    @JsonIgnore
    public void setFileGrInfo(GetSVInfoResDFileGrInfo fileGrInfo) {
        FileGrInfo = fileGrInfo;
    }

    @JsonIgnore
    public GetSVInfoResDDC getDC() {
        return DC;
    }
    @JsonIgnore
    public void setDC(GetSVInfoResDDC DC) {
        this.DC = DC;
    }
    @JsonIgnore
    public GetSVInfoResDSkillG getSkillG() {
        return SkillG;
    }
    @JsonIgnore
    public void setSkillG(GetSVInfoResDSkillG skillG) {
        SkillG = skillG;
    }

    @JsonIgnore
    public int getSpecRd() {
        return SpecRd;
    }

    @JsonIgnore
    public void setSpecRd(int specRd) {
        SpecRd = specRd;
    }

    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
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
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }

    @JsonIgnore
    public String getIsDef() {
        return IsDef;
    }

    @JsonIgnore
    public void setIsDef(String isDef) {
        IsDef = isDef;
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
    public GetSVInfoResDOpert getOpert() {
        return Opert;
    }

    @JsonIgnore
    public void setOpert(GetSVInfoResDOpert opert) {
        Opert = opert;
    }

    @JsonIgnore
    public GetSVInfoResDDevice getDevice() {
        return Device;
    }

    @JsonIgnore
    public void setDevice(GetSVInfoResDDevice device) {
        Device = device;
    }

    @JsonIgnore
    public GetSVInfoResDDC getAfterDC() {
        return AfterDC;
    }

    @JsonIgnore
    public void setAfterDC(GetSVInfoResDDC afterDC) {
        AfterDC = afterDC;
    }
}


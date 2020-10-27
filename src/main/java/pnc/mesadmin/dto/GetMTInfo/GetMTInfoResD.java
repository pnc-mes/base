package pnc.mesadmin.dto.GetMTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/8/21.
 */
public class GetMTInfoResD implements Serializable {
    @JsonProperty("MTRd")
    private int MTRd;

    @JsonProperty("PMTRd")
    private int PMTRd;

    @JsonProperty("MTCode")
    private String MTCode;

    @JsonProperty("MTName")
    private String MTName;

    @JsonProperty("MaterialType")
    private String MaterialType;

    @JsonProperty("DSource")
    private String DSource;

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

    @JsonProperty("ExpandInfo")
    private GetMTInfoResDExpand ExpandInfo;

    @JsonIgnore
    public int getPMTRd() {
        return PMTRd;
    }

    @JsonIgnore
    public void setPMTRd(int PMTRd) {
        this.PMTRd = PMTRd;
    }

    @JsonIgnore
    public String getMaterialType() {
        return MaterialType;
    }

    @JsonIgnore
    public void setMaterialType(String materialType) {
        MaterialType = materialType;
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
    public int getMTRd() {
        return MTRd;
    }

    @JsonIgnore
    public void setMTRd(int MTRd) {
        this.MTRd = MTRd;
    }

    @JsonIgnore
    public String getMTCode() {
        return MTCode;
    }

    @JsonIgnore
    public void setMTCode(String MTCode) {
        this.MTCode = MTCode;
    }

    @JsonIgnore
    public String getMTName() {
        return MTName;
    }

    @JsonIgnore
    public void setMTName(String MTName) {
        this.MTName = MTName;
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
    public GetMTInfoResDExpand getExpandInfo() {
        return ExpandInfo;
    }
    @JsonIgnore
    public void setExpandInfo(GetMTInfoResDExpand expandInfo) {
        ExpandInfo = expandInfo;
    }
}

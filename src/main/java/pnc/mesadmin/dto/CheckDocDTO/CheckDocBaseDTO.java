package pnc.mesadmin.dto.CheckDocDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: mesadmin
 * @description: ${description}
 * @author: yin.yang
 * @create: 2019-03-19
 **/
public class CheckDocBaseDTO {
    @JsonProperty(value = "CDOCRd")
    private Integer CDOCRd;
    @JsonProperty(value = "CheckDocName")
    private String CheckDocName;
    @JsonProperty(value = "RelType")
    private String RelType;
    @JsonProperty(value = "RelRd")
    private Integer RelRd;
    @JsonProperty(value = "RelName")
    private String RelName;
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
    @JsonProperty("CDocInfo")
    private List<CDocInfo> CDocInfo;

    public static class CDocInfo {
        @JsonProperty("FileName")
        private String FileName;
        @JsonProperty("FileUrl")
        private String FileUrl;

        @JsonIgnore
        public String getFileName() {
            return FileName;
        }

        @JsonIgnore
        public void setFileName(String fileName) {
            FileName = fileName;
        }

        @JsonIgnore
        public String getFileUrl() {
            return FileUrl;
        }

        @JsonIgnore
        public void setFileUrl(String fileUrl) {
            FileUrl = fileUrl;
        }
    }

    @JsonIgnore
    public String getRelName() {
        return RelName;
    }

    @JsonIgnore
    public void setRelName(String relName) {
        RelName = relName;
    }

    @JsonIgnore
    public Integer getCDOCRd() {
        return CDOCRd;
    }

    @JsonIgnore
    public void setCDOCRd(Integer CDOCRd) {
        this.CDOCRd = CDOCRd;
    }

    @JsonIgnore
    public String getCheckDocName() {
        return CheckDocName;
    }

    @JsonIgnore
    public void setCheckDocName(String checkDocName) {
        CheckDocName = checkDocName;
    }

    @JsonIgnore
    public String getRelType() {
        return RelType;
    }

    @JsonIgnore
    public void setRelType(String relType) {
        RelType = relType;
    }
    @JsonIgnore
    public Integer getRelRd() {
        return RelRd;
    }
    @JsonIgnore
    public void setRelRd(Integer relRd) {
        RelRd = relRd;
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
    public List<CheckDocBaseDTO.CDocInfo> getCDocInfo() {
        return CDocInfo;
    }

    @JsonIgnore
    public void setCDocInfo(List<CheckDocBaseDTO.CDocInfo> CDocInfo) {
        this.CDocInfo = CDocInfo;
    }
}

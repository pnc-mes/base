package pnc.mesadmin.dto.GetFileGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件组信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-7-22
 * 修改人：
 * 修改时间：
 */
public class GetFileGInfoResD implements Serializable {
    @JsonProperty("FileGRd")
    private int FileGRd;

    @JsonProperty("FileGpName")
    private String FileGpName;

    @JsonProperty("FileInfo")
    private List<GetFileGInfoResDFileInfo> FileInfo;

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
    public int getFileGRd() {
        return FileGRd;
    }

    @JsonIgnore
    public void setFileGRd(int fileGRd) {
        FileGRd = fileGRd;
    }

    @JsonIgnore
    public String getFileGpName() {
        return FileGpName;
    }

    @JsonIgnore
    public void setFileGpName(String fileGpName) {
        FileGpName = fileGpName;
    }

    @JsonIgnore
    public List<GetFileGInfoResDFileInfo> getFileInfo() {
        return FileInfo;
    }

    @JsonIgnore
    public void setFileInfo(List<GetFileGInfoResDFileInfo> fileInfo) {
        FileInfo = fileInfo;
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

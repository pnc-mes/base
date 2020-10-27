package pnc.mesadmin.dto.SaveFileInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存文件信息DTO请求业务数据实体类BD00：新增
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public class SaveFileInfoReqBD00 implements Serializable{

    @JsonProperty("FileName")
    private String FileName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("FilePath")
    private String FilePath;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getFileName() {
        return FileName;
    }

    @JsonIgnore
    public void setFileName(String fileName) {
        FileName = fileName;
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
    public String getFilePath() {
        return FilePath;
    }

    @JsonIgnore
    public void setFilePath(String filePath) {
        FilePath = filePath;
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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

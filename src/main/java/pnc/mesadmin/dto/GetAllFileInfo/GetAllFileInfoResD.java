package pnc.mesadmin.dto.GetAllFileInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件列表信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public class GetAllFileInfoResD implements Serializable{

    @JsonProperty("FileRd")
    private int FileRd;

    @JsonProperty("FileName")
    private String FileName;

    @JsonProperty("FileVerRd")
    private int FileVerRd;

    @JsonProperty("Version")
    private String Version;

    @JsonIgnore
    public int getFileRd() {
        return FileRd;
    }

    @JsonIgnore
    public void setFileRd(int fileRd) {
        FileRd = fileRd;
    }

    @JsonIgnore
    public String getFileName() {
        return FileName;
    }

    @JsonIgnore
    public void setFileName(String fileName) {
        FileName = fileName;
    }

    @JsonIgnore
    public int getFileVerRd() {
        return FileVerRd;
    }

    @JsonIgnore
    public void setFileVerRd(int fileVerRd) {
        FileVerRd = fileVerRd;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }
}

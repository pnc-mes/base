package pnc.mesadmin.dto.GetFileTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件版本信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-7-21
 * 修改人：
 * 修改时间：
 */
public class GetFileTreeInfoResD implements Serializable {

    @JsonProperty("FileVerRd")
    private int FileVerRd;

    @JsonProperty("Version")
    private String Version;

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

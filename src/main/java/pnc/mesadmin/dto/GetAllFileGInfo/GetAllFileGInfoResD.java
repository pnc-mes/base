package pnc.mesadmin.dto.GetAllFileGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件组信息DTO返回业务数据实体类body
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */

public class GetAllFileGInfoResD implements Serializable{

    @JsonProperty("FileGRd")
    private int FileGRd;

    @JsonProperty("FileGpName")
    private String FileGpName;

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
}

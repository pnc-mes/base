package pnc.mesadmin.dto.SaveFileGPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存文件组信息DTO请求业务数据实体类BD00：新增
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public class SaveFileGPInfoReqBD00 implements Serializable{

    @JsonProperty("FileGpName")
    private String FileGpName;

    @JsonProperty("FileInfo")
    private List<SaveFileGPInfoReqBD00FileInfo> FileInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public List<SaveFileGPInfoReqBD00FileInfo> getFileInfo() {
        return FileInfo;
    }

    @JsonIgnore
    public void setFileInfo(List<SaveFileGPInfoReqBD00FileInfo> fileInfo) {
        FileInfo = fileInfo;
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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

package pnc.mesadmin.dto.SaveFileGPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.utils.PageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存文件组信息DTO请求业务数据实体类BD02：编辑
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public class SaveFileGPInfoReqBD02 extends PageUtil implements Serializable{

    @JsonProperty("FileGRd")
    private int FileGRd;

    @JsonProperty("FileGpName")
    private String FileGpName;

    @JsonProperty("FileInfo")
    private List<SaveFileGPInfoReqBD02FileInfo>  FileInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public List<SaveFileGPInfoReqBD02FileInfo> getFileInfo() {
        return FileInfo;
    }

    @JsonIgnore
    public void setFileInfo(List<SaveFileGPInfoReqBD02FileInfo> fileInfo) {
        FileInfo = fileInfo;
    }

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
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}

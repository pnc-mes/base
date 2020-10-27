package pnc.mesadmin.dto.SaveBdFileInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存绑定文件信息DTO请求业务数据实体类BD00：新增
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public class SaveBdFileInfoReqBD00 implements Serializable{

    @JsonProperty("FileGRd")
    private int FileGRd;

    @JsonProperty("FileVerRd")
    private int FileVerRd;

    @JsonProperty("FileRd")
    private int FileRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getFileRd() {
        return FileRd;
    }

    @JsonIgnore
    public void setFileRd(int fileRd) {
        FileRd = fileRd;
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
    public int getFileVerRd() {
        return FileVerRd;
    }

    @JsonIgnore
    public void setFileVerRd(int fileVerRd) {
        FileVerRd = fileVerRd;
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

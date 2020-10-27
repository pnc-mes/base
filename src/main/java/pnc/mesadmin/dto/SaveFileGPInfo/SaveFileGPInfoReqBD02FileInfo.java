package pnc.mesadmin.dto.SaveFileGPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存文件组信息DTO请求业务数据实体类BD02：编辑
 * 创建人：刘福志
 * 创建时间：2017-7-22
 * 修改人：
 * 修改时间：
 */
public class SaveFileGPInfoReqBD02FileInfo implements Serializable {
    @JsonProperty("GPDtlRd")
    private int GPDtlRd;

    @JsonProperty("FileVerRd")
    private int FileVerRd;

    @JsonIgnore
    public int getGPDtlRd() {
        return GPDtlRd;
    }

    @JsonIgnore
    public void setGPDtlRd(int GPDtlRd) {
        this.GPDtlRd = GPDtlRd;
    }

    @JsonIgnore
    public int getFileVerRd() {
        return FileVerRd;
    }

    @JsonIgnore
    public void setFileVerRd(int fileVerRd) {
        FileVerRd = fileVerRd;
    }
}

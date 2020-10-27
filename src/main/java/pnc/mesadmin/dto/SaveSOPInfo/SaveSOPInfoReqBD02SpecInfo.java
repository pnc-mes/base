package pnc.mesadmin.dto.SaveSOPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存工艺文件信息DTO请求业务数据实体类BD02 SpecInfo:编辑
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
public class SaveSOPInfoReqBD02SpecInfo implements Serializable{

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("FileGrRd")
    private int FileGrRd;

    @JsonProperty("DCVerRd")
    private int DCVerRd;

    @JsonProperty("AfDCVerRd")
    private int AfDCVerRd;

    @JsonProperty("DevGpRd")
    private int DevGpRd;


    public int getDCVerRd() {
        return DCVerRd;
    }

    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }

    public int getDevGpRd() {
        return DevGpRd;
    }

    public void setDevGpRd(int devGpRd) {
        DevGpRd = devGpRd;
    }

    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public int getFileGrRd() {
        return FileGrRd;
    }

    @JsonIgnore
    public void setFileGrRd(int fileGrRd) {
        FileGrRd = fileGrRd;
    }
    @JsonIgnore
    public int getAfDCVerRd() {
        return AfDCVerRd;
    }
    @JsonIgnore
    public void setAfDCVerRd(int afDCVerRd) {
        AfDCVerRd = afDCVerRd;
    }
}

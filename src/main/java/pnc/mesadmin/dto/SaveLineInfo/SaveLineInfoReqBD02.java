package pnc.mesadmin.dto.SaveLineInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：编辑工厂信息dto
 * 创建人：郝赞
 * 创建时间：2018-6-8
 * 修改人：
 * 修改时间：
 */
public class SaveLineInfoReqBD02 implements Serializable{

    @JsonProperty("LineRd")
    private Integer LineRd;

    @JsonProperty("LineCode")
    private String LineCode;

    @JsonProperty("LineName")
    private String LineName;

    @JsonProperty("LineDes")
    private String LineDes;

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("OEMLine")
    private List<SaveOEMLineinfoReq02OEMLine> OEMLine;

    @JsonIgnore
    public String getLineCode() {
        return LineCode;
    }

    @JsonIgnore
    public void setLineCode(String lineCode) {
        LineCode = lineCode;
    }

    @JsonIgnore
    public Integer getLineRd() {
        return LineRd;
    }

    @JsonIgnore
    public void setLineRd(Integer lineRd) {
        LineRd = lineRd;
    }

    @JsonIgnore
    public String getLineName() {
        return LineName;
    }

    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
    }

    @JsonIgnore
    public int getFaRd() {
        return FaRd;
    }

    @JsonIgnore
    public void setFaRd(int faRd) {
        FaRd = faRd;
    }

    @JsonIgnore
    public String getLineDes() {
        return LineDes;
    }

    @JsonIgnore
    public void setLineDes(String lineDes) {
        LineDes = lineDes;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }

    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }

    @JsonIgnore
    public List<SaveOEMLineinfoReq02OEMLine> getOEMLine() {
        return OEMLine;
    }

    @JsonIgnore
    public void setOEMLine(List<SaveOEMLineinfoReq02OEMLine> OEMLine) {
        this.OEMLine = OEMLine;
    }
}

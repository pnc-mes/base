package pnc.mesadmin.dto.SaveLineInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：新增工厂信息dto返回实体类Res
 *创建人：郝赞
 * 创建时间：2018-6-8
 * 修改人：
 * 修改时间：
 */
public class SaveLineInfoReqBD00 implements Serializable{

    @JsonProperty("FaRd")
    private int FaRd;

    @JsonProperty("LineCode")
    private String LineCode;

    @JsonProperty("LineName")
    private String LineName;


    @JsonProperty("LineDes")
    private String LineDes;

    @JsonProperty("Remark")
    private String Remark;


    @JsonProperty("OEMLine")
    private List<SaveOEMLineInfoReq00OEMLineList> OEMLine;

    @JsonIgnore
    public String getLineCode() {
        return LineCode;
    }

    @JsonIgnore
    public void setLineCode(String lineCode) {
        LineCode = lineCode;
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
    public String getLineName() {
        return LineName;
    }
    @JsonIgnore
    public void setLineName(String lineName) {
        LineName = lineName;
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
    public List<SaveOEMLineInfoReq00OEMLineList> getOEMLine() {
        return OEMLine;
    }
    @JsonIgnore
    public void setOEMLine(List<SaveOEMLineInfoReq00OEMLineList> OEMLine) {
        this.OEMLine = OEMLine;
    }
}

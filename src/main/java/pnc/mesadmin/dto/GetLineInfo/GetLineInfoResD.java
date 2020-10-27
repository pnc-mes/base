package pnc.mesadmin.dto.GetLineInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取线体信息DTO返回实体类Data
 * 创建人：郝赞
 * 创建时间：2018-6-7
 * 修改人：
 * 修改时间：
 */
public class GetLineInfoResD implements Serializable {

    @JsonProperty("LineRd")
    private Integer LineRd;

    @JsonProperty("LineGd")
    private String LineGd;

    @JsonProperty("LineCode")
    private String LineCode;

    @JsonProperty("LineName")
    private String LineName;

    @JsonProperty("LineDes")
    private String LineDes;

    @JsonProperty("Factory")
    private GetLineInfoResDFactory Factory;

    @JsonProperty("OEMLine")
    private List<GetLineInfoResDB> OEMLine;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

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
    public String getLineDes() {
        return LineDes;
    }

    @JsonIgnore
    public void setLineDes(String lineDes) {
        LineDes = lineDes;
    }

    @JsonIgnore
    public GetLineInfoResDFactory getFactory() {
        return Factory;
    }

    @JsonIgnore
    public void setFactory(GetLineInfoResDFactory factory) {
        Factory = factory;
    }


    @JsonIgnore
    public String getCreator() {
        return Creator;
    }

    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }


    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }

    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }

    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }

    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public List<GetLineInfoResDB> getOEMLine() {
        return OEMLine;
    }

    @JsonIgnore
    public void setOEMLine(List<GetLineInfoResDB> OEMLine) {
        this.OEMLine = OEMLine;
    }

    @JsonIgnore
    public String getLineGd() {
        return LineGd;
    }

    @JsonIgnore
    public void setLineGd(String lineGd) {
        LineGd = lineGd;
    }
}

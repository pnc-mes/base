package pnc.mesadmin.dto.GetDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-5-23
 * 修改人：
 * 修改时间：
 */
public class GetDevInfoResD implements Serializable{

    @JsonProperty("DevRd")
    private int DevRd;

    @JsonProperty("DevName")
    private String DevName;
    @JsonProperty("DevCode")
    private String DevCode;

    @JsonProperty("Place")
    private String Place;
    @JsonProperty("SupRd")
    private int  SupRd;

    @JsonProperty("SupName")
    private String  SupName;

    @JsonProperty("DevFRd")
    private int  DevFRd;

    @JsonProperty("DevSRd")
    private int DevSRd;

    @JsonProperty("DevFName")
    private String  DevFName;

    @JsonProperty("DSMRd")
    private int  DSMRd;

    @JsonProperty("ModelName")
    private String  ModelName;

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

    @JsonProperty("LineInfo")
    private List<GetDevInfoResDLine> LineInfo;

    @JsonProperty("ToolFInfo")
    private GetDevInfoResDToolF ToolFInfo;

    @JsonProperty("CheckPlanInfo")
    private List<GetDevInfoResDCheckPlan> CheckPlanInfo;

    @JsonProperty("PMInfo")
    private List<GetDevInfoResDPM> PMInfo;

    @JsonProperty("DevPropertyInfo")
    private List<GetDevPropertyInfo> DevPropertyInfo;

    @JsonProperty("DevMapInfo")
    private List<GetDevInfoResDDevMap> DevMapInfo;

    @JsonProperty("DevBZInfo")
    private GetDevInfoResDDevBZInfo DevBZInfo;


    @JsonIgnore
    public List<GetDevInfoResDDevMap> getDevMapInfo() {
        return DevMapInfo;
    }
    @JsonIgnore
    public void setDevMapInfo(List<GetDevInfoResDDevMap> devMapInfo) {
        DevMapInfo = devMapInfo;
    }

    @JsonIgnore
    public String getDevCode() {
        return DevCode;
    }
    @JsonIgnore
    public void setDevCode(String devCode) {
        DevCode = devCode;
    }

    @JsonIgnore
    public int getDSMRd() {
        return DSMRd;
    }

    @JsonIgnore
    public void setDSMRd(int DSMRd) {
        this.DSMRd = DSMRd;
    }

    @JsonIgnore
    public String getModelName() {
        return ModelName;
    }

    @JsonIgnore
    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    @JsonIgnore
    public int getDevFRd() {
        return DevFRd;
    }

    @JsonIgnore
    public void setDevFRd(int devFRd) {
        DevFRd = devFRd;
    }

    @JsonIgnore
    public String getDevFName() {
        return DevFName;
    }

    @JsonIgnore
    public void setDevFName(String devFName) {
        DevFName = devFName;
    }

    @JsonIgnore
    public int getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(int devRd) {
        DevRd = devRd;
    }

    @JsonIgnore
    public String getDevName() {
        return DevName;
    }

    @JsonIgnore
    public void setDevName(String devName) {
        DevName = devName;
    }

    @JsonIgnore
    public int getSupRd() {
        return SupRd;
    }

    @JsonIgnore
    public void setSupRd(int supRd) {
        SupRd = supRd;
    }
    @JsonIgnore
    public String getSupName() {
        return SupName;
    }

    @JsonIgnore
    public void setSupName(String supName) {
        SupName = supName;
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
    public List<GetDevInfoResDLine> getLineInfo() {
        return LineInfo;
    }
    @JsonIgnore
    public void setLineInfo(List<GetDevInfoResDLine> lineInfo) {
        LineInfo = lineInfo;
    }
    @JsonIgnore
    public GetDevInfoResDToolF getToolFInfo() {
        return ToolFInfo;
    }
    @JsonIgnore
    public void setToolFInfo(GetDevInfoResDToolF toolFInfo) {
        ToolFInfo = toolFInfo;
    }
    @JsonIgnore
    public List<GetDevInfoResDCheckPlan> getCheckPlanInfo() {
        return CheckPlanInfo;
    }
    @JsonIgnore
    public void setCheckPlanInfo(List<GetDevInfoResDCheckPlan> checkPlanInfo) {
        CheckPlanInfo = checkPlanInfo;
    }
    @JsonIgnore
    public List<GetDevInfoResDPM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<GetDevInfoResDPM> PMInfo) {
        this.PMInfo = PMInfo;
    }
    @JsonIgnore
    public List<GetDevPropertyInfo> getDevPropertyInfo() {
        return DevPropertyInfo;
    }
    @JsonIgnore
    public void setDevPropertyInfo(List<GetDevPropertyInfo> devPropertyInfo) {
        DevPropertyInfo = devPropertyInfo;
    }
    @JsonIgnore
    public int getDevSRd() {
        return DevSRd;
    }
    @JsonIgnore
    public void setDevSRd(int devSRd) {
        DevSRd = devSRd;
    }
    @JsonIgnore
    public GetDevInfoResDDevBZInfo getDevBZInfo() {
        return DevBZInfo;
    }
    @JsonIgnore
    public void setDevBZInfo(GetDevInfoResDDevBZInfo devBZInfo) {
        DevBZInfo = devBZInfo;
    }
    @JsonIgnore
    public String getPlace() {
        return Place;
    }
    @JsonIgnore
    public void setPlace(String place) {
        Place = place;
    }
}


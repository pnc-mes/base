package pnc.mesadmin.dto.SaveDevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetDevInfo.GetDevInfoResDLine;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存设备信息DTO请求业务数据实体类BD02：编辑
 * 创建人：刘福志
 * 创建时间：2017-5-23
 * 修改人：
 * 修改时间：
 */
public class SaveDevInfoReqBD02 implements Serializable{

    @JsonProperty("DevRd")
    private int DevRd;

    @JsonProperty("DevName")
    private String DevName;

    @JsonProperty("DevCode")
    private String DevCode;

    @JsonProperty("Place")
    private String Place;

    @JsonProperty("SupRd")
    private int SupRd;

    @JsonProperty("DevFRd")
    private int DevFRd;

    @JsonProperty("DSMRd")
    private int DSMRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("DevBZInfo")
    private SaveDevInfoReqBD02DevBZInfo DevBZInfo;

    @JsonProperty("ToolFRd")
    private int ToolFRd;

    @JsonProperty("PMInfo")
    private List<SaveDevInfoReqBD02PM> PMInfo;

    @JsonProperty("CheckPlanInfo")
    private List<SaveDevInfoReqBD02CheckPlan> CheckPlanInfo;


    @JsonProperty("LineInfo")
    private List<GetDevInfoResDLine> LineInfo;

    @JsonProperty("DevPropertyInfo")
    private List<SaveDevPropertyInfo> DevPropertyInfo;

    @JsonIgnore
    public List<SaveDevPropertyInfo> getDevPropertyInfo() {
        return DevPropertyInfo;
    }
    @JsonIgnore
    public void setDevPropertyInfo(List<SaveDevPropertyInfo> devPropertyInfo) {
        DevPropertyInfo = devPropertyInfo;
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
    public List<GetDevInfoResDLine> getLineInfo() {
        return LineInfo;
    }
    @JsonIgnore
    public void setLineInfo(List<GetDevInfoResDLine> lineInfo) {
        LineInfo = lineInfo;
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
    public int getDevFRd() {
        return DevFRd;
    }

    @JsonIgnore
    public void setDevFRd(int devFRd) {
        DevFRd = devFRd;
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
    public int getToolFRd() {
        return ToolFRd;
    }
    @JsonIgnore
    public void setToolFRd(int toolFRd) {
        ToolFRd = toolFRd;
    }
    @JsonIgnore
    public List<SaveDevInfoReqBD02PM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<SaveDevInfoReqBD02PM> PMInfo) {
        this.PMInfo = PMInfo;
    }
    @JsonIgnore
    public List<SaveDevInfoReqBD02CheckPlan> getCheckPlanInfo() {
        return CheckPlanInfo;
    }
    @JsonIgnore
    public void setCheckPlanInfo(List<SaveDevInfoReqBD02CheckPlan> checkPlanInfo) {
        CheckPlanInfo = checkPlanInfo;
    }
    @JsonIgnore
    public SaveDevInfoReqBD02DevBZInfo getDevBZInfo() {
        return DevBZInfo;
    }
    @JsonIgnore
    public void setDevBZInfo(SaveDevInfoReqBD02DevBZInfo devBZInfo) {
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

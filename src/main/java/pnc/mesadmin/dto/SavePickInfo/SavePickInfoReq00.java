package pnc.mesadmin.dto.SavePickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：从页面传过来的请求信息
 * 创建人：张亮亮
 * 创建时间：2017-8-9
 * 修改人：
 * 修改时间：
 */
public class SavePickInfoReq00 {
    @JsonProperty("PickCode")
    private String PickCode;

    @JsonProperty("AssCode")
    private String AssCode;

    @JsonProperty("AssSource")
    private String AssSource;

    @JsonProperty("PrePickDate")
    private String PrePickDate;

    @JsonProperty("PKMaInfo")
    private List<SavePickInfoReq00RKMa> PKMaInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getAssCode() {
        return AssCode;
    }
    @JsonIgnore
    public void setAssCode(String assCode) {
        AssCode = assCode;
    }
    @JsonIgnore
    public List<SavePickInfoReq00RKMa> getPKMaInfo() {
        return PKMaInfo;
    }
    @JsonIgnore
    public void setPKMaInfo(List<SavePickInfoReq00RKMa> PKMaInfo) {
        this.PKMaInfo = PKMaInfo;
    }
    @JsonIgnore
    public String getPickCode() {
        return PickCode;
    }
    @JsonIgnore
    public void setPickCode(String pickCode) {
        PickCode = pickCode;
    }
    @JsonIgnore
    public String getPrePickDate() {
        return PrePickDate;
    }
    @JsonIgnore
    public void setPrePickDate(String prePickDate) {
        PrePickDate = prePickDate;
    }
    @JsonIgnore
    public String getAssSource() {
        return AssSource;
    }
    @JsonIgnore
    public void setAssSource(String assSource) {
        AssSource = assSource;
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

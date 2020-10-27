package pnc.mesadmin.dto.SaveNPickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：从页面传过来的请求信息
 * 创建人：张亮亮
 * 创建时间：2017-10-24
 * 修改人：
 * 修改时间：
 */
public class SaveNPickInfoReq00 {
    @JsonProperty("PickCode")
    private String PickCode;

    @JsonProperty("PickerRd")
    private int PickerRd;

    @JsonProperty("PrePickDate")
    private String PrePickDate;


    @JsonProperty("Use")
    private String Use;

    @JsonProperty("PKMaInfo")
    private List<SaveNPickInfoReq00RKMa> PKMaInfo;


    @JsonIgnore
    public String getPickCode() {
        return PickCode;
    }
    @JsonIgnore
    public void setPickCode(String pickCode) {
        PickCode = pickCode;
    }
    @JsonIgnore
    public int getPickerRd() {
        return PickerRd;
    }
    @JsonIgnore
    public void setPickerRd(int pickerRd) {
        PickerRd = pickerRd;
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
    public String getUse() {
        return Use;
    }
    @JsonIgnore
    public void setUse(String use) {
        Use = use;
    }
    @JsonIgnore
    public List<SaveNPickInfoReq00RKMa> getPKMaInfo() {
        return PKMaInfo;
    }
    @JsonIgnore
    public void setPKMaInfo(List<SaveNPickInfoReq00RKMa> PKMaInfo) {
        this.PKMaInfo = PKMaInfo;
    }
}

package pnc.mesadmin.dto.SaveDicInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保存字典信息DTO请求业务数据实体类BD00：新增
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class SaveDicInfoReqBD00 implements Serializable{

    @JsonProperty("LanCode")
    private String LanCode;

    @JsonProperty("LabelID")
    private String LabelID;

    @JsonProperty("LabelDes")
    private String LabelDes;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getLanCode() {
        return LanCode;
    }

    @JsonIgnore
    public void setLanCode(String lanCode) {
        LanCode = lanCode;
    }

    @JsonIgnore
    public String getLabelID() {
        return LabelID;
    }

    @JsonIgnore
    public void setLabelID(String labelID) {
        LabelID = labelID;
    }

    @JsonIgnore
    public String getLabelDes() {
        return LabelDes;
    }

    @JsonIgnore
    public void setLabelDes(String labelDes) {
        LabelDes = labelDes;
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

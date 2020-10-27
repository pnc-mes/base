package pnc.mesadmin.dto.GetAllDicInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息列表DTO返回业务数据实体类Data
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetAllDicInfoResD implements Serializable{

    @JsonProperty("DicRd")
    private int DicRd;

    @JsonProperty("LabelID")
    private String LabelID;

    @JsonProperty("LabelDes")
    private String LabelDes;

    @JsonIgnore
    public int getDicRd() {
        return DicRd;
    }

    @JsonIgnore
    public void setDicRd(int dicRd) {
        DicRd = dicRd;
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
}

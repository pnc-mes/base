package pnc.mesadmin.dto.GetDicInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息DTO返回业务数据实体类Data
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class GetDicInfoResD implements Serializable{

    @JsonProperty("DicRd")
    private int DicRd;

    @JsonProperty("LabelID")
    private String LabelID;

    @JsonProperty("LabelDes")
    private String LabelDes;

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
}

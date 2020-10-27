package pnc.mesadmin.dto.GetDVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集版本详细信息DTO，返回版本列表的业务实体类DCItemsInfo
 * 创建人：赵超
 * 创建时间：2017-5-25
 * 修改人：
 * 修改时间：
 */
public class GetDVInfoResDDCItems implements Serializable{

    @JsonProperty("ItemRd")
    private int ItemRd;

    @JsonProperty("ItemName")
    private String ItemName;

    @JsonProperty("DataAlias")
    private String DataAlias;

    @JsonProperty("ItemType")
    private GetDVInfoResDDItemType ItemType;

    @JsonProperty("SValue")
    private String SValue;

    @JsonProperty("UpLimit")
    private String UpLimit;

    @JsonProperty("DwLimit")
    private String DwLimit;

    @JsonProperty("DefValue")
    private String DefValue;

    @JsonProperty("Checked")
    private String Checked;
    @JsonIgnore
    public String getDataAlias() {
        return DataAlias;
    }
    @JsonIgnore
    public void setDataAlias(String dataAlias) {
        DataAlias = dataAlias;
    }

    @JsonIgnore
    public int getItemRd() {
        return ItemRd;
    }

    @JsonIgnore
    public void setItemRd(int itemRd) {
        ItemRd = itemRd;
    }

    @JsonIgnore
    public String getItemName() {
        return ItemName;
    }

    @JsonIgnore
    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    @JsonIgnore
    public GetDVInfoResDDItemType getItemType() {
        return ItemType;
    }

    @JsonIgnore
    public void setItemType(GetDVInfoResDDItemType itemType) {
        ItemType = itemType;
    }

    @JsonIgnore
    public String getSValue() {
        return SValue;
    }

    @JsonIgnore
    public void setSValue(String SValue) {
        this.SValue = SValue;
    }

    @JsonIgnore
    public String getUpLimit() {
        return UpLimit;
    }

    @JsonIgnore
    public void setUpLimit(String upLimit) {
        UpLimit = upLimit;
    }

    @JsonIgnore
    public String getDwLimit() {
        return DwLimit;
    }

    @JsonIgnore
    public void setDwLimit(String dwLimit) {
        DwLimit = dwLimit;
    }

    @JsonIgnore
    public String getDefValue() {
        return DefValue;
    }

    @JsonIgnore
    public void setDefValue(String defValue) {
        DefValue = defValue;
    }

    @JsonIgnore
    public String getChecked() {
        return Checked;
    }

    @JsonIgnore
    public void setChecked(String checked) {
        Checked = checked;
    }
}

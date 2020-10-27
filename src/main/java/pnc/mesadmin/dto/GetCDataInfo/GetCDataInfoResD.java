package pnc.mesadmin.dto.GetCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:28
 * @Description:
 */
public class GetCDataInfoResD implements Serializable {
    @JsonProperty("CusDataRd")
    private int CusDataRd;

    @JsonProperty("CusDataName")
    private String CusDataName;

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

    @JsonProperty("CusDataDtl")
    private List<GetCDataInfoResDCusDataDtl> CusDataDtl;
    @JsonIgnore
    public int getCusDataRd() {
        return CusDataRd;
    }
    @JsonIgnore
    public void setCusDataRd(int cusDataRd) {
        CusDataRd = cusDataRd;
    }
    @JsonIgnore
    public String getCusDataName() {
        return CusDataName;
    }
    @JsonIgnore
    public void setCusDataName(String cusDataName) {
        CusDataName = cusDataName;
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
    public List<GetCDataInfoResDCusDataDtl> getCusDataDtl() {
        return CusDataDtl;
    }
    @JsonIgnore
    public void setCusDataDtl(List<GetCDataInfoResDCusDataDtl> cusDataDtl) {
        CusDataDtl = cusDataDtl;
    }
}

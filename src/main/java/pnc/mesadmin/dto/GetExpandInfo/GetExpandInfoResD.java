package pnc.mesadmin.dto.GetExpandInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 17:32
 * @Description:
 */
public class GetExpandInfoResD  implements Serializable {
    @JsonProperty("ExpandRd")
    private int ExpandRd;

    @JsonProperty("ExpandName")
    private String ExpandName;

    @JsonProperty("IsSettleObj")
    private String IsSettleObj;

    @JsonProperty("ExpandType")
    private String ExpandType;

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

    @JsonProperty("ExpandDtl")
    private List<GetExpandInfoResDExpandDtl> ExpandDtl;
    @JsonIgnore
    public int getExpandRd() {
        return ExpandRd;
    }
    @JsonIgnore
    public void setExpandRd(int expandRd) {
        ExpandRd = expandRd;
    }
    @JsonIgnore
    public String getExpandType() {
        return ExpandType;
    }
    @JsonIgnore
    public void setExpandType(String expandType) {
        ExpandType = expandType;
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
    public List<GetExpandInfoResDExpandDtl> getExpandDtl() {
        return ExpandDtl;
    }
    @JsonIgnore
    public void setExpandDtl(List<GetExpandInfoResDExpandDtl> expandDtl) {
        ExpandDtl = expandDtl;
    }
    @JsonIgnore
    public String getExpandName() {
        return ExpandName;
    }
    @JsonIgnore
    public void setExpandName(String expandName) {
        ExpandName = expandName;
    }
    @JsonIgnore
    public String getIsSettleObj() {
        return IsSettleObj;
    }
    @JsonIgnore
    public void setIsSettleObj(String isSettleObj) {
        IsSettleObj = isSettleObj;
    }
}

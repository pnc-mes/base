package pnc.mesadmin.dto.GetUrcyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：紧急度代码信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-8-17
 * 修改人：
 * 修改时间：
 */
public class GetUrcyInfoResD implements Serializable {
    @JsonProperty("UrcyRd")
    private int UrcyRd;

    @JsonProperty("UrcyCode")
    private String UrcyCode;

    @JsonProperty("UrcyDes")
    private String UrcyDes;

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
    public int getUrcyRd() {
        return UrcyRd;
    }

    @JsonIgnore
    public void setUrcyRd(int urcyRd) {
        UrcyRd = urcyRd;
    }

    @JsonIgnore
    public String getUrcyCode() {
        return UrcyCode;
    }

    @JsonIgnore
    public void setUrcyCode(String urcyCode) {
        UrcyCode = urcyCode;
    }

    @JsonIgnore
    public String getUrcyDes() {
        return UrcyDes;
    }

    @JsonIgnore
    public void setUrcyDes(String urcyDes) {
        UrcyDes = urcyDes;
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

package pnc.mesadmin.dto.GetPrInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：打印机信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-5-27
 * 修改人：
 * 修改时间：
 */
public class GetPrInfoResD implements Serializable{

    @JsonProperty("PrRd")
    private int PrRd;

    @JsonProperty("PrName")
    private String PrName;

    @JsonProperty("PrSer")
    private String PrSer;

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
    public int getPrRd() {
        return PrRd;
    }

    @JsonIgnore
    public void setPrRd(int prRd) {
        PrRd = prRd;
    }

    @JsonIgnore
    public String getPrName() {
        return PrName;
    }

    @JsonIgnore
    public void setPrName(String prName) {
        PrName = prName;
    }

    @JsonIgnore
    public String getPrSer() {
        return PrSer;
    }

    @JsonIgnore
    public void setPrSer(String prSer) {
        PrSer = prSer;
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

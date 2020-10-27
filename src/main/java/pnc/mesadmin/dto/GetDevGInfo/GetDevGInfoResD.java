package pnc.mesadmin.dto.GetDevGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备组信息DTO返回业务数据实体类data
 * 创建人：刘福志
 * 创建时间：2017-5-23
 * 修改人：
 * 修改时间：
 */
public class GetDevGInfoResD implements Serializable{

    @JsonProperty("DevGRd")
    private int DevGRd;

    @JsonProperty("DevGpName")
    private String DevGpName;

    @JsonProperty("DevInfo")
    private List<GetDevGInfoResDDevInfo> DevInfo;

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
    public List<GetDevGInfoResDDevInfo> getDevInfo() {
        return DevInfo;
    }

    @JsonIgnore
    public void setDevInfo(List<GetDevGInfoResDDevInfo> devInfo) {
        DevInfo = devInfo;
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
    public int getDevGRd() {
        return DevGRd;
    }

    @JsonIgnore
    public void setDevGRd(int devGRd) {
        DevGRd = devGRd;
    }

    @JsonIgnore
    public String getDevGpName() {
        return DevGpName;
    }

    @JsonIgnore
    public void setDevGpName(String devGpName) {
        DevGpName = devGpName;
    }

}

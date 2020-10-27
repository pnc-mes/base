package pnc.mesadmin.dto.GetCarrierFamilyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取载具家族信息DTO返回实体类Res
 * 创建人：郝赞
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
public class GetCarrierFamilyInfoResD implements Serializable{

    @JsonProperty("CarrierFamilyRd")
    private Integer CarrierFamilyRd;

    @JsonProperty("CarrierFamilyName")
    private String CarrierFamilyName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Modle")
    private  Modle Modle;

    @JsonProperty("Factory")
    private Factory Factory;

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

    @JsonProperty("PMInfo")
    private List<GetCarrierFamilyInfoResDPM> PMInfo;

    public   static  class Factory{
        @JsonProperty("FaRd")
        private Integer FaRd;

        @JsonProperty("FaName")
        private  String FaName;

        @JsonIgnore
        public Integer getFaRd() {
            return FaRd;
        }

        @JsonIgnore
        public void setFaRd(Integer faRd) {
            FaRd = faRd;
        }


        @JsonIgnore
        public String getFaName() {
            return FaName;
        }
        @JsonIgnore
        public void setFaName(String faName) {
            FaName = faName;
        }
    }

    public   static  class Modle{

        @JsonProperty("DSMRd")
        private Integer DSMRd;

        @JsonProperty("ModelName")
        private  String ModelName;

        @JsonIgnore
        public Integer getDSMRd() {
            return DSMRd;
        }
        @JsonIgnore
        public void setDSMRd(Integer DSMRd) {
            this.DSMRd = DSMRd;
        }
        @JsonIgnore
        public String getModelName() {
            return ModelName;
        }
        @JsonIgnore
        public void setModelName(String modelName) {
            ModelName = modelName;
        }
    }

    @JsonIgnore
    public Integer getCarrierFamilyRd() {
        return CarrierFamilyRd;
    }

    @JsonIgnore
    public void setCarrierFamilyRd(Integer carrierFamilyRd) {
        CarrierFamilyRd = carrierFamilyRd;
    }

    @JsonIgnore
    public String getCarrierFamilyName() {
        return CarrierFamilyName;
    }

    @JsonIgnore
    public void setCarrierFamilyName(String carrierFamilyName) {
        CarrierFamilyName = carrierFamilyName;
    }

    @JsonIgnore
    public String getDescription() {
        return Description;
    }

    @JsonIgnore
    public void setDescription(String description) {
        Description = description;
    }
    @JsonIgnore
    public GetCarrierFamilyInfoResD.Modle getModle() {
        return Modle;
    }
    @JsonIgnore
    public void setModle(GetCarrierFamilyInfoResD.Modle modle) {
        Modle = modle;
    }
    @JsonIgnore
    public GetCarrierFamilyInfoResD.Factory getFactory() {
        return Factory;
    }
    @JsonIgnore
    public void setFactory(GetCarrierFamilyInfoResD.Factory factory) {
        Factory = factory;
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
    public List<GetCarrierFamilyInfoResDPM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<GetCarrierFamilyInfoResDPM> PMInfo) {
        this.PMInfo = PMInfo;
    }
}

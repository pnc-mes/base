package pnc.mesadmin.dto.GetCarrierInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetCheckPlanInfoDto;
import pnc.mesadmin.dto.SaveCarrierInfoReqBD00CheckPlanInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：获取载具信息DTO返回实体类Body
 * 创建人：郝赞
 * 创建时间：2018-6-13
 * 修改人：
 * 修改时间：
 */
public class GetCarrierInfoResD implements Serializable{

    @JsonProperty("CarrierRd")
    private Integer CarrierRd;

    @JsonProperty("CarrierName")
    private String CarrierName;

    @JsonProperty("CarrierFamily")
    private CarrierFamily CarrierFamily;

    @JsonProperty("Suppier")
    private Suppier Suppier;

    @JsonProperty("Model")
    private Model Model;
    //载具序列号
    @JsonProperty("VenderSN")
    private String VenderSN;

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

    @JsonProperty("CarrierBZInfo")
    private GetCarrierInfoResDCarrierBZInfo CarrierBZInfo;


    @JsonProperty("Status")
    private String Status;

    @JsonProperty("PMInfo")
    private List<GetCarrierInfoResDPM> PMInfo;

    //熊伟开始
    @JsonProperty("CheckPlanInfo")
    private List<SaveCarrierInfoReqBD00CheckPlanInfo> CheckPlanInfo;

    @JsonIgnore
    public List<GetCarrierInfoResDPM> getPMInfo() {
        return PMInfo;
    }
    @JsonIgnore
    public void setPMInfo(List<GetCarrierInfoResDPM> PMInfo) {
        this.PMInfo = PMInfo;
    }

    public static class Factory{
        @JsonProperty("FaRd")
        private Integer FaRd;

        @JsonProperty("FaName")
        private String FaName;

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
    public static class Suppier{

        @JsonProperty("SupRd")
        private Integer SupRd;

        @JsonProperty("SupName")
        private String SupName;

        @JsonIgnore
        public Integer getSupRd() {
            return SupRd;
        }
        @JsonIgnore
        public void setSupRd(Integer supRd) {
            SupRd = supRd;
        }
        @JsonIgnore
        public String getSupName() {
            return SupName;
        }
        @JsonIgnore
        public void setSupName(String supName) {
            SupName = supName;
        }
    }
    public static class Model{

        @JsonProperty("DSMRd")
        private Integer DSMRd;

        @JsonProperty("ModelName")
        private String ModelName;

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
    public static class CarrierFamily{

        @JsonProperty("CarrierFamilyRd")
        private Integer CarrierFamilyRd;

        @JsonProperty("CarrierFamilyName")
        private String CarrierFamilyName;

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
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public Integer getCarrierRd() {
        return CarrierRd;
    }

    @JsonIgnore
    public void setCarrierRd(Integer carrierRd) {
        CarrierRd = carrierRd;
    }

    @JsonIgnore
    public String getCarrierName() {
        return CarrierName;
    }

    @JsonIgnore
    public void setCarrierName(String carrierName) {
        CarrierName = carrierName;
    }

    @JsonIgnore
    public CarrierFamily getCarrierFamily() {
        return CarrierFamily;
    }

    @JsonIgnore
    public void setCarrierFamily(CarrierFamily CarrierFamily) {
        this.CarrierFamily = CarrierFamily;
    }
    @JsonIgnore
    public GetCarrierInfoResD.Suppier getSuppier() {
        return Suppier;
    }
    @JsonIgnore
    public void setSuppier(GetCarrierInfoResD.Suppier suppier) {
        Suppier = suppier;
    }
    @JsonIgnore
    public String getVenderSN() {
        return VenderSN;
    }
    @JsonIgnore
    public void setVenderSN(String venderSN) {
        VenderSN = venderSN;
    }

    @JsonIgnore
    public Model getModel() {
        return Model;
    }

    @JsonIgnore
    public void setModel(Model model) {
        Model = model;
    }

    @JsonIgnore
    public Factory getFactory() {
        return Factory;
    }

    @JsonIgnore
    public void setFactory(Factory factory) {
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
    public GetCarrierInfoResDCarrierBZInfo getCarrierBZInfo() {
        return CarrierBZInfo;
    }
    @JsonIgnore
    public void setCarrierBZInfo(GetCarrierInfoResDCarrierBZInfo carrierBZInfo) {
        CarrierBZInfo = carrierBZInfo;
    }
    @JsonIgnore
    public List<SaveCarrierInfoReqBD00CheckPlanInfo> getCheckPlanInfo() {
        return CheckPlanInfo;
    }
    @JsonIgnore
    public void setCheckPlanInfo(List<SaveCarrierInfoReqBD00CheckPlanInfo> checkPlanInfo) {
        CheckPlanInfo = checkPlanInfo;
    }
}

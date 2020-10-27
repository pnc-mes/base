package pnc.mesadmin.dto.GetSPartInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 16:29
 * @Description:
 */
public class GetSPartInfoResD implements Serializable {
    @JsonProperty("SPartRd")
    private int SPartRd;

    @JsonProperty("SPartName")
    private String SPartName;

    @JsonProperty("VenderSN")
    private String VenderSN;

    @JsonProperty("Factory")
    private Factory Factory;

    @JsonProperty("Suppier")
    private Suppier Suppier;

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
    public int getSPartRd() {
        return SPartRd;
    }
    @JsonIgnore
    public void setSPartRd(int SPartRd) {
        this.SPartRd = SPartRd;
    }
    @JsonIgnore
    public String getSPartName() {
        return SPartName;
    }
    @JsonIgnore
    public void setSPartName(String SPartName) {
        this.SPartName = SPartName;
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
    public GetSPartInfoResD.Factory getFactory() {
        return Factory;
    }
    @JsonIgnore
    public void setFactory(GetSPartInfoResD.Factory factory) {
        Factory = factory;
    }
    @JsonIgnore
    public GetSPartInfoResD.Suppier getSuppier() {
        return Suppier;
    }
    @JsonIgnore
    public void setSuppier(GetSPartInfoResD.Suppier suppier) {
        Suppier = suppier;
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

    public static class Factory{
        @JsonProperty("FaRd")
        private int FaRd;

        @JsonProperty("FaName")
        private String FaName;
        @JsonIgnore
        public int getFaRd() {
            return FaRd;
        }
        @JsonIgnore
        public void setFaRd(int faRd) {
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
        private int SupRd;

        @JsonProperty("SupName")
        private String SupName;
        @JsonIgnore
        public int getSupRd() {
            return SupRd;
        }
        @JsonIgnore
        public void setSupRd(int supRd) {
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

}

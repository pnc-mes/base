package pnc.mesadmin.dto.GetAllCuOrderInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/8/24.
 */
public class GetAllCuOrderInfoResD {
    @JsonProperty("CoRd")
    private Integer CoRd;
    @JsonProperty("OrderCode")
    private String OrderCode;
    @JsonProperty("Num")
    private float Num;
    @JsonProperty("DSource")
    private String DSource; //数据来源 00#外部数据 01#自建
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
    @JsonProperty("COTInfo")
    private COTInfo COTInfo;
    @JsonProperty("MaInfo")
    private MaInfo MaInfo;
    @JsonProperty("UnitInfo")
    private UnitInfo UnitInfo;
    @JsonProperty("CustomerInfo")
    private CustomerInfo CustomerInfo;
    @JsonProperty("woInfoList")
    private List<WoInfo> woInfoList;

    //订单类型
    public static class COTInfo {
        @JsonProperty("CTRd")
        private Integer CTRd;
        @JsonProperty("CTName")
        private String CTName;

        public Integer getCTRd() {
            return CTRd;
        }

        public void setCTRd(Integer CTRd) {
            this.CTRd = CTRd;
        }

        public String getCTName() {
            return CTName;
        }

        public void setCTName(String CTName) {
            this.CTName = CTName;
        }
    }

    //物料管理
    public static class MaInfo {
        @JsonProperty("MaVerRd")
        private Integer MaVerRd;
        @JsonProperty("MaCode")
        private String MaCode;
        @JsonProperty("MaName")
        private String MaName;
        @JsonProperty("MaDes")
        private String MaDes;

        public Integer getMaVerRd() {
            return MaVerRd;
        }

        public void setMaVerRd(Integer maVerRd) {
            MaVerRd = maVerRd;
        }

        public String getMaCode() {
            return MaCode;
        }

        public void setMaCode(String maCode) {
            MaCode = maCode;
        }

        public String getMaName() {
            return MaName;
        }

        public void setMaName(String maName) {
            MaName = maName;
        }

        public String getMaDes() {
            return MaDes;
        }

        public void setMaDes(String maDes) {
            MaDes = maDes;
        }
    }

    //单位信息
    public static class UnitInfo {
        @JsonProperty("UnitRd")
        private Integer UnitRd;
        @JsonProperty("UnitName")
        private String UnitName;

        public Integer getUnitRd() {
            return UnitRd;
        }

        public void setUnitRd(Integer unitRd) {
            UnitRd = unitRd;
        }

        public String getUnitName() {
            return UnitName;
        }

        public void setUnitName(String unitName) {
            UnitName = unitName;
        }
    }

    //客户信息
    public static class CustomerInfo {
        @JsonProperty("CustomerRd")
        private Integer CustomerRd;
        @JsonProperty("CustomerName")
        private String CustomerName;

        public Integer getCustomerRd() {
            return CustomerRd;
        }

        public void setCustomerRd(Integer customerRd) {
            CustomerRd = customerRd;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }
    }

    //关联工单信息
    public static class WoInfo {
        @JsonProperty("WoCode")
        private Integer WoRd;
        @JsonProperty("WoCode")
        private String WoCode;
        @JsonProperty("MaVerRd")
        private Integer MaVerRd;
        @JsonProperty("MaName")
        private String MaName;
        @JsonProperty("MaterialDes")
        private String MaterialDes;
        @JsonProperty("Num")
        private Float Num;
        @JsonProperty("UnitName")
        private String UnitName;
        @JsonProperty("JFDate")
        private String JFDate;
        @JsonProperty("SFDate")
        private String SFDate;
        @JsonProperty("Status")
        private String Status;

        public Integer getWoRd() {
            return WoRd;
        }

        public void setWoRd(Integer woRd) {
            WoRd = woRd;
        }

        public String getWoCode() {
            return WoCode;
        }

        public void setWoCode(String woCode) {
            WoCode = woCode;
        }

        public Integer getMaVerRd() {
            return MaVerRd;
        }

        public void setMaVerRd(Integer maVerRd) {
            MaVerRd = maVerRd;
        }

        public String getMaName() {
            return MaName;
        }

        public void setMaName(String maName) {
            MaName = maName;
        }

        public String getMaterialDes() {
            return MaterialDes;
        }

        public void setMaterialDes(String materialDes) {
            MaterialDes = materialDes;
        }

        public Float getNum() {
            return Num;
        }

        public void setNum(Float num) {
            Num = num;
        }

        public String getUnitName() {
            return UnitName;
        }

        public void setUnitName(String unitName) {
            UnitName = unitName;
        }

        public String getJFDate() {
            return JFDate;
        }

        public void setJFDate(String JFDate) {
            this.JFDate = JFDate;
        }

        public String getSFDate() {
            return SFDate;
        }

        public void setSFDate(String SFDate) {
            this.SFDate = SFDate;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }
    }

    public Integer getCoRd() {
        return CoRd;
    }

    public void setCoRd(Integer coRd) {
        CoRd = coRd;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public float getNum() {
        return Num;
    }

    public void setNum(float num) {
        Num = num;
    }

    public String getDSource() {
        return DSource;
    }

    public void setDSource(String DSource) {
        this.DSource = DSource;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public String getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public GetAllCuOrderInfoResD.COTInfo getCOTInfo() {
        return COTInfo;
    }

    public void setCOTInfo(GetAllCuOrderInfoResD.COTInfo COTInfo) {
        this.COTInfo = COTInfo;
    }

    public GetAllCuOrderInfoResD.MaInfo getMaInfo() {
        return MaInfo;
    }

    public void setMaInfo(GetAllCuOrderInfoResD.MaInfo maInfo) {
        MaInfo = maInfo;
    }

    public GetAllCuOrderInfoResD.UnitInfo getUnitInfo() {
        return UnitInfo;
    }

    public void setUnitInfo(GetAllCuOrderInfoResD.UnitInfo unitInfo) {
        UnitInfo = unitInfo;
    }

    public GetAllCuOrderInfoResD.CustomerInfo getCustomerInfo() {
        return CustomerInfo;
    }

    public void setCustomerInfo(GetAllCuOrderInfoResD.CustomerInfo customerInfo) {
        CustomerInfo = customerInfo;
    }

    public List<WoInfo> getWoInfoList() {
        return woInfoList;
    }

    public void setWoInfoList(List<WoInfo> woInfoList) {
        this.woInfoList = woInfoList;
    }
}

package pnc.mesadmin.dto.WmsMaterialsBDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原材料批次库存DTO
 * 创建人：潘俊峰
 * 创建时间：2019-10-31
 * 修改人：
 * 修改时间：
 */
public class WmsMaterialsBStoreRes {
    @JsonProperty("MaCode")
    private String MaCode;

    @JsonProperty("MaName")
    private String MaName;

    @JsonProperty("GradeName")
    private String GradeName;

    @JsonProperty("StoreName")
    private String StoreName;

    @JsonProperty("Num")
    private float Num;

    @JsonProperty("BInfo")
    private List<BInfo> BInfo;

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }

    @JsonIgnore
    public String getMaName() {
        return MaName;
    }

    @JsonIgnore
    public void setMaName(String maName) {
        MaName = maName;
    }

    @JsonIgnore
    public String getGradeName() {
        return GradeName;
    }

    @JsonIgnore
    public void setGradeName(String gradeName) {
        GradeName = gradeName;
    }

    @JsonIgnore
    public String getStoreName() {
        return StoreName;
    }

    @JsonIgnore
    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    @JsonIgnore
    public float getNum() {
        return Num;
    }

    @JsonIgnore
    public void setNum(float num) {
        Num = num;
    }

    @JsonIgnore
    public List<WmsMaterialsBStoreRes.BInfo> getBInfo() {
        return BInfo;
    }

    @JsonIgnore
    public void setBInfo(List<WmsMaterialsBStoreRes.BInfo> BInfo) {
        this.BInfo = BInfo;
    }

    public static class BInfo {
        @JsonProperty("MaCode")
        private String MaCode;

        @JsonProperty("MaName")
        private String MaName;

        @JsonProperty("StoreName")
        private String StoreName;

        @JsonProperty("Num")
        private float Num;

        @JsonProperty("Batch")
        private String Batch;

        @JsonProperty("GradeName")
        private String GradeName;

        @JsonProperty("BatchNo")
        private String BatchNo;

        @JsonProperty("IQCStatus")
        private String IQCStatus;

        @JsonProperty("CreateTime")
        private String CreateTime;

        @JsonProperty("Creator")
        private String Creator;

        @JsonIgnore
        public String getMaCode() {
            return MaCode;
        }

        @JsonIgnore
        public void setMaCode(String maCode) {
            MaCode = maCode;
        }

        @JsonIgnore
        public String getMaName() {
            return MaName;
        }

        @JsonIgnore
        public void setMaName(String maName) {
            MaName = maName;
        }

        @JsonIgnore
        public String getStoreName() {
            return StoreName;
        }

        @JsonIgnore
        public void setStoreName(String storeName) {
            StoreName = storeName;
        }

        @JsonIgnore
        public float getNum() {
            return Num;
        }

        @JsonIgnore
        public void setNum(float num) {
            Num = num;
        }

        @JsonIgnore
        public String getBatch() {
            return Batch;
        }

        @JsonIgnore
        public void setBatch(String batch) {
            Batch = batch;
        }

        @JsonIgnore
        public String getGradeName() {
            return GradeName;
        }

        @JsonIgnore
        public void setGradeName(String gradeName) {
            GradeName = gradeName;
        }

        @JsonIgnore
        public String getBatchNo() {
            return BatchNo;
        }

        @JsonIgnore
        public void setBatchNo(String batchNo) {
            BatchNo = batchNo;
        }

        @JsonIgnore
        public String getIQCStatus() {
            return IQCStatus;
        }

        @JsonIgnore
        public void setIQCStatus(String IQCStatus) {
            this.IQCStatus = IQCStatus;
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
        public String getCreator() {
            return Creator;
        }

        @JsonIgnore
        public void setCreator(String creator) {
            Creator = creator;
        }
    }
}

package pnc.mesadmin.dto.GetAllBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-WES管理系统
 * Created by liufuzhi on 2017/10/30.
 */
public class GetAllBatchInfoResD1 {
    @JsonProperty("MaCode")
    private String MaCode;
    @JsonProperty("MaName")
    private String MaName;
    @JsonProperty("CreateDate")
    private String CreateDate;
    @JsonProperty("InStockStatus")
    private String InStockStatus;
    @JsonProperty("CreateDate1")
    private String CreateDate1;
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
    public String getCreateDate() {
        return CreateDate;
    }

    @JsonIgnore
    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    @JsonIgnore
    public String getInStockStatus() {
        return InStockStatus;
    }

    @JsonIgnore
    public void setInStockStatus(String inStockStatus) {
        InStockStatus = inStockStatus;
    }

    @JsonIgnore
    public String getCreateDate1() {
        return CreateDate1;
    }
    @JsonIgnore
    public void setCreateDate1(String createDate1) {
        CreateDate1 = createDate1;
    }
}

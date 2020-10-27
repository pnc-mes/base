package pnc.mesadmin.dto.newmove.GetRePackInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2019/4/28 10:39
 * @Description:
 */
public class GetRePackInfoReq00 {
    @JsonProperty("BarCode")
    private String BarCode;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("Detail")
    private List<GetRePackInfoResDetail> Detail;
    @JsonIgnore
    public String getBarCode() {
        return BarCode;
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
    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
    @JsonIgnore
    public List<GetRePackInfoResDetail> getDetail() {
        return Detail;
    }
    @JsonIgnore
    public void setDetail(List<GetRePackInfoResDetail> detail) {
        Detail = detail;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public static class GetRePackInfoResDetail{
        @JsonProperty("Batch")
        private String Batch;

        @JsonIgnore
        public String getBatch() {
            return Batch;
        }
        @JsonIgnore
        public void setBatch(String batch) {
            Batch = batch;
        }
    }
}

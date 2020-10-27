package pnc.mesadmin.dto.SaveCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 15:16
 * @Description:
 */
public class SaveCTInfoReq00 {
    @JsonProperty("CTName")
    private String  CTName;

    @JsonProperty("Remark")
    private String  Remark;
    @JsonIgnore
    public String getCTName() {
        return CTName;
    }
    @JsonIgnore
    public void setCTName(String CTName) {
        this.CTName = CTName;
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

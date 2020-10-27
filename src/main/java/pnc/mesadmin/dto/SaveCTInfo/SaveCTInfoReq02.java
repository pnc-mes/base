package pnc.mesadmin.dto.SaveCTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 15:17
 * @Description:
 */
public class SaveCTInfoReq02 {
    @JsonProperty("CTRd")
    private int CTRd;

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
    @JsonIgnore
    public int getCTRd() {
        return CTRd;
    }
    @JsonIgnore
    public void setCTRd(int CTRd) {
        this.CTRd = CTRd;
    }
}

package pnc.mesadmin.dto.SaveCDataInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 13:27
 * @Description:
 */
public class SaveCDataInfoReq01 implements Serializable {
    @JsonProperty("CusDataRd")
    private int CusDataRd;
    @JsonIgnore
    public int getCusDataRd() {
        return CusDataRd;
    }
    @JsonIgnore
    public void setCusDataRd(int cusDataRd) {
        CusDataRd = cusDataRd;
    }
}

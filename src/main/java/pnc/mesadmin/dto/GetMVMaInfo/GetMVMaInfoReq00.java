package pnc.mesadmin.dto.GetMVMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
public class GetMVMaInfoReq00 {
    @JsonProperty("MVRd")
    private int MVRd;
    @JsonIgnore
    public int getMVRd() {
        return MVRd;
    }
    @JsonIgnore
    public void setMVRd(int MVRd) {
        this.MVRd = MVRd;
    }
}

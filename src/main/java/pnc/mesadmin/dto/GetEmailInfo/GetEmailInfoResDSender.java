package pnc.mesadmin.dto.GetEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/11 16:49
 * @Description:
 */
public class GetEmailInfoResDSender implements Serializable {
    @JsonProperty("SendRd")
    private int SendRd;
    @JsonProperty("SendName")
    private String SendName;
    @JsonIgnore
    public int getSendRd() {
        return SendRd;
    }
    @JsonIgnore
    public void setSendRd(int sendRd) {
        SendRd = sendRd;
    }
    @JsonIgnore
    public String getSendName() {
        return SendName;
    }
    @JsonIgnore
    public void setSendName(String sendName) {
        SendName = sendName;
    }
}

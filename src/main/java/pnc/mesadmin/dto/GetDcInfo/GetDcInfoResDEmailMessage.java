package pnc.mesadmin.dto.GetDcInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：数据采集信息DTO返回业务实体类Data
 * 创建人：张亮亮
 * 创建时间：2018-11-9
 * 修改人：
 * 修改时间：
 */
public class GetDcInfoResDEmailMessage implements Serializable{

    @JsonProperty("EmailMessageRd")
    private int EmailMessageRd;

    @JsonProperty("EmailMessageName")
    private String EmailMessageName;

    @JsonIgnore
    public int getEmailMessageRd() {
        return EmailMessageRd;
    }
    @JsonIgnore
    public void setEmailMessageRd(int emailMessageRd) {
        EmailMessageRd = emailMessageRd;
    }
    @JsonIgnore
    public String getEmailMessageName() {
        return EmailMessageName;
    }
    @JsonIgnore
    public void setEmailMessageName(String emailMessageName) {
        EmailMessageName = emailMessageName;
    }
}

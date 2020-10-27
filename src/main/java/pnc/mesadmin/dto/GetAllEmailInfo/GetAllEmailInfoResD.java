package pnc.mesadmin.dto.GetAllEmailInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：邮件内容列表信息DTO返回业务数据实体类data
 * 创建人：乔帅阳
 * 创建时间：2017-7-4
 * 修改人：
 * 修改时间：
 */
public class GetAllEmailInfoResD implements Serializable {

    @JsonProperty("EmailRd")
    private int EmailRd;

    @JsonProperty("EmailName")
    private String EmailName;
    @JsonIgnore
    public int getEmailRd() {
        return EmailRd;
    }
    @JsonIgnore
    public void setEmailRd(int emailRd) {
        EmailRd = emailRd;
    }
    @JsonIgnore
    public String getEmailName() {
        return EmailName;
    }
    @JsonIgnore
    public void setEmailName(String emailName) {
        EmailName = emailName;
    }
}

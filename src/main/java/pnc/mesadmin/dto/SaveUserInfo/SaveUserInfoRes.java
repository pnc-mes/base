package pnc.mesadmin.dto.SaveUserInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：用户返回所有的json
 * 创建人：张亮亮
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public class SaveUserInfoRes implements Serializable{

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Body")
    private SaveUserInfoResB Body;

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public SaveUserInfoResB getBody() {
        return Body;
    }

    @JsonIgnore
    public void setBody(SaveUserInfoResB body) {
        Body = body;
    }
}

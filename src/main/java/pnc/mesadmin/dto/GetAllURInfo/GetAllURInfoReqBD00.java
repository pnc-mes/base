package pnc.mesadmin.dto.GetAllURInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/23.
 */
public class GetAllURInfoReqBD00 implements Serializable {
    @JsonProperty("RoleName")
    private String RoleName;

    @JsonIgnore
    public String getRoleName() {
        return RoleName;
    }

    @JsonIgnore
    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}

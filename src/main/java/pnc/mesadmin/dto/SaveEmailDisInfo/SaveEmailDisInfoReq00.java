package pnc.mesadmin.dto.SaveEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 20:14
 * @Description:
 */
public class SaveEmailDisInfoReq00 {
    @JsonProperty("EmailDisName")
    private String EmailDisName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("UserList")
    private List<SaveEmailDisInfoReq00UserList> UserList;

    @JsonProperty("RoleList")
    private List<SaveEmailDisInfoReq00RoleList> RoleList;

    @JsonProperty("AddressList")
    private List<SaveEmailDisInfoReq00AddressList> AddressList;
    @JsonIgnore
    public String getEmailDisName() {
        return EmailDisName;
    }
    @JsonIgnore
    public void setEmailDisName(String emailDisName) {
        EmailDisName = emailDisName;
    }
    @JsonIgnore
    public String getDescription() {
        return Description;
    }
    @JsonIgnore
    public void setDescription(String description) {
        Description = description;
    }
    @JsonIgnore
    public List<SaveEmailDisInfoReq00UserList> getUserList() {
        return UserList;
    }
    @JsonIgnore
    public void setUserList(List<SaveEmailDisInfoReq00UserList> userList) {
        UserList = userList;
    }
    @JsonIgnore
    public List<SaveEmailDisInfoReq00RoleList> getRoleList() {
        return RoleList;
    }
    @JsonIgnore
    public void setRoleList(List<SaveEmailDisInfoReq00RoleList> roleList) {
        RoleList = roleList;
    }
    @JsonIgnore
    public List<SaveEmailDisInfoReq00AddressList> getAddressList() {
        return AddressList;
    }
    @JsonIgnore
    public void setAddressList(List<SaveEmailDisInfoReq00AddressList> addressList) {
        AddressList = addressList;
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

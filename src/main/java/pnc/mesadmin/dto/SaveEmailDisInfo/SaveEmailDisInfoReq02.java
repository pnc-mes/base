package pnc.mesadmin.dto.SaveEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 20:14
 * @Description:
 */
public class SaveEmailDisInfoReq02 {
    @JsonProperty("EmailDisRd")
    private int EmailDisRd;

    @JsonProperty("EmailDisName")
    private String EmailDisName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("UserList")
    private List<SaveEmailDisInfoReq02UserList> UserList;

    @JsonProperty("RoleList")
    private List<SaveEmailDisInfoReq02RoleList> RoleList;

    @JsonProperty("AddressList")
    private List<SaveEmailDisInfoReq02AddressList> AddressList;
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
    public List<SaveEmailDisInfoReq02UserList> getUserList() {
        return UserList;
    }
    @JsonIgnore
    public void setUserList(List<SaveEmailDisInfoReq02UserList> userList) {
        UserList = userList;
    }
    @JsonIgnore
    public List<SaveEmailDisInfoReq02RoleList> getRoleList() {
        return RoleList;
    }
    @JsonIgnore
    public void setRoleList(List<SaveEmailDisInfoReq02RoleList> roleList) {
        RoleList = roleList;
    }
    @JsonIgnore
    public List<SaveEmailDisInfoReq02AddressList> getAddressList() {
        return AddressList;
    }
    @JsonIgnore
    public void setAddressList(List<SaveEmailDisInfoReq02AddressList> addressList) {
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
    @JsonIgnore
    public int getEmailDisRd() {
        return EmailDisRd;
    }
    @JsonIgnore
    public void setEmailDisRd(int emailDisRd) {
        EmailDisRd = emailDisRd;
    }
}

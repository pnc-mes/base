package pnc.mesadmin.dto.GetEmailDisInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/5 18:39
 * @Description:
 */
public class GetEmailDisInfoResD {
    @JsonProperty("EmailDisRd")
    private int EmailDisRd;

    @JsonProperty("EmailDisName")
    private String EmailDisName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("UserList")
    private List<GetEmailDisInfoResDUserList> UserList;

    @JsonProperty("RoleList")
    private List<GetEmailDisInfoResDRoleList> RoleList;

    @JsonProperty("AddressList")
    private List<GetEmailDisInfoResDAddressList> AddressList;
    @JsonIgnore
    public int getEmailDisRd() {
        return EmailDisRd;
    }
    @JsonIgnore
    public void setEmailDisRd(int emailDisRd) {
        EmailDisRd = emailDisRd;
    }
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
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }
    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public List<GetEmailDisInfoResDUserList> getUserList() {
        return UserList;
    }
    @JsonIgnore
    public void setUserList(List<GetEmailDisInfoResDUserList> userList) {
        UserList = userList;
    }
    @JsonIgnore
    public List<GetEmailDisInfoResDRoleList> getRoleList() {
        return RoleList;
    }
    @JsonIgnore
    public void setRoleList(List<GetEmailDisInfoResDRoleList> roleList) {
        RoleList = roleList;
    }
    @JsonIgnore
    public List<GetEmailDisInfoResDAddressList> getAddressList() {
        return AddressList;
    }
    @JsonIgnore
    public void setAddressList(List<GetEmailDisInfoResDAddressList> addressList) {
        AddressList = addressList;
    }
}

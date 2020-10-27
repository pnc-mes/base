package pnc.mesadmin.dto.GetSysInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/5/19.
 */
public class GetSysInfoResD implements Serializable{

    @JsonProperty("AppName")
    private String AppName;

    @JsonProperty("AppLogo")
    private String AppLogo;

    @JsonProperty("Face")
    private String Face;

    @JsonProperty("SkinCode")
    private String SkinCode;

    @JsonProperty("MsgCount")
    private int MsgCount;

    @JsonProperty("MenuInfo")
    private List<GetSysInfoResDMenuInfo> MenuInfo;

    @JsonProperty("RecetClickMenu")
    private List<GetSysInfoResDRecetClickMenu> RecetClickMenu;

    @JsonProperty("LanguageList")
    private List<GetSysInfoResDLanguageList> LanguageList;

    @JsonIgnore
    public String getAppName() {
        return AppName;
    }

    @JsonIgnore
    public void setAppName(String appName) {
        AppName = appName;
    }

    @JsonIgnore
    public String getAppLogo() {
        return AppLogo;
    }

    @JsonIgnore
    public void setAppLogo(String appLogo) {
        AppLogo = appLogo;
    }

    @JsonIgnore
    public String getFace() {
        return Face;
    }

    @JsonIgnore
    public void setFace(String face) {
        Face = face;
    }

    @JsonIgnore
    public String getSkinCode() {
        return SkinCode;
    }

    @JsonIgnore
    public void setSkinCode(String skinCode) {
        SkinCode = skinCode;
    }

    @JsonIgnore
    public int getMsgCount() {
        return MsgCount;
    }

    @JsonIgnore
    public void setMsgCount(int msgCount) {
        MsgCount = msgCount;
    }

    @JsonIgnore
    public List<GetSysInfoResDMenuInfo> getMenuInfo() {
        return MenuInfo;
    }

    @JsonIgnore
    public void setMenuInfo(List<GetSysInfoResDMenuInfo> menuInfo) {
        MenuInfo = menuInfo;
    }

    @JsonIgnore
    public List<GetSysInfoResDRecetClickMenu> getRecetClickMenu() {
        return RecetClickMenu;
    }

    @JsonIgnore
    public void setRecetClickMenu(List<GetSysInfoResDRecetClickMenu> recetClickMenu) {
        RecetClickMenu = recetClickMenu;
    }

    @JsonIgnore
    public List<GetSysInfoResDLanguageList> getLanguageList() {
        return LanguageList;
    }

    @JsonIgnore
    public void setLanguageList(List<GetSysInfoResDLanguageList> languageList) {
        LanguageList = languageList;
    }
}

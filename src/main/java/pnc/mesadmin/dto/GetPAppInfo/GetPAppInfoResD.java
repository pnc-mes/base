package pnc.mesadmin.dto.GetPAppInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/9/6.
 */
public class GetPAppInfoResD {

    @JsonProperty("AppID")
    private String AppID;

    @JsonProperty("AppName")
    private String AppName;

    @JsonProperty("SAppVer")
    private String SAppVer;

    @JsonProperty("AppVer")
    private String AppVer;

    @JsonProperty("ForUpApp")
    private String ForUpApp;

    @JsonProperty("UpDes")
    private String UpDes;

    @JsonIgnore
    public String getAppID() {
        return AppID;
    }

    @JsonIgnore
    public void setAppID(String appID) {
        AppID = appID;
    }

    @JsonIgnore
    public String getAppName() {
        return AppName;
    }

    @JsonIgnore
    public void setAppName(String appName) {
        AppName = appName;
    }

    @JsonIgnore
    public String getSAppVer() {
        return SAppVer;
    }

    @JsonIgnore
    public void setSAppVer(String SAppVer) {
        this.SAppVer = SAppVer;
    }

    @JsonIgnore
    public String getAppVer() {
        return AppVer;
    }

    @JsonIgnore
    public void setAppVer(String appVer) {
        AppVer = appVer;
    }

    @JsonIgnore
    public String getForUpApp() {
        return ForUpApp;
    }

    @JsonIgnore
    public void setForUpApp(String forUpApp) {
        ForUpApp = forUpApp;
    }

    @JsonIgnore
    public String getUpDes() {
        return UpDes;
    }

    @JsonIgnore
    public void setUpDes(String upDes) {
        UpDes = upDes;
    }
}

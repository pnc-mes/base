package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVInfoResDPackInfo implements Serializable{

    @JsonProperty("Tray")
    private List<GetMVInfoResDPTray> Tray;

    @JsonProperty("Box")
    private List<GetMVInfoResDPBox> Box;

    @JsonIgnore
    public List<GetMVInfoResDPTray> getTray() {
        return Tray;
    }

    @JsonIgnore
    public void setTray(List<GetMVInfoResDPTray> tray) {
        Tray = tray;
    }

    @JsonIgnore
    public List<GetMVInfoResDPBox> getBox() {
        return Box;
    }

    @JsonIgnore
    public void setBox(List<GetMVInfoResDPBox> box) {
        Box = box;
    }

}

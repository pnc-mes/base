package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/14.
 */
//@Document
public class SaveIOSInfoReqBD06 implements Serializable{

   // @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("DoMaInfo")
    private List<SaveIOSInfoReqBD06DoMaInfo> DoMaInfo;

    @JsonProperty("DoDCInfo")
    private List<SaveIOSInfoReqBD06DoDCInfo> DoDCInfo;

    @JsonProperty("DoRCInfo")
    private List<SaveIOSInfoReqBD06DoRCInfo> DoRCInfo;

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public List<SaveIOSInfoReqBD06DoMaInfo> getDoMaInfo() {
        return DoMaInfo;
    }

    @JsonIgnore
    public void setDoMaInfo(List<SaveIOSInfoReqBD06DoMaInfo> doMaInfo) {
        DoMaInfo = doMaInfo;
    }

    @JsonIgnore
    public List<SaveIOSInfoReqBD06DoDCInfo> getDoDCInfo() {
        return DoDCInfo;
    }

    @JsonIgnore
    public void setDoDCInfo(List<SaveIOSInfoReqBD06DoDCInfo> doDCInfo) {
        DoDCInfo = doDCInfo;
    }

    @JsonIgnore
    public List<SaveIOSInfoReqBD06DoRCInfo> getDoRCInfo() {
        return DoRCInfo;
    }

    @JsonIgnore
    public void setDoRCInfo(List<SaveIOSInfoReqBD06DoRCInfo> doRCInfo) {
        DoRCInfo = doRCInfo;
    }
}

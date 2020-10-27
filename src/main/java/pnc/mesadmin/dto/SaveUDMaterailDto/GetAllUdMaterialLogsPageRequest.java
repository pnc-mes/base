package pnc.mesadmin.dto.SaveUDMaterailDto;

import pnc.mesadmin.utils.PageUtil;

import java.util.List;

/**
 * Created by yin.yang on 2010/05/27
 */
public class GetAllUdMaterialLogsPageRequest extends PageUtil {
    private String woCode; //工单号
    private String specGuid; //工序标识
    private List<String> woCodes; //多工单
    private List<String> specGuids; //多工序
    private String udType; //上下料 00-上料 01-下料

    public List<String> getWoCodes() {
        return woCodes;
    }

    public void setWoCodes(List<String> woCodes) {
        this.woCodes = woCodes;
    }

    public String getWoCode() {
        return woCode;
    }

    public void setWoCode(String woCode) {
        this.woCode = woCode;
    }

    public String getSpecGuid() {
        return specGuid;
    }

    public void setSpecGuid(String specGuid) {
        this.specGuid = specGuid;
    }

    public List<String> getSpecGuids() {
        return specGuids;
    }

    public void setSpecGuids(List<String> specGuids) {
        this.specGuids = specGuids;
    }

    public String getUdType() {
        return udType;
    }

    public void setUdType(String udType) {
        this.udType = udType;
    }
}

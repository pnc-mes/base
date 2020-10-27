package pnc.mesadmin.dto.SaveUDMaterailDto;

import pnc.mesadmin.entity.ULineMaterialDetailInfo;
import pnc.mesadmin.entity.ULineMaterialInfo;
import pnc.mesadmin.utils.PageUtil;

import java.util.List;

/**
 * Created by yin.yang on 2010/05/27
 */
public class SaveUdMateriaLallotRequest extends PageUtil {
    private String woCode; //调出工单号
    private String specGuid; //调出工序标识
    private String udType; //上下料 00-按工序 01-按工单
    private String woCodeIn; //调入工单号
    private String specGuidIn; //调入工序标识
    private List<ULineMaterialDetailInfo> detail;

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

    public String getUdType() {
        return udType;
    }

    public void setUdType(String udType) {
        this.udType = udType;
    }

    public String getWoCodeIn() {
        return woCodeIn;
    }

    public void setWoCodeIn(String woCodeIn) {
        this.woCodeIn = woCodeIn;
    }

    public String getSpecGuidIn() {
        return specGuidIn;
    }

    public void setSpecGuidIn(String specGuidIn) {
        this.specGuidIn = specGuidIn;
    }

    public List<ULineMaterialDetailInfo> getDetail() {
        return detail;
    }

    public void setDetail(List<ULineMaterialDetailInfo> detail) {
        this.detail = detail;
    }
}

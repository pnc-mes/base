package pnc.mesadmin.dto.SaveUDMaterailDto;

import pnc.mesadmin.entity.ULineMaterialDetailInfo;

/**
 * @Description 产线余料查询返回response
 * @Author yin.yang
 * @Date 2020/7/13
 * @Param
 * @Return
 * @Exception
 */
public class GetAllUDMaterialsRespons extends ULineMaterialDetailInfo {
    private String woCode; //工单号
    private String specName;//工序名称
    private String dateTime;//时间

    public String getWoCode() {
        return woCode;
    }

    public void setWoCode(String woCode) {
        this.woCode = woCode;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

package pnc.mesadmin.service;


import pnc.mesadmin.dto.GetWOInfo.GetWOInfoReqBD00;
import pnc.mesadmin.dto.SaveUDMaterailDto.SaveUDMaterailRequest;
import pnc.mesadmin.utils.BaseResponse;


/**
 * @Description 装卸物料
 * @Author yin.yang
 * @Date 2019/12/19
 * @Param
 * @Return
 * @Exception
 */
public interface UDMaterialService {
    /**
     * @Description 上料/下料
     * @Author yin.yang
     * @Date 2019/12/19
     * @Param
     * @Return
     * @Exception
     */
    void AddSaveUDMaterial(SaveUDMaterailRequest request);

    /**
     * @Description 根据工单号查询所有上下物料记录
     * @Author yin.yang
     * @Date 2019/12/19
     * @Param
     * @Return
     * @Exception
     */
    BaseResponse GetAllByWoCode(SaveUDMaterailRequest request);

    //dto查询工单信息 根据工单id,自己的id
    BaseResponse GetGetWOInfoRes(GetWOInfoReqBD00 argGetWOInfoReqBD00);
}

package pnc.mesadmin.service;


import pnc.mesadmin.dto.GetWOInfo.GetWOInfoReqBD00;
import pnc.mesadmin.dto.SaveUDMaterailDto.GetAllUdMaterialLogsPageRequest;
import pnc.mesadmin.dto.SaveUDMaterailDto.SaveUdMateriaLallotRequest;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.dto.SaveUDMaterailDto.SaveUDMaterailRequest;


/**
 * @Description 装卸物料
 * @Author yin.yang
 * @Date 2019/12/19
 * @Param
 * @Return
 * @Exception
 */
public interface UDMaterialPlusService {
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

    /**
     * @Description dto查询工单信息 根据工单id,自己的id
     * @Author yin.yang
     * @Date 2020/10/15
     * @Param
     * @Return
     * @Exception
     */
    BaseResponse GetGetWOInfoRes(GetWOInfoReqBD00 argGetWOInfoReqBD00);

    /**
     * @Description 分页查询上下料日志信息
     * @Author yin.yang
     * @Date 2020/10/15
     * @Param
     * @Return
     * @Exception
     */
    BaseResponse GetAllUDMaterialLogs(GetAllUdMaterialLogsPageRequest request);

    /**
     * @Description 分页查询上下料日志信息
     * @Author yin.yang
     * @Date 2020/10/15
     * @Param
     * @Return
     * @Exception
     */
    BaseResponse GetAllUDMaterials(GetAllUdMaterialLogsPageRequest request);

    /**
     * @Description 产线余料调拨
     * @Author yin.yang
     * @Date 2020/10/15
     * @Param
     * @Return
     * @Exception
     */
    BaseResponse SaveUdMateriaLallot(SaveUdMateriaLallotRequest request);
}

package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoReq00;
import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoRes;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoReq01;
import pnc.mesadmin.dto.SaveCKInfo.SaveCKInfoResB;
import pnc.mesadmin.dto.SaveCarrierRelationInfo.*;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:53
 * @Description:
 */
public interface CarrierRelationIService {
    //新增
    SaveCarrierRelationInfoRes AddCarrierRelationInfoRes(SaveCarrierRelationInfoReq00 saveCarrierRelationInfoReq00);

    //删除
    SaveCarrierRelationInfoRes RmSaveCarrierRelationInfoRes(SaveCarrierRelationInfoReq01 saveCarrierRelationInfoReq01);

    //根据载具id获取所有的关联批次信息
    GetCarrierRelationInfoRes QALLGetCarrierRelationInfoRes(GetCarrierRelationInfoReq00 getCarrierRelationInfoReq00);

    //清空载具
    SaveCarrierRelationInfoRes RmSaveCarrierRelationInfo(SaveCarrierRelationInfoReq02 saveCarrierRelationInfoReq02);

    //清空历史
    SaveCarrierRelationInfoRes RmSaveCarrierRelationInfo01(SaveCarrierRelationInfoReq03 saveCarrierRelationInfoReq03);

}

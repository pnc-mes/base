package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllCDataInfo.GetAllCDataInfoRes;
import pnc.mesadmin.dto.GetCDataInfo.GetCDataInfoReq00;
import pnc.mesadmin.dto.GetCDataInfo.GetCDataInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCDataInfo.SaveCDataInfoReq00;
import pnc.mesadmin.dto.SaveCDataInfo.SaveCDataInfoReq01;
import pnc.mesadmin.dto.SaveCDataInfo.SaveCDataInfoReq02;
import pnc.mesadmin.dto.SaveCDataInfo.SaveCDataInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 10:08
 * @Description:
 */
public interface CusDataIService {

    //查询列表
    GetAllCDataInfoRes QALLGetAllCDataInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //查询单个
    GetCDataInfoRes GetGetCDataInfoRes(GetCDataInfoReq00 getCDataInfoReq00);

    //保存
    SaveCDataInfoRes AddSaveCDataInfoRes(SaveCDataInfoReq00 saveCDataInfoReq00);

    //删除
    SaveCDataInfoRes RmSaveCDataInfoRes(SaveCDataInfoReq01 saveCDataInfoReq01);

    //修改
    SaveCDataInfoRes ModSaveCDataInfoRes(SaveCDataInfoReq02 saveCDataInfoReq02);
}

package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllCTInfo.GetAllCTInfoRes;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoReq00;
import pnc.mesadmin.dto.GetCTInfo.GetCTInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveCTInfo.SaveCTInfoReq00;
import pnc.mesadmin.dto.SaveCTInfo.SaveCTInfoReq01;
import pnc.mesadmin.dto.SaveCTInfo.SaveCTInfoReq02;
import pnc.mesadmin.dto.SaveCTInfo.SaveCTInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/27 14:42
 * @Description:
 */
public interface CoTypeIService {
    //查询所有
    GetAllCTInfoRes QALLGetAllCTInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //查询单个
    GetCTInfoRes GetGetCTInfoRes(GetCTInfoReq00 getCTInfoReq00);

    //增
    SaveCTInfoRes AddSaveCTInfoRes(SaveCTInfoReq00 saveCTInfoReq00);

    //删除
    SaveCTInfoRes RmSaveCTInfoRes(SaveCTInfoReq01 saveCTInfoReq01);

    //修改
    SaveCTInfoRes ModSaveCTInfoRes(SaveCTInfoReq02 saveCTInfoReq02);
}

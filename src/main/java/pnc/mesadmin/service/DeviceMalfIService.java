package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetDevMalfInfo.GetDevMalfInfoReq00;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq00;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq01;
import pnc.mesadmin.dto.SaveDevMalfInfo.SaveDevMalfInfoReq02;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 17:37
 * @Description:
 */
public interface DeviceMalfIService {
    //列表
    BaseRes QALLBaseRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //单个
    BaseRes GetBaseRes(GetDevMalfInfoReq00 getDevMalfInfoReq00);

    //保存
    BaseRes AddBaseRes(SaveDevMalfInfoReq00 saveDevMalfInfoReq00);

    //删除
    BaseRes RmBaseRes(SaveDevMalfInfoReq01 saveDevMalfInfoReq01);

    //更新
   BaseRes ModBaseRes(SaveDevMalfInfoReq02 saveDevMalfInfoReq02);
}

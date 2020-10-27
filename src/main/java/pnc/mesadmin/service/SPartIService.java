package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetSPartInfo.GetSPartInfoReq00;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq00;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq01;
import pnc.mesadmin.dto.SaveSPartInfo.SaveSPartInfoReq02;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/11/8 16:12
 * @Description:
 */
public interface SPartIService {
    //列表
    BaseRes QALLBaseRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //单个
    BaseRes GetBaseRes(GetSPartInfoReq00 getSPartInfoReq00);

    //保存
    BaseRes AddBaseRes(SaveSPartInfoReq00 saveSPartInfoReq00);

    //删除
    BaseRes RmBaseRes(SaveSPartInfoReq01 saveSPartInfoReq01);

    //更新
    BaseRes ModBaseRes(SaveSPartInfoReq02 saveSPartInfoReq02);

}

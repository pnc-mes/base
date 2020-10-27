package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllMaPerionInfo.GetAllMaPerionInfoRes;
import pnc.mesadmin.dto.GetMaPerionInfo.GetMaPerionInfoReq00;
import pnc.mesadmin.dto.GetMaPerionInfo.GetMaPerionInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveMaPerionInfo.SaveMaPerionInfoReq00;
import pnc.mesadmin.dto.SaveMaPerionInfo.SaveMaPerionInfoReq01;
import pnc.mesadmin.dto.SaveMaPerionInfo.SaveMaPerionInfoReq02;
import pnc.mesadmin.dto.SaveMaPerionInfo.SaveMaPerionInfoRes;
import java.util.List;

/**
 * Created by 郝赞 on 2018/9/6.
 */
public interface MaPerionIService {
    //获取在线物料有效期列表信息
    GetAllMaPerionInfoRes GetAllMaPerionInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取在线物料有效期信息
    GetMaPerionInfoRes GetMaPerionInfo(GetMaPerionInfoReq00 getMaPerionInfoReq00);

    //00保存在线物料有效期信息
    SaveMaPerionInfoRes AddSaveMaPerionInfo(SaveMaPerionInfoReq00 saveMaPerionInfoReq00);

    //01删除在线物料有效期信息
    SaveMaPerionInfoRes RmSaveMaPerionInfo(SaveMaPerionInfoReq01 saveMaPerionInfoReq01);

    //02修改在线物料有效期信息
    SaveMaPerionInfoRes ModSaveMaPerionInfo(SaveMaPerionInfoReq02 saveMaPerionInfoReq02);

}

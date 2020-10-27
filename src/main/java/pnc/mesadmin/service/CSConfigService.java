package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetCsConfigReq;
import pnc.mesadmin.dto.GetAllCsConfigDto.GetCsConfigRes;
import pnc.mesadmin.dto.GetAllCsConfigDtoV2.GetCsConfigV2Res;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;

import java.util.List;

/**
 * Created by yinyang on 2018/11/19.
 */
public interface CSConfigService {
    BaseRes GetAllCSInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    BaseRes GetCSInfo(GetCsConfigReq request);

    //新增
    BaseRes AddSaveCSInfo00(GetCsConfigV2Res req);

    //删除
    BaseRes AddSaveCSInfo01(GetCsConfigReq req);

    //修改
    BaseRes AddSaveCSInfo02(GetCsConfigV2Res req);

    //03发布菜单
    BaseRes AddSaveCSInfo03(GetCsConfigRes req);
}

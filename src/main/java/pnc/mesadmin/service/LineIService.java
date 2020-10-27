package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllLinefo.GetAllLinefoRes;
import pnc.mesadmin.dto.GetAllLinefo.GetAllLinefoResD;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoReqBD00;
import pnc.mesadmin.dto.GetFaInfo.GetFaInfoRes;
import pnc.mesadmin.dto.GetLineInfo.GetLineInfoReqBD00;
import pnc.mesadmin.dto.GetLineInfo.GetLineInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveFaInfo.*;
import pnc.mesadmin.dto.SaveLineInfo.*;

import java.util.List;

/**
 * Created by haozan on 2018/6/7.
 */
public interface LineIService {
    //dto获取线体列表信息
    GetAllLinefoRes QALLGetLinefoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    /**
     * 获取线体列表信息（新）
     * @param req
     * @return
     */
    PageResult<GetAllLinefoResD> QALLGetLineNewRes(BaseRequest req);

    //dto查询线体信息，根据LineRd(线体ID)
    GetLineInfoRes GetGetLineInfoRes(GetLineInfoReqBD00 argGetLineInfoReqBD00);

    //dto新增线体信息
    SaveLineInfoRes AddGetLineInfoRes(SaveLineInfoReqBD00 argSaveLineInfoReqBD00);

    //dto删除线体信息 根据传过来的线体id
    SaveLineInfoRes RmSaveLineInfoRes(SaveLineInfoReqBD01 argSaveLineInfoReqBD01);

    //tdo更新线体信息
    SaveLineInfoRes ModSaveLineInfoRes(SaveLineInfoReqBD02 argSaveLineInfoReqBD02);

    //复制更新线体信息
    SaveLineInfoRes AddSaveLineInfoRes(SaveLineInfoReqBD03 argSaveLineInfoReqBD03);
}

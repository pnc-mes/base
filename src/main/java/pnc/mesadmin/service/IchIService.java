package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllIChInfo.GetAllIChInfoRes;
import pnc.mesadmin.dto.GetAllIDlInfo.GetAllIDlInfoRes;
import pnc.mesadmin.dto.GetIncInfoRes.GetIncInfoReq00;
import pnc.mesadmin.dto.GetIncInfoRes.GetIncInfoRes;
import pnc.mesadmin.dto.GetWIncMaInfo.GetWIncMaInfoReqBD00;
import pnc.mesadmin.dto.GetWIncMaInfo.GetWIncMaInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveIChInfo.*;

import java.util.List;

/**
 * Created by PNC on 2017/8/24.
 */
public interface IchIService {

    //获取来料单列表信息
    GetAllIChInfoRes QALLGetAllIChInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取来料单明细信息
    GetAllIDlInfoRes QALLGetAllIDlInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //保存来料单
    SaveIChInfoRes AddSaveIChInfoRes(SaveIChInfoReq00 argSaveIChInfoReq00);

    //获取来料单信息
    GetIncInfoRes GetGetIncInfoRes(GetIncInfoReq00 argGetIncInfoReq00);

    //删除来料单
    SaveIChInfoRes RmSaveIChInfoRes(SaveIChInfoReq01 argSaveIChInfoReq01);

    //更新来料单
    SaveIChInfoRes ModSaveIChInfoRes(SaveIChInfoReq02 argSaveIChInfoReq02);

    /**
     * 获取来料收货未开单物料信息
     * @param argBD00
     * @return
     */
    GetWIncMaInfoRes GetWIncMaInfo(GetWIncMaInfoReqBD00 argBD00);

    /**
     * 下达
     * @param argBD03
     * @return
     */
    SaveIChInfoRes ModSaveIChOn(SaveIChInfoReq03 argBD03);

    /**
     * 取消
     * @param argBD04
     * @return
     */
    SaveIChInfoRes ModSaveIChOff(SaveIChInfoReq04 argBD04);
}

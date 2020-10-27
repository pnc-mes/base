package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllExpandInfo.GetAllExpandInfoRes;
import pnc.mesadmin.dto.GetAllExpandInfo.GetAllExpandInfoResD;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoReq00;
import pnc.mesadmin.dto.GetDWExpandInfo.GetDWExpandInfoRes;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoReq00;
import pnc.mesadmin.dto.GetExpandFieldInfo.GetExpandFieldInfoRes;
import pnc.mesadmin.dto.GetExpandInfo.GetExpandInfoReq00;
import pnc.mesadmin.dto.GetExpandInfo.GetExpandInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveExpandInfo.SaveExpandInfoReq00;
import pnc.mesadmin.dto.SaveExpandInfo.SaveExpandInfoReq01;
import pnc.mesadmin.dto.SaveExpandInfo.SaveExpandInfoReq02;
import pnc.mesadmin.dto.SaveExpandInfo.SaveExpandInfoRes;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/3 15:39
 * @Description:
 */
public interface ExpandIService {
    //列表
    GetAllExpandInfoRes QALLGetAllExpandInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //保存
    SaveExpandInfoRes AddSaveExpandInfoRes(SaveExpandInfoReq00 saveExpandInfoReq00);

    //删除
    SaveExpandInfoRes RmSaveExpandInfoRes(SaveExpandInfoReq01 saveExpandInfoReq01);

    //修改
    SaveExpandInfoRes ModSaveExpandInfoRes(SaveExpandInfoReq02 saveExpandInfoReq02);

    //单个
    GetExpandInfoRes GetGetExpandInfoRes(GetExpandInfoReq00 getExpandInfoReq00);

    //获取物料扩展字段对象信息
    GetDWExpandInfoRes GetDWExpandInfo(GetDWExpandInfoReq00 getDWExpandInfoReq00);

    //获取设备扩展字段对象信息
    GetDWExpandInfoRes GetDWExpandInfo01(GetDWExpandInfoReq00 getDWExpandInfoReq00);

    //获取扩展字段 获取扩展字段 对象 字段 信息
    GetExpandFieldInfoRes GetGetExpandFieldInfoRes(GetExpandFieldInfoReq00 getExpandFieldInfoReq00);

    /**
     * 获取物料扩展字段列表(新)
     * @param req
     * @return
     */
    PageResult<GetAllExpandInfoResD> QALLExpandNewRes(BaseRequest req);
}

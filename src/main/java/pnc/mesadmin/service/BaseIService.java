package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllEmailDisInfo.GetAllEmailDisInfoRes;
import pnc.mesadmin.dto.GetEmailDisInfo.GetEmailDisInfoReq00;
import pnc.mesadmin.dto.GetEmailDisInfo.GetEmailDisInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveEmailDisInfo.SaveEmailDisInfoReq00;
import pnc.mesadmin.dto.SaveEmailDisInfo.SaveEmailDisInfoReq01;
import pnc.mesadmin.dto.SaveEmailDisInfo.SaveEmailDisInfoReq02;
import pnc.mesadmin.dto.SaveEmailDisInfo.SaveEmailDisInfoRes;
import pnc.mesadmin.dto.SystemException;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：Base管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-08-04
 * 修改人：
 * 修改时间：
 */
public interface BaseIService {

    /**
     * 根据条件查询所有
     * @param clazz
     * @param method
     * @param initDataFields
     * @param pageInfo
     * @param <T>
     * @return
     * @throws SystemException
     */
    <T>List<T> QALLBaseInfo(Class clazz, String method, List<InitDataField> initDataFields,
                            PageInfo pageInfo) throws SystemException;

    /**
     * @Auther: zhangliangliang
     * @Date: 2018/7/5 18:28
     * @Description:
     */
    interface EmailDisIService {
        //获取分发列表
        GetAllEmailDisInfoRes QALLGetAllEmailDisInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

        //获取单个列表
        GetEmailDisInfoRes GetGetEmailDisInfoRes(GetEmailDisInfoReq00 getEmailDisInfoReq00);

        //新增分发
        SaveEmailDisInfoRes AddSaveEmailDisInfoRes(SaveEmailDisInfoReq00 saveEmailDisInfoReq00);

        //删除分发
        SaveEmailDisInfoRes RmSaveEmailDisInfoRes(SaveEmailDisInfoReq01 saveEmailDisInfoReq01);

        //修改分发
        SaveEmailDisInfoRes ModSaveEmailDisInfoRes(SaveEmailDisInfoReq02 saveEmailDisInfoReq02);
    }
}

package pnc.mesadmin.service;

import pnc.mesadmin.dto.GetAllDicInfo.GetAllDicInfoRes;
import pnc.mesadmin.dto.GetDicInfo.GetDicInfoRes;
import pnc.mesadmin.dto.GetDicLanTypeInfo.GetDicLanTypeInfoRes;
import pnc.mesadmin.dto.SaveDicInfo.SaveDicInfoReqBD00;
import pnc.mesadmin.dto.SaveDicInfo.SaveDicInfoReqBD01;
import pnc.mesadmin.dto.SaveDicInfo.SaveDicInfoReqBD02;
import pnc.mesadmin.dto.SaveDicInfo.SaveDicInfoRes;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.DicInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息Service接口
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
public interface DicIService {

    /**
     * 获取语言类别树
     * @return
     */
    GetDicLanTypeInfoRes getDicTree() throws SystemException;
    /**
     * 获取字典信息列表
     * @return
     */
    GetAllDicInfoRes getDicInfoListByLanCode(String lanCode)throws SystemException;
    /**
     * 获取字典信息
     * @return
     */
    GetDicInfoRes getDicInfoByGuid(String dicRd)throws SystemException;
    /**
     * 保存字典信息
     * @return
     */
    SaveDicInfoRes saveDicInfo(SaveDicInfoReqBD00 busData00, DicInfo dicInfo) throws SystemException;
    /**
     * 删除字典信息
     * @return
     */
    SaveDicInfoRes deleteDicInfoByRuid(List<SaveDicInfoReqBD01> dicRdList) throws SystemException;
    /**
     * 修改字典信息
     * @return
     */
    SaveDicInfoRes update(SaveDicInfoReqBD02 busData02, DicInfo dicInfo) throws SystemException;
}

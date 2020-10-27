package pnc.mesadmin.service;

import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllSupInfo.GetAllSupInfoRes;
import pnc.mesadmin.dto.GetAllSupInfo.GetAllSupInfoResD;
import pnc.mesadmin.dto.GetSupInfo.GetSupInfoRes;
import pnc.mesadmin.dto.SaveSupInfo.SaveSupInfoReqBD00;
import pnc.mesadmin.dto.SaveSupInfo.SaveSupInfoReqBD02;
import pnc.mesadmin.dto.SaveSupInfo.SaveSupInfoReqBD03;
import pnc.mesadmin.dto.SaveSupInfo.SaveSupInfoRes;
import pnc.mesadmin.entity.SupplierInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：供应商信息Service层接口
 * 创建人：刘福志
 * 创建时间：2017-5-25
 * 修改人：
 * 修改时间：
 */
public interface SupplierIService {
    //查询供应商列表信息
    GetAllSupInfoRes QALLselectAllSupplierInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException;

    //查询供应商信息
    GetSupInfoRes GetselectBySuppId(int supRd) throws SystemException;

    //保存供应商信息
    SaveSupInfoRes AddinsertSupplierInfo(SaveSupInfoReqBD00 busData00, SupplierInfo supplierInfo) throws SystemException;

    //更新供应商信息
    SaveSupInfoRes ModupdateSupplierInfo(SaveSupInfoReqBD02 busData02, SupplierInfo supplierInfo) throws SystemException;

    //复制供应商信息
    SaveSupInfoRes copySupplierInfo(SaveSupInfoReqBD03 busData03, SupplierInfo supplierInfo) throws SystemException;

    //删除供应商信息
    SaveSupInfoRes RmdeleteSupplierInfo(int ruid) throws SystemException;

    /**
     * 查询供应商列表信息(新)
     * @param req
     * @return
     */
    PageResult<GetAllSupInfoResD> QALLSupplierNew(BaseRequest req);
}

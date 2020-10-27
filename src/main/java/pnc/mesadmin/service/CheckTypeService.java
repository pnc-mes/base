package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoRes;
import pnc.mesadmin.dto.GetAllBatchInfo.GetAllBatchInfoResD1;
import pnc.mesadmin.dto.GetAllCheckTypeDTO.GetCheckTypeInfoResponse;
import pnc.mesadmin.dto.GetAllReq;
import pnc.mesadmin.dto.GetBatchInfo.GetBatchInfoRes;
import pnc.mesadmin.dto.GetHoldBatchDTO.GetHoldBatchReq;
import pnc.mesadmin.dto.GetSUBInfo.GetSUBInfoReqBD00;
import pnc.mesadmin.dto.GetSUBInfo.GetSUBInfoRes;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.MBaseDto.MBaseRes;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoReqBD00;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoReqBD02;
import pnc.mesadmin.dto.SaveBOptInfo.SaveBOptInfoRes;
import pnc.mesadmin.dto.SaveBatchInfo.*;
import pnc.mesadmin.dto.SaveSUBInfo.SaveSUBInfoReqBD00;
import pnc.mesadmin.dto.SaveSUBInfo.SaveSUBInfoReqBD01;
import pnc.mesadmin.dto.SaveSUBInfo.SaveSUBInfoRes;
import pnc.mesadmin.dto.SystemException;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.List;

public interface CheckTypeService {
    //检验类型-列表信息
    BaseRes GetAllCTInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo);

    //获取检验类型信息
    BaseRes GetCTInfo(GetCheckTypeInfoResponse request);

    //新增
    BaseRes AddCTInfo(GetCheckTypeInfoResponse reqBD00);

    //删除
    BaseRes RmDelCTInfo(GetCheckTypeInfoResponse reqBD00);

    //保存
    BaseRes AddSaveCTInfo(GetCheckTypeInfoResponse reqBD00);

    /**
     * 公司名称：驭航信息技术（上海）有限公司
     * 系统名称：PNC-MES管理系统
     * 子系统名称：批次拆分、合并管理控制器
     * 创建人：潘俊峰
     * 创建时间：2017-07-10
     * 修改人：
     * 修改时间：
     */
    interface BatchIService {

        public GetAllBatchInfoRes QAllBatchInfo(GetAllBatchInfoResD1 argGetAllBatchInfoResD1);

        //导出批次列表
        ByteArrayOutputStream ExportBatchInfo(GetAllBatchInfoResD1 argGetAllBatchInfoResD1);

        GetBatchInfoRes QBatchInfo(HttpServletRequest request, GetAllReq getAllReq);

        /**
         * 有无工单创建批次
         * @param argBD00
         * @return
         */
        SaveBatchInfoRes AddBatch(SaveBatchInfoReqBD00 argBD00);

        /**
         * 删除批次
         * @param argBD01
         * @return
         */
        SaveBatchInfoRes RmBatch(SaveBatchInfoReqBD01 argBD01);

        /**
         * 编辑批次
         * @param argBD02
         * @return
         */
        SaveBatchInfoRes ModBatch(SaveBatchInfoReqBD02 argBD02);

        /**
         * 物料创建批次
         * @param argBD03
         * @return
         */
        SaveBatchInfoRes AddMVBatch(SaveBatchInfoReqBD03 argBD03);

        /**
         * 获取批次拆分、合并信息
         * @param argBD00
         * @return
         * @throws SystemException
         */
        GetSUBInfoRes GetSUBInfo(GetSUBInfoReqBD00 argBD00) throws SystemException;

        /**
         * 保存拆分批次信息
         * @param argBD00
         * @return
         * @throws SystemException
         */
        SaveSUBInfoRes AddSplitBatch(SaveSUBInfoReqBD00 argBD00) throws SystemException;

        SaveSUBInfoRes AddSplitBatchPhone(String argBatch, String argSplitBatch, float argNum,
                                          String argReCode, String argOrderType, String userName);

        /**
         * 合并批次
         * @param argBD01
         * @return
         */
        SaveSUBInfoRes AddAndBatch(SaveSUBInfoReqBD01 argBD01);

        /**
         * 批次操作（冻结）
         * @param argBD00
         * @return
         */
        SaveBOptInfoRes AddHold(SaveBOptInfoReqBD00 argBD00);

        /**
         * 批次操作（解冻）
         * @param argBD00
         * @return
         */
        SaveBOptInfoRes AddUnHold(SaveBOptInfoReqBD00 argBD00);

        /**
         * 批次操作（不良）
         * @param argBD00
         * @return
         */
        SaveBOptInfoRes AddReject(SaveBOptInfoReqBD00 argBD00);

        /**
         * 批次操作（解除不良）
         * @param argBD00
         * @return
         */
        SaveBOptInfoRes AddUnReject(SaveBOptInfoReqBD00 argBD00);

        /**
         * 批次操作（打开）
         * @param argBD00
         * @return
         */
        SaveBOptInfoRes AddOpen(SaveBOptInfoReqBD00 argBD00);

        /**
         * 批次操作（关闭）
         * @param argBD00
         * @return
         */
        SaveBOptInfoRes AddClose(SaveBOptInfoReqBD00 argBD00);

        /**
         * 批次操作（报废原因）
         * @param argBD00
         * @return
         */
        SaveBOptInfoRes AddScrop(SaveBOptInfoReqBD00 argBD00);

        /**
         * 批次操作（更改数量）
         * @param argBD02
         * @return
         */
        SaveBOptInfoRes AddChgQty(SaveBOptInfoReqBD02[] argBD02);

        MBaseRes GetHoldBatch(GetHoldBatchReq model);
    }
}

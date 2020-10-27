package pnc.mesadmin.service;

import pnc.mesadmin.utils.BaseResponse;

/**
 * @Description
 * @Author yin.yang
 * @Date 2020/9/7
 * @Param
 * @Return
 * @Exception
 */
public interface BaseRequestSelectService {

    /**
     * @Description 所有物料查询
     */
    BaseResponse GetAllMaterial();

    /**
     * @Description 所有工序查询
     */
    BaseResponse GetAllSpec();

    /**
     * @Description 所有工单查询
     */
    BaseResponse GetAllWo();

    /**
     * @Description 所有设备查询
     */
    BaseResponse GetAllDev();

    /**
     * @Description 所有文件查询
     */
    BaseResponse GetAllFile();

    /**
     * @Description 所有企业查询
     */
    BaseResponse GetAllCompany();

}

package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSCSearchRelInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 通用查询配置与查询设置关系信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public interface CSCSearchRelInfoDAO {
    List<CSCSearchRelInfo> SelectAll();

    //通用查询标识 查询 查询设置
    CSCSearchRelInfo SelectcsConfigInfo(String CSConfigGd);

    //新增
    int InsertCSCSearchRelInfo(CSCSearchRelInfo model);

    //删除所有查询设置相关

    int DeleteAllByConfigGd(String configGd);
}

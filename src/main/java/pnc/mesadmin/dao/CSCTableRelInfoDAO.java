package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSCTableRelInfo;

import java.util.List;

/**
 * @program: mesadmin
 * @description: 通用查询配置与表格设置关系信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public interface CSCTableRelInfoDAO {
    List<CSCTableRelInfo> SelectAll();

    //配置Guid 查询 表格设置
    CSCTableRelInfo SelectcscTableRelInfo(String CSConfigGd);

    //新增
    int InsertCSCTableRelInfo(CSCTableRelInfo model);

    //删除
    int DeleteAllByConfigGd(String configGd);
}

package pnc.mesadmin.dao;

import pnc.mesadmin.entity.CSTableColumnsInfo;

import java.util.List;

public interface CSTableColumnsDAO {
    List<CSTableColumnsInfo> SelectAllInfo();

    //设置输出字段
    List<CSTableColumnsInfo> SelectCSTableColumnsInfo(String CSTableGd);

    //新增
    int InsertCSTableColumnsInfo(CSTableColumnsInfo model);
}

package pnc.mesadmin.dao;

import pnc.mesadmin.entity.FileGpDtlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件组信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-6
 * 修改人：
 * 修改时间：
 */
public interface FileGpDtlDAO {

    //查询文件组明细所有信息
    List<FileGpDtlInfo> SelectAllFileGpDtlInfo();

    //查询文件信息
    FileGpDtlInfo SelectBygPDtlRd(int gPDtlRd);

    //查询关联文件组明细信息
    List<FileGpDtlInfo> SelectByguid(String guid);

    //查询关联明细信息
    FileGpDtlInfo SelectByfileVerGd(String fileVerGd);

    //新增绑定文件信息
    int InsertFileGpDtlInfo(FileGpDtlInfo fileGpDtlInfo);

    //更新绑定文件信息
    int UpdateFileGpDtlInfo(FileGpDtlInfo fileGpDtlInfo);

    //删除绑定文件信息
    int DeleteFileGpDtlInfo(String guid);

    //删除文件信息
    int DeleteFileGpGd(String fileGpGd);
}

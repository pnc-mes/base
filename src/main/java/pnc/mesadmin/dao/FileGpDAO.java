package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.FileGpInfo;

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
public interface FileGpDAO extends BaseMapper<FileGpInfo> {
    //查询文件组信息
    List<FileGpInfo> SelectAllFileGpInfo();

    //查询文件组名字
    FileGpInfo  SelectfileGpName(String fileGpName);

    //查询文件列表信息
    FileGpInfo SelectByruid(int fileGRd);

    //查询关联文件guid
    FileGpInfo SelectByguid(String fileGpGd);

    //新增文件组信息
    int InsertFileFileGpInfo(FileGpInfo fileGpInfo);

    //更新文件组信息
    int UpdateFileGpInfo(FileGpInfo fileGpInfo);

    //删除文件组信息
    int DeleteFileGpInfo(int ruid);

}

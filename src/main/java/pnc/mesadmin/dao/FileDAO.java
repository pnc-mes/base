package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.FileInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public interface FileDAO extends BaseMapper<FileInfo> {
    //查询文件列表信息
    List<FileInfo> SelectAllFileInfo();

    //查询文件名称
    FileInfo SelectfileName(String fileName);

    //查询文件信息
    FileInfo SelectByfileRd(int fileRd);

    // 查询关联文件版本表信息
    FileInfo SelectByguid(String fileGd);

    //保存文件信息
    int InsertFileInfo(FileInfo fileInfo);

    //删除文件信息
    int DeleteFileInfo(int ruid);

    //更新文件信息
    int UpdateFileInfo(FileInfo fileInfo);
}

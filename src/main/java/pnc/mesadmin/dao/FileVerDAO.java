package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.FileVerInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件版本信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-3
 * 修改人：
 * 修改时间：
 */
public interface FileVerDAO extends BaseMapper<FileVerInfo> {
    //查询文件版本所有数据
    List<FileVerInfo> SelectAllfileInfo();

    //查询文件版本信息
    FileVerInfo DelectByfileRd(int fileRd);

    //查询文件版本信息
    FileVerInfo SelectByfileVerRd(int fileVerRd);

    //查询文件版本版本信息
    FileVerInfo SelectVersion(@Param("fileGd") String fileGd, @Param("version") String version);

   /* //查询文件版本fileGd和guid
    FileVerInfo SelectByfileGdguid(@Param("fileGd") String fileGd,@Param("guid") String guid);*/

    //查询关联文件组明细表信息
    FileVerInfo SelectByFileVerGd(String fileVerGd);

    //通过文件标识关联查询文件版本信息
    List<FileVerInfo> SelectByfileGd(String guid);

    //查询版本信息关联文件信息
    FileVerInfo SelectByguid(String guid);

    //新增文件版本信息
    int InsertFileVerInfo(FileVerInfo fileVerInfo);

    //删除文件版本信息
    int DeleteFileVerInforuid(int ruid);

    //更新版本
    int UpdateFileVerInfo(FileVerInfo fileVerInfo);

    //查询关联文件表
    List<FileVerInfo> SelectFileVerInfoByfileGd(String argfileGd);

    //查询关联文件表默认版本标识
    List<FileVerInfo> SelectByVerGd(String VerGd);

    //查询filegd
    List<FileVerInfo> SelectFileVerInfofileGd(String fileGd);

    //删除文件版本所有信息
    int DeleteFileVerInfo(@Param("fileGd") String argfileGd, @Param("guid") String argGuid);

    //根据物料、工序查询
    List<FileVerInfo> SelectByMaSpec(@Param("argMaGd") String argMaGd, @Param("argSpecGd") String argSpecGd, @Param("argWFVerGd") String argWFVerGd);

    //根据工序查询
    List<FileVerInfo> SelectBySpec(@Param("argSpecGd") String argSpecGd);
}

package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.SOPDlInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工序工艺文件信息DAO
 * 创建人：刘福志
 * 创建时间：2017-6-1
 * 修改人：
 * 修改时间：
 */
public interface SOPDlDAO extends BaseMapper<SOPDlInfo> {

    //查询工艺文件明细所有信息
    List<SOPDlInfo> SelectAllSOPDlInfo();


    //查询工序工艺信息guid
    List<SOPDlInfo> SelectByguid(String guid);

    //关联查询关联工艺表
    List<SOPDlInfo> SelectBySOPGd(String guid);

    //新增工序工艺文件信息
    int InsertSOPDlInfo(SOPDlInfo sopDlInfo);


    //删除工序工艺文件信息
    int DeleteSOPDlInfo(int maVerRd);

    //根据MaVerGd和SpecVerGd查询工序工艺文件信息  (By-pjf)
    SOPDlInfo SelectByMS(@Param("argMaVerGd") String argMaVerGd, @Param("argSpecVerGd") String argSpecVerGd);
}

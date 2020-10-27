package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.MaTypeInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：物料类别信息DAO
 * 创建人：刘福志
 * 创建时间：2017-08-21
 * 修改人：
 * 修改时间：
 */
public interface MaTypeDAO extends BaseMapper<MaTypeInfo> {
    //查询物料类别列表信息
    List<MaTypeInfo> SelectAllMaTypeInfo();

    //根据rd查询物料类别信息
    MaTypeInfo SelectByRuid(int mTRd);

    MaTypeInfo SelectAllMaTypeInfoByPMaTGdAndMaTName(@Param("pMaTGd") String pMaTGd, @Param("maTName") String maTName);

    MaTypeInfo SelectrootMaTypeInfoByMaTCode(@Param("materialType") String materialType, @Param("maTCode") String maTCode);

    MaTypeInfo SelectMaTypeChildrenInfoAndMaTCode(@Param("pMaTGd") String pMaTGd, @Param("maTCode") String maTCode);
    //根据gd查询物料类别信息
    MaTypeInfo SelectOneByGuid(String mTGd);

    MaTypeInfo SelectMaTypeInfoByMaTCode(String maTCode);

    //查询物料类别名称
    MaTypeInfo  SelectBymaTName(String maTName);

    //查询物料类别代码
    MaTypeInfo SelectBymaTCode(String maTCode);

    //根据物料类别gd查询
    List<MaTypeInfo>  SelectByGuid(String guid);

    //查询物料类别
    List<MaTypeInfo> SelectBypMaTGd();

    //新增物料类别信息
    int InsertMaTypeInfo(MaTypeInfo maTypeInfo);

    //删除物料类别信息
    int DeleteMaTypeInfo(int ruid);

    //更新物料类别信息
    int UpdateMaTypeInfo(MaTypeInfo maTypeInfo);

    //根据guid删除子信息
    int Deleteguid(String guid);

    //通过上级ID查询下级物料
    List<MaTypeInfo> SelectMaTypeChildrenInfo(String pMaTGd);
    //根目录
    List<MaTypeInfo> SelectrootMaTypeInfo(String materialType);

    //根据物料类别gd查询
    MaTypeInfo  SelectGuid(String guid);
}

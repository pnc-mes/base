package pnc.mesadmin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.ReaCodeInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：原因代码DAO
 * 创建人：张亮亮
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
public interface ReasonDAO extends BaseMapper<ReaCodeInfo> {
    //查询原因代码
    List<ReaCodeInfo> SelectReacodeInfo();
    //获取原因代码类别信息
    List<String> SelectReacodeInfoReaType();
    //查询原因代码类型数量
    int SelectReacodeInfoAccount(String reaType);
    //根据ruid查询原因代码信息
    ReaCodeInfo SelectReacodeInfoByruid(int argruid);
    //根据guid查询原因代码信息
    ReaCodeInfo SelectReacodeInfoByguid(String argguid);
    //增加原因代码
    int InsertReacodeInfo(ReaCodeInfo argReaCodeInfo);
    //删除原因代码
    int DeleteReacodeInfo(int argruid);
    //更新原因代码
    int UpdateReacodeInfo(ReaCodeInfo argReaCodeInfo);

    ReaCodeInfo SelectReacodeInfoByReaCode(String argReaCode);

    ReaCodeInfo SelectReacodeInfoByReaCodeAndReaType(@Param("reaCode") String reaCode, @Param("reaType") String reaType);
    //根据原因类别查询原因代码信息 --王怀龙
    List<ReaCodeInfo> SelectReacodeInfoByReaType(String reaType);
}

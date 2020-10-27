package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.SpecInfo;
import pnc.mesadmin.entity.UnitInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工序信息DAO
 * 创建人：张亮亮
 * 创建时间：2017-06-02
 * 修改人：
 * 修改时间：
 */
public interface SpecDAO  extends BaseMapper<SpecInfo>{
    //查询工序信息
    List<SpecInfo> SelectSpecInfo();

    //根据工序ID查询工序信息
    SpecInfo SelectSpecInfoByruid(int argruid);

    //根据guiid查询工序信息
    SpecInfo SelectSpecInfoByguid(String argguid);

    //查询工序名称--LFZ
    SpecInfo  SelectSpecName(String specName);

    //增加工序信息
    int InsertSpecInfo(SpecInfo argSpecInfo);

    //删除工序信息
    int DeleteSpecInfoByruid(int argruid);

    //更新工序信息
    int UpdateSpecInfo(SpecInfo argSpecInfo);

}

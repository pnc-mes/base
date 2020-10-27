package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.CarrierInfo;

import java.util.List;


/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：载具信息Dao层接口
 * 创建人：郝赞
 * 创建时间：2018-6-15
 * 修改人：
 * 修改时间：
 */
public interface CarrierDao extends BaseMapper<CarrierInfo> {

    //  获取载具列表信息
    List<CarrierInfo> SelectAllCarrierInfo();

    //根据guid查询载具信息
    CarrierInfo SelectCarrierInfoByruid(int argruid);


    //增加载具信息
    int InsertCarrierInfo(CarrierInfo argCarrierInfo);

    //根据guid查询载具信息
    CarrierInfo SelectCarrierInfoByguid(String argguid);

    //更新载具信息
    int UpdateCarrierInfoByruid(CarrierInfo argCarrierInfo);

    //根据载具名称查询载具信息
    CarrierInfo SelectCarrierInfoByFactoryName(String argCarrierName);

    //删除载具信息根据载具id
    int DeleteToolInfoByruid(int argruid);

    //根据VenderSN查询载具信息
    CarrierInfo SelectCarrierSN(String VenderSN);

    //删除载具信息根据载具id
    int DeleteToolInfoByruiddd(int argruid);





}

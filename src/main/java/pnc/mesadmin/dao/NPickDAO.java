package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.NPickInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/10/24.
 */
public interface NPickDAO {
    //新增来料单信息
    int InsertNPickInfo(NPickInfo argNPickInfo);

    //根据领料单ID更新领料单信息
    int UpdatenNPickInfo(NPickInfo argNPickInfo);

    //获取领料单列表信息
    List<NPickInfo> SelectNPickInfo();

    //获取领料单信息
    NPickInfo SelectNPickInfoByRuid(int argRuid);

    //通过领料单号获取领料单信息
    NPickInfo SelectNPickInfoByPickCode(String pickCode);

    //根据guid查询信息
    NPickInfo SelectNPickInfoGuid(String guid);

    //删除领料单信息
    int DeleteNPickInfoByRuid(int argRuid);

    //筛选
    List<NPickInfo> SelectNPickInfoShaiXuan(@Param("userRd") int userRd, @Param("pickCode") String pickCode,
                                            @Param("exStatus") String exStatus,
                                            @Param("createTime") String createTime, @Param("createTime1") String createTime1);
}

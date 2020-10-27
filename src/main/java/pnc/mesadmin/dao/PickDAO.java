package pnc.mesadmin.dao;

import pnc.mesadmin.entity.PickInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/30.
 */
public interface PickDAO {
    //新增来料单信息
    int InsertPickInfo(PickInfo argPickInfo);

    //根据领料单ID查询领料单信息  --王怀龙
    PickInfo SelectPickInfoByPickCode(String argPickCode);

    //根据领料单ID更新领料单信息       --王怀龙
    int UpdatePickInfo(PickInfo pickDtlInfo);

    //获取领料单列表信息
    List<PickInfo> SelectPickInfo();

    //获取领料单信息
    PickInfo SelectPickInfoByRuid(int argRuid);

    //删除领料单信息
    int DeletePickInfoByRuid(int argRuid);

    //根据关联单号查询多条信息
    List<PickInfo> SelectPickInfoByAssCode(String argAssCode);


    //根据guid查询
    PickInfo SelectPickInfoGuid(String guid);
}

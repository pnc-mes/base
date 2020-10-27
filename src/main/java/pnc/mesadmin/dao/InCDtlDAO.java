package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.InCDtlInfo;

import java.util.List;

/**
 * Created by PNC on 2017/8/24.
 */
public interface InCDtlDAO {
    //查询来料单那明细
    List<InCDtlInfo> SelectInCDtlInfo();

    //新增明细
    int InsertInCDtlInfo(InCDtlInfo argInCDtlInfo);

    //根据来料单好查询信息
    List<InCDtlInfo> SelectInCDtlInfoByInCCode(String argInCCode);

    //根据来料单InChGd查询明细信息
    List<InCDtlInfo> SelectInCDtlInfoByInChGd(String argInChGd);

    //根据guid查询来料单明细信息
    InCDtlInfo SelectInCDtlInfoByGuid(String argguid);

    //根据ruid查询来料单明细信息
    InCDtlInfo SelectInCDtlInfoByRuid(int argRuid);

    //更新来料单明细信息
    int UpdateInChDtlInfo(InCDtlInfo argInCDtlInfo);

    //删除来料单明细
    int DeleteInChDtlInfo(int argRuid);

    InCDtlInfo SelectInCDtlInfoByInCCodeAndMaVerGd(@Param("argInCCode") String argInCCode, @Param("argMaVerGd") String argMaVerGd);

    /**
     * 根据采购单查询已开单数量
     * @param argPurChDtlGd
     * @return
     */
    Float SelectIncDtlNumsByPurGd(String argPurChDtlGd);
}

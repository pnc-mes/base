package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.PackSpecificationInfo;

import java.util.List;

/**
 * Created by test on 2017/8/21.
 */
public interface PackSpecificationDAO extends BaseMapper<PackSpecificationInfo> {
    // 查询所有的包装规格
    List<PackSpecificationInfo> SelectAllPackSpecification();

    PackSpecificationInfo SelectPackSpecificationByRuid(int mpRd);

    PackSpecificationInfo SelectPackSpecificationByGuid(String mpGd);

    int SelectPackSpecificationByName(String mpName);

    int InsertPackSpecification(PackSpecificationInfo packSpecificationInfo);

    int DeletePackSpecificationByRuid(String mpRd);


    int SelectPackSPByName(PackSpecificationInfo ps);

    int UpdatePackSpecification(PackSpecificationInfo packSpecificationInfo);
}

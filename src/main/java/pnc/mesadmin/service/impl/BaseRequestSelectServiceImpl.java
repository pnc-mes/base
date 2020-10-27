package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.entity.MaVerInfo;
import pnc.mesadmin.entity.UnitInfo;
import pnc.mesadmin.entity.WoInfo;
import pnc.mesadmin.service.BaseRequestSelectService;
import pnc.mesadmin.utils.BaseResponse;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author yin.yang
 * @Date 2020/9/18
 * @Param
 * @Return
 * @Exception
 */
@Service
@Transactional
public class BaseRequestSelectServiceImpl implements BaseRequestSelectService {
    @Resource
    private MaVerDAO maVerDAO;
    @Resource
    private SpecVerDAO specVerDAO;
    @Resource
    private UnitDAO unitDAO;
    @Resource
    private WODAO wodao;
    @Resource
    private DevDAO devDAO;
    @Resource
    private FileDAO fileDAO;
    @Resource
    private CompanyDAO companyDAO;

    /**
     * @Description 所有物料查询
     */
    @Override
    public BaseResponse GetAllMaterial() {
        List<MaVerInfo> maVerInfoList = maVerDAO.selectList(new QueryWrapper<>());
        maVerInfoList.forEach(maVerInfo -> {
            UnitInfo unitInfo = unitDAO.selectOne(new QueryWrapper<UnitInfo>().eq("guid", maVerInfo.getUnitGd()));
            maVerInfo.setUnitGd(unitInfo != null ? unitInfo.getUnitName() : "");
        });
        return BaseResponse.success(maVerInfoList);
    }

    /**
     * @Description 所有工序查询
     */
    @Override
    public BaseResponse GetAllSpec() {
        return BaseResponse.success(specVerDAO.selectList(new QueryWrapper<>()));
    }

    /**
     * @Description 所有工单查询
     */
    @Override
    public BaseResponse GetAllWo() {
        return BaseResponse.success(wodao.selectList(new QueryWrapper<WoInfo>()
                .orderByDesc("createTime")));
    }

    /**
     * @Description 所有工单查询
     */
    @Override
    public BaseResponse GetAllDev() {
        return BaseResponse.success(devDAO.selectList(new QueryWrapper<>()));
    }

    @Override
    public BaseResponse GetAllFile() {
        return BaseResponse.success(fileDAO.selectList(new QueryWrapper<>()));
    }

    @Override
    public BaseResponse GetAllCompany() {
        return BaseResponse.success(companyDAO.selectList(new QueryWrapper<>()));
    }
}



package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetAllDevInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.DeviceIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description 设备信息
 * @Author yin.yang
 * @Date 2020/9/7
 * @Param
 * @Return
 * @Exception
 */
@Transactional
@Service
public class DeviceService implements DeviceIService {
    @Resource
    private DevDAO devDAO;
    @Resource
    private LineDao lineDao;
    @Resource
    private DevTypeDAO devTypeDAO;
    @Resource
    private DevGpDtlDAO devGpDtlDAO;


    /**
     * @Description 分页查询设备列表
     * @Author yin.yang
     * @Date 2020/9/7
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse GetDevList(GetDevListsRequest request) {
        Page<DevInfo> page = new Page<>(request.getPage(), request.getLimit());
        IPage<DevInfo> response = devDAO.selectPage(page, new QueryWrapper<DevInfo>()
                .like(!StringUtils.isBlank(request.getDevCode()), "devCode", request.getDevCode())
                .like(!StringUtils.isBlank(request.getDevName()), "devName", request.getDevName())
                .eq(!StringUtils.isBlank(request.getDevStatus()), "devStatus", request.getDevStatus())
                .eq(request.getDevTypeRd() != null, "devTypeRd", request.getDevTypeRd())
                .eq(request.getLineRd() != null, "lineRd", request.getLineRd())
                .between(!StringUtils.isBlank(request.getEndTime()), "createTime", request.getStartTime(), request.getEndTime()));
        for (DevInfo model : response.getRecords()) {
            //设备状态 00在线 01离线 02闲置
            model.setDevStatus(model.getDevStatus().equals("00") ? "在线" : model.getDevStatus().equals("01") ? "离线" : model.getDevStatus().equals("02") ? "闲置" : "");
            Lineinfo lineinfo = lineDao.SelectLineInfoBygruid(model.getLineRd());
            model.setLastModifyMan(lineinfo != null ? lineinfo.getLineName() : "");
            DevTypeInfo devTypeInfo = devTypeDAO.SelectByRuid(model.getDevTypeRd());
            model.setRemark(devTypeInfo != null ? devTypeInfo.getDevType() : "");
        }
        return BaseResponse.success(response);
    }

    /**
     * @Description 查询设备详情
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse GetDevDetails(DevSaveRequest request) {
        return BaseResponse.success(devDAO.selectById(request.getRuid()));
    }

    /**
     * @Description 新增设备
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse AddDevInfo(DevSaveRequest request) {
        Integer count = devDAO.selectCount(new QueryWrapper<DevInfo>().eq("devCode", request.getDevCode()));
        if (count > 0)
            throw new SystemException("EEEE", "设备编号出现重复!");
        request.setGuid(CommonUtils.getRandomNumber());
        request.setCreator("SYS");
        request.setCreateTime(new Date());
        devDAO.insert(request);
        return BaseResponse.SUCCESS;
    }

    /**
     * @Description 删除设备
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse RmDevInfo(DevSaveRequest request) {
        DevInfo devInfo = devDAO.selectById(request.getRuid());
        Integer count = devGpDtlDAO.selectCount(new QueryWrapper<DevGpDtlInfo>().eq("devGd", devInfo.getGuid()));
        if (count > 0)
            throw new SystemException("EEEE", "当前设备不允许删除，请先解绑设备组");
        devDAO.deleteById(request.getRuid());
        return BaseResponse.SUCCESS;
    }

    /**
     * @Description 修改设备
     * @Author yin.yang
     * @Date 2020/9/8
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse ModSaveDevInfo(DevSaveRequest request) {
        List<DevInfo> devInfoList = devDAO.selectList(new QueryWrapper<DevInfo>().eq("devCode", request.getDevCode()));
        if (devInfoList.size() > 0) {
            if (devInfoList.get(0).getRuid() != request.getRuid())
                throw new SystemException("EEEE", "设备编号出现重复");
        }
        devDAO.updateById(request);
        return BaseResponse.SUCCESS;
    }
}

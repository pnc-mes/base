package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DevDAO;
import pnc.mesadmin.dao.DevGpDAO;
import pnc.mesadmin.dao.DevGpDtlDAO;
import pnc.mesadmin.dao.SpecVerDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevGInfo.GetAllDevGInfoRes;
import pnc.mesadmin.dto.GetAllDevGInfo.GetAllDevGInfoResB;
import pnc.mesadmin.dto.GetAllDevGInfo.GetAllDevGInfoResD;
import pnc.mesadmin.dto.GetAllOpertInfo.GetAllOpertInfoResD;
import pnc.mesadmin.dto.GetDevGInfo.GetDevGInfoRes;
import pnc.mesadmin.dto.GetDevGInfo.GetDevGInfoResB;
import pnc.mesadmin.dto.GetDevGInfo.GetDevGInfoResD;
import pnc.mesadmin.dto.GetDevGInfo.GetDevGInfoResDDevInfo;
import pnc.mesadmin.dto.SaveDevGPInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.DeviceGIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class DeviceGService implements DeviceGIService {

    @Resource
    private DevGpDAO devGpDAO;

    @Resource
    private DevGpDtlDAO devGpDtlDAO;

    @Resource
    private DevDAO devDAO;

    @Resource
    private SpecVerDAO specVerDAO;

    @Resource
    private BaseIService baseIService;

    /**
     * @Description 分页查询
     * @Author yin.yang
     * @Date 2020/9/21
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse GetDevGroupList(SaveDevGpInfoReqBD02 request) {
        Page<DevGpInfo> page = new Page<>(request.getPage(), request.getLimit());
        IPage<DevGpInfo> response = devGpDAO.selectPage(page, new QueryWrapper<DevGpInfo>()
                .like(!StringUtils.isBlank(request.getDevGpName()), "devGpName", request.getDevGpName()));
        return BaseResponse.success(response);
    }

    //查询设备组列表信息
    public GetAllDevGInfoRes QALLGetAllDevGInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllDevGInfoRes objEGetAllDevGInfoRes = new GetAllDevGInfoRes();

        GetAllDevGInfoResB body = new GetAllDevGInfoResB();

        List<GetAllDevGInfoResD> dataList = new ArrayList<GetAllDevGInfoResD>();

        // 获取设备组信息
        List<DevGpInfo> devGpInfoList = baseIService.QALLBaseInfo(DevGpDAO.class, "SelectAllDevGpInfo",
                argInitDataFields, argPageInfo);

        if (devGpInfoList != null || devGpInfoList.size() > 0) {
            GetAllDevGInfoResD data = null;

            // 循环赋值查询文件组
            for (int i = 0; i < devGpInfoList.size(); i++) {
                data = new GetAllDevGInfoResD();
                data.setDevGRd(devGpInfoList.get(i).getRuid());
                data.setDevGpName(devGpInfoList.get(i).getDevGpName());
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllDevGInfoRes.setBody(body);

        return objEGetAllDevGInfoRes;
    }

    @Override
    public PageResult<GetAllDevGInfoResD> QALLGetAllDevGNew(BaseRequest req) {
        List<GetAllDevGInfoResD> resDList = new ArrayList<GetAllDevGInfoResD>();
        GetAllDevGInfoResD resD = null;

        IPage<DevGpInfo> iPage = devGpDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        // 循环赋值查询文件组
        for (DevGpInfo obj : iPage.getRecords()) {
            resD = new GetAllDevGInfoResD();
            resD.setDevGRd(obj.getRuid());
            resD.setDevGpName(obj.getDevGpName());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }

    //查询设备组信息
    public GetDevGInfoRes GetGetDevGInfoRes(int devGRd) throws SystemException {
        GetDevGInfoRes objEGetDevGInfoRes = new GetDevGInfoRes();

        GetDevGInfoResB body = new GetDevGInfoResB();

        GetDevGInfoResD data = new GetDevGInfoResD();

        // 获取设备组信息
        DevGpInfo objEDevGpInfo = devGpDAO.SelectDevGpById(devGRd);

        List<GetDevGInfoResDDevInfo> objEDevInfos = new ArrayList<GetDevGInfoResDDevInfo>();

        if (objEDevGpInfo != null) {
            //查询设备组明细信息
            List<DevGpDtlInfo> objEDevGpDtlInfo = devGpDtlDAO.SelectByguid(objEDevGpInfo.getGuid());

            for (DevGpDtlInfo obj : objEDevGpDtlInfo) {
                GetDevGInfoResDDevInfo objeGetDevGInfoResDDevInfo = new GetDevGInfoResDDevInfo();
                //查询设备信息
                DevInfo objEDevInfo = devDAO.SelectByguid(obj.getDevGd());
                if (objEDevInfo != null) {
                    objeGetDevGInfoResDDevInfo.setDevRd(objEDevInfo.getRuid());
                    objeGetDevGInfoResDDevInfo.setDevName(objEDevInfo.getDevName());
                }

                objEDevInfos.add(objeGetDevGInfoResDDevInfo);
            }

            //赋值查询信息
            data.setDevGRd(objEDevGpInfo.getRuid());
            data.setDevGpName(objEDevGpInfo.getDevGpName());
            data.setDevInfo(objEDevInfos);
            data.setCreator(objEDevGpInfo.getCreator());
            data.setCreateTime(DateUtil.format(objEDevGpInfo.getCreateTime()));
            data.setLastModifyMan(objEDevGpInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(objEDevGpInfo.getLastModifyTime()));
            data.setRemark(objEDevGpInfo.getRemark());
        }

        body.setData(data);
        objEGetDevGInfoRes.setBody(body);

        return objEGetDevGInfoRes;
    }

    //新增设备组信息
    public SaveDevGpInfoRes AddinsertDevGpInfo(SaveDevGpInfoReqBD00 busData00, DevGpInfo devGpInfo) throws SystemException {
        SaveDevGpInfoRes saveDevGpInfoRes = new SaveDevGpInfoRes();

        SaveDevGpInfoResB body = new SaveDevGpInfoResB();

        SaveDevGpInfoResD data = new SaveDevGpInfoResD();

        String guid = CommonUtils.getRandomNumber();
        //查询设备组所有信息
        List<DevGpInfo> objEDevGpInfos = devGpDAO.SelectAllDevGpInfo();

        // 赋值新增设备组
        devGpInfo.setGuid(guid);

        //逻辑校验保存的文件组名称不能相同
        for (DevGpInfo obj : objEDevGpInfos) {
            if (obj.getDevGpName().equals(busData00.getDevGpName())) {
                throw new SystemException("MG0006F", "设备组名称已存在");
            }
        }

        devGpInfo.setDevGpName(busData00.getDevGpName());
        devGpInfo.setCreator(CommonUtils.readUser().getRealName());
        devGpInfo.setCreateTime(new Date());
        devGpInfo.setRemark(busData00.getRemark());

        //设备信息不能重复
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < busData00.getDevInfo().size(); i++) {
            set.add(busData00.getDevInfo().get(i).getDevRd());
        }

        if (set.size() < busData00.getDevInfo().size()) {
            throw new SystemException("MG_MES2001012010001F", "设备信息重复,不能保存");
        }

        //新增设备信息
        for (SaveDevGpInfoReqBD00DevInfo obj : busData00.getDevInfo()) {
            //查询设备信息
            DevInfo objeDevInfo = devDAO.SelectBydevRd(obj.getDevRd());

            if (objeDevInfo == null) {
                throw new SystemException("MG_MES2001012010001F", "查询设备信息为空！");
            }

            DevGpDtlInfo objeDevGpDtlInfo = new DevGpDtlInfo();

            objeDevGpDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objeDevGpDtlInfo.setDevGd(objeDevInfo.getGuid());
            objeDevGpDtlInfo.setDevGpGd(guid);
            objeDevGpDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            objeDevGpDtlInfo.setCreateTime(new Date());
            objeDevGpDtlInfo.setRemark(busData00.getRemark());

            devGpDtlDAO.InsertDevGpDtlInfo(objeDevGpDtlInfo);
        }

        // 保存
        devGpDAO.InsertDevGpInfo(devGpInfo);


        body.setData(data);
        saveDevGpInfoRes.setBody(body);

        return saveDevGpInfoRes;
    }

    //更新设备组信息
    public SaveDevGpInfoRes ModupdateDevGpInfo(SaveDevGpInfoReqBD02 busData02, DevGpInfo devGpInfo) throws SystemException {
        SaveDevGpInfoRes saveDevGpInfoRes = new SaveDevGpInfoRes();

        SaveDevGpInfoResB body = new SaveDevGpInfoResB();

        SaveDevGpInfoResD data = new SaveDevGpInfoResD();

        // 获取设备组信息
        DevGpInfo objEDevGpInfo = devGpDAO.SelectDevGpById(busData02.getDevGRd());

        // 判断返回设备组是否为空
        if (objEDevGpInfo == null) {
            throw new SystemException("MG_MES2001010010001F", "查询设备组信息为空！");
        }

        //查询设备组名称信息
        DevGpInfo objEDevGpInfos = devGpDAO.SelectdevGpName(busData02.getDevGpName());

        if (objEDevGpInfos != null && !objEDevGpInfos.getDevGpName().equals(objEDevGpInfo.getDevGpName())) {
            throw new SystemException("MG0006F", "更新失败，设备组名称已存在");
        }

        // 赋值更新设备组信息
        devGpInfo.setRuid(busData02.getDevGRd());
        devGpInfo.setDevGpName(busData02.getDevGpName());
        devGpInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        devGpInfo.setLastModifyTime(new Date());
        devGpInfo.setRemark(busData02.getRemark());

        //查询设备组明细信息
        List<DevGpDtlInfo> objEDevGpDtlInfo = devGpDtlDAO.SelectByguid(objEDevGpInfo.getGuid());

        for (DevGpDtlInfo obj : objEDevGpDtlInfo) {
            devGpDtlDAO.DeleteDevGpDtlInfo(obj.getDevGpGd());
        }

        //设备信息不能重复
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < busData02.getDevInfo().size(); i++) {
            set.add(busData02.getDevInfo().get(i).getDevRd());
        }

        if (set.size() < busData02.getDevInfo().size()) {
            throw new SystemException("MG_MES2001012010001F", "设备信息重复,不能保存");
        }

        if (busData02.getDevInfo() != null) {
            for (SaveDevGpInfoReqBD02DevInfo obj : busData02.getDevInfo()) {

                //查询设备信息
                DevInfo objEDevInfo = devDAO.SelectBydevRd(obj.getDevRd());

                if (objEDevInfo == null) {
                    throw new SystemException("MG_MES2001012010001F", "查询设备信息为空！");
                }


                DevGpDtlInfo objeDevGpDtlInfo = new DevGpDtlInfo();

                objeDevGpDtlInfo.setGuid(CommonUtils.getRandomNumber());
                objeDevGpDtlInfo.setDevGpGd(objEDevGpInfo.getGuid());
                objeDevGpDtlInfo.setDevGd(objEDevInfo.getGuid());
                objeDevGpDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                objeDevGpDtlInfo.setCreateTime(new Date());
                objeDevGpDtlInfo.setRemark(busData02.getRemark());

                devGpDtlDAO.InsertDevGpDtlInfo(objeDevGpDtlInfo);
            }
        }

        // 更新
        int count = devGpDAO.UpdateDevGpInfo(devGpInfo);

        if (count <= 0) throw new SystemException("MG_MES4001214030002F", "更新设备组信息失败！");

        body.setData(data);
        saveDevGpInfoRes.setBody(body);
        return saveDevGpInfoRes;
    }

    //复制设备组信息
    public SaveDevGpInfoRes AddinsertDevGpInfo(SaveDevGpInfoReqBD03 busData03, DevGpInfo devGpInfo) throws SystemException {
        SaveDevGpInfoRes objESaveDevGpInfoRes = new SaveDevGpInfoRes();
        SaveDevGpInfoResB objESaveDevGpInfoResB = new SaveDevGpInfoResB();

        //查询设备组信息
        DevGpInfo objEDevGpInfo = devGpDAO.SelectDevGpById(busData03.getDevGRd());

        if (objEDevGpInfo == null) {
            throw new SystemException("MG_MES2001010010001F", "查询设备组信息为空");
        }

        String guids = CommonUtils.getRandomNumber();

        devGpInfo.setGuid(guids);
        devGpInfo.setDevGpName(objEDevGpInfo.getDevGpName());
        devGpInfo.setCreateTime(new Date());
        devGpInfo.setCreator(CommonUtils.readUser().getRealName());

        devGpDAO.InsertDevGpInfo(devGpInfo);

        DevGpInfo objEDevGpInfos = devGpDAO.SelectByguid(devGpInfo.getGuid());

        if (objEDevGpInfos == null) {
            throw new SystemException("MG_MES2001010010001F", "查询设备组信息为空");
        }

        objEDevGpInfos.setDevGpName(objEDevGpInfos.getDevGpName() + objEDevGpInfos.getRuid());
        if (devGpDAO.UpdateDevGpInfo(objEDevGpInfos) <= 0) {
            throw new SystemException("MG_MES3001212040003F", "复制设备组信息失败");
        }

        //查询设备组明细信息
        List<DevGpDtlInfo> objEDevGpDtlInfo = devGpDtlDAO.SelectByguid(objEDevGpInfo.getGuid());

        if (objEDevGpDtlInfo != null || objEDevGpDtlInfo.size() > 0) {
            for (DevGpDtlInfo obj : objEDevGpDtlInfo) {
                DevGpDtlInfo objeDevGpDtlInfos = new DevGpDtlInfo();

                objeDevGpDtlInfos.setGuid(CommonUtils.getRandomNumber());
                objeDevGpDtlInfos.setDevGd(obj.getDevGd());
                objeDevGpDtlInfos.setDevGpGd(guids);
                objeDevGpDtlInfos.setCreator(CommonUtils.readUser().getRealName());
                objeDevGpDtlInfos.setCreateTime(new Date());
                objeDevGpDtlInfos.setRemark(obj.getRemark());

                devGpDtlDAO.InsertDevGpDtlInfo(objeDevGpDtlInfos);
            }
        }


        objESaveDevGpInfoRes.setBody(objESaveDevGpInfoResB);
        return objESaveDevGpInfoRes;
    }

    //删除设备组信息
    public SaveDevGpInfoRes RmdeleteDevGpInfo(int ruid) throws SystemException {
        SaveDevGpInfoRes saveDevGpInfoRes = new SaveDevGpInfoRes();

        SaveDevGpInfoResB body = new SaveDevGpInfoResB();

        SaveDevGpInfoResD data = new SaveDevGpInfoResD();

        //查询设备组信息
        DevGpInfo objEDevGpInfo = devGpDAO.SelectDevGpById(ruid);
        if (objEDevGpInfo == null) {
            throw new SystemException("MG_MES4001210010001F", "查询设备组信息为空！");
        }

        Integer countQ = specVerDAO.selectCount(new QueryWrapper<SpecVerInfo>().eq("devGrGd", objEDevGpInfo.getGuid()));
        if (countQ > 0)
            throw new SystemException("MG_MES4001210010001F", "当前设备组绑定的工序, 请先解绑在进行删除操作！");

        //查询设备组明细信息
        List<DevGpDtlInfo> objEDevGpDtlInfo = devGpDtlDAO.SelectByguid(objEDevGpInfo.getGuid());

        //循环删除明细
        for (DevGpDtlInfo obj : objEDevGpDtlInfo) {
            devGpDtlDAO.DeleteDevGpDtlInfo(obj.getDevGpGd());
        }

        // 删除设备组信息
        int count = devGpDAO.DeleteDevGpInfo(ruid);

        if (count <= 0) throw new SystemException("MG_MES4001214020001F", "删除设备组信息失败！");

        body.setData(data);
        saveDevGpInfoRes.setBody(body);

        return saveDevGpInfoRes;
    }
}

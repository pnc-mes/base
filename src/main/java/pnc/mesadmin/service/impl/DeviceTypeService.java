package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DevTypeDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllDevTypeInfo.GetAllDevTypeInfoRes;
import pnc.mesadmin.dto.GetAllDevTypeInfo.GetAllDevTypeInfoResB;
import pnc.mesadmin.dto.GetAllDevTypeInfo.GetAllDevTypeInfoResD;
import pnc.mesadmin.dto.GetDevTypeInfo.GetDevTypeInfoRes;
import pnc.mesadmin.dto.GetDevTypeInfo.GetDevTypeInfoResB;
import pnc.mesadmin.dto.GetDevTypeInfo.GetDevTypeInfoResD;
import pnc.mesadmin.dto.SaveDevTypeInfo.*;
import pnc.mesadmin.entity.DevTypeInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.DeviceTypeIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：设备类型信息Service实现类
 * 创建人：蒋顾毅
 * 创建时间：2020-9-17
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class DeviceTypeService implements DeviceTypeIService{

    @Resource
    private DevTypeDAO devTypeDAO;

    @Resource
    private BaseIService baseIService;

    @Override
    public PageResult<GetAllDevTypeInfoResD> QALLselectAllDevTypeInfo(BaseRequest req) throws SystemException {
        List<GetAllDevTypeInfoResD> dataList = new ArrayList<>();
        GetAllDevTypeInfoResD resD = null;

        if(req.getSize() <= 0){
            req.setSize(1000);
        }

        Page<DevTypeInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<DevTypeInfo> devTypeInfoIPage = devTypeDAO.selectPage(page, CommonUtils.getQueryWrapper(req.getFields()));

        //获取紧急代码列表信息
        List<DevTypeInfo> devTypeInfos = devTypeInfoIPage.getRecords();
        for(DevTypeInfo obj : devTypeInfos){
            resD = new GetAllDevTypeInfoResD();
            resD.setDevTypeRd(obj.getRuid());
            resD.setDevType(obj.getDevType());
            dataList.add(resD);
        }

        return new PageResult(devTypeInfoIPage.getTotal(), devTypeInfoIPage.getPages(), devTypeInfoIPage.getCurrent(), devTypeInfoIPage.getSize(), dataList);
    }
    //获取设备类型信息
    @Override
    public GetDevTypeInfoRes GetselectByRuid(int DevTypeRd) throws SystemException {
        GetDevTypeInfoRes objGetDevTypeInfoRes = new GetDevTypeInfoRes();
        GetDevTypeInfoResB body = new GetDevTypeInfoResB();
        GetDevTypeInfoResD date = new GetDevTypeInfoResD();
        DevTypeInfo devTypeInfo = devTypeDAO.SelectByRuid(DevTypeRd);
        if(devTypeInfo!=null){
            date.setDevTypeRd(devTypeInfo.getRuid());
            date.setDevType(devTypeInfo.getDevType());
            date.setCreator(devTypeInfo.getCreator());
            date.setCreateTime(DateUtil.format(devTypeInfo.getCreateTime()));
            date.setLastModifyMan(devTypeInfo.getLastModifyMan());
            date.setLastModifyTime(DateUtil.format(devTypeInfo.getLastModifyTime()));
            date.setRemark(devTypeInfo.getRemark());
        }
        body.setData(date);
        objGetDevTypeInfoRes.setBody(body);
        return objGetDevTypeInfoRes;
    }

    //保存设备类型信息
    @Override
    public SaveDevTypeInfoRes AddinsertDevTypeInfo(SaveDevTypeInfoReqBD00 busData00, DevTypeInfo devTypeInfo) throws SystemException {
        SaveDevTypeInfoRes saveDevTypeInfoRes = new SaveDevTypeInfoRes();
        SaveDevTypeInfoResB body = new SaveDevTypeInfoResB();
        SaveDevTypeInfoResD data = new SaveDevTypeInfoResD();
/*        List<DevTypeInfo> objDevTypeInfo = devTypeDAO.SelectAllDevTypeInfo();
        for(DevTypeInfo obj:devTypeInfo){
            if(obj.getDevType().equals(busData00.getDevType())){
                throw new SystemException("","设备类型已存在");
            }
        }*/
        if("".equals(busData00.getDevType())||busData00.getDevType()==null){
            throw new SystemException("","设备类型不能为空");
        }

        DevTypeInfo devTypeInfo1=devTypeDAO.SelectAllDevTypeInfoByDevType(busData00.getDevType());
        if(devTypeInfo1!=null){
            throw new SystemException("","新增失败，设备类型已存在");
        }

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        devTypeInfo.setGuid(CommonUtils.getRandomNumber());
        devTypeInfo.setDevType(busData00.getDevType());
        devTypeInfo.setRemark(busData00.getRemark());
        devTypeInfo.setCreator(userName);
        devTypeInfo.setCreateTime(date);
        devTypeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        devTypeInfo.setLastModifyTime(new Date());
        devTypeDAO.InsertDevTypeInfo(devTypeInfo);
        body.setData(data);
        saveDevTypeInfoRes.setBody(body);
        return saveDevTypeInfoRes;
    }

    //更新设备类型信息

    @Override
    public SaveDevTypeInfoRes ModupdateDevTypeInfo(SaveDevTypeInfoReqBD02 busData02, DevTypeInfo devTypeInfo) throws SystemException {
        SaveDevTypeInfoRes saveDevTypeInfoRes = new SaveDevTypeInfoRes();
        SaveDevTypeInfoResB body = new SaveDevTypeInfoResB();
        SaveDevTypeInfoResD data = new SaveDevTypeInfoResD();

        if("".equals(busData02.getDevType())||busData02.getDevType()==null){
            throw new SystemException("","设备类型不能为空");
        }

        DevTypeInfo devTypeInfos = devTypeDAO.SelectByRuid(busData02.getDevTypeRd());
        if(devTypeInfos==null){
            throw new SystemException("","信息不存在");
        }

/*        DevTypeInfo DevTypeInfoss = devTypeDAO.SelectAllDevTypeInfoByDevType(busData02.getDevType());
        if(DevTypeInfoss!=null&&!DevTypeInfoss.getDevType().equals(devTypeInfo.getDevType()){
            throw new SystemException("","设备类型已存在");
        }*/
        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        devTypeInfo.setRuid(busData02.getDevTypeRd());
        devTypeInfo.setDevType(busData02.getDevType());
        devTypeInfo.setRemark(busData02.getRemark());
        devTypeInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        devTypeInfo.setLastModifyTime(new Date());
        devTypeInfo.setCreator(userName);
        devTypeInfo.setCreateTime(date);
        int count = devTypeDAO.UpdateDevTypeInfo(devTypeInfo);
        if(count<=0){
            throw new SystemException("","更新班别管理失败！");
        }
        body.setData(data);
        saveDevTypeInfoRes.setBody(body);
        return saveDevTypeInfoRes;
    }

    //删除
    @Override
    public SaveDevTypeInfoRes RmdeleteDevTypeInfo(int Ruid) throws SystemException {
        SaveDevTypeInfoRes saveDevTypeInfoRes = new SaveDevTypeInfoRes();
        SaveDevTypeInfoResB body = new SaveDevTypeInfoResB();
        SaveDevTypeInfoResD data = new SaveDevTypeInfoResD();
        int count = devTypeDAO.DeleteDevTypeInfo(Ruid);
        if(count <=0) throw new SystemException("MG_MES","删除客户信息失败！");
        body.setData(data);
        saveDevTypeInfoRes.setBody(body);
        return saveDevTypeInfoRes;
    }
}

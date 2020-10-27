package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.UrgencyDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllShiftsInfo.GetAllShiftsInfoResD;
import pnc.mesadmin.dto.GetAllUrcyInfo.GetAllUrcyInfoRes;
import pnc.mesadmin.dto.GetAllUrcyInfo.GetAllUrcyInfoResB;
import pnc.mesadmin.dto.GetAllUrcyInfo.GetAllUrcyInfoResD;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoRes;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoResB;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoResD;
import pnc.mesadmin.dto.GetUrcyInfo.GetUrcyInfoRes;
import pnc.mesadmin.dto.GetUrcyInfo.GetUrcyInfoResB;
import pnc.mesadmin.dto.GetUrcyInfo.GetUrcyInfoResD;
import pnc.mesadmin.dto.SaveShiftInfo.*;
import pnc.mesadmin.dto.SaveUrcyInfo.*;
import pnc.mesadmin.entity.ShiftInfo;
import pnc.mesadmin.entity.UrgencyInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.UrgencyIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：紧急度代码信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-8-17
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class UrgencyService implements UrgencyIService{

    @Resource
    private UrgencyDAO urgencyDAO;

    @Resource
    private BaseIService baseIService;

    //查询紧急代码列表信息
    public GetAllUrcyInfoRes QALLselectAllUrgencyInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllUrcyInfoRes objEGetAllUrcyInfoRes= new GetAllUrcyInfoRes();

        GetAllUrcyInfoResB body = new GetAllUrcyInfoResB();

        List<GetAllUrcyInfoResD> dataList =  new ArrayList<GetAllUrcyInfoResD>();

        // 获取紧急代码信息列表信息
        List<UrgencyInfo> urgencyInfoList = baseIService.QALLBaseInfo(UrgencyDAO.class, "SelectAllUrgencyInfo",
                argInitDataFields, argPageInfo);

        if(urgencyInfoList.size()>0 || urgencyInfoList!=null) {
            GetAllUrcyInfoResD data = null;

            // 循环赋值查询
            for (int i = 0; i < urgencyInfoList.size(); i++) {
                data = new GetAllUrcyInfoResD();
                data.setUrcyRd(urgencyInfoList.get(i).getRuid());
                data.setUrcyCode(urgencyInfoList.get(i).getUrcyCode());
                data.setUrcyDes(urgencyInfoList.get(i).getUrcyDes());
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllUrcyInfoRes.setBody(body);

        return objEGetAllUrcyInfoRes;
    }


    @Override
    public PageResult<GetAllUrcyInfoResD> QALLselectAllUrgencyInfoNew(BaseRequest req) throws SystemException {
        List<GetAllUrcyInfoResD> dataList = new ArrayList<>();
        GetAllUrcyInfoResD resD = null;

        if(req.getSize() <= 0){
            req.setSize(1000);
        }

        Page<UrgencyInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<UrgencyInfo> urgencyInfoIPage = urgencyDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        //获取紧急代码列表信息
        List<UrgencyInfo> urgencyInfos = urgencyInfoIPage.getRecords();
        for(UrgencyInfo obj : urgencyInfos){
            resD = new GetAllUrcyInfoResD();
            resD.setUrcyRd(obj.getRuid());
            resD.setUrcyCode(obj.getUrcyCode());
            resD.setUrcyDes(obj.getUrcyDes());
            dataList.add(resD);
        }

        return new PageResult(urgencyInfoIPage.getTotal(), urgencyInfoIPage.getPages(), urgencyInfoIPage.getCurrent(), urgencyInfoIPage.getSize(), dataList);
    }
    //获取紧急度代码信息
    @Override   
    public GetUrcyInfoRes GetselectByRuid(int UrcyRd) throws SystemException {
        GetUrcyInfoRes objGetUrcyInfoRes = new GetUrcyInfoRes();
        GetUrcyInfoResB body = new GetUrcyInfoResB();
        GetUrcyInfoResD date = new GetUrcyInfoResD();
        UrgencyInfo urgencyInfo = urgencyDAO.SelectByRuid(UrcyRd);
        if(urgencyInfo!=null){
            date.setUrcyRd(urgencyInfo.getRuid());
            date.setUrcyCode(urgencyInfo.getUrcyCode());
            date.setUrcyDes(urgencyInfo.getUrcyDes());
            date.setCreator(urgencyInfo.getCreator());
            date.setCreateTime(DateUtil.format(urgencyInfo.getCreateTime()));
            date.setLastModifyMan(urgencyInfo.getLastModifyMan());
            date.setLastModifyTime(DateUtil.format(urgencyInfo.getLastModifyTime()));
            date.setRemark(urgencyInfo.getRemark());
        }
        body.setData(date);
        objGetUrcyInfoRes.setBody(body);
        return objGetUrcyInfoRes;
    }

    //保存紧急度代码信息
    @Override
    public SaveUrcyInfoRes AddinsertUrgencyInfo(SaveUrcyInfoReqBD00 busData00, UrgencyInfo urgencyInfo) throws SystemException {
        SaveUrcyInfoRes saveUrcyInfoRes = new SaveUrcyInfoRes();
        SaveUrcyInfoResB body = new SaveUrcyInfoResB();
        SaveUrcyInfoResD data = new SaveUrcyInfoResD();
/*        List<UrgencyInfo> objUrgencyInfo = urgencyDAO.SelectAllUrgencyInfo();
        for(UrgencyInfo obj:objUrgencyInfo){
            if(obj.getUrcyCode().equals(busData00.getUrcyCode())){
                throw new SystemException("","紧急度代码已存在");
            }
        }*/
        if("".equals(busData00.getUrcyCode())||busData00.getUrcyCode()==null){
            throw new SystemException("","紧急度代码不能为空");
        }

        if("".equals(busData00.getUrcyDes())||busData00.getUrcyDes()==null){
            throw new SystemException("","紧急度名称不能为空");
        }
        UrgencyInfo urgencyInfo1=urgencyDAO.SelectAllUrgencyInfoByUrcyCode(busData00.getUrcyCode());
        if(urgencyInfo1!=null){
            throw new SystemException("","新增失败，紧急度代码已存在");
        }

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        urgencyInfo.setGuid(CommonUtils.getRandomNumber());
        urgencyInfo.setUrcyCode(busData00.getUrcyCode());
        urgencyInfo.setUrcyDes(busData00.getUrcyDes());
        urgencyInfo.setRemark(busData00.getRemark());
        urgencyInfo.setCreator(userName);
        urgencyInfo.setCreateTime(date);
        urgencyInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        urgencyInfo.setLastModifyTime(new Date());
        urgencyDAO.InsertUrgencyInfo(urgencyInfo);
        body.setData(data);
        saveUrcyInfoRes.setBody(body);
        return saveUrcyInfoRes;
    }

    //更新紧急度代码信息
    @Override
    public SaveUrcyInfoRes ModupdateUrgencyInfo(SaveUrcyInfoReqBD02 busData02, UrgencyInfo urgencyInfo) throws SystemException {
        SaveUrcyInfoRes saveUrcyInfoRes = new SaveUrcyInfoRes();
        SaveUrcyInfoResB body = new SaveUrcyInfoResB();
        SaveUrcyInfoResD data = new SaveUrcyInfoResD();

        if("".equals(busData02.getUrcyCode())||busData02.getUrcyCode()==null){
            throw new SystemException("","紧急度代码不能为空");
        }

        if("".equals(busData02.getUrcyDes())||busData02.getUrcyDes()==null){
            throw new SystemException("","紧急度名称不能为空");
        }

        UrgencyInfo urgencyInfos = urgencyDAO.SelectByRuid(busData02.getUrcyRd());
        if(urgencyInfos==null){
            throw new SystemException("","信息不存在");
        }

/*        UrgencyInfo UrgencyInfoss = urgencyDAO.SelectAllUrgencyInfoByUrcyCode(busData02.getUrcyCode());
        if(UrgencyInfoss!=null&&!UrgencyInfoss.getUrcyCode().equals(urgencyInfo.getUrcyCode())){
            throw new SystemException("","紧急度代码已存在");
        }*/
        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        urgencyInfo.setRuid(busData02.getUrcyRd());
        urgencyInfo.setUrcyCode(busData02.getUrcyCode());
        urgencyInfo.setUrcyDes(busData02.getUrcyDes());
        urgencyInfo.setRemark(busData02.getRemark());
        urgencyInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        urgencyInfo.setLastModifyTime(new Date());
        urgencyInfo.setCreator(userName);
        urgencyInfo.setCreateTime(date);
        int count = urgencyDAO.UpdateUrgencyInfo(urgencyInfo);
        if(count<=0){
            throw new SystemException("","更新紧急度代码失败！");
        }
        body.setData(data);
        saveUrcyInfoRes.setBody(body);
        return saveUrcyInfoRes;
    }

    //删除
    @Override
    public SaveUrcyInfoRes RmdeleteUrgencyInfo(int Ruid) throws SystemException {
        SaveUrcyInfoRes saveUrcyInfoRes = new SaveUrcyInfoRes();
        SaveUrcyInfoResB body = new SaveUrcyInfoResB();
        SaveUrcyInfoResD data = new SaveUrcyInfoResD();
        int count = urgencyDAO.DeleteUrgencyInfo(Ruid);
        if(count <=0) throw new SystemException("MG_MES","删除客户信息失败！");
        body.setData(data);
        saveUrcyInfoRes.setBody(body);
        return saveUrcyInfoRes;
    }

/*    //复制
    @Override
    public SaveShiftInfoRes copyShiftInfo(SaveShiftInfoReqBD03 busData03, ShiftInfo shiftInfo) throws SystemException {
        SaveShiftInfoRes saveShiftInfoRes = new SaveShiftInfoRes();
        SaveShiftInfoResB body = new SaveShiftInfoResB();
        SaveShiftInfoResD data = new SaveShiftInfoResD();
        ShiftInfo objshiftInfo = shiftDAO.SelectShiftByID(busData03.getShiftRd());
        if(objshiftInfo==null){
            throw new SystemException("","查询班别信息为空");
        }
        //复制一条班别信息
        shiftInfo.setGuid(CommonUtils.getRandomNumber());
        shiftInfo.setShiftName(objshiftInfo.getShiftName());
        shiftInfo.setDescription(objshiftInfo.getDescription());
        shiftInfo.setStartTime(objshiftInfo.getStartTime());
        shiftInfo.setEndTime(objshiftInfo.getEndTime());
        shiftInfo.setCreator(objshiftInfo.getCreator());
        shiftInfo.setCreateTime(new Date());
        shiftInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        shiftInfo.setLastModifyTime(new Date());
        shiftInfo.setRemark(objshiftInfo.getRemark());
        // 调用班别信息sql复制一条数据
        int count = shiftDAO.InsertShiftInfo(shiftInfo);
        if(count <=0) throw new SystemException("","复制客户信息失败！");
        //查询班别信息
        ShiftInfo objShiftInfo  = shiftDAO.SelectByGuid(shiftInfo.getGuid());
        objShiftInfo.setShiftName(objShiftInfo.getShiftName()+objShiftInfo.getGuid());
        if(shiftDAO.UpdateShiftInfo(objShiftInfo)<=0){
            throw new SystemException("","复制客户信息失败！");
        }
        body.setData(data);
        saveShiftInfoRes.setBody(body);
        return saveShiftInfoRes;
    }

    //获取当前班别信息
    @Override
    public ShiftInfo GetShiftInfo() throws SystemException {
        //当前时分
        Calendar now = Calendar.getInstance();
        Time time = new Time(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));

        //查询班别信息
        ShiftInfo objEShiftInfo = null;
        List<ShiftInfo> shiftInfos = shiftDAO.SelectAllShiftInfo();
        for(ShiftInfo shiftInfo : shiftInfos){
            if(shiftInfo.getStartTime().before(shiftInfo.getEndTime())){
                if(shiftInfo.getStartTime().before(time) && time.before(shiftInfo.getEndTime())){
                    objEShiftInfo = shiftInfo;
                    break;
                }
            }else {
                if(shiftInfo.getStartTime().before(time) && shiftInfo.getEndTime().before(time)){
                    objEShiftInfo = shiftInfo;
                    break;
                }

                if(shiftInfo.getStartTime().after(time) && shiftInfo.getEndTime().after(time)){
                    objEShiftInfo = shiftInfo;
                    break;
                }
            }
        }

        return objEShiftInfo;
    }*/
}

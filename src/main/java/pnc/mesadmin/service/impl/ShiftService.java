package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import pnc.mesadmin.dao.ShiftDAO;
import pnc.mesadmin.dto.BaseRequest;
import pnc.mesadmin.dto.GetAllShiftsInfo.GetAllShiftsInfoResD;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoRes;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoResB;
import pnc.mesadmin.dto.GetShiftInfo.GetShiftInfoResD;
import pnc.mesadmin.dto.PageResult;
import pnc.mesadmin.dto.SaveShiftInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.ShiftInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.ShiftIService;
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
 * 子系统名称：班别管理Service层
 * 创建人：乔帅阳
 * 创建时间：2018-6-19
 * 修改人：
 * 修改时间：
 */
@Service
public class ShiftService implements ShiftIService {
    @Resource
    private BaseIService baseIService;

    @Resource
    private  ShiftDAO shiftDAO;

    @Override
    public PageResult<GetAllShiftsInfoResD> QALLselectAllShiftsInfoInfo(BaseRequest req) throws SystemException {
        List<GetAllShiftsInfoResD> dataList = new ArrayList<>();
        GetAllShiftsInfoResD resD = null;

        if(req.getSize() <= 0){
            req.setSize(1000);
        }

        Page<ShiftInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<ShiftInfo> shiftInfoIPage = shiftDAO.selectPage(page, CommonUtils.getQueryWrapper(req.getFields()));

        //获取班别列表信息
        List<ShiftInfo> shiftInfos = shiftInfoIPage.getRecords();
        for(ShiftInfo obj : shiftInfos){
            resD = new GetAllShiftsInfoResD();
            resD.setShiftRd(obj.getRuid());
            resD.setShiftName(obj.getShiftName());
            dataList.add(resD);
        }

        return new PageResult(shiftInfoIPage.getTotal(), shiftInfoIPage.getPages(), shiftInfoIPage.getCurrent(), shiftInfoIPage.getSize(), dataList);
    }
  //获取班别信息
    @Override
    public GetShiftInfoRes GetselectByShiftsId(int ShiftRd) throws SystemException {
        GetShiftInfoRes objGetShiftInfoRes = new GetShiftInfoRes();
        GetShiftInfoResB body = new GetShiftInfoResB();
        GetShiftInfoResD date = new GetShiftInfoResD();
        ShiftInfo shiftInfo = shiftDAO.SelectShiftByID(ShiftRd);
        if(shiftInfo!=null){
            date.setShiftRd(shiftInfo.getRuid());
            date.setShiftName(shiftInfo.getShiftName());
            date.setShiftDes(shiftInfo.getDescription());
            date.setStartTime(shiftInfo.getStartTime().toString().substring(0,5));
            date.setEndTime(shiftInfo.getEndTime().toString().substring(0,5));
          /*  date.setStartTime(DateUtil.format(shiftInfo.getStartTime()));
            date.setEndTime(DateUtil.format(shiftInfo.getEndTime()));*/
            date.setCreator(shiftInfo.getCreator());
            date.setCreateTime(DateUtil.format(shiftInfo.getCreateTime()));
            date.setLastModifyMan(shiftInfo.getLastModifyMan());
            date.setLastModifyTime(DateUtil.format(shiftInfo.getLastModifyTime()));
            date.setRemark(shiftInfo.getRemark());
        }
        body.setData(date);
        objGetShiftInfoRes.setBody(body);
        return objGetShiftInfoRes;
    }
  //保存班别信息
    @Override
    public SaveShiftInfoRes AddinsertShiftInfo(SaveShiftInfoReqBD00 busData00) throws SystemException {
        SaveShiftInfoRes saveShiftInfoRes = new SaveShiftInfoRes();
        SaveShiftInfoResB body = new SaveShiftInfoResB();
        SaveShiftInfoResD data = new SaveShiftInfoResD();
        ShiftInfo shiftInfo = new ShiftInfo();
  /*      List<ShiftInfo> objShiftInfo = shiftDAO.SelectAllShiftInfo();
        for(ShiftInfo obj:objShiftInfo){
            if(obj.getShiftName().equals(busData00.getShiftName())){
                throw new SystemException("","班别名称已存在");
            }
        }*/
        if("".equals(busData00.getShiftName())||busData00.getShiftName()==null){
              throw new SystemException("","班别名称不能为空");
        }

        if("".equals(busData00.getStartTime())||busData00.getStartTime()==null){
            throw new SystemException("","开始时间不能为空");
        }
        if("".equals(busData00.getEndTime())||busData00.getEndTime()==null){
            throw new SystemException("","结束时间不能为空");
        }
        ShiftInfo shiftInfo1=shiftDAO.SelectAllShiftInfoByShiftName(busData00.getShiftName());
        if(shiftInfo1!=null){
            throw new SystemException("","新增失败，班别名称已存在");
        }

        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        shiftInfo.setGuid(CommonUtils.getRandomNumber());
        shiftInfo.setShiftName(busData00.getShiftName());
        shiftInfo.setDescription(busData00.getShiftDes());
        shiftInfo.setStartTime(DateUtil.getTime(busData00.getStartTime()));
        shiftInfo.setEndTime(DateUtil.getTime(busData00.getEndTime()));
        shiftInfo.setRemark(busData00.getRemark());
        shiftInfo.setCreator(userName);
        shiftInfo.setCreateTime(date);
        shiftInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        shiftInfo.setLastModifyTime(new Date());
        shiftDAO.InsertShiftInfo(shiftInfo);
        body.setData(data);
        saveShiftInfoRes.setBody(body);
        return saveShiftInfoRes;
    }
    //更新班别信息
    @Override
    public SaveShiftInfoRes ModupdateCustomerInfo(SaveShiftInfoReqBD02 busData02) throws SystemException {
        SaveShiftInfoRes saveShiftInfoRes = new SaveShiftInfoRes();
       SaveShiftInfoResB body = new SaveShiftInfoResB();
        SaveShiftInfoResD data = new SaveShiftInfoResD();

        if("".equals(busData02.getShiftName())||busData02.getShiftName()==null){
            throw new SystemException("","班别名称不能为空");
        }


        if("".equals(busData02.getStartTime())||busData02.getStartTime()==null){
            throw new SystemException("","更新班别管理失败，开始时间不能为空！");
        }
        if("".equals(busData02.getEndTime())||busData02.getEndTime()==null){
            throw new SystemException("","更新班别管理失败，结束时间不能为空！");
        }
        ShiftInfo shiftInfo = shiftDAO.SelectShiftByID(busData02.getShiftRd());
        if(shiftInfo==null){
            throw new SystemException("","信息不存在");
        }
        ShiftInfo ShiftInfoss = shiftDAO.SelectAllShiftInfoByShiftName(busData02.getShiftName());
        if(ShiftInfoss!=null&&!ShiftInfoss.getShiftName().equals(shiftInfo.getShiftName())){
            throw new SystemException("","名称已存在");
        }
        //当前用户
        String userName = CommonUtils.readUser().getRealName();
        //当前时间
        Date date = new Date();
        shiftInfo.setRuid(busData02.getShiftRd());
        shiftInfo.setShiftName(busData02.getShiftName());
        shiftInfo.setDescription(busData02.getShiftDes());
        Time t= DateUtil.getTime(busData02.getStartTime());
        shiftInfo.setStartTime(DateUtil.getTime(busData02.getStartTime()));
        shiftInfo.setEndTime(DateUtil.getTime(busData02.getEndTime()));
        shiftInfo.setRemark(busData02.getRemark());
        shiftInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        shiftInfo.setLastModifyTime(new Date());
        shiftInfo.setCreator(userName);
        shiftInfo.setCreateTime(date);
        int count = shiftDAO.UpdateShiftInfo(shiftInfo);
        if(count<=0){
            throw new SystemException("","更新班别管理失败！");
        }
        body.setData(data);
        saveShiftInfoRes.setBody(body);
        return saveShiftInfoRes;
    }
  //删除
    @Override
    public SaveShiftInfoRes RmdeleteShiftInfo(int Ruid) throws SystemException {
        SaveShiftInfoRes saveShiftInfoRes = new SaveShiftInfoRes();
        SaveShiftInfoResB body = new SaveShiftInfoResB();
        SaveShiftInfoResD data = new SaveShiftInfoResD();
        int count = shiftDAO.DeleteShiftInfo(Ruid);
        if(count <=0) throw new SystemException("MG_MES","删除客户信息失败！");
        body.setData(data);
        saveShiftInfoRes.setBody(body);
        return saveShiftInfoRes;
    }
    //复制
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
    }
}

package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.MaVerDAO;
import pnc.mesadmin.dao.UnitDAO;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoRes;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoResB;
import pnc.mesadmin.dto.GetAllUnitInfo.GetAllUnitInfoResD;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoReqBD00;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoRes;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoResB;
import pnc.mesadmin.dto.GetUnitInfo.GetUnitInfoResD;
import pnc.mesadmin.dto.SaveUnitInfo.*;
import pnc.mesadmin.entity.MaVerInfo;
import pnc.mesadmin.entity.UnitInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.UnitIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：计量单位实现层
 * 创建人：张亮亮
 * 创建时间：2017-05-31
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class UnitService implements UnitIService {
   @Resource
    private UnitDAO unitDAO;

    @Resource
    private BaseIService baseIService;

    @Resource
    private MaVerDAO maVerDAO;

    //dto查询计量单位列表
    public GetAllUnitInfoRes QALLGetAllUnitInfoRes(List<InitDataField> argInitDataFields, PageInfo argPageInfo) {
        GetAllUnitInfoRes objEGetAllUnitInfoRes=new GetAllUnitInfoRes();
        GetAllUnitInfoResB objEGetAllUnitInfoResB=new GetAllUnitInfoResB();
        List<GetAllUnitInfoResD> objEGetAllUnitInfoResDs=new ArrayList<GetAllUnitInfoResD>();
        //查询所有的计量单位
        List<UnitInfo> objEUnitInfo=  baseIService.QALLBaseInfo(UnitDAO.class, "SelectUnitInfo",
                argInitDataFields, argPageInfo);
        if(objEUnitInfo!=null||objEUnitInfo.size()>0) {

            //赋值dto并返回
            for (UnitInfo obj : objEUnitInfo) {
                GetAllUnitInfoResD objEGetAllUnitInfoResD = new GetAllUnitInfoResD();
                objEGetAllUnitInfoResD.setUnitRd(obj.getRuid());
                objEGetAllUnitInfoResD.setUnitName(obj.getUnitName());
                objEGetAllUnitInfoResDs.add(objEGetAllUnitInfoResD);
            }
        }
        objEGetAllUnitInfoResB.setData(objEGetAllUnitInfoResDs);
        objEGetAllUnitInfoRes.setBody(objEGetAllUnitInfoResB);
        return objEGetAllUnitInfoRes;
    }
    @Override
    public PageResult<GetAllUnitInfoResD> QALLGetAllUnitNewRes(BaseRequest req) {
        List<GetAllUnitInfoResD> objEGetAllUnitInfoResDs=new ArrayList<GetAllUnitInfoResD>();
        GetAllUnitInfoResD resD = null;

        Page<UnitInfo> page = new Page<>(req.getCurrent(), req.getSize());

        IPage<UnitInfo> iPage = unitDAO.selectPage(page, CommonUtils.getQueryWrapper(req.getFields()));

        //赋值dto并返回
        for (UnitInfo obj : iPage.getRecords()) {
            resD = new GetAllUnitInfoResD();
            resD.setUnitRd(obj.getRuid());
            resD.setUnitName(obj.getUnitName());
            objEGetAllUnitInfoResDs.add(resD);
        }

        return new PageResult(iPage.getTotal(), iPage.getPages(), iPage.getCurrent(), iPage.getSize(), objEGetAllUnitInfoResDs);
    }
    /**
     * 查询计量单位列表(新)
     * @param req
     * @return
     */
    @Override
    public PageResult<GetAllUnitInfoResD> QALLUnitNew(BaseRequest req) {
        List<GetAllUnitInfoResD> resDList = new ArrayList<GetAllUnitInfoResD>();
        GetAllUnitInfoResD resD = null;

        IPage<UnitInfo> iPage = unitDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));

        for (UnitInfo obj : iPage.getRecords()) {
            resD = new GetAllUnitInfoResD();
            resD.setUnitRd(obj.getRuid());
            resD.setUnitName(obj.getUnitName());
            resDList.add(resD);
        }

        return new PageResult<>(iPage, resDList);
    }
    //dto查询计量单位
    public GetUnitInfoRes GetGetUnitInfoRes(GetUnitInfoReqBD00 argGetUnitInfoReqBD00) {

        GetUnitInfoRes objEGetUnitInfoRes=new GetUnitInfoRes();
        GetUnitInfoResB objEGetUnitInfoResB=new GetUnitInfoResB();
        UnitInfo objEUnitInfo= unitDAO.SelectUnitInfoByruid(argGetUnitInfoReqBD00.getUnitRd());
        if(objEUnitInfo!=null) {

            GetUnitInfoResD objEGetUnitInfoResD = new GetUnitInfoResD();

            objEGetUnitInfoResD.setUnitRd(objEUnitInfo.getRuid());
            objEGetUnitInfoResD.setUnitName(objEUnitInfo.getUnitName());
            objEGetUnitInfoResD.setDSource(objEUnitInfo.getdSource());
            objEGetUnitInfoResD.setCreator(objEUnitInfo.getCreator());
            objEGetUnitInfoResD.setCreateTime(DateUtil.format(objEUnitInfo.getCreateTime()));
            objEGetUnitInfoResD.setLastModifyMan(objEUnitInfo.getLastModifyMan());
            objEGetUnitInfoResD.setLastModifyTime(DateUtil.format(objEUnitInfo.getLastModifyTime()));
            objEGetUnitInfoResD.setRemark(objEUnitInfo.getRemark());

            objEGetUnitInfoResB.setData(objEGetUnitInfoResD);
        }
        objEGetUnitInfoRes.setBody(objEGetUnitInfoResB);

        return objEGetUnitInfoRes;
    }
    //dto新增计量单位
    public SaveUnitInfoRes AddSaveUnitInfoRes(SaveUnitInfoReqBD00 argSaveUnitInfoReqBD00) {
        SaveUnitInfoRes objESaveUserInfoRes=new SaveUnitInfoRes();
        SaveUnitInfoResB objESaveUserInfoResB=new SaveUnitInfoResB();

        UnitInfo objEUnitInfo=new UnitInfo();
        objEUnitInfo.setGuid(CommonUtils.getRandomNumber());
        //不允许计量单位重复
        List<UnitInfo> objEUnitInfos=unitDAO.SelectUnitInfo();
        for(UnitInfo obj:objEUnitInfos){
            if(obj.getUnitName().equals(argSaveUnitInfoReqBD00.getUnitName())){
                throw new SystemException("MG_MES3001612010001F","新增失败，计量单位名称已存在");
            }
        }
        objEUnitInfo.setUnitName(argSaveUnitInfoReqBD00.getUnitName());
        objEUnitInfo.setdSource("01");
        objEUnitInfo.setRemark(argSaveUnitInfoReqBD00.getRemark());
        objEUnitInfo.setCreateTime(new Date());
        objEUnitInfo.setCreator(CommonUtils.readUser().getRealName());
        //新增计量单位
        unitDAO.InsertUnitInfo(objEUnitInfo);
        objESaveUserInfoRes.setBody(objESaveUserInfoResB);
        return objESaveUserInfoRes;
    }

    //删除计量单位
    public SaveUnitInfoRes RmSaveUnitInfoRes(SaveUnitInfoReqBD01 argSaveUnitInfoReqBD01) {
        SaveUnitInfoRes objESaveUserInfoRes=new SaveUnitInfoRes();
        SaveUnitInfoResB objESaveUserInfoResB=new SaveUnitInfoResB();


        UnitInfo objEUnitInfo= unitDAO.SelectUnitInfoByruid(argSaveUnitInfoReqBD01.getUnitRd());

        if("00".equals(objEUnitInfo.getdSource())){
            throw new SystemException("","为外部数据不能删除");
        }
        //查询是否被物料引用
        //select count(1) from tpm_maverinfo where maTypeGd = '23233'
        if(maVerDAO.selectCount(new LambdaQueryWrapper<MaVerInfo>().eq(MaVerInfo::getUnitGd,  objEUnitInfo.getGuid())) > 0){
            throw new SystemException("", "该类型已被使用,不允许删除");
        }

        if(unitDAO.DeleteUnitInfo(argSaveUnitInfoReqBD01.getUnitRd())<=0){
            throw  new SystemException("MG_MES3001612020001F","删除计量单位失败");
        }
        objESaveUserInfoRes.setBody(objESaveUserInfoResB);

        return objESaveUserInfoRes;
    }

    //dto更新计量单位
    public SaveUnitInfoRes ModSaveUnitInfoRes(SaveUnitInfoReqBD02 argSaveUnitInfoReqBD02) {
        SaveUnitInfoRes objESaveUserInfoRes=new SaveUnitInfoRes();
        SaveUnitInfoResB objESaveUserInfoResB=new SaveUnitInfoResB();
        //查询计量单位
        UnitInfo objEUnitInfo= unitDAO.SelectUnitInfoByruid(argSaveUnitInfoReqBD02.getUnitRd());
        if(objEUnitInfo==null){
            throw  new SystemException("MG_MES3001612030001F","查询计量单位失败");
        }
        objEUnitInfo.setRuid(argSaveUnitInfoReqBD02.getUnitRd());
        //不允许计量单位重复

        UnitInfo unitInfoname=unitDAO.SelectByUnitName(argSaveUnitInfoReqBD02.getUnitName());
        if(unitInfoname!=null&&!unitInfoname.getUnitName().equals(objEUnitInfo.getUnitName())){
            throw new SystemException("MG_MES3001612030002F","更新失败，计量单位名称已存在");
        }

        if("00".equals(objEUnitInfo.getdSource())){
            throw new SystemException("","为外部数据不能更新");
        }

        objEUnitInfo.setUnitName(argSaveUnitInfoReqBD02.getUnitName());
        objEUnitInfo.setRemark(argSaveUnitInfoReqBD02.getRemark());
        objEUnitInfo.setLastModifyTime(new Date());
        objEUnitInfo.setLastModifyMan(CommonUtils.readUser().getRealName());

        if(unitDAO.UpdateUnitInfo(objEUnitInfo)<=0){
            throw  new SystemException("MG_MES3001612030003F","更新计量单位失败");
        }
        objESaveUserInfoRes.setBody(objESaveUserInfoResB);

        return objESaveUserInfoRes;
    }

    //dto复制计量单位
    public SaveUnitInfoRes AddSaveUnitInfoRes(SaveUnitInfoReqBD03 argSaveUnitInfoReqBD03) {
        SaveUnitInfoRes objESaveUserInfoRes=new SaveUnitInfoRes();
        SaveUnitInfoResB objESaveUserInfoResB=new SaveUnitInfoResB();
        UnitInfo objEUnitInfo= unitDAO.SelectUnitInfoByruid(argSaveUnitInfoReqBD03.getUnitRd());
        if (objEUnitInfo==null){
            throw  new SystemException("MG_MES3001612040001F","查询计量单位失败");
        }
        objEUnitInfo.setGuid(CommonUtils.getRandomNumber());
        objEUnitInfo.setLastModifyTime(new Date());
        objEUnitInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        objEUnitInfo.setCreateTime(new Date());
        objEUnitInfo.setCreator(CommonUtils.readUser().getRealName());
        unitDAO.InsertUnitInfo(objEUnitInfo);
        objEUnitInfo.setUnitName(objEUnitInfo.getUnitName()+objEUnitInfo.getRuid());
        if(unitDAO.UpdateUnitInfo(objEUnitInfo)<=0){
            throw  new SystemException("MG_MES3001612040002F","复制计量单位失败");
        }
        objESaveUserInfoRes.setBody(objESaveUserInfoResB);
        return objESaveUserInfoRes;
    }
}

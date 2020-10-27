package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.BLevelDAO;
import pnc.mesadmin.dto.GetAllBLInfo.GetAllBLInfoRes;
import pnc.mesadmin.dto.GetAllBLInfo.GetAllBLInfoResB;
import pnc.mesadmin.dto.GetAllBLInfo.GetAllBLInfoResD;
import pnc.mesadmin.dto.GetBLInfo.GetBLInfoRes;
import pnc.mesadmin.dto.GetBLInfo.GetBLInfoResB;
import pnc.mesadmin.dto.GetBLInfo.GetBLInfoResD;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SaveBLInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.BLevelInfo;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.BatchLIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次等级信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class BatchLService implements BatchLIService {

    @Resource
    private BLevelDAO bLevelInfoMapper;

    @Resource
    private BaseIService baseIService;

    //查询批次等级列表信息
    public GetAllBLInfoRes QALLselectAllBLevelInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllBLInfoRes objEGetAllBLInfoRes = new GetAllBLInfoRes();

        GetAllBLInfoResB body = new GetAllBLInfoResB();

        // 获取批次等级信息列表
        List<GetAllBLInfoResD> dataList =  new ArrayList<GetAllBLInfoResD>();

        //查询批次等级所有信息
        List<BLevelInfo> bLevelInfoList = baseIService.QALLBaseInfo(BLevelDAO.class, "SelectAllBLevelInfo",
                argInitDataFields, argPageInfo);

        if(bLevelInfoList!=null || bLevelInfoList.size()>0) {

            GetAllBLInfoResD data = null;

            // 循环赋值
            for (int i = 0; i < bLevelInfoList.size(); i++) {
                data = new GetAllBLInfoResD();
                data.setBLRd(bLevelInfoList.get(i).getRuid());
                data.setBLName(bLevelInfoList.get(i).getbLName());
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllBLInfoRes.setBody(body);

        return objEGetAllBLInfoRes;
    }

    //查询批次等级信息
    public GetBLInfoRes GetselectBybLRd(int bLRd) throws SystemException {
        GetBLInfoRes objEGetBLInfoRes = new  GetBLInfoRes();

        GetBLInfoResB body = new GetBLInfoResB();

        GetBLInfoResD data = new GetBLInfoResD();

        // 获取批次等级信息
        BLevelInfo bLevelInfo =bLevelInfoMapper.SelectBybLRd(bLRd);

        if(bLevelInfo!=null) {
            // 赋值查询批次等级信息
            data.setBLRd(bLevelInfo.getRuid());
            data.setBLName(bLevelInfo.getbLName());
            data.setCreator(bLevelInfo.getCreator());
            data.setCreateTime(DateUtil.format(bLevelInfo.getCreateTime()));
            data.setLastModifyMan(bLevelInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(bLevelInfo.getLastModifyTime()));
            data.setRemark(bLevelInfo.getRemark());
        }

        body.setData(data);
        objEGetBLInfoRes.setBody(body);

        return objEGetBLInfoRes;
    }

    //保存批次等级信息
    public SaveBLInfoRes AddinsertBLevelInfo(SaveBLInfoReqBD00 busData00, BLevelInfo bLevelInfo) throws SystemException {
        SaveBLInfoRes saveBLInfoRes = new SaveBLInfoRes();

        SaveBLInfoResB body = new SaveBLInfoResB();

        SaveBLInfoResD data = new SaveBLInfoResD();

        //查询批次等级的名称信息
        List<BLevelInfo> objEBLevelInfos=bLevelInfoMapper.SelectAllBLevelInfo();

        // 赋值新增批次等级信息
        bLevelInfo.setGuid(CommonUtils.getRandomNumber());

        //逻辑校验保存的批次等级名称不能相同
        for (BLevelInfo obj:objEBLevelInfos){
            if (obj.getbLName().equals(busData00.getBLName())){
                throw new SystemException("MG0006F","批次等级名称已存在");
            }
        }

        //批次等级的名称校验
        if ((busData00.getBLName()).equals("")){
            throw new SystemException("MG0003F","批次等级名称不能为空");
        }

        bLevelInfo.setbLName(busData00.getBLName());
        bLevelInfo.setCreator(CommonUtils.readUser().getRealName());
        bLevelInfo.setCreateTime(new Date());
        bLevelInfo.setRemark(busData00.getRemark());

        // 调用批次等级sql保存
        bLevelInfoMapper.InsertBLevelInfo(bLevelInfo);

        body.setData(data);
        saveBLInfoRes.setBody(body);

        return saveBLInfoRes;
    }

    //更新批次等级信息
    public SaveBLInfoRes ModupdateBLevelInfo(SaveBLInfoReqBD02 busData02, BLevelInfo bLevelInfo) throws SystemException {
        SaveBLInfoRes saveBLInfoRes = new SaveBLInfoRes();

        SaveBLInfoResB body = new SaveBLInfoResB();

        SaveBLInfoResD data = new SaveBLInfoResD();

        // 获取批次等级信息
        BLevelInfo objebLevelInfo =bLevelInfoMapper.SelectBybLRd(busData02.getBLRd());

        // 判断返回批次等级是否为空
        if (objebLevelInfo == null){
            throw new SystemException("MG_MES3001711010001F", "查询批次等级信息为空！");
        }

        //查询批次等级信息
        BLevelInfo objEBLevelInfos=bLevelInfoMapper.SelectBybLName(busData02.getBLName());

        if(objEBLevelInfos!=null && !objEBLevelInfos.getbLName().equals(objebLevelInfo.getbLName())){
            throw new SystemException("MG0006F","更新失败，批次等级名称已存在");
        }

        // 赋值更新批次等级信息
        bLevelInfo.setRuid(busData02.getBLRd());

        //批次等级的名称校验
        if ((busData02.getBLName()).equals("")){
            throw new SystemException("MG0003F","批次等级名称不能为空");
        }

        bLevelInfo.setbLName(busData02.getBLName());
        bLevelInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        bLevelInfo.setLastModifyTime(new Date());
        bLevelInfo.setRemark(busData02.getRemark());

        // 调用批次等级sql更新
        int count = bLevelInfoMapper.UpdateBLevelInfo(bLevelInfo);

        if(count <=0) throw new SystemException("MG_MES3001712030002F","更新批次等级信息失败！");

        body.setData(data);
        saveBLInfoRes.setBody(body);

        return saveBLInfoRes;
    }

    //复制批次等级信息
    public SaveBLInfoRes copyBLevelInfo(SaveBLInfoReqBD03 busData03, BLevelInfo bLevelInfo) throws SystemException {
        SaveBLInfoRes saveBLInfoRes = new SaveBLInfoRes();

        SaveBLInfoResB body = new SaveBLInfoResB();

        SaveBLInfoResD data = new SaveBLInfoResD();

        //查询批次等级信息
        bLevelInfo =bLevelInfoMapper.SelectBybLRd(busData03.getBLRd());

        if (bLevelInfo==null){
            throw new SystemException("MG_MES3001711010001F","查询批次等级信息为空");
        }

        // 赋值复制一条批次等级数据
        bLevelInfo.setGuid(CommonUtils.getRandomNumber());
        bLevelInfo.setbLName(bLevelInfo.getbLName());
        bLevelInfo.setCreator(CommonUtils.readUser().getRealName());
        bLevelInfo.setCreateTime(new Date());
        bLevelInfo.setRemark(bLevelInfo.getRemark());

        // 复制新增一条批次等级数据
        int count = bLevelInfoMapper.InsertBLevelInfo(bLevelInfo);

        if(count <=0) throw new SystemException("MG_MES3001712040003F","复制批次等级信息失败！");

        //查询批次等级信息
        BLevelInfo objEbLevelInfo =bLevelInfoMapper.SelectByGuid(bLevelInfo.getGuid());

        objEbLevelInfo.setbLName(objEbLevelInfo.getbLName()+objEbLevelInfo.getRuid());

        if (bLevelInfoMapper.UpdateBLevelInfo(objEbLevelInfo)<=0){
            throw new SystemException("MG_MES3001712030002F","复制批次等级信息失败！");
        }

        body.setData(data);
        saveBLInfoRes.setBody(body);

        return saveBLInfoRes;
    }

    //删除批次等级信息
    public SaveBLInfoRes RmdeleteBLevelInfo(int ruid) throws SystemException {
        SaveBLInfoRes saveBLInfoRes = new SaveBLInfoRes();

        SaveBLInfoResB body = new SaveBLInfoResB();

        SaveBLInfoResD data = new SaveBLInfoResD();

        //调用批次等级sql删除信息
        int count = bLevelInfoMapper.DeleteBLevelInfo(ruid);

        if(count <=0) throw new SystemException("MG_MES3001712020001F","删除批次等级信息失败！");

        body.setData(data);
        saveBLInfoRes.setBody(body);

        return saveBLInfoRes;
    }

}

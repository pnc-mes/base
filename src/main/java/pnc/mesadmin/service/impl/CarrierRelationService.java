package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.BDAO;
import pnc.mesadmin.dao.CarrierDao;
import pnc.mesadmin.dao.CarrierreLationDAO;
import pnc.mesadmin.dao.MaVerDAO;
import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoReq00;
import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoRes;
import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoResB;
import pnc.mesadmin.dto.GetCarrierRelationInfo.GetCarrierRelationInfoResD;
import pnc.mesadmin.dto.SaveCarrierRelationInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.BInfo;
import pnc.mesadmin.entity.CarrierInfo;
import pnc.mesadmin.entity.CarrierreLationInfo;
import pnc.mesadmin.entity.MaVerInfo;
import pnc.mesadmin.service.CarrierRelationIService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 19:53
 * @Description:
 */
@Service
@Transactional
public class CarrierRelationService implements CarrierRelationIService {
    @Resource
    private CarrierreLationDAO carrierreLationDAO;

    @Resource
    private BDAO bdao;

    @Resource
    private CarrierDao carrierDao;

    @Resource
    private MaVerDAO maVerDAO;

    //新增
    @Override
    public SaveCarrierRelationInfoRes AddCarrierRelationInfoRes(SaveCarrierRelationInfoReq00 saveCarrierRelationInfoReq00) {
        SaveCarrierRelationInfoRes saveCarrierRelationInfoRes=new SaveCarrierRelationInfoRes();
        SaveCarrierRelationInfoResB saveCarrierRelationInfoResB=new  SaveCarrierRelationInfoResB();

        if(saveCarrierRelationInfoReq00.getVenderSN()==null||saveCarrierRelationInfoReq00.getVenderSN().equals("")){
            throw new SystemException("xxx","新增失败，载具序列号不能为空");
        }

        if(saveCarrierRelationInfoReq00.getBatch()==null||saveCarrierRelationInfoReq00.getBatch().equals("")){
            throw new SystemException("xxx","新增失败，批次不能为空");
        }

        //多个载具对应一个批次
        BInfo bInfo=bdao.selectBatchInfoByBatch(saveCarrierRelationInfoReq00.getBatch());
        if(bInfo==null){
                throw new SystemException("xxx","新增失败批次不存在");
        }


        CarrierInfo carrierInfo=carrierDao.SelectCarrierSN(saveCarrierRelationInfoReq00.getVenderSN());
        if(carrierInfo==null){
            throw new SystemException("xxx","新增失败，载具不存在");
        }
        CarrierreLationInfo carrierreLationInfos= carrierreLationDAO.SelectBatchSN(saveCarrierRelationInfoReq00.getBatch(),saveCarrierRelationInfoReq00.getVenderSN());

        if(carrierreLationInfos!=null){
            throw new SystemException("xxx","新增失败，该批次已在此载具上!");
        }

        CarrierreLationInfo carrierreLationInfo=new CarrierreLationInfo();
        carrierreLationInfo.setBatch(bInfo.getBatch());
        carrierreLationInfo.setCarrierGd(carrierInfo.getGuid());
        carrierreLationDAO.inserteCarrierreLationInfo(carrierreLationInfo);

        saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
        return saveCarrierRelationInfoRes;
    }


    //删除
    @Override
    public SaveCarrierRelationInfoRes RmSaveCarrierRelationInfoRes(SaveCarrierRelationInfoReq01 saveCarrierRelationInfoReq01) {
        SaveCarrierRelationInfoRes saveCarrierRelationInfoRes=new SaveCarrierRelationInfoRes();
        SaveCarrierRelationInfoResB saveCarrierRelationInfoResB=new  SaveCarrierRelationInfoResB();
        if(saveCarrierRelationInfoReq01.getVenderSN()==null||"".equals(saveCarrierRelationInfoReq01.getVenderSN())){
            throw new SystemException("xxx","删除失败，载具不能为空");
        }
        CarrierInfo carrierInfo=carrierDao.SelectCarrierSN(saveCarrierRelationInfoReq01.getVenderSN());
        if(carrierInfo==null){
            throw new SystemException("xxx","删除失败，载具不存在");
        }
        List<SaveCarrierRelationInfoReq01.BInfo> BInfo =saveCarrierRelationInfoReq01.getBInfo();

        if(BInfo.size()<=0){
            throw new SystemException("xxx","删除失败，请选择批次信息");
        }
        String VenderSN=saveCarrierRelationInfoReq01.getVenderSN();
        //卸载载具信息
        for(SaveCarrierRelationInfoReq01.BInfo b:BInfo){
            //根据Batch,VenderSN查询载具关系信息
            CarrierreLationInfo carrierreLationInfo=carrierreLationDAO.SelectBatchSN(b.getBatch(),carrierInfo.getGuid());
            if(carrierreLationInfo==null){
                throw new SystemException("xxx","删除失败，批次"+b.getBatch()+"和此载具未绑定,请检查是否已解绑!");
            }
            //解绑
            int delete=carrierreLationDAO.deleteCarrierreLationInfoByruid(carrierreLationInfo.getRuid());

        }
        saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
        return saveCarrierRelationInfoRes;
    }

    //根据载具id获取所有的关联批次信息
    @Override
    public GetCarrierRelationInfoRes QALLGetCarrierRelationInfoRes(GetCarrierRelationInfoReq00 getCarrierRelationInfoReq00) {
        GetCarrierRelationInfoRes getCarrierRelationInfoRes=new GetCarrierRelationInfoRes();
        GetCarrierRelationInfoResB getCarrierRelationInfoResB=new GetCarrierRelationInfoResB();
        List<GetCarrierRelationInfoResD> getCarrierRelationInfoResDS=new ArrayList<GetCarrierRelationInfoResD>();
        if(getCarrierRelationInfoReq00.getVenderSN()==null||"".equals(getCarrierRelationInfoReq00.getVenderSN())){
            throw  new SystemException("xxx","查询失败，该载具不能为空");
        }
        CarrierInfo carrierInfo=carrierDao.SelectCarrierSN(getCarrierRelationInfoReq00.getVenderSN());
        if(carrierInfo==null){
            throw  new SystemException("xxx","查询失败，该载具信息不存在");
        }

        List<CarrierreLationInfo> carrierreLationInfos=carrierreLationDAO.selectCarrierreLationInfoBycarrierGd(carrierInfo.getGuid());
        if(carrierreLationInfos!=null&&carrierreLationInfos.size()>0){
            for(CarrierreLationInfo carrierreLationInfo:carrierreLationInfos){
                GetCarrierRelationInfoResD getCarrierRelationInfoResD=new GetCarrierRelationInfoResD();
                getCarrierRelationInfoResD.setBatch(carrierreLationInfo.getBatch());
                //查询批次信息
                BInfo bInfo=bdao.selectBatchInfoByBatch(carrierreLationInfo.getBatch());
                if(bInfo!=null){
                    //查询该批次物料版本信息
                    MaVerInfo maVerInfo=maVerDAO.SelectMaverInfo(bInfo.getMaVerGd());
                    if(maVerInfo!=null){
                        getCarrierRelationInfoResD.setMaCode(maVerInfo.getMaterialCode());
                        getCarrierRelationInfoResD.setMaName(maVerInfo.getMaterialName());
                        getCarrierRelationInfoResD.setMaVerRd(maVerInfo.getRuid());
                        getCarrierRelationInfoResD.setMaDes(maVerInfo.getMaterialDes());
                    }
                }
                getCarrierRelationInfoResDS.add(getCarrierRelationInfoResD);
            }
        }

        getCarrierRelationInfoResB.setData(getCarrierRelationInfoResDS);
        getCarrierRelationInfoRes.setBody(getCarrierRelationInfoResB);
        return getCarrierRelationInfoRes;
    }

    //清空载具
    @Override
    public SaveCarrierRelationInfoRes RmSaveCarrierRelationInfo(SaveCarrierRelationInfoReq02 saveCarrierRelationInfoReq02) {
        SaveCarrierRelationInfoRes saveCarrierRelationInfoRes=new SaveCarrierRelationInfoRes();
        SaveCarrierRelationInfoResB saveCarrierRelationInfoResB=new  SaveCarrierRelationInfoResB();
        if(saveCarrierRelationInfoReq02.getCarrierRd()<=0||"".equals(saveCarrierRelationInfoReq02.getCarrierRd())){
            throw new SystemException("xxx","载具不能为空");
        }
        CarrierInfo carrierInfo=carrierDao.SelectCarrierInfoByruid(saveCarrierRelationInfoReq02.getCarrierRd());
        if(carrierInfo!=null) {
            List<CarrierreLationInfo> carrierreLationInfos = carrierreLationDAO.selectCarrierreLationInfoBycarrierGd(carrierInfo.getGuid());
            if (carrierreLationInfos != null && carrierreLationInfos.size() > 0) {
                for (CarrierreLationInfo obj : carrierreLationInfos) {
                    if (carrierreLationDAO.deleteCarrierreLationInfoByruid(obj.getRuid()) <= 0) {
                        throw new SystemException("xxx", "删除失败");
                    }
                }
            }

            if(carrierDao.DeleteToolInfoByruid(carrierInfo.getRuid())<=0){
                throw new SystemException("xxx", "删除失败");
            }
        }
        saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
        return saveCarrierRelationInfoRes;
    }

    //清空历史
    @Override
    public SaveCarrierRelationInfoRes RmSaveCarrierRelationInfo01(SaveCarrierRelationInfoReq03 saveCarrierRelationInfoReq03) {
        SaveCarrierRelationInfoRes saveCarrierRelationInfoRes=new SaveCarrierRelationInfoRes();
        SaveCarrierRelationInfoResB saveCarrierRelationInfoResB=new  SaveCarrierRelationInfoResB();
        if(saveCarrierRelationInfoReq03.getVenderSN()==null||"".equals(saveCarrierRelationInfoReq03.getVenderSN())){
            throw new SystemException("xxx","载具序列号不能为空");
        }
        int i=carrierreLationDAO.delectByVenderSN(saveCarrierRelationInfoReq03.getVenderSN());
        saveCarrierRelationInfoRes.setBody(saveCarrierRelationInfoResB);
        return saveCarrierRelationInfoRes;
    }
}

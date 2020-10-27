package pnc.mesadmin.dao;

import org.apache.ibatis.annotations.Param;
import pnc.mesadmin.entity.CarrierreLationInfo;
import pnc.mesadmin.dto.newmove.GetCarrierHisInfo.GetCarrierHisInfoResD;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/25 18:44
 * @Description:
 */
public interface CarrierreLationDAO {
    //新增
    void inserteCarrierreLationInfo(CarrierreLationInfo carrierreLationInfo);

    //删除
    int deleteCarrierreLationInfoByruid(int ruid);

    //查询多个信息
    List<CarrierreLationInfo> selectCarrierreLationInfoBycarrierGd(String carrierGd);

    //查询单个信息根据载具gd和批次
    CarrierreLationInfo selectCarrierreLationInfoBycarrierGdAndbatch(@Param("carrierGd") String carrierGd, @Param("batch") String batch);

    //查询单个信息根据载具批次
    CarrierreLationInfo selectCarrierreLationInfoBybatch(String batch);


    //根据载具序列号查询
    List<CarrierreLationInfo> SelectByVenderSN(String argVenderSN);
    //根据载具序列号删除信息
    int delectByVenderSN(String argVenderSN);

    //根据CarrierGd删除信息
    int deleteCarrierreLationInfoByGuid(String CarrierGd);
    //tpm_CarrierRelationInfo,trt_BoxInfo,tpm_carrierinfo 获取载具历史记录
    List<GetCarrierHisInfoResD> selectAll(String argVenderSN);

    //查询单个信息根据载具批次
    CarrierreLationInfo SelectBatchSN(@Param("Batch") String Batch, @Param("CarrierGd") String CarrierGd);
}

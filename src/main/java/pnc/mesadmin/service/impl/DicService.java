package pnc.mesadmin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.DicDAO;
import pnc.mesadmin.dto.GetAllDicInfo.GetAllDicInfoResB;
import pnc.mesadmin.dto.GetAllDicInfo.GetAllDicInfoResD;
import pnc.mesadmin.dto.GetAllDicInfo.GetAllDicInfoRes;
import pnc.mesadmin.dto.GetDicInfo.GetDicInfoResB;
import pnc.mesadmin.dto.GetDicInfo.GetDicInfoResD;
import pnc.mesadmin.dto.GetDicInfo.GetDicInfoRes;
import pnc.mesadmin.dto.GetDicLanTypeInfo.GetDicLanTypeInfoResB;
import pnc.mesadmin.dto.GetDicLanTypeInfo.GetDicLanTypeInfoResD;
import pnc.mesadmin.dto.GetDicLanTypeInfo.GetDicLanTypeInfoRes;
import pnc.mesadmin.dto.SaveDicInfo.*;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.DicInfo;
import pnc.mesadmin.service.DicIService;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：字典信息Service实现类
 * 创建人：赵超
 * 创建时间：2017-5-24
 * 修改人：
 * 修改时间：
 */
@Transactional
@Service
public class DicService implements DicIService {

    @Resource
    private DicDAO dicDAO;

    /**
     * 获取语言类别树
     * @return
     */
    public GetDicLanTypeInfoRes getDicTree() throws SystemException {

        GetDicLanTypeInfoRes objEGetDicLanTypeInfoRes = new GetDicLanTypeInfoRes();

        GetDicLanTypeInfoResB body = new GetDicLanTypeInfoResB();

        List<GetDicLanTypeInfoResD> dataList = new ArrayList<GetDicLanTypeInfoResD>();

        GetDicLanTypeInfoResD data = null;
        // 加载语言类别信息列表树
        List<String> languageList = new ArrayList<String>();//dicDAO.getLanguageList();
        languageList.add("00");
        languageList.add("01");
        // 判断返回语言类别列表是否为空
        if (languageList.size() <= 0 || languageList == null) { // 为空
            throw new SystemException("MG000001", "语言类别列表为空！");
        }

        // 循环写入
        int count = 0;
        for (int i = 0; i < languageList.size(); i++) {
            data = new GetDicLanTypeInfoResD();
            String language = languageList.get(i);
            String lanName = null;
            // 00 表示中文
            if ("00".equals(language)) {
                lanName = "中文";
            } else {
                lanName = "英文";
            }
            data.setLanName(lanName);
            data.setLanCode(language);
            // 查询语言labID标签数量
            count = dicDAO.getDicCountByLanguage(language);
            data.setCount(count);
            dataList.add(data);
        }
        body.setData(dataList);
        objEGetDicLanTypeInfoRes.setBody(body);

        return objEGetDicLanTypeInfoRes;
    }
    /**
     * 获取字典信息列表
     * @return
     */
    public GetAllDicInfoRes getDicInfoListByLanCode(String lanCode) throws SystemException{

        GetAllDicInfoRes objEGetAllDicInfoRes = new GetAllDicInfoRes();

        GetAllDicInfoResB body = new GetAllDicInfoResB();

        // 获取字典信息列表
        List<GetAllDicInfoResD> dataList =  new ArrayList<GetAllDicInfoResD>();

        List<DicInfo> dicInfoList = dicDAO.getDicInfoListByLanCode(lanCode);

        GetAllDicInfoResD data = null;
        // 判断返回字典列表是否为空
        if (dicInfoList.size() <= 0 || dicInfoList == null) { // 为空
            throw new SystemException("MG000001", "字典信息列表为空！");
        }
        // 循环赋值
        for (int i = 0; i < dicInfoList.size(); i++){
            data = new GetAllDicInfoResD();
            data.setDicRd(dicInfoList.get(i).getRuid());
            data.setLabelID(dicInfoList.get(i).getLabelID());
            data.setLabelDes(dicInfoList.get(i).getLabelDes());
            dataList.add(data);
        }
        body.setData(dataList);
        objEGetAllDicInfoRes.setBody(body);

        return objEGetAllDicInfoRes;
    }
    /**
     * 获取字典信息
     * @return
     */
    public GetDicInfoRes getDicInfoByGuid(String dicRd) throws SystemException{
        GetDicInfoRes objEGetDicInfoRes = new  GetDicInfoRes();

        GetDicInfoResB body = new GetDicInfoResB();

        GetDicInfoResD data = new GetDicInfoResD();
        // 获取字典信息
        DicInfo dicInfo = dicDAO.getDicInfoByGuid(dicRd);
        // 判断返回字典是否为空
        if (dicInfo == null){
            throw new SystemException("MG000001", "字典信息为空！");
        }
        // 赋值
        data.setLabelID(dicInfo.getLabelID());
        data.setLabelDes(dicInfo.getLabelDes());
        data.setCreator(dicInfo.getCreator());
        data.setCreateTime(DateUtil.format(dicInfo.getCreateTime()));
        data.setLastModifyMan(dicInfo.getLastModifyMan());
        data.setLastModifyTime(DateUtil.format(dicInfo.getLastModifyTime()));
        data.setRemark(dicInfo.getRemark());

        body.setData(data);
        objEGetDicInfoRes.setBody(body);

        return objEGetDicInfoRes;
    }
    /**
     * 保存字典信息
     * @return
     */
    public SaveDicInfoRes saveDicInfo(SaveDicInfoReqBD00 busData00, DicInfo dicInfo) throws SystemException{

        SaveDicInfoRes saveDicInfoRes = new SaveDicInfoRes();

        SaveDicInfoResB body = new SaveDicInfoResB();

        SaveDicInfoResD data = new SaveDicInfoResD();
        // 赋值
        dicInfo.setGuid(CommonUtils.getRandomNumber());
        dicInfo.setLanguage(busData00.getLanCode());
        dicInfo.setLabelID(busData00.getLabelID());
        dicInfo.setLabelDes(busData00.getLabelDes());
        dicInfo.setRemark(busData00.getRemark());
        // 保存
        int count = dicDAO.saveDicInfo(dicInfo);

        if(count <=0) throw new SystemException("MG000001","保存失败！");

        saveDicInfoRes.setBody(body);

        return saveDicInfoRes;
    }

    /**
     * 删除字典信息
     * @return
     */
    public SaveDicInfoRes deleteDicInfoByRuid(List<SaveDicInfoReqBD01> dicRdList) throws SystemException{
        SaveDicInfoRes saveDicInfoRes = new SaveDicInfoRes();

        SaveDicInfoResB body = new SaveDicInfoResB();

        SaveDicInfoResD data = new SaveDicInfoResD();

        for(SaveDicInfoReqBD01 bd01 : dicRdList) {
            // 保存
            int count = dicDAO.deleteDicInfoByRuid(bd01.getDicRd());

            if (count <= 0) throw new SystemException("MG000001", "删除失败！");
        }
        saveDicInfoRes.setBody(body);

        return saveDicInfoRes;
    }
    /**
     * 更新字典信息
     * @return
     */
    public SaveDicInfoRes update(SaveDicInfoReqBD02 busData02, DicInfo dicInfo) throws SystemException{
        SaveDicInfoRes saveDicInfoRes = new SaveDicInfoRes();

        SaveDicInfoResB body = new SaveDicInfoResB();

        SaveDicInfoResD data = new SaveDicInfoResD();
        // 赋值
        dicInfo.setRuid(busData02.getDicRd());
        dicInfo.setLabelID(busData02.getLabelID());
        dicInfo.setLabelDes(busData02.getLabelDes());
        dicInfo.setRemark(busData02.getRemark());
        // 更新
        int count = dicDAO.update(dicInfo);

        if(count <=0) throw new SystemException("MG000001","更新失败！");

        saveDicInfoRes.setBody(body);

        return saveDicInfoRes;
    }
}

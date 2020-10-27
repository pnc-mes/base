package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.*;
import pnc.mesadmin.dto.GetAllFileGInfo.GetAllFileGInfoRes;
import pnc.mesadmin.dto.GetAllFileGInfo.GetAllFileGInfoResB;
import pnc.mesadmin.dto.GetAllFileGInfo.GetAllFileGInfoResD;
import pnc.mesadmin.dto.GetAllSGInfo.GetAllSGInfoResD;
import pnc.mesadmin.dto.GetFileGInfo.GetFileGInfoRes;
import pnc.mesadmin.dto.GetFileGInfo.GetFileGInfoResB;
import pnc.mesadmin.dto.GetFileGInfo.GetFileGInfoResD;
import pnc.mesadmin.dto.GetFileGInfo.GetFileGInfoResDFileInfo;
import pnc.mesadmin.dto.SaveFileGPInfo.*;
import pnc.mesadmin.entity.*;
import pnc.mesadmin.service.BaseIService;
import pnc.mesadmin.service.FileGroupIService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;
import pnc.mesadmin.utils.MyPage;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：文件组信息Service实现类
 * 创建人：刘福志
 * 创建时间：2017-6-6
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class FileGroupService implements FileGroupIService{
    @Resource
    private FileGpDAO fileGpDAO;

    @Resource
    private FileGpDtlDAO fileGpDtlDAO;

    @Resource
    private FileVerDAO fileVerDAO;

    @Resource
    private FileDAO fileDAO;

    @Resource
    private BaseIService baseIService;

    /**
     * @Description 分页查询
     * @Author jgy
     * @Date 2020/10/13
     * @Param
     * @Return
     * @Exception
     */
    @Override
    public BaseResponse GetFileGroupList(SaveFileGPInfoReqBD02 request) {
        Page<FileGpInfo> page = new Page<>(request.getPage(), request.getLimit());
        IPage<FileGpInfo> response = fileGpDAO.selectPage(page, new QueryWrapper<FileGpInfo>()
                .like(!StringUtils.isBlank(request.getFileGpName()), "fileGpName", request.getFileGpName()));
        return BaseResponse.success(response);
    }

    //查询文件组列表信息
    public GetAllFileGInfoRes QALLselectAllFileGpInfo(List<InitDataField> argInitDataFields, PageInfo argPageInfo) throws SystemException {
        GetAllFileGInfoRes objEGetAllFileGInfoRes = new GetAllFileGInfoRes();

        GetAllFileGInfoResB body = new GetAllFileGInfoResB();

        List<GetAllFileGInfoResD> dataList =  new ArrayList<GetAllFileGInfoResD>();

        // 获取文件组信息
        List<FileGpInfo> fileGpInfoList =  baseIService.QALLBaseInfo(FileGpDAO.class, "SelectAllFileGpInfo",
                argInitDataFields, argPageInfo);

        if(fileGpInfoList!=null || fileGpInfoList.size()>0) {
            GetAllFileGInfoResD data = null;

            // 循环赋值查询文件组
            for (int i = 0; i < fileGpInfoList.size(); i++) {
                data = new GetAllFileGInfoResD();
                data.setFileGRd(fileGpInfoList.get(i).getRuid());
                data.setFileGpName(fileGpInfoList.get(i).getFileGpName());
                dataList.add(data);
            }
        }

        body.setData(dataList);
        objEGetAllFileGInfoRes.setBody(body);

        return objEGetAllFileGInfoRes;
    }

    @Override
    public PageResult<GetAllFileGInfoResD> QALLGetAllFileGNew(BaseRequest req) {
        List<GetAllFileGInfoResD> resDList = new ArrayList<GetAllFileGInfoResD>();
        GetAllFileGInfoResD resD = null;

        IPage<FileGpInfo> iPage = fileGpDAO.selectPage(new MyPage(req), CommonUtils.getQueryWrapper(req.getFields()));
        //赋值dto并返回
        for (FileGpInfo obj : iPage.getRecords()) {
            resD = new GetAllFileGInfoResD();
            resD.setFileGRd(obj.getRuid());
            resD.setFileGpName(obj.getFileGpName());
            resDList.add(resD);
        }
        return new PageResult<>(iPage, resDList);
    }


    //查询文件组信息
    public GetFileGInfoRes GetselectByruid(int fileGRd) throws SystemException {
        GetFileGInfoRes objEGetFileGInfoRes = new  GetFileGInfoRes();

        GetFileGInfoResB body = new GetFileGInfoResB();

        GetFileGInfoResD data=new GetFileGInfoResD();

        // 获取文件组信息
        FileGpInfo objEfileGpInfo =fileGpDAO.SelectByruid(fileGRd);

        List<GetFileGInfoResDFileInfo> objEFileInfos=new ArrayList<GetFileGInfoResDFileInfo>();

        if(objEfileGpInfo!=null) {
            //查询文件组明细信息
            List<FileGpDtlInfo> objEFileGpDtlInfo = fileGpDtlDAO.SelectByguid(objEfileGpInfo.getGuid());

            for (FileGpDtlInfo obj : objEFileGpDtlInfo) {
                GetFileGInfoResDFileInfo objeGetFileGInfoResDFileInfo = new GetFileGInfoResDFileInfo();
                //查询文件版本信息
                FileVerInfo objEFileVerInfo = fileVerDAO.SelectByFileVerGd(obj.getFileVerGd());

                objeGetFileGInfoResDFileInfo.setGPDtlRd(obj.getRuid());
                if(objEFileVerInfo!=null) {
                    objeGetFileGInfoResDFileInfo.setFileVerRd(objEFileVerInfo.getRuid());
                    objeGetFileGInfoResDFileInfo.setFileName(objEFileVerInfo.getFileName());
                    objeGetFileGInfoResDFileInfo.setVersion(objEFileVerInfo.getVersion());
                }
                objEFileInfos.add(objeGetFileGInfoResDFileInfo);
            }

            //赋值查询信息
            data.setFileGRd(objEfileGpInfo.getRuid());
            data.setFileGpName(objEfileGpInfo.getFileGpName());
            data.setFileInfo(objEFileInfos);
            data.setCreator(objEfileGpInfo.getCreator());
            data.setCreateTime(DateUtil.format(objEfileGpInfo.getCreateTime()));
            data.setLastModifyMan(objEfileGpInfo.getLastModifyMan());
            data.setLastModifyTime(DateUtil.format(objEfileGpInfo.getLastModifyTime()));
            data.setRemark(objEfileGpInfo.getRemark());
        }

        body.setData(data);
        objEGetFileGInfoRes.setBody(body);

        return objEGetFileGInfoRes;
    }


    //新增文件组信息
    public SaveFileGPInfoRes AddinsertFileGpInfo(SaveFileGPInfoReqBD00 busData00, FileGpInfo fileGpInfo) throws SystemException {
        SaveFileGPInfoRes saveFileGPInfoRes = new SaveFileGPInfoRes();

        SaveFileGPInfoResB body = new SaveFileGPInfoResB();

        SaveFileGPInfoResD data = new SaveFileGPInfoResD();

        String guid = CommonUtils.getRandomNumber();
        //查询文件组所有信息
        List<FileGpInfo> objEFileGpInfos=fileGpDAO.SelectAllFileGpInfo();

        // 赋值新增文件组
        fileGpInfo.setGuid(guid);

        //逻辑校验保存的文件组名称不能相同
        for (FileGpInfo obj:objEFileGpInfos){
            if (obj.getFileGpName().equals(busData00.getFileGpName())){
                throw new SystemException("MG0006F","文件组名称已存在");
            }
        }

        if ((busData00.getFileGpName()).equals("")){
            throw new SystemException("MG0003F","文件组名称不能为空也不能有空格");
        }

        fileGpInfo.setFileGpName(busData00.getFileGpName());
        fileGpInfo.setCreator(CommonUtils.readUser().getRealName());
        fileGpInfo.setCreateTime(new Date());
        fileGpInfo.setRemark(busData00.getRemark());

        //文件信息不能重复
        Set<Integer> set = new HashSet<Integer>();

        for(int i=0;i<busData00.getFileInfo().size();i++){
            set.add(busData00.getFileInfo().get(i).getFileVerRd());
        }

        if(set.size() < busData00.getFileInfo().size()){
            throw new SystemException("MG0006F","文件信息重复,不能保存");
        }

        //新增文件版本信息
        for(SaveFileGPInfoReqBD00FileInfo obj:busData00.getFileInfo()){
            //查询文件版本信息
            FileVerInfo objeFileVerInfo=fileVerDAO.SelectByfileVerRd(obj.getFileVerRd());

            if(objeFileVerInfo==null){
                throw new SystemException("", "文件信息不能为空！");
            }

            FileGpDtlInfo objeFileGpDtlInfo = new FileGpDtlInfo();

            objeFileGpDtlInfo.setGuid(CommonUtils.getRandomNumber());
            objeFileGpDtlInfo.setFileGd(objeFileVerInfo.getFileGd());
            objeFileGpDtlInfo.setFileGpGd(guid);
            objeFileGpDtlInfo.setFileVerGd(objeFileVerInfo.getGuid());
            objeFileGpDtlInfo.setCreator(CommonUtils.readUser().getRealName());
            objeFileGpDtlInfo.setCreateTime(new Date());
            objeFileGpDtlInfo.setRemark(busData00.getRemark());

            fileGpDtlDAO.InsertFileGpDtlInfo(objeFileGpDtlInfo);
        }
        // 保存
        fileGpDAO.InsertFileFileGpInfo(fileGpInfo);


        body.setData(data);
        saveFileGPInfoRes.setBody(body);

        return saveFileGPInfoRes;
    }

    //更新文件组信息
    public SaveFileGPInfoRes ModupdateFileGpInfo(SaveFileGPInfoReqBD02 busData02, FileGpInfo fileGpInfo) throws SystemException {
        SaveFileGPInfoRes saveFileGPInfoRes = new SaveFileGPInfoRes();

        SaveFileGPInfoResB body = new SaveFileGPInfoResB();

        SaveFileGPInfoResD data = new SaveFileGPInfoResD();


        // 获取文件组信息
        FileGpInfo objEfileGpInfo =fileGpDAO.SelectByruid(busData02.getFileGRd());

        // 判断返回文件组是否为空
        if (objEfileGpInfo == null){
            throw new SystemException("MG_MES4001210010001F", "查询文件组信息为空！");
        }

        //查询文件组名称信息
        FileGpInfo objEFileGpInfos=fileGpDAO.SelectfileGpName(busData02.getFileGpName());

        if(objEFileGpInfos!=null && !objEFileGpInfos.getFileGpName().equals(objEfileGpInfo.getFileGpName())){
            throw new SystemException("MG0006F","更新失败，文件组名称已存在");
        }

        // 赋值更新文件组信息
        fileGpInfo.setRuid(busData02.getFileGRd());

        if ((busData02.getFileGpName()).equals("")){
            throw new SystemException("MG0003F","文件组名称不能为空");
        }

        fileGpInfo.setFileGpName(busData02.getFileGpName());
        fileGpInfo.setLastModifyMan(CommonUtils.readUser().getRealName());
        fileGpInfo.setLastModifyTime(new Date());
        fileGpInfo.setRemark(busData02.getRemark());


        //查询文件组明细信息
        List<FileGpDtlInfo> objEFileGpDtlInfo=fileGpDtlDAO.SelectByguid(objEfileGpInfo.getGuid());

        //文件信息不能重复
        Set<Integer> set = new HashSet<Integer>();

        for(int i=0;i<busData02.getFileInfo().size();i++){
            set.add(busData02.getFileInfo().get(i).getFileVerRd());
        }

        if(set.size() < busData02.getFileInfo().size()){
            throw new SystemException("MG0006F","文件信息重复,不能保存");
        }

        if(busData02.getFileInfo()!=null) {
            for (SaveFileGPInfoReqBD02FileInfo obj : busData02.getFileInfo()) {

                //查询文件版本信息
                FileVerInfo objEFileVerInfo = fileVerDAO.SelectByfileVerRd(obj.getFileVerRd());

                if (objEFileVerInfo == null) {
                    throw new SystemException("", "文件信息不能为空！");
                }

                if (obj.getGPDtlRd() != 0) {
                    for(int i=0; i<objEFileGpDtlInfo.size(); i++) {
                        if (obj.getGPDtlRd() == objEFileGpDtlInfo.get(i).getRuid()) {
                            FileGpDtlInfo objeFileGpDtlInfos =objEFileGpDtlInfo.get(i);

                            objeFileGpDtlInfos.setRuid(obj.getGPDtlRd());
                            objeFileGpDtlInfos.setFileGd(objEFileVerInfo.getFileGd());
                            objeFileGpDtlInfos.setFileVerGd(objEFileVerInfo.getGuid());

                            if (fileGpDtlDAO.UpdateFileGpDtlInfo(objeFileGpDtlInfos) <= 0) {
                                throw new SystemException("", "更新文件组明细信息失败！");
                            }
                            objEFileGpDtlInfo.remove(i);
                            break;
                        }
                    }
                } else {
                    FileGpDtlInfo objeFileGpDtlInfo = new FileGpDtlInfo();

                    objeFileGpDtlInfo.setGuid(CommonUtils.getRandomNumber());
                    objeFileGpDtlInfo.setFileGd(objEFileVerInfo.getFileGd());
                    objeFileGpDtlInfo.setFileGpGd(objEfileGpInfo.getGuid());
                    objeFileGpDtlInfo.setFileVerGd(objEFileVerInfo.getGuid());
                    objeFileGpDtlInfo.setCreator(CommonUtils.readUser().getRealName());
                    objeFileGpDtlInfo.setCreateTime(new Date());
                    objeFileGpDtlInfo.setRemark(busData02.getRemark());

                    fileGpDtlDAO.InsertFileGpDtlInfo(objeFileGpDtlInfo);
                }
            }
        }

        // 更新
        int count = fileGpDAO.UpdateFileGpInfo(fileGpInfo);

        if(count <=0) throw new SystemException("MG_MES4001214030002F","更新文件组信息失败！");

        for(int i=0; i<objEFileGpDtlInfo.size(); i++){
            if(fileGpDtlDAO.DeleteFileGpDtlInfo(objEFileGpDtlInfo.get(i).getGuid()) <= 0){
                throw new SystemException("MG000001", "更新文件组信息失败");
            }
        }

        body.setData(data);
        saveFileGPInfoRes.setBody(body);

        return saveFileGPInfoRes;
    }

    //删除文件组信息
    public SaveFileGPInfoRes RmdeleteFileGpInfo(int ruid) throws SystemException {
        SaveFileGPInfoRes saveFileGPInfoRes = new SaveFileGPInfoRes();

        SaveFileGPInfoResB body = new SaveFileGPInfoResB();

        SaveFileGPInfoResD data = new SaveFileGPInfoResD();

        //查询文件组信息
        FileGpInfo objEFileGpInfo=fileGpDAO.SelectByruid(ruid);

        if (objEFileGpInfo==null){
            throw new SystemException("MG_MES4001210010001F","查询文件组信息为空！");
        }

        //查询文件组明细信息
        List<FileGpDtlInfo> objEFileGpDtlInfo=fileGpDtlDAO.SelectByguid(objEFileGpInfo.getGuid());

        //循环删除文件
        for (FileGpDtlInfo obj:objEFileGpDtlInfo){
            fileGpDtlDAO.DeleteFileGpGd(obj.getFileGpGd());
        }

        // 删除文件组信息
        int count = fileGpDAO.DeleteFileGpInfo(ruid);

        if(count <=0) throw new SystemException("MG_MES4001214020001F","删除文件组信息失败！");

        body.setData(data);
        saveFileGPInfoRes.setBody(body);

        return saveFileGPInfoRes;
    }
}

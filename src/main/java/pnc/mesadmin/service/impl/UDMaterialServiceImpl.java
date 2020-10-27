package pnc.mesadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dao.*;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWFInfo;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWSDevGInfo;
import pnc.mesadmin.dto.GetCMWInfo.GetCMWInfoResDWSpecInfo;
import pnc.mesadmin.dto.GetWOInfo.*;
import pnc.mesadmin.dto.SaveUDMaterailDto.GetAllByWoCodeResponse;
import pnc.mesadmin.dto.SaveUDMaterailDto.SaveUDMaterailRequest;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.entity.SpecVerInfo;
import pnc.mesadmin.entity.UDMaterialLogInfo;
import pnc.mesadmin.entity.WoInfo;
import pnc.mesadmin.service.GConfigIService;
import pnc.mesadmin.service.UDMaterialService;
import pnc.mesadmin.utils.BaseResponse;
import pnc.mesadmin.utils.CommonUtils;
import pnc.mesadmin.utils.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description 装卸物料
 * @Author yin.yang
 * @Date 2019/12/19
 * @Param
 * @Return
 * @Exception
 */
@Transactional
@Service
public class UDMaterialServiceImpl implements UDMaterialService {


    @Override
    public void AddSaveUDMaterial(SaveUDMaterailRequest request) {

    }

    @Override
    public BaseResponse GetAllByWoCode(SaveUDMaterailRequest request) {
        return null;
    }

    @Override
    public BaseResponse GetGetWOInfoRes(GetWOInfoReqBD00 argGetWOInfoReqBD00) {
        return null;
    }
}

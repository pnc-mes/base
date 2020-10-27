package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.SaveDevMaTainDto.SaveDevMaTainRes00;
import pnc.mesadmin.dto.SaveDevMaTainDto.SaveDevMaTainRes01;

import java.util.List;

public interface DevMaTainService {

    BaseRes AddSaveDevOpt00(SaveDevMaTainRes00 res);

    BaseRes AddSaveDevOpt01(List<SaveDevMaTainRes01> res01);
}

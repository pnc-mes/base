package pnc.mesadmin.service;

import pnc.mesadmin.dto.BaseDto.BaseRes;
import pnc.mesadmin.dto.GetAllToolDevDto.GetToolDevRes;
import pnc.mesadmin.dto.GetAllToolDevDto.SaveToolDevRes00;
import pnc.mesadmin.dto.GetAllToolDevDto.SaveToolDevRes01;

import java.util.List;

public interface ToolDevService {

    BaseRes GetToolDevRelInfo(GetToolDevRes res);

    BaseRes SaveToolDevRel00(SaveToolDevRes00 res);

    BaseRes SaveToolDevRel01(List<SaveToolDevRes01> res01);
}

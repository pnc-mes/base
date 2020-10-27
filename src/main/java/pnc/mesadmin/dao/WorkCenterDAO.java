package pnc.mesadmin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pnc.mesadmin.entity.UnitInfo;
import pnc.mesadmin.entity.WorkCenterInfo;

import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：工作中心DAO
 * 创建人：张亮亮
 * 创建时间：2017-05-27
 * 修改人：
 * 修改时间：
 */
public interface WorkCenterDAO extends BaseMapper<WorkCenterInfo> {
   //查询工作中心
   List<WorkCenterInfo> SelectWorkCenterInfo();

   //根据ruid查询工作中心
   WorkCenterInfo SelectWorkCenterByruid(int argruid);

   //新增工作中心
   int InsertWorkCenterInfo(WorkCenterInfo argWorkCenterInfo);

   //删除工作中心
   int DeleteWorkCenterInfo(int argruid);

   //更新工作中心
   int UpdateWorkCenterInfo(WorkCenterInfo argWorkCenterInfo);

   //根据guid查询工作中心
   WorkCenterInfo SelectWorkCenterByguid(String argguid);

   WorkCenterInfo SelectWorkCenterByCenterName(String argCenterName);

}

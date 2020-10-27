package pnc.mesadmin.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pnc.mesadmin.dto.BaseRequest;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：自定义MybatisPlus分页处理
 * 创建人：pjf
 * 创建时间：2020-09-11
 * 修改人：
 * 修改时间：
 */
public class MyPage extends Page {
    public MyPage(BaseRequest request){
        super(request.getCurrent(), request.getSize() <= 0 ? 1000 : request.getSize(), 0L);
    }
}

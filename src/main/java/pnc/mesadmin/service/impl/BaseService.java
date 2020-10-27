package pnc.mesadmin.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pnc.mesadmin.dto.InitDataField;
import pnc.mesadmin.dto.PageInfo;
import pnc.mesadmin.dto.SystemException;
import pnc.mesadmin.service.BaseIService;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：Base管理业务信息逻辑
 * 创建人：潘俊峰
 * 创建时间：2017-08-04
 * 修改人：
 * 修改时间：
 */
@Service
@Transactional
public class BaseService implements BaseIService {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public <T>List<T> QALLBaseInfo(Class clazz, String method, List<InitDataField> initDataFields,
                                         PageInfo pageInfo) throws SystemException {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        String sql = sqlSession.getConfiguration().getMappedStatement(clazz.getName() + "." + method).getBoundSql(null).getSql();

        StringBuilder newSql = new StringBuilder();
        StringBuilder conditionSql = new StringBuilder();

        newSql.append(sql);

        if(initDataFields != null) {
            for (int i = 0; i < initDataFields.size(); i++) {
                InitDataField field = initDataFields.get(i);
                if("order by".equals(field.getFieldOpt().toLowerCase())){
                    conditionSql.append(field.getFieldName() + ",");
                    continue;
                }

                if (i == 0) {
                    newSql.append(" WHERE ");
                } else {
                    newSql.append(" AND ");
                }
                switch (field.getFieldOpt().toLowerCase()){
                    case "in":
                        newSql.append(field.getFieldName() + " "
                                + field.getFieldOpt() + " " + field.getFieldVal() + " ");
                        break;
                    default:
                        newSql.append(field.getFieldName() + " "
                                + field.getFieldOpt() + " '" + field.getFieldVal() + "' ");
                        break;
                }
            }
        }

        if(conditionSql.length() > 0){
            newSql.append(" ORDER BY " + conditionSql.substring(0, conditionSql.length() - 1));
        }

        if(pageInfo != null){
            newSql.append(" LIMIT " + pageInfo.getPageIndex() * pageInfo.getPageSize() + "," + pageInfo.getPageSize());
        }

        //定义返回对象
        List<T> objs = new ArrayList<T>();

        //获取当前实体类
        String objName = sqlSession.getConfiguration().getMappedStatement(clazz.getName() + "." + method).getResultMaps().get(0).getType().getName();

        try {
            Class objClazz = Class.forName(objName);

            List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

            Map<String,Object> param = new HashMap<String, Object>();

            String key = "";

            param.put("sql", newSql);

            list = sqlSession.selectList("BaseMapper.SelectAll", param);

            for (HashMap<String, Object> map : list) {

                Object obj =  objClazz.newInstance();

                Map<String, Object> newMap = new HashMap<String, Object>();

                for(Map.Entry<String, Object> entry : map.entrySet()){

                    newMap.put(entry.getKey().toLowerCase(), entry.getValue());
                }

                Field[] fields = objClazz.getDeclaredFields();
                for(int i=0; i<fields.length; i++){
                    Field field = fields[i];
                    field.setAccessible(true);

                    key = field.getName().toLowerCase();

                    if(newMap.containsKey(key)){
                        field.set(obj, newMap.get(key));
                    }
                }

                objs.add((T) obj);

                //System.out.println(map.toString() + map.get("MaterialName")); // 注:map的key为数据表的列，区分大小写
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return objs;
    }
}

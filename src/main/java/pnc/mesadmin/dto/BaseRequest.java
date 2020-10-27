package pnc.mesadmin.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：查询通用实体类
 * 创建人：pjf
 * 创建时间：2020-09-08
 * 修改人：
 * 修改时间：
 */
public class BaseRequest<T> implements Serializable {
    private long current; //当前页

    private long size; //每页数

    private List<BaseInitField> fields; //查询方案

    private String execType; //类型

    private T data; //业务数据

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<BaseInitField> getFields() {
        return fields;
    }

    public void setFields(List<BaseInitField> fields) {
        this.fields = fields;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

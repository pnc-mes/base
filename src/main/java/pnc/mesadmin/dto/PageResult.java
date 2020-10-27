package pnc.mesadmin.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：分页返回通用实体类
 * 创建人：pjf
 * 创建时间：2020-09-08
 * 修改人：
 * 修改时间：
 */
public class PageResult<T> implements Serializable {
    private long total; //总条数

    private long totalPage; //总页数

    private long current; //当前页

    private long size; //每页数

    private List<T> list; //业务数据

    public long getTotal() {
        return total;
    }

    public PageResult(long total, long totalPage, long current, long size, List<T> list){
        this.total = total;
        this.totalPage = totalPage;
        this.current = current;
        this.size = size;
        this.list = list;
    }

    public PageResult(IPage iPage, List<T> list){
        this.total = iPage.getTotal();
        this.totalPage = iPage.getPages();
        this.current = iPage.getCurrent();
        this.size = iPage.getSize();
        this.list = list;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

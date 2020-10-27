package pnc.mesadmin.utils;

/**
 * @program: mesadmin
 * @description: 通用返回结果集
 * @author: yin.yang
 * @create: 2020-05-25
 **/
public class PageUtil {
    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

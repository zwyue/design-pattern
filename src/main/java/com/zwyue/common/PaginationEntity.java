package com.zwyue.common;

/**
 * PaginationEntity class
 * 用于分页的实体类
 *
 * @author zwy
 * @date 2018/11/29 9:59
 */
public class PaginationEntity {

    //页面下标（即第几页）
    private Integer pageIndex;

    //页面条数（即一页有几条数据）
    private Integer pageSize;

    //页面偏移量（即从第几条查询）
    private Integer pageOffset;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageOffset() {
        if(pageIndex!=null&&pageSize!=null){
            pageOffset = pageSize * (pageIndex - 1);
        }
        return pageOffset;
    }

    public void setPageOffset(Integer pageSize) {
        if(pageSize!=null&&pageIndex!=null){
            this.pageOffset = pageSize * (pageIndex - 1) ;
        }
    }
}

package cn.jzteam.swift.query;


/**
 * 分页条件查询的基类
 */
public class QueryCondition {

    // 希望获取的字段，以逗号分隔。
    protected String queryColumnStr;

	// 排序字段。需要包含排序方式。e.g.: " out_nation ASC, in_nation DESC "
    protected String orderStr;
    
    // 是否查询记录总数
    protected boolean hasCount = false;

    // 当前页码，默认为第一页。
    protected int currentPage = 1;

    // 每页记录数，默认20。
    protected int pageSize = 20;
    
    /**
     * mapper文件使用，返回当前页的初始记录序号。
     *
     * @return
     */
    public long getStartIndex() {
        if (this.currentPage <= 1) {
            return 0;
        }
        return (this.currentPage - 1) * this.pageSize;
    }

	public boolean getHasCount() {
		return hasCount;
	}

	public void setHasCount(boolean hasCount) {
		this.hasCount = hasCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
    public String getQueryColumnStr() {
    	return queryColumnStr;
    }
    
    public void setQueryColumnStr(String queryColumnStr) {
    	this.queryColumnStr = queryColumnStr;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

}

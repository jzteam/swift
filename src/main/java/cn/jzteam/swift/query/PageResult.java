package cn.jzteam.swift.query;

import java.util.List;

public class PageResult<T> {

	// 总记录数
	private long totalCount;
	
	// 数据
	private List<T> dataList;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	
}

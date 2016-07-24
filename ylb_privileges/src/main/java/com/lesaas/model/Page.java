package com.lesaas.model;

import java.util.List;

public class Page {
	
	//-- 分页参数 --//
	protected int status = 0; //状态
	
	protected int pageNo = 1; //当前页数
	
	protected int pageSize = 1; //总页数
	
	protected int numPerPage=10; //每页条数
	
	protected boolean isPaged=true;
	
	//总数据条目数
	protected int totalCount;
	 
	//--数据--//
	private List<?> result;
	
	/****配置功能返回信息***********/
	private String[] disTitle ;//显示列标题
	
	private int disCols ; //显示列数
	 
	public int getPageNo() {
		if(pageNo>pageSize)pageNo=pageSize;
		if(pageNo<1)pageNo=1;
		if(status==1)pageNo=1;
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageSize() {
		pageSize=totalCount/numPerPage+(totalCount%numPerPage==0?0:1);
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getNumPerPage() {
		return numPerPage;
	}
	
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<?> getResult() {
		return result;
	}
	
	public void setResult(List<?> result) {
		this.result = result;
	}
	
	public boolean getHasPrev(){
	  return pageNo>1;	
	}
	
	public boolean getHasNext(){
		return pageNo<pageSize;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public boolean getIsPaged() {
		return isPaged;
	}

	public void setIsPaged(boolean isPaged) {
		this.isPaged = isPaged;
	}

	public String[] getDisTitle() {
		return disTitle;
	}

	public void setDisTitle(String[] disTitle) {
		this.disTitle = disTitle;
	}

	public int getDisCols() {
		return disCols;
	}

	public void setDisCols(int disCols) {
		this.disCols = disCols;
	}
}

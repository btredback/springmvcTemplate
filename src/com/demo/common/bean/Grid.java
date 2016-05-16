package com.demo.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;

public class Grid implements Serializable {
	/**
	 * datagrid接收数据返回格式
	 * @author bt
	 */
	private static final long serialVersionUID = 5239079466502258696L;
	private Long total = 0L;
	private List<T> rows = new ArrayList<T>();
	private List<T> footer = new ArrayList<T>();
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public List<T> getFooter() {
		return footer;
	}
	public void setFooter(List<T> footer) {
		this.footer = footer;
	}
	

}

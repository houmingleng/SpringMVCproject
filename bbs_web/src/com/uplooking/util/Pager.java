package com.uplooking.util;

import java.io.Serializable;
import java.util.List;

public class Pager implements Serializable {
	private int index;
	private int count;
	private int size;
	private int page;
	private String url;
	private List<?> items;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
	public Pager() {
		super();
	}
	public Pager(int index, int count, int size, String url, List<?> items) {
		super();
		this.index = index;
		this.count = count;
		this.size = size;
		this.page = count%size==0?count/size:count/size+1;
		this.url = url;
		this.items = items;
	}
	@Override
	public String toString() {
		return "Pager [index=" + index + ", count=" + count + ", size=" + size + ", page=" + page + ", url=" + url
				+ ", items=" + items + "]";
	}
	
}

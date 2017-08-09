package com.winds.smartlink.repo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KPaging<T> implements Serializable {

	protected static final long serialVersionUID = -469021099521481471L;

	/** The max display page. */
	public static int MAX_DISPLAY_PAGE = 10;
	
	/** The page. */
	protected int page;

	/** The page size. */
	protected int pageSize;

	/** The pages. */
	protected Integer[] pages;

	/** The total pages. */
	protected int totalPages;

	/** The total rows. */
	protected int totalRows;

	/** The current last rows. */
	protected int currentLastRows;

	/** The max result. */
	private int maxResult;

	protected int maxDisplayPage = MAX_DISPLAY_PAGE;
	
	/** The list. */
	protected List<T> list = new ArrayList<T>();

	/**
	 * Instantiates a new k paging.
	 * 
	 * @param pageSize
	 *            the page size
	 */
	public KPaging(int pageSize) {
		super();
		this.pageSize = pageSize;
		this.list = null;
		this.totalPages = -1;
		this.page = 0;

	}

	public KPaging(int pageSize, int maxDisplayPage) {
		super();
		this.pageSize = pageSize;
		this.list = null;
		this.totalPages = -1;
		this.page = 0;
		this.maxDisplayPage = maxDisplayPage;
	}

	/**
	 * @return the maxDisplayPage
	 */
	public int getMaxDisplayPage() {
		return maxDisplayPage;
	}

	public KPaging() {
		super();
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 * 
	 * @param page
	 *            the new page
	 */
	public void setPage(final int page) {
		this.page = page;

		if (totalRows > 0) {
			setCurrentListPage();
		}
		// currentLastRows = endPage;
	}

	/**
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize
	 *            the new page size
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Gets the pages.
	 * 
	 * @return the pages
	 */
	public Integer[] getPages() {
		return pages;
	}

	/**
	 * Gets the list.
	 * 
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 * 
	 * @param list
	 *            the new list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * Gets the total pages.
	 * 
	 * @return the total pages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Sets the total pages.
	 * 
	 * @param totalPages
	 *            the new total pages
	 */
	public void setTotalPages(int totalPages) {

		this.totalPages = totalPages;
		setCurrentListPage();
	}

	/**
	 * Gets the total rows.
	 * 
	 * @return the total rows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * Sets the total rows.
	 * 
	 * @param totalRows
	 *            the new total rows
	 */
	public void setTotalRows(final int totalRows) {

		this.totalRows = totalRows;
		if (totalRows == 0) {
			totalPages = 0;
		} else {
			totalPages = (totalRows - 1) / pageSize + 1;
		}
		setTotalPages(totalPages);
	}

	/**
	 * Gets the current last rows.
	 * 
	 * @return the current last rows
	 */
	public int getCurrentLastRows() {
		return currentLastRows;
	}

	/**
	 * Sets the current last rows.
	 * 
	 * @param currentLastRows
	 *            the new current last rows
	 */
	public void setCurrentLastRows(int currentLastRows) {
		this.currentLastRows = currentLastRows;
	}

	/**
	 * Gets the max result.
	 * 
	 * @return the max result
	 */
	public int getMaxResult() {
		return maxResult;
	}

	/**
	 * Sets the max result.
	 * 
	 * @param maxResult
	 *            the new max result
	 */
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	/**
	 * Sets the current list page.
	 */
	protected void setCurrentListPage() {
		// if (page == 0)
		// maxDisplayPage += 2;
		currentLastRows = (page + 1) * pageSize;
		currentLastRows = currentLastRows > totalRows ? totalRows
				: currentLastRows;

		int startPage = 0;
		int endPage = totalPages - 1;
		if (page >= startPage + maxDisplayPage - 1) {

			// startPage = ((page + 1) / maxDisplayPage) * maxDisplayPage - 2;
			// endPage = ((page + 1) / maxDisplayPage + 1) * maxDisplayPage - 1;

			startPage = ((page + 1) / (maxDisplayPage / 2) - 1)
					* (maxDisplayPage / 2);
			endPage = startPage + maxDisplayPage - 1;

		} else {
			startPage = (page / maxDisplayPage) * maxDisplayPage;
			endPage = startPage + maxDisplayPage - 1;
		}

		if (endPage > totalPages - 1) {
			endPage = totalPages - 1;
		}
		if (endPage + 1 - startPage < 0) {
			startPage = endPage + 1;
		}
		pages = new Integer[endPage + 1 - startPage];
		for (int i = startPage; i <= endPage; i++) {
			pages[i - startPage] = i;
		}
	}
}

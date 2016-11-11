package com.lenicliu.blog.model;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by lenicliu on 11/11/16.
 */
public class Page<T> implements Iterable<T> {
    private int page = 1;
    private int rows = 5;
    private List<T> data;
    private int total;

    public Page(int page, int rows) {
        setPage(page);
        setRows(rows);
    }

    public Page(Page<T> page) {
        setPage(page.getPage());
        setRows(page.getRows());
    }

    public List<T> getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getOffset() {
        return (page - 1) * rows;
    }

    public int getRows() {
        return rows;
    }

    public int getNextPage() {
        return page < getTotalPage() ? page + 1 : page;
    }

    public int getPrePage() {
        return page > 1 ? page - 1 : 1;
    }

    private int getTotalPage() {
        return total / rows + (total % rows > 0 ? 1 : 0);
    }

    public void setPage(int page) {
        this.page = page < 1 ? 1 : page;
    }

    public void setRows(int rows) {
        this.rows = rows < 1 ? 5 : rows;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public Iterator<T> iterator() {
        return getData() == null ? null : getData().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        if (getData() != null) {
            getData().forEach(action);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return getData() == null ? null : getData().spliterator();
    }
}

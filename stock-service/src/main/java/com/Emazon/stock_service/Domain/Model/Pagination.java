package com.Emazon.stock_service.Domain.Model;

import org.springframework.data.domain.Sort;

public class Pagination {
    private int page;
    private int size;
    private String sort;
    private Sort.Direction direction;

    public Pagination() {
    }

    public Pagination(int page, int size, String sort, Sort.Direction direction) {
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }
}

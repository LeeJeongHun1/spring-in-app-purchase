package com.springinapppurchase.dto;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {

    private List<T> list;

    @Expose
    private long totalCount;

    @Expose
    private int pageSize;

    @Expose
    private int currentPage;

    @Expose
    private int totalPages;

    @Expose
    private boolean hasNext;

    @Expose
    private boolean hasPrevious;

    public PageDto(Page<T> page) {
        this.setList(page.getContent());
        this.setTotalCount(page.getTotalElements());
        this.setTotalPages(page.getTotalPages());
        this.setPageSize(page.getSize());
        this.setCurrentPage(page.getNumber());
        this.setHasNext(page.hasNext());
        this.setHasPrevious(page.hasPrevious());
    }

    public HttpHeaders getHeader() {
        String paginationString = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(this);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-pagination", paginationString);

        return headers;
    }
}

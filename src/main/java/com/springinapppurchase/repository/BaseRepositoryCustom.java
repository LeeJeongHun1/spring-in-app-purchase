package com.springinapppurchase.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public abstract class BaseRepositoryCustom {
    protected final JPAQueryFactory queryFactory;

    public BaseRepositoryCustom(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    protected void applyPage(JPAQuery<?> query, Pageable pageable) {
        query.offset(pageable.getOffset()).limit(pageable.getPageSize());
    }
}

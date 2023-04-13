package com.springinapppurchase.repository.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.springinapppurchase.entity.user.User;
import com.springinapppurchase.repository.BaseRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.springinapppurchase.entity.user.QUser.user;


@Repository
public class UserRepositoryCustomImpl extends BaseRepositoryCustom implements UserRepositoryCustom {

    public UserRepositoryCustomImpl(EntityManager entityManager)
    {
        super(entityManager);
    }

    @Override
    public Optional<User> findUserWithLoginInfoByUserName(String username) {
        JPAQuery<User> query = queryFactory
                .selectFrom(user)
                .where(user.userName.eq(username));

        return Optional.ofNullable(query.fetchOne());
    }

    @Override
    public Optional<User> findUserWithDevicesByUserName(String username) {
        JPAQuery<User> query = queryFactory
                .selectFrom(user)
                .where(user.userName.eq(username));

        return Optional.ofNullable(query.fetchOne());
    }

    private BooleanExpression eqUserName(String username) {
        return StringUtils.isBlank(username) ? null : user.userName.eq(username);
    }
}

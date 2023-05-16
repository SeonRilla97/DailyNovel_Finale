package com.dailynovel.dailynovelapi.repository;


import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.entity.QDiary;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class DiaryRepositoryCustomImpl implements DiaryRepositoryCustom {

    QDiary diary = QDiary.diary;

    @Autowired
    JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Diary> getListWithFiltering(String feeling, String weather, Date date, String order, String collection,
            String query) {

        


        OrderSpecifier<Date> orderSpecifier;
        if (order.equals("1")) {
        orderSpecifier = diary.date.desc();
        } else {
        orderSpecifier = diary.date.asc();
        }

        List<Diary> list = jpaQueryFactory
        .selectFrom(diary)
        .where()
        .orderBy(orderSpecifier)
        .fetch();
        return list;
    }


    private BooleanExpression feelingEq(String feeling) {
        if (feeling == null) {
            return null;
        }
        return diary.feeling.eq(feeling);
    }

    private BooleanExpression weatherEq(String weather) {
        if (weather == null) {
            return null;
        }
        return diary.weather.eq(weather);
    }

    private BooleanExpression dateEq(Date date) {
        if (date == null) {
            return null;
        }
        return diary.date.eq(date);
    }



    private BooleanExpression queryTitleEq(String query) {
        if (query == null) {
            return null;
        }
        return diary.title.eq(query);
    }


    private BooleanExpression queryContentEq(String query) {
        if (query == null) {
            return null;
        }
        return diary.content.eq(query);
    }


}

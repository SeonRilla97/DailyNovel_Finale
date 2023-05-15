package com.dailynovel.dailynovelapi.repository;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dailynovel.dailynovelapi.entity.Diary;
import com.dailynovel.dailynovelapi.entity.QDiary;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class DiaryRepositoryCustomImpl implements DiaryRepositoryCustom {

    QDiary diary = QDiary.diary;

    @Autowired
    JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Diary> getListWithFiltering(String feeling, String weather, Date date, String order, String collection,
            String query) {

        
        return jpaQueryFactory
		.selectFrom(diary)
		.where(
            // feelingEq(feeling),
            // weatherEq(weather),
            // dateEq(date),
            // queryContentEq(query),
            // queryTitleEq(query)
        )
		.fetch();
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

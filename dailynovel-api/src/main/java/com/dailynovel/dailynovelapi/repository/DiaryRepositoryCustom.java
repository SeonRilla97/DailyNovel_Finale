package com.dailynovel.dailynovelapi.repository;


import java.util.Date;
import java.util.List;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DiaryRepositoryCustom {
    List<Diary> getListWithFiltering(String feeling, String weather, Date date, String order, String collection,
    String query);
}

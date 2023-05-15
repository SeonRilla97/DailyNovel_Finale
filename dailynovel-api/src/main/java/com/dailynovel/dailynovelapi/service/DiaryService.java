package com.dailynovel.dailynovelapi.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dailynovel.dailynovelapi.entity.Diary;

public interface DiaryService {
    List<Object[]> getByFeeling();
    List<Object[]> getByTag(Integer memberId);
    List<Object[]>  getByHonesty(Integer memberId);
    Map<String, List<Diary>> getListGroupingMonthly(String feeling, String weather, Date date, String order,
            String collection, String query);
}
 
